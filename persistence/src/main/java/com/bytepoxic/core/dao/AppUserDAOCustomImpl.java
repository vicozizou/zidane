package com.bytepoxic.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bytepoxic.core.model.AppUser;

@Repository
public class AppUserDAOCustomImpl implements AppUserDAOCustom {
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public AppUser findAppUserByUsername(String username) {
		Query q = entityManager.createQuery("SELECT au FROM AppUser au WHERE au.username = :username", AppUser.class);
        q.setParameter("username", username);
        return (AppUser) q.getSingleResult();
	}
}
