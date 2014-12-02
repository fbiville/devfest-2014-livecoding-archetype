#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository;

import ${package}.model.Company;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface CompaniesRepository extends GraphRepository<Company> {

    Company findByName(String name);
}
