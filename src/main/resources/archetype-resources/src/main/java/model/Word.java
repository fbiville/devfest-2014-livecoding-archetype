#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.Labels;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
@Labels(defaultValue = "word")
public class Word {

    private Long id;
    private String text;

    @GraphId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "wordContents")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
