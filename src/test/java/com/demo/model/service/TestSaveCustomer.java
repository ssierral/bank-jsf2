package com.demo.model.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.demo.exceptions.NonFullAgeException;
import com.demo.model.dao.CustomerDAO;
import com.demo.model.dao.ICrudDAO;
import com.demo.model.service.CustomerServiceImpl;
import com.demo.model.service.ICustomerService;
import com.demo.pojo.Card;
import com.demo.pojo.Customer;
import com.demo.pojo.Gender;

public class TestSaveCustomer {
	
	private static CustomerServiceImpl service;
	private static ICrudDAO<Customer, Integer> dao;
        private static String successMessage = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test(expected = ConstraintViolationException.class)
    public void invalidLengthNameCustomer() {

		service = new CustomerServiceImpl();
		dao = new CustomerDAO();

        Customer customer1 = new Customer();
        
        Calendar calendar = new GregorianCalendar();
        calendar.set((int) Math.floor(Math.random() * 20 + 1950),
                (int) Math.floor(Math.random() * 12),
                (int) Math.floor(Math.random() * 29));

        customer1.setName("Sebastian sierra loaiza gonzalez buitrago");
        customer1.setBirthday(calendar.getTime());
        customer1.setGender(Gender.MALE);
        customer1.setAbout("Just an empty description");
        customer1.setCard(Card.MASTERCARD);
        customer1.setMail("ssierral@unal.edu.co");
        customer1.setMailingList(Boolean.TRUE);
        customer1.setNumberOfCards(5);
        customer1.setLicense(Boolean.TRUE);

    	service.setDao(dao);
        try {
			service.save(customer1);
		} catch (NonFullAgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        service.destroyEntityManager();
    }
    
	@Test
    public void creationCustomerValidName() {
		
    	service = new CustomerServiceImpl();
    	dao = new CustomerDAO();
    	Customer selectedCustomer = new Customer();
        
    	
    	Calendar calendar = new GregorianCalendar();
        calendar.set((int) Math.floor(Math.random() * 20 + 1950),
                (int) Math.floor(Math.random() * 12),
                (int) Math.floor(Math.random() * 29));


    	selectedCustomer.setName("Sebastian ");
    	selectedCustomer.setBirthday(calendar.getTime());
    	selectedCustomer.setGender(Gender.MALE);
    	selectedCustomer.setAbout("Just an empty description");
    	selectedCustomer.setCard(Card.MASTERCARD);
        selectedCustomer.setMail("ssierral@unal.edu.co");
    	selectedCustomer.setMailingList(Boolean.TRUE);
    	selectedCustomer.setNumberOfCards(5);
    	selectedCustomer.setLicense(Boolean.TRUE);
    	
        service.setDao(dao);
        try {
			service.save(selectedCustomer);
		} catch (NonFullAgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        service.destroyEntityManager();
    }
    
    @Test(expected = NonFullAgeException.class)
    public void invalidBirthDateCustomer() throws NonFullAgeException {

    	service = new CustomerServiceImpl();
    	dao = new CustomerDAO();

        Customer customer1 = new Customer();
        
        Calendar calendar = new GregorianCalendar();
        calendar.set(2003,
                (int) Math.floor(Math.random() * 12),
                (int) Math.floor(Math.random() * 29));

        customer1.setName("Sebastian");
        customer1.setBirthday(calendar.getTime());
        customer1.setGender(Gender.MALE);
        customer1.setAbout("Just an empty description");
        customer1.setCard(null);
        customer1.setMail("ssierral@unal.edu.co");
        customer1.setMailingList(Boolean.TRUE);
        customer1.setNumberOfCards(5);
        customer1.setLicense(Boolean.TRUE);

        service.setDao(dao);        
	service.save(customer1);
        service.destroyEntityManager();
    }

    @Test
    public void birthDateCustomer(){
        Customer customer2 = new Customer();
        Calendar calendar = new GregorianCalendar();
        calendar.set((int) Math.floor(Math.random() * 20 + 1950),
                (int) Math.floor(Math.random() * 12),
                (int) Math.floor(Math.random() * 29));
        customer2.setName("sierra");
        customer2.setBirthday(calendar.getTime());
        customer2.setGender(Gender.MALE);
        customer2.setAbout("Just an empty description");
        customer2.setCard(null);
        customer2.setMail("ssierral@unal.edu.co");
        customer2.setMailingList(Boolean.TRUE);
        customer2.setNumberOfCards(5);
        customer2.setLicense(Boolean.TRUE);

        service.setDao(dao);
        try {
			successMessage = service.save(customer2);
		} catch (NonFullAgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        service.destroyEntityManager();
        assertEquals("Customer created succesfully", successMessage);
        successMessage = null;
    }
    
    @Test
    public void genderNullCustome() {

    	service = new CustomerServiceImpl();
    	dao = new CustomerDAO();

        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();
        
        Calendar calendar = new GregorianCalendar();
        calendar.set((int) Math.floor(Math.random() * 20 + 1950),
                (int) Math.floor(Math.random() * 12),
                (int) Math.floor(Math.random() * 29));

        customer1.setName("Frida");
        customer1.setBirthday(calendar.getTime());
        customer1.setGender(Gender.FEMALE);
        customer1.setAbout("Just an empty description");
        customer1.setCard(null);
        customer1.setMail("fkahlo@unal.edu.co");
        customer1.setMailingList(Boolean.TRUE);
        customer1.setNumberOfCards(5);
        customer1.setLicense(Boolean.TRUE);

        customer2.setName("Sebastian");
        customer2.setBirthday(calendar.getTime());
        customer2.setGender(Gender.MALE);
        customer2.setAbout("Just an empty description");
        customer2.setCard(null);
        customer2.setMail("ssierral@unal.edu.co");
        customer2.setMailingList(Boolean.TRUE);
        customer2.setNumberOfCards(5);
        customer2.setLicense(Boolean.TRUE);

        customer3.setName("sierra");
        customer3.setBirthday(calendar.getTime());
        customer3.setGender(null);
        customer3.setAbout("Just an empty description");
        customer3.setCard(null);
        customer3.setMail("ssierral@unal.edu.co");
        customer3.setMailingList(Boolean.TRUE);
        customer3.setNumberOfCards(5);
        customer3.setLicense(Boolean.TRUE);

        service.setDao(dao);
        try {
			service.save(customer1);
			service.save(customer2);
	        service.save(customer3);
		} catch (NonFullAgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        service.destroyEntityManager();
    }

    @Test(expected = ConstraintViolationException.class)
    public void lengthDescriptionCustomer() {

    	service = new CustomerServiceImpl();
    	dao = new CustomerDAO();

        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        
        Calendar calendar = new GregorianCalendar();
        calendar.set((int) Math.floor(Math.random() * 20 + 1950),
                (int) Math.floor(Math.random() * 12),
                (int) Math.floor(Math.random() * 29));

        customer1.setName("Frida");
        customer1.setBirthday(calendar.getTime());
        customer1.setGender(Gender.FEMALE);
        customer1.setAbout("Lorem ipsum dolor sit amet, consectetur "
                + "adipiscing elit. Nam sem dui , sagittis a interdum vitae, "
                + "adipiscing in lacus. Etiam id lacus nibh, ut cursus dolor. "
                + "Mauris eu libero in nulla tristique pharetra.Ut blandit sem "
                + "vel sapien aliquet at rhoncus diam mattis. Morbi vitae massa "
                + "aliquet sem cras amet.");
        customer1.setCard(null);
        customer1.setMail("fkahlo@unal.edu.co");
        customer1.setMailingList(true);

        customer2.setName("Sebastian");
        customer2.setBirthday(calendar.getTime());
        customer2.setGender(Gender.MALE);
        customer2.setAbout("Just an empty description");
        customer2.setCard(null);
        customer2.setMail("ssierral@unal.edu.co");
        customer2.setMailingList(true);

        service.setDao(dao);
        try {
			service.save(customer1);
	        service.save(customer2);
		} catch (NonFullAgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        service.destroyEntityManager();

    }

    @Test
    public void invalidFormatEmail() {
        String s1, s2, s3, s4;
        s1 = s2 = s3 = s4 = null;
        service = new CustomerServiceImpl();
        dao = new CustomerDAO();

        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();
        Customer customer4 = new Customer();
        
        Calendar calendar = new GregorianCalendar();
        calendar.set((int) Math.floor(Math.random() * 20 + 1950),
                (int) Math.floor(Math.random() * 12),
                (int) Math.floor(Math.random() * 29));


        customer1.setName("Frida");
        customer1.setBirthday(calendar.getTime());
        customer1.setGender(Gender.FEMALE);
        customer1.setAbout("Just an empty description");
        customer1.setCard(null);
        customer1.setMail("fkahlounal.edu.co");
        customer1.setMailingList(Boolean.TRUE);
        customer1.setNumberOfCards(5);
        customer1.setLicense(Boolean.TRUE);

        customer2.setName("Sebastian");
        customer2.setBirthday(calendar.getTime());
        customer2.setGender(Gender.MALE);
        customer2.setAbout("Just an empty description");
        customer2.setCard(null);
        customer2.setMail("@unal.edu.co");
        customer2.setMailingList(Boolean.TRUE);
        customer2.setNumberOfCards(5);
        customer2.setLicense(Boolean.TRUE);

        customer3.setName("sierra");
        customer3.setBirthday(calendar.getTime());
        customer3.setGender(Gender.MALE);
        customer3.setAbout("Just an empty description");
        customer3.setCard(null);
        customer3.setMail("ssierral@");
        customer3.setMailingList(Boolean.TRUE);
        customer3.setNumberOfCards(5);
        customer3.setLicense(Boolean.TRUE);

        customer4.setName("loaiza");
        customer4.setBirthday(calendar.getTime());
        customer4.setGender(Gender.MALE);
        customer4.setAbout("Just an empty description");
        customer4.setCard(null);
        customer4.setMail("ssierral@unal");
        customer4.setMailingList(Boolean.TRUE);
        customer4.setNumberOfCards(5);
        customer4.setLicense(Boolean.TRUE);

        service.setDao(dao);
        try {
		s1 = service.save(customer1);
	        s2 = service.save(customer2);
	        s3 = service.save(customer3);
	        s4 = service.save(customer4);
		} catch (NonFullAgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        service.destroyEntityManager();
        assertEquals("Your EMail Address is invalid", s1);
        assertEquals("Your EMail Address is invalid", s2);
        assertEquals("Your EMail Address is invalid", s3);
        assertEquals("Your EMail Address is invalid", s4);
    }

    @Test
    public void validFormatEmail() {

        service = new CustomerServiceImpl();
        dao = new CustomerDAO();
        
        Customer customer1 = new Customer();
        
        Calendar calendar = new GregorianCalendar();
        calendar.set((int) Math.floor(Math.random() * 20 + 1950),
                (int) Math.floor(Math.random() * 12),
                (int) Math.floor(Math.random() * 29));

        customer1.setName("Frida");
        customer1.setBirthday(calendar.getTime());
        customer1.setGender(Gender.FEMALE);
        customer1.setAbout("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        customer1.setCard(null);
        customer1.setMail("fkahlo@unal.edu.co");
        customer1.setMailingList(Boolean.TRUE);
        customer1.setNumberOfCards(5);
        customer1.setLicense(Boolean.TRUE);
        
        service.setDao(dao);
        try {
			successMessage = service.save(customer1);
		} catch (NonFullAgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        service.destroyEntityManager();
        assertEquals("Customer created succesfully", successMessage);
        successMessage = null;
    }  
    
     
}
