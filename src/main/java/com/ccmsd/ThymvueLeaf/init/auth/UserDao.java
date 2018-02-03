package com.ccmsd.ThymvueLeaf.init.auth;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	public int getUserDetails(String username) {
		Query query =  entityManager.createQuery("select id from User where username= :username" );
		query.setParameter("username", username);
		System.out.println();
		return query.getResultList().size()>0?1:0;
	}
	public void createUserDetails(User user) {
		 entityManager.persist(user);
	}

}