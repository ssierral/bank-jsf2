/**
 * @author <a href=mailto:volkodavav@gmail.com>volkodavav</a>
 */
package com.demo.model.service;

import java.util.List;

import com.demo.exceptions.NonFullAgeException;
import com.demo.pojo.Customer;

public abstract interface ICustomerService
{
    public abstract long count();

    public abstract Customer create();

    public abstract void delete(Customer entity);

    public abstract int deleteAll();

    public abstract List<Customer> findAll();

    public abstract Customer findById(Integer id);

    public abstract String save(Customer entity) throws NonFullAgeException;

    public abstract Customer update(Customer entity);
}