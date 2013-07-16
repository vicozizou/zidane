package com.bytepoxic.core.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bytepoxic.core.model.Location;

@Repository
public class LocationDAOCustomImpl implements LocationDAOCustom {
	@PersistenceContext
    private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Location> findLocationsByParent(Location parent) {
		Query q = entityManager.createQuery("SELECT l FROM Location l WHERE l.parent = :parent", Location.class);
        q.setParameter("parent", parent);
        List<Location> resultList = (List<Location>) q.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            return resultList;
        }
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Location> findMainLocations() {
		Query q = entityManager.createQuery("SELECT l FROM Location l WHERE l.appCurrent = true", Location.class);
        List<Location> resultList = (List<Location>) q.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            return resultList;
        }
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Location> findLocationsByName(String name) {
		Query q = entityManager.createQuery("SELECT l FROM Location l WHERE l.name = :name", Location.class);
        q.setParameter("name", name);
        List<Location> resultList = (List<Location>) q.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            return resultList;
        }
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Location> findLocationsByCode(String code) {
		Query q = entityManager.createQuery("SELECT l FROM Location l WHERE l.code = :code", Location.class);
        q.setParameter("code", code);
        List<Location> resultList = (List<Location>) q.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            return resultList;
        }
		return null;
	}
}
