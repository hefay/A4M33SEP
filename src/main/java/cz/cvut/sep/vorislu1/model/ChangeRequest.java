package cz.cvut.sep.vorislu1.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
@Entity
public class ChangeRequest {
    @Transient
    public static final String EDIT = "EDIT";
    @Transient
    public static final String NEW = "NEW";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long clientId;
    private Long userId;
    private Date changeDate;
    private boolean sync;
    @OneToMany(targetEntity = Change.class, mappedBy = "request", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Change> changes;
    private String type;

    public ChangeRequest() {

    }

    public ChangeRequest(Long clientId, Long userId) {
        this.clientId = clientId;
        this.userId = userId;
        this.changeDate = new Date();
        this.changes = new ArrayList<>();
    }

    public void setSync() {
        sync = true;
    }

    public void clearSync() {
        sync = false;
    }

    public ChangeRequest addChange(Change ch) {
        ch.setRequest(this);
        changes.add(ch);
        return this;
    }

    public ChangeRequest removeChange(Change ch) {
        ch.setRequest(null);
        changes.remove(ch);
        return this;
    }

    public Long getId() {
        return id;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public List<Change> getChanges() {
        return changes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getClientId() {
        return clientId;
    }

    public boolean isSync() {
        return sync;
    }
}
