package net.ins.xlogger.user;

/**
 * Created by ins on 3/5/15.
 */
public enum Role {
    ADMIN(0L),
    GUEST(1L),
    USER(2L);

    private long roleId;

    private Role(long roleId) {
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }
}
