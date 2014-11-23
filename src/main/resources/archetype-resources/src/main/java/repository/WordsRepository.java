#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository;

import ${package}.model.Word;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordsRepository extends GraphRepository<Word> {

    @Query("" +
        "MATCH path=((first:Word)-[:FOLLOWED_BY*1..]->(:Word))${symbol_escape}n" +
        "WHERE first.text =~ ${symbol_escape}"{0}.*${symbol_escape}"${symbol_escape}n" +
        "WITH path${symbol_escape}n" +
        "ORDER BY LENGTH(path) ASC ${symbol_escape}n" +
        "WITH LAST(COLLECT(path)) AS longestPath${symbol_escape}n" +
        "RETURN TRIM(REDUCE(sentence = ${symbol_escape}"${symbol_escape}", word IN nodes(longestPath)| sentence + ${symbol_escape}" ${symbol_escape}" + word.text)) AS sentence"
    )
    String findLongestSentenceStartingWith(String start);
}
