package net.ins.xlogger.msg.entities;

import net.ins.xlogger.msg.MarkupType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ins on 3/6/15.
 */
@Entity
@Table(name = "MSG")
@NamedQueries({
        @NamedQuery(name = "message.getByTopicId", query = "select m from Message m where m.topicId = :topicId")
})
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = true, updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "body", insertable = true, updatable = false, nullable = true, unique = false)
    private String body;

    @Column(name = "subject", insertable = true, updatable = false, nullable = true, unique = false)
    private String subject;

    @Column(name = "markup", insertable = true, updatable = false, nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private MarkupType markupType;

    @Column(name = "post_date", insertable = true, updatable = false, nullable = false, unique = false)
    private Date postDate;

    @Column(name = "topicId", insertable = true, updatable = false, nullable = true, unique = false)
    private Long topicId;

    public Message() {

    }

    public Message(String subject, String body, MarkupType markupType, Date postDate) {
        this.subject = subject;
        this.body = body;
        this.markupType = markupType;
        this.postDate = postDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public MarkupType getMarkupType() {
        return markupType;
    }

    public void setMarkupType(MarkupType markupType) {
        this.markupType = markupType;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (body != null ? !body.equals(message.body) : message.body != null) return false;
        if (id != null ? !id.equals(message.id) : message.id != null) return false;
        if (markupType != message.markupType) return false;
        if (postDate != null ? !postDate.equals(message.postDate) : message.postDate != null) return false;
        if (subject != null ? !subject.equals(message.subject) : message.subject != null) return false;
        if (topicId != null ? !topicId.equals(message.topicId) : message.topicId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (markupType != null ? markupType.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (topicId != null ? topicId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", subject='" + subject + '\'' +
                ", markupType=" + markupType +
                ", postDate=" + postDate +
                ", topicId=" + topicId +
                '}';
    }
}
