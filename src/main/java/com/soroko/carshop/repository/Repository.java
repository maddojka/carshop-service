package com.soroko.carshop.repository;

import java.util.List;

/**
 * abstract class of repository layer
 * @author yuriy.soroko
 */
abstract public class Repository<T, ID> {

    abstract List<T> findAll() throws Exception;

    abstract ID save(T t) throws Exception;

    abstract void update(T t) throws Exception;

    abstract void deleteById(ID id) throws Exception;

    abstract T getById(ID id) throws Exception;
}