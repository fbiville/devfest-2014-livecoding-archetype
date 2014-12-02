#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository;

import ${package}.configuration.TestApplication;
import ${package}.model.CompanyProduct;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Sort.Direction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@TransactionConfiguration
@Transactional
public class CompanyProductsRepositoryTest {

    @Autowired
    private GraphDatabaseService graphDb;

    @Autowired
    private CompanyProductsRepository companyProductsRepository;

    @Before
    public void inserts_data() {
        new ExecutionEngine(graphDb).execute("CREATE " +
                "(neoTech:Company:_Company {name:${symbol_escape}"Neo Technology${symbol_escape}"})," +
                "(neoComm:CompanyProduct:_CompanyProduct  {name:${symbol_escape}"Neo4j Community Edition${symbol_escape}"})," +
                "(neoPro:CompanyProduct:_CompanyProduct  {name:${symbol_escape}"Neo4j Enterprise Edition${symbol_escape}"})," +
                "(neoComm)-[:IS_OWNED_BY]->(neoTech)<-[:IS_OWNED_BY]-(neoPro)"
        );
    }

    @Test
    public void finds_products_by_companies() {
        Collection<CompanyProduct> products = companyProductsRepository.findByCompanyName(
            "Neo Technology",
            new Sort(Direction.ASC, "name")
        );
        assertThat(products).extracting(CompanyProduct::getName).containsExactly(
            "Neo4j Community Edition",
            "Neo4j Enterprise Edition"
        );
    }
}
