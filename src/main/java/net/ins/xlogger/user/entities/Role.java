package net.ins.xlogger.user.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ins on 3/5/15.
 */
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, updatable = false, insertable = true, unique = true)
    private long id;

    @Column(name = "role_name", nullable = true, updatable = false, insertable = true, unique = true)
    private String roleName;

    @Column(name = "role_sys_name", nullable = false, updatable = false, insertable = true, unique = true)
    private String roleSysName;

    @ManyToMany(mappedBy = "roles", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    private List<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleSysName() {
        return roleSysName;
    }

    public void setRoleSysName(String roleSysName) {
        this.roleSysName = roleSysName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != role.id) return false;
        if (roleName != null ? !roleName.equals(role.roleName) : role.roleName != null) return false;
        if (roleSysName != null ? !roleSysName.equals(role.roleSysName) : role.roleSysName != null) return false;
        if (users != null ? !users.equals(role.users) : role.users != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (roleSysName != null ? roleSysName.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleSysName='" + roleSysName + '\'' +
                ", users=" + users +
                '}';
    }
}
