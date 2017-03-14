package com.hjbello.test;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hjbello.dao.PersistenceManager;
import com.hjbello.dao.User;
import com.hjbello.dao.UserPK;



public class TestJpa {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void testingJpa() {

		// to insert data into the database:  
		User exampleUser = new User();
		UserPK pk = new UserPK();
		pk.setUsername("username3");
		pk.setPassword("pw3");

		exampleUser.setIdPk(pk);
		exampleUser.setName("Joe3");
		exampleUser.setEmail("email@email.com");
		exampleUser.setSurname("Palomardine");
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction()
		.begin();
		em.persist(exampleUser);
		em.getTransaction()
		.commit();

		UserPK pkToFind = new UserPK();
		pkToFind.setUsername("hjbello");
		pkToFind.setPassword("1234");   
		try {
			User foundUser = em.find(User.class, pkToFind);
			logger.info(foundUser.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}

		em.close();
		PersistenceManager.INSTANCE.close();
	}
}