package net.ins.xlogger.msg;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by ins on 3/7/15.
 */
public class MessageRequest implements Serializable {
    private String subject;
    @NotEmpty(message = "{message,validation.body.empty}")
    private String body;
    private MarkupType markupType;
    private Long commentId;

    public MessageRequest() {

    }

    public MessageRequest(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public MessageRequest(String subject, String body, MarkupType markupType) {
        this(subject, body);
        this.markupType = markupType;
    }

    public MessageRequest(String subject, String body, MarkupType markupType, Long commentId) {
        this(subject, body, markupType);
        this.commentId = commentId;
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

    public MarkupType getMarkupType() {
        return markupType;
    }

    public void setMarkupType(MarkupType markupType) {
        this.markupType = markupType;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}
