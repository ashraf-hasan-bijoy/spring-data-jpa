package demo.spring.jpa.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author ashrafhasan
 * @since 10/10/16
 */
@Entity
@Table(name = "table_developer")
public class Developer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "developer_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "developer_generator", sequenceName = "developer_sequence")
    private long id;

    @NotNull(message = "Required")
    @Column(nullable = false)
    @Size(min = 1)
    private String name;

    @NotNull(message = "Required")
    @Column(nullable = false)
    @Size(min = 1)
    private String designation;

    @Transient
    private DeveloperActivity developerActivity;

    public Developer() {
    }

    public Developer(String name, String designation) {
        this.name = name;
        this.designation = designation;
    }

    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feature> featureList;

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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<Feature> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public DeveloperActivity getDeveloperActivity() {
        return developerActivity;
    }

    public void setDeveloperActivity(DeveloperActivity developerActivity) {
        this.developerActivity = developerActivity;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designation=" + designation +
                ", featureList=" + featureList +
                '}';
    }

    public boolean isNew() {
        return id == 0;
    }
}
