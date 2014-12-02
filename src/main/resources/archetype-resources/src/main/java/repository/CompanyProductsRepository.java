#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository;

import ${package}.model.CompanyProduct;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.Collection;

public interface CompanyProductsRepository extends GraphRepository<CompanyProduct> {

    public Collection<CompanyProduct> findByCompanyName(String name, Sort sort);
}
