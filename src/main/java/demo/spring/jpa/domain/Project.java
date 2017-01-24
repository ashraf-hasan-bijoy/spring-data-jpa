package demo.spring.jpa.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ashrafhasan
 * @since 1/23/17
 */
@Entity
@Table(name = "table_project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "project_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "project_generator", sequenceName = "project_sequence")
    private long id;

    @NotNull(message = "Required")
    @Column(nullable = false)
    private String name;

    @Column(length = 1024)
    private String description;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    private Developer developer;

    public Project() {
    }

    public Project(Developer developer) {
        this.developer = developer;
    }

    public Project(String name, Developer developer) {
        this.name = name;
        this.developer = developer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNew() {
        return id == 0;
    }
}
