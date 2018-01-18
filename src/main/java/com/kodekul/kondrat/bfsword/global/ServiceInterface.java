package com.kodekul.kondrat.bfsword.global;

import com.kodekul.kondrat.bfsword.global.exception.IdNotExistsException;
import com.kodekul.kondrat.bfsword.global.exception.IncompleteDataException;

import java.util.NoSuchElementException;

public interface ServiceInterface <T> {

    Iterable showAll();

    Iterable<T> showAvailable();

    T showOne(Integer id);

    T showOneAvailable(Integer id) throws NoSuchElementException;

    T create(T obj) throws IncompleteDataException;

    void delete(Integer id);

    void archive(Integer id) throws IdNotExistsException;

    T update(T obj) throws IdNotExistsException, IncompleteDataException;
}
