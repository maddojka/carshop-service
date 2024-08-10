package com.soroko.carshop.repository;

/**
 * @author yuriy.soroko
 */
abstract public class Repository<T, ID> {

    abstract ID insert(T t) throws Exception;

    abstract void update(T t) throws Exception;

    abstract void deleteById(ID id) throws Exception;

    abstract T findById(ID id) throws Exception;
}
