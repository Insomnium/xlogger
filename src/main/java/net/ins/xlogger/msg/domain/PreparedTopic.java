package net.ins.xlogger.msg.domain;

import java.util.Date;

/**
 * Created by ins on 3/28/15.
 */
public class PreparedTopic {
    private long id;
    private String subject;
    private String body;
    private String authorName;
    private Date postDate;
    private String url;

    public PreparedTopic() {
    }

    public PreparedTopic(long id, String subject, String body, String authorName, Date postDate, String url) {
        this.id = id;
        this.subject = subject;
        this.body = body;
        this.authorName = authorName;
        this.postDate = postDate;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

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

        PreparedTopic that = (PreparedTopic) o;

        if (id != that.id) return false;
        if (authorName != null ? !authorName.equals(that.authorName) : that.authorName != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (postDate != null ? !postDate.equals(that.postDate) : that.postDate != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PreparedTopic{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", authorName='" + authorName + '\'' +
                ", postDate=" + postDate +
                ", url='" + url + '\'' +
                '}';
    }
}
