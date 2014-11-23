#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.configuration;

import ${package}.repository.WordsRepository;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Configuration
@EnableNeo4jRepositories(basePackageClasses = WordsRepository.class)
public class Application extends Neo4jConfiguration {

    @Bean
    public GraphDatabaseService graphDatabaseService() throws IOException {
        File directory = Files.createTempDirectory("neo").toFile();
        return new GraphDatabaseFactory().newEmbeddedDatabase(directory.getPath());
    }
}
