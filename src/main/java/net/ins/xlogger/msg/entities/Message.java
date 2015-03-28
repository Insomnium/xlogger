package net.ins.xlogger.msg.entities;

import net.ins.xlogger.msg.MarkupType;
import net.ins.xlogger.user.entities.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ins on 3/6/15.
 */
@Entity
@Table(name = "MSG")
@NamedQueries({
        @NamedQuery(name = "message.getByTopicId", query = "select m from Message m where m.topic.id = :topicId")
})
@Access(AccessType.PROPERTY)
public class Message implements Serializable {

    private Long id;

    private String body;

    private String subject;


    private MarkupType markupType;

    private Date postDate;

//    @Column(name = "topic_id", insertable = true, updatable = false, nullable = true, unique = false)
//    private Long topicId;
    private Topic topic;

    private Message replyTo;

    private List<Message> replies;

    private User author;

    public Message() {

    }

    public Message(String subject, String body, MarkupType markupType, Date postDate) {
        this.subject = subject;
        this.body = body;
        this.markupType = markupType;
        this.postDate = postDate;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = true, updatable = false, nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "body", insertable = true, updatable = false, nullable = true, unique = false)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Column(name = "markup", insertable = true, updatable = false, nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    public MarkupType getMarkupType() {
        return markupType;
    }

    public void setMarkupType(MarkupType markupType) {
        this.markupType = markupType;
    }

    @Column(name = "post_date", insertable = true, updatable = false, nullable = false, unique = false)
    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    @Column(name = "subject", insertable = true, updatable = false, nullable = true, unique = false)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_to")
    public Message getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Message replyTo) {
        this.replyTo = replyTo;
    }

    @OneToMany(mappedBy = "replyTo")
    public List<Message> getReplies() {
        return replies;
    }

    public void setReplies(List<Message> replies) {
        this.replies = replies;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
        if (replies != null ? !replies.equals(message.replies) : message.replies != null) return false;
        if (replyTo != null ? !replyTo.equals(message.replyTo) : message.replyTo != null) return false;
        if (subject != null ? !subject.equals(message.subject) : message.subject != null) return false;
        if (topic != null ? !topic.equals(message.topic) : message.topic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (markupType != null ? markupType.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (replyTo != null ? replyTo.hashCode() : 0);
        result = 31 * result + (replies != null ? replies.hashCode() : 0);
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
                '}';
    }
}
