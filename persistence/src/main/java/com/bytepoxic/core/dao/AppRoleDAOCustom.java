package com.bytepoxic.core.dao;

import java.util.List;

import com.bytepoxic.core.model.AppRole;

public interface AppRoleDAOCustom {
	List<AppRole> findAppRolesByName(String name);
	AppRole findAppRoleByName(String name);
}
