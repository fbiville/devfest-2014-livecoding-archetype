#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = "FOLLOWED_BY")
public class FollowedBy {

    private Word first;
    private Word second;

    @StartNode
    public Word getFirst() {
        return first;
    }

    public void setFirst(Word first) {
        this.first = first;
    }

    @EndNode
    public Word getSecond() {
        return second;
    }

    public void setSecond(Word second) {
        this.second = second;
    }
}
