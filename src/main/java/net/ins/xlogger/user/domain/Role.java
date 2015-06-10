package net.ins.xlogger.user.domain;

/**
 * Created by ins on 3/5/15.
 */
public enum Role {
    ADMIN(0L, "ROLE_ADMIN"),
    GUEST(1L, "ROLE_GUEST"),
    USER(2L, "ROLE_USER");

    private long roleId;
    private String roleSysName;

    private Role(long roleId, String roleSysName) {
        this.roleId = roleId;
        this.roleSysName = roleSysName;
    }

    public String getRoleSysName() {
        return roleSysName;
    }

    public long getRoleId() {
        return roleId;
    }

    public static Role getRoleBySysName(String roleSysName) {
        for (Role role : values()) {
            if (role.getRoleSysName().equals(roleSysName)) {
                return role;
            }
        }

        return null;
    }
}
