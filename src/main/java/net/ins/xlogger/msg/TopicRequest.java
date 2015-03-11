package net.ins.xlogger.msg;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by ins on 3/7/15.
 */
public class TopicRequest implements Serializable {
    @NotEmpty(message = "{topic.validation.subject.empty}")
    private String subject;
    @NotEmpty(message = "{topic.validation.body.empty}")
    private String body;
    private MarkupType markupType;
    private boolean draft;
    private boolean shout;

    public TopicRequest() {

    }

    public TopicRequest(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public TopicRequest(String subject, String body, MarkupType markupType) {
        this(subject, body);
        this.markupType = markupType;
    }

    public TopicRequest(String subject, String body, MarkupType markupType, boolean draft, boolean shout) {
        this(subject, body, markupType);
        this.draft = draft;
        this.shout = shout;
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

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public boolean isShout() {
        return shout;
    }

    public void setShout(boolean shout) {
        this.shout = shout;
    }
}
