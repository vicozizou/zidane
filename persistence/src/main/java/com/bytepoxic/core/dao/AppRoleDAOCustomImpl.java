package com.bytepoxic.core.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bytepoxic.core.model.AppRole;
import com.bytepoxic.core.model.Location;

@Repository
public class AppRoleDAOCustomImpl implements AppRoleDAOCustom {
	@PersistenceContext
    private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<AppRole> findAppRolesByName(String name) {
		Query q = entityManager.createQuery("SELECT ar FROM AppRole ar WHERE ar.name = :name", Location.class);
        q.setParameter("name", name);
        List<AppRole> resultList = (List<AppRole>) q.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            return resultList;
        }
		return null;
	}

	@Override
	public AppRole findAppRoleByName(String name) {
		Query q = entityManager.createQuery("SELECT ar FROM AppRole ar WHERE ar.name = :name", AppRole.class);
        q.setParameter("name", name);
        return (AppRole) q.getSingleResult();        
	}
}
