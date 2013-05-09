/**
 * @author <a href=mailto:volkodavav@gmail.com>volkodavav</a>
 */
package com.demo.model.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.demo.exceptions.NonFullAgeException;
import com.demo.model.dao.CustomerDAO;
import com.demo.model.dao.ICrudDAO;
import com.demo.pojo.Customer;

@ManagedBean(name = "customerServiceImpl")
@ApplicationScoped
public class CustomerServiceImpl implements ICustomerService
{
    @ManagedProperty("#{customerDAO}")
    private ICrudDAO<Customer, Integer> dao;

    public ICrudDAO<Customer, Integer> getDao() {
		return dao;
	}

	@Override
    public long count()
    {
        return this.dao.count();
    }

    @Override
    public Customer create()
    {
        return this.dao.create();
    }

    @Override
    public void delete(Customer entity)
    {
        this.dao.delete(entity);
    }

    @Override
    public int deleteAll()
    {
        return this.dao.deleteAll();
    }

    @Override
    public List<Customer> findAll()
    {
        return new ArrayList<Customer>(this.dao.findAll());
    }

    @Override
    public Customer findById(Integer id)
    {
        return this.dao.findById(id);
    }

    @PostConstruct
    public void postConstruct()
    {
        if (this.dao == null)
        {
            this.dao = new CustomerDAO();
            System.err.println("*** WARNING *** Dao not injected.");
        }
    }

    @PreDestroy
    public void preDestroy()
    {
        this.dao = null;
    }

    @Override
    public String save(Customer entity) throws NonFullAgeException
    {
    	Calendar now = Calendar.getInstance();
    	Calendar birthday = Calendar.getInstance();
    	birthday.setTime(entity.getBirthday());
    	if (now.after(entity.getBirthday())){
    		throw new NonFullAgeException("Can be born in the future");
    	}
    	if ((now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR)) < 18){
    		throw new NonFullAgeException("Must be Full Age to register");
    	}
        if(!isValidEmailAddress(entity.getMail())){
            return "Your EMail Address is invalid";
        }
        this.dao.save(entity);
        return "Customer created succesfully";
    }

    public void setDao(ICrudDAO<Customer, Integer> dao)
    {
        this.dao = dao;
        dao.setEntityManager();
    }
    
    public void destroyEntityManager(){
    	dao.destroyEntityManager();
    }

    @Override
    public Customer update(Customer entity)
    {
        return this.dao.update(entity);
    }
    
public boolean isValidEmailAddress(String email) {
       java.util.regex.Pattern p = java.util.regex.Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
       java.util.regex.Matcher m = p.matcher(email);
       boolean matchFound = m.matches();
       return matchFound;
}
}