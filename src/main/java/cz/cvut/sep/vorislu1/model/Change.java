package cz.cvut.sep.vorislu1.model;

import javax.persistence.*;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
@Entity
@Table(name = "change_request_change")
public class Change {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Class type;
    @Column(name = "from_value")
    private String from;
    @Column(name = "to_value")
    private String to;
    @ManyToOne(targetEntity = ChangeRequest.class)
    @JoinColumn(name = "request_id", nullable = false)
    private ChangeRequest request;

    public Change(){}

    public Change(Class type, String name, Object from, Object to) {
        this.type = type;
        this.from = from == null ? null : from.toString();
        this.to = to == null ? null : to.toString();
        this.name = name;
    }

    public Change(Class type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Change setFrom(Object from) {
        this.from = from == null ? null : from.toString();
        return this;
    }

    public Change setTo(Object to) {
        this.to = to == null ? null : to.toString();
        return this;
    }

    public Long getId() {
        return id;
    }

    public Class getType() {
        return type;
    }

    public Object getFrom() {
        return from;
    }

    public Object getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Change{" +
                name +
                " for " +
                from +
                " => " +
                to + '}';
    }

    public ChangeRequest getRequest() {
        return request;
    }

    public void setRequest(ChangeRequest request) {
        this.request = request;
    }
}
