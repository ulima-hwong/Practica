package com.hwongu.service;

import java.util.List;

public interface ICRUDService<T, ID>{

    T save(T entity) throws Exception;

    T update(T entity, ID id) throws Exception;

    T readById(ID id) throws Exception;

    List<T> readAll() throws Exception;

    void delete(ID id) throws Exception;
}

