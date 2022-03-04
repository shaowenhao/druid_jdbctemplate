package jdbctemplate;

/**
 * <p>
 *     Create Time: 2022年02月19日 08:21
 * </p>
 **/
public class Subscription {
    private String id;
    private String subsentence;
    private String replyto;
    private String entities;
    private Boolean active;
    private String clients;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubsentence() {
        return subsentence;
    }

    public void setSubsentence(String subsentence) {
        this.subsentence = subsentence;
    }

    public String getReplyto() {
        return replyto;
    }

    public void setReplyto(String replyto) {
        this.replyto = replyto;
    }

    public String getEntities() {
        return entities;
    }

    public void setEntities(String entities) {
        this.entities = entities;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getClients() {
        return clients;
    }

    public void setClients(String clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id='" + id + '\'' +
                ", subsentence='" + subsentence + '\'' +
                ", replyto='" + replyto + '\'' +
                ", entities='" + entities + '\'' +
                ", active=" + active +
                ", clients='" + clients + '\'' +
                '}';
    }
}
