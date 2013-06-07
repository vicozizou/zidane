// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.service;

import com.bytepoxic.core.model.AppRole;
import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.UserTrack;
import com.bytepoxic.core.service.UserService;
import java.util.List;

privileged aspect UserService_Roo_Service {
    
    public abstract long UserService.countAllAppUsers();    
    public abstract void UserService.deleteAppUser(AppUser appUser);    
    public abstract List<AppUser> UserService.findAllAppUsers();    
    public abstract List<AppUser> UserService.findAppUserEntries(int firstResult, int maxResults);    
    public abstract void UserService.saveAppUser(AppUser appUser);    
    public abstract long UserService.countAllUserTracks();    
    public abstract void UserService.deleteUserTrack(UserTrack userTrack);    
    public abstract List<UserTrack> UserService.findAllUserTracks();    
    public abstract List<UserTrack> UserService.findUserTrackEntries(int firstResult, int maxResults);    
    public abstract void UserService.saveUserTrack(UserTrack userTrack);    
    public abstract UserTrack UserService.updateUserTrack(UserTrack userTrack);    
    public abstract long UserService.countAllAppRoles();    
    public abstract void UserService.deleteAppRole(AppRole appRole);    
    public abstract List<AppRole> UserService.findAllAppRoles();    
    public abstract List<AppRole> UserService.findAppRoleEntries(int firstResult, int maxResults);    
    public abstract void UserService.saveAppRole(AppRole appRole);    
}
