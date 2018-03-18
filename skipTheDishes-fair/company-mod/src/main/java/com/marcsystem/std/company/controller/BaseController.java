package com.marcsystem.std.company.controller;

import com.marcsystem.std.company.model.AuditableEntity;
import com.marcsystem.std.company.model.IEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import static java.util.Objects.isNull;

@RequestMapping("/v1")
public class BaseController<E extends IEntity, ID extends Serializable, R extends JpaRepository<E, ID>> {

    @Autowired
    private R repository;

    public R getRepository() {
        return repository;
    }


    public E save(E entity) {
        if (isNull(entity.getId()) && entity instanceof AuditableEntity) {
            if (isNull(((AuditableEntity) entity).getCreateAt()))
                ((AuditableEntity) entity).setCreateAt(Instant.now());
            else
                ((AuditableEntity) entity).setUpdateAt(Instant.now());
        }
        return getRepository().save(entity);
    }

    public List<E> getAll() {
        return getRepository().findAll();
    }

    public E getOne(ID id) {
        return getRepository().getOne(id);
    }

    public void delete(ID id) {
        getRepository().delete(id);
    }

}
