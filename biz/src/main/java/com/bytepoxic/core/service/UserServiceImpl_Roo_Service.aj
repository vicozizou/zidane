// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.service;

import com.bytepoxic.core.dao.AppRoleDAO;
import com.bytepoxic.core.dao.AppUserDAO;
import com.bytepoxic.core.dao.UserTrackDAO;
import com.bytepoxic.core.model.AppRole;
import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.UserTrack;
import com.bytepoxic.core.service.UserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserServiceImpl_Roo_Service {
    
    declare @type: UserServiceImpl: @Service;
    
    declare @type: UserServiceImpl: @Transactional;
    
    @Autowired
    AppRoleDAO UserServiceImpl.appRoleDAO;
    
    @Autowired
    AppUserDAO UserServiceImpl.appUserDAO;
    
    @Autowired
    UserTrackDAO UserServiceImpl.userTrackDAO;
    
    public long UserServiceImpl.countAllAppRoles() {
        return appRoleDAO.count();
    }
    
    public void UserServiceImpl.deleteAppRole(AppRole appRole) {
        appRoleDAO.delete(appRole);
    }
    
    public AppRole UserServiceImpl.findAppRole(Long id) {
        return appRoleDAO.findOne(id);
    }
    
    public List<AppRole> UserServiceImpl.findAllAppRoles() {
        return appRoleDAO.findAll();
    }
    
    public List<AppRole> UserServiceImpl.findAppRoleEntries(int firstResult, int maxResults) {
        return appRoleDAO.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void UserServiceImpl.saveAppRole(AppRole appRole) {
        appRoleDAO.save(appRole);
    }
    
    public AppRole UserServiceImpl.updateAppRole(AppRole appRole) {
        return appRoleDAO.save(appRole);
    }
    
    public long UserServiceImpl.countAllAppUsers() {
        return appUserDAO.count();
    }
    
    public void UserServiceImpl.deleteAppUser(AppUser appUser) {
        appUserDAO.delete(appUser);
    }
    
    public AppUser UserServiceImpl.findAppUser(Long id) {
        return appUserDAO.findOne(id);
    }
    
    public List<AppUser> UserServiceImpl.findAllAppUsers() {
        return appUserDAO.findAll();
    }
    
    public List<AppUser> UserServiceImpl.findAppUserEntries(int firstResult, int maxResults) {
        return appUserDAO.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void UserServiceImpl.saveAppUser(AppUser appUser) {
        appUserDAO.save(appUser);
    }
    
    public AppUser UserServiceImpl.updateAppUser(AppUser appUser) {
        return appUserDAO.save(appUser);
    }
    
    public long UserServiceImpl.countAllUserTracks() {
        return userTrackDAO.count();
    }
    
    public void UserServiceImpl.deleteUserTrack(UserTrack userTrack) {
        userTrackDAO.delete(userTrack);
    }
    
    public UserTrack UserServiceImpl.findUserTrack(Long id) {
        return userTrackDAO.findOne(id);
    }
    
    public List<UserTrack> UserServiceImpl.findAllUserTracks() {
        return userTrackDAO.findAll();
    }
    
    public List<UserTrack> UserServiceImpl.findUserTrackEntries(int firstResult, int maxResults) {
        return userTrackDAO.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void UserServiceImpl.saveUserTrack(UserTrack userTrack) {
        userTrackDAO.save(userTrack);
    }
    
    public UserTrack UserServiceImpl.updateUserTrack(UserTrack userTrack) {
        return userTrackDAO.save(userTrack);
    }
    
}
