package com.bytepoxic.core.dao;

import com.bytepoxic.core.model.AppUser;

public interface AppUserDAOCustom {
	AppUser findAppUserByUsername(String username);
}
