package com.hibernate.app.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin_users")
public class AdminUser extends User {

    private String role;
    private String permissions;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
