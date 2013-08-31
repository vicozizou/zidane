package com.bytepoxic.core.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.UserTrack;

@RooJpaRepository(domainType = UserTrack.class)
public interface UserTrackDAO {
	@Query("select ut from UserTrack ut where ut.tracked = :appUser")
	List<UserTrack> findUserTracksByUser(@Param("appUser") AppUser appUser);
	
	@Query("select ut from UserTrack ut where ut.trackingDate >= :fromDate and ut.trackingDate <= :toDate")
	List<UserTrack> findUserTracksByDates(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
	
	@Query("select ut from UserTrack ut where ut.tracked = :appUser and ut.trackingDate >= :fromDate and ut.trackingDate <= :toDate")
	List<UserTrack> findUserTracksByUserAndDates(
			@Param("appUser") AppUser appUser, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}
