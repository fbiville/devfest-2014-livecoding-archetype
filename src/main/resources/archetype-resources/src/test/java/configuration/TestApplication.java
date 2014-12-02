#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.configuration;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.io.IOException;

@Import(Application.class)
public class TestApplication {

    @Bean
    public GraphDatabaseService graphDatabaseService() throws IOException {
        return new TestGraphDatabaseFactory().newImpermanentDatabase();
    }
}