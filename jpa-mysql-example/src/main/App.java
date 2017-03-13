package main;

import javax.persistence.EntityManager;

import model.User;
import model.UserPK;
import model.PersistenceManager;

public class App {
  public static void main(String[] args) {
    
	// to insert data into the database:  
	User exampleUser = new User();
	UserPK pk = new UserPK();
	pk.setUsername("username");
	pk.setPassword("pw");
	
	exampleUser.setIdPk(pk);
	exampleUser.setName("Joe");
    exampleUser.setEmail("email@email.com");
    exampleUser.setSurname("Palomardine");
    EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
    em.getTransaction()
        .begin();
    em.persist(exampleUser);
    em.getTransaction()
        .commit();
    em.close();
    PersistenceManager.INSTANCE.close();
  }
}