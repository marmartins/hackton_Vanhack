package com.marcsystem.std.company.model;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
public abstract class AuditableEntity<T extends Serializable> implements IEntity<T>{

    private String loggedUser;

    private boolean enabled;

    private Instant createAt;

    private Instant updateAt;

    public String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuditableEntity)) return false;

        AuditableEntity obj = (AuditableEntity) o;

        return getId().equals(obj.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
