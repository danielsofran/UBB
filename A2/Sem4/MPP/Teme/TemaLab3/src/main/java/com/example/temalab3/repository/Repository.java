package com.example.temalab3.repository;

import com.example.temalab3.domain.HasID;

import java.util.Collection;

public interface Repository <ID, E extends HasID<ID>>{
    int size();
    void save(E entity);
    void delete(ID id);
    void update(ID id, E entity);
    E findOne(ID id);
    Collection<E> findAll();
}
