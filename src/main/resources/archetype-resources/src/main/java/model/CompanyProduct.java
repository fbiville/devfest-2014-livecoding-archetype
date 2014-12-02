#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class CompanyProduct {

    private Long id;
    private Company company;
    private String name;

    @GraphId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @RelatedTo(direction = Direction.OUTGOING, type = "IS_OWNED_BY")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Indexed
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
