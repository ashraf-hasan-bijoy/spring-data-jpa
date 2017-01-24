package demo.spring.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ashrafhasan
 * @since 1/25/17
 */
@Entity
@Table(name = "table_developer_activity")
public class DeveloperActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "developer_activity_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "developer_activity_generator", sequenceName = "developer_activity_sequence")
    private long id;

    @ElementCollection
    @CollectionTable(name = "table_activity_message", joinColumns = @JoinColumn(name = "devel_activity_id"))
    @OrderColumn(name = "idx")
    private List<String> activityMessages;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "developer_id")
    private Developer developer;

    public DeveloperActivity() {
        activityMessages = new ArrayList<>();
    }

    public DeveloperActivity(Developer developer) {
        this.developer = developer;
    }

    public DeveloperActivity(List<String> activityMessages, Developer developer) {
        this.activityMessages = activityMessages;
        this.developer = developer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getActivityMessages() {
        return activityMessages;
    }

    public void setActivityMessages(List<String> activityMessages) {
        this.activityMessages = activityMessages;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
}
