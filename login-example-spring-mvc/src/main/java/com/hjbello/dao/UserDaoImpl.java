package com.hjbello.dao;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component(value="userDaoImpl")
public class UserDaoImpl implements UserDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String error="";

	public UserDaoImpl (){

	}
	@Override
	public boolean isValidUser(String username, String password) {
		boolean isValid = false;
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		UserPK pkToFind = new UserPK();
		pkToFind.setUsername(username);
		pkToFind.setPassword(password);
		try {
			User foundUser = em.find(User.class, pkToFind);

			isValid=!(foundUser==null);

		} catch (Exception e) {
			e.printStackTrace();

		}
		if (isValid){
			logger.info("User "+ username + " // " + password + " found");
		} else {
			logger.info("User "+ username + " // " + password + " NOT found");
		}

		return isValid;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
