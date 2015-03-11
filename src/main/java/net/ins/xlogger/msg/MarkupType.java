package net.ins.xlogger.msg;

/**
 * Created by ins on 3/6/15.
 */
public enum MarkupType {
    PLAIN(1),
    HTML(2),
    BB_CODE(3),
    MARKDOWN(4);

    private int id;

    private MarkupType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
