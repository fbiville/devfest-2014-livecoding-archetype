#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository;

import ${package}.configuration.TestApplication;
import ${package}.model.Company;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static ${package}.model.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@TransactionConfiguration
@Transactional
public class CompaniesRepositoryTest {

    @Autowired
    private GraphDatabaseService graphDb;

    @Autowired
    private CompaniesRepository companiesRepository;


    @Before
    public void inserts_data() {
        new ExecutionEngine(graphDb).execute(
            "CREATE (:Company:_Company  {name:${symbol_escape}"Neo Technology${symbol_escape}"})"
        );
    }

    @Test
    public void find_companies_by_name() {
        Company company = companiesRepository.findByName("Neo Technology");

        assertThat(company).hasName("Neo Technology");
    }
}