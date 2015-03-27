package net.ins.xlogger.msg.entities;

import net.ins.xlogger.msg.MarkupType;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ins on 3/6/15.
 */
@Entity
@Table(name = "TOPIC")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "Topics")
@Access(AccessType.PROPERTY)
public class Topic implements Serializable {

    private Long id;

    private String subject;

    private String body;

    private MarkupType markupType;

    private Date postDate;

    private Date lastModificationDate;

    private boolean deleted;

    private boolean resolved;

    private boolean shout;

    private boolean draft;

    private String url;

    public Topic() {

    }

    public Topic(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public Topic(String subject, String body, MarkupType markupType, boolean shout, boolean draft) {
        this(subject, body);
        this.markupType = markupType;
        this.shout = shout;
        this.draft = draft;
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

    @Column(name = "subject", insertable = true, updatable = false, nullable = false, unique = false)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "body", insertable = true, updatable = false, nullable = false, unique = false)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Column(name = "markup", insertable = true, updatable = true, nullable = false, unique = false)
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

    @Column(name = "last_mod_date", insertable = true, updatable = true, nullable = true, unique = false)
    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    @Column(name = "deleted", insertable = true, updatable = true, nullable = false, unique = false)
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Column(name = "resolved", insertable = true, updatable = true, nullable = false, unique = false)
    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    @Column(name = "shout", insertable = true, updatable = true, nullable = false, unique = false)
    public boolean isShout() {
        return shout;
    }

    public void setShout(boolean shout) {
        this.shout = shout;
    }

    @Column(name = "is_draft", insertable = true, updatable = true, nullable = false, unique = false)
    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    @Transient
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic = (Topic) o;

        if (deleted != topic.deleted) return false;
        if (draft != topic.draft) return false;
        if (resolved != topic.resolved) return false;
        if (shout != topic.shout) return false;
        if (body != null ? !body.equals(topic.body) : topic.body != null) return false;
        if (id != null ? !id.equals(topic.id) : topic.id != null) return false;
        if (lastModificationDate != null ? !lastModificationDate.equals(topic.lastModificationDate) : topic.lastModificationDate != null)
            return false;
        if (markupType != topic.markupType) return false;
        if (postDate != null ? !postDate.equals(topic.postDate) : topic.postDate != null) return false;
        if (subject != null ? !subject.equals(topic.subject) : topic.subject != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (markupType != null ? markupType.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (lastModificationDate != null ? lastModificationDate.hashCode() : 0);
        result = 31 * result + (deleted ? 1 : 0);
        result = 31 * result + (resolved ? 1 : 0);
        result = 31 * result + (shout ? 1 : 0);
        result = 31 * result + (draft ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", markupType=" + markupType +
                ", postDate=" + postDate +
                ", lastModificationDate=" + lastModificationDate +
                ", deleted=" + deleted +
                ", resolved=" + resolved +
                ", shout=" + shout +
                ", draft=" + draft +
                '}';
    }
}
