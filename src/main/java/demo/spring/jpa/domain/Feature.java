package demo.spring.jpa.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ashrafhasan
 * @since 10/10/16
 */
@Entity
@Table(name = "table_feature")
public class Feature implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "feature_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "feature_generator", sequenceName = "feature_sequence")
    private long id;

    @NotNull(message = "Required")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Required")
    @Column(nullable = false)
    private String releaseVersion;

    public Feature() {
    }

    public Feature(Developer developer) {
        this.developer = developer;
    }

    public Feature(String name, String releaseVersion, Developer developer) {
        this.name = name;
        this.releaseVersion = releaseVersion;
        this.developer = developer;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "devel_id", nullable = false)
    private Developer developer;

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

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public boolean isNew() {
        return this.id == 0;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseVersion=" + releaseVersion +
                '}';
    }
}
