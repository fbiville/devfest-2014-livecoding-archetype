#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository;

import ${package}.configuration.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class WordsRepositoryTest {

    @Autowired
    private GraphDatabaseService graphDb;

    @Autowired
    private WordsRepository repository;


    @Before
    public void inserts_data() {
        try (Transaction transaction = graphDb.beginTx()) {
            new ExecutionEngine(graphDb).execute(
                "CREATE ${symbol_escape}n" +
                    "(_6:Word  {text:${symbol_escape}"Hello${symbol_escape}"}),${symbol_escape}n" +
                    "(_7:Word  {text:${symbol_escape}"World${symbol_escape}"}),${symbol_escape}n" +
                    "(_8:Word  {text:${symbol_escape}"!${symbol_escape}"}),${symbol_escape}n" +
                    "(_9:Word  {text:${symbol_escape}"Kitty${symbol_escape}"}),${symbol_escape}n" +
                    "_6-[:FOLLOWED_BY]->_7,${symbol_escape}n" +
                    "_7-[:FOLLOWED_BY]->_8,${symbol_escape}n" +
                    "_6-[:FOLLOWED_BY]->_9"
            );
            transaction.success();
        }
    }

    @Test
    public void retrieves_longest_sentence() {
        String longestSentence = repository.findLongestSentenceStartingWith("Hell");

        assertThat(longestSentence).isEqualTo("Hello World !");
    }
}