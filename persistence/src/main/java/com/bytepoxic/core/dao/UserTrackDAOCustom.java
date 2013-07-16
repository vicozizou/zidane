package com.bytepoxic.core.dao;

import java.util.Date;
import java.util.List;

import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.UserTrack;

public interface UserTrackDAOCustom {
	List<UserTrack> findUserTracksByUser(AppUser appUser);
	List<UserTrack> findUserTracksByUserAndDate(AppUser appUser, Date date);
	List<UserTrack> findUserTracksByDate(Date date);
}
