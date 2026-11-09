package com.hwongu.service.impl;

import com.hwongu.exception.ModelNotFoundException;
import com.hwongu.repo.IPostgreRepo;
import com.hwongu.service.ICRUDService;

import java.util.List;

public abstract class CRUDImpl<T, ID> implements ICRUDService<T, ID> {

    protected abstract IPostgreRepo<T, ID> getPostgreRepo();

    @Override
    public T save(T entity) throws Exception {
        return getPostgreRepo().save(entity);
    }

    @Override
    public T update(T entity, ID id) throws Exception {
        getPostgreRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("No se encontro el ID: " + id));
        return getPostgreRepo().save(entity);
    }

    @Override
    public T readById(ID id) throws Exception {
        return getPostgreRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("No se encontro el ID: " + id));
    }

    @Override
    public List<T> readAll() throws Exception {
        return getPostgreRepo().findAll();
    }

    @Override
    public void delete(ID id) throws Exception {
        getPostgreRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("No se encontro el ID: " + id));
        getPostgreRepo().deleteById(id);
    }
}

