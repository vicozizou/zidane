package com.bytepoxic.core.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.UserTrack;

@Repository
public class UserTrackDAOCustomImpl implements UserTrackDAOCustom {
	@PersistenceContext
    private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserTrack> findUserTracksByUser(AppUser appUser) {
		Query q = entityManager.createQuery("SELECT ut FROM UserTrack ut WHERE ut.tracked = :tracked", UserTrack.class);
        q.setParameter("tracked", appUser);
        List<UserTrack> resultList = (List<UserTrack>) q.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            return resultList;
        }
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserTrack> findUserTracksByUserAndDate(AppUser appUser, Date date) {
		Query q = entityManager.createQuery("SELECT ut FROM UserTrack ut WHERE ut.tracked = :tracked AND ut.trackingDate = :trackingDate", UserTrack.class);
		q.setParameter("tracked", appUser);
        q.setParameter("trackingDate", date);
        List<UserTrack> resultList = (List<UserTrack>) q.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            return resultList;
        }
		return null;
	}

	@Override
	public List<UserTrack> findUserTracksByDate(Date date) {
		Query q = entityManager.createQuery("SELECT ut FROM UserTrack ut WHERE ut.trackingDate = :trackingDate", UserTrack.class);
        q.setParameter("trackingDate", date);
        List<UserTrack> resultList = (List<UserTrack>) q.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            return resultList;
        }
		return null;
	}

}
