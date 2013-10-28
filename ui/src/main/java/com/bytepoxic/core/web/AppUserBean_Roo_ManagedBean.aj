// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.web;

import com.bytepoxic.core.dao.PersonDAO;
import com.bytepoxic.core.model.AppRole;
import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.Person;
import com.bytepoxic.core.model.UserStatus;
import com.bytepoxic.core.service.UserService;
import com.bytepoxic.core.web.AppUserBean;
import com.bytepoxic.core.web.util.MessageFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;

privileged aspect AppUserBean_Roo_ManagedBean {
    
    declare @type: AppUserBean: @ManagedBean(name = "appUserBean");
    
    declare @type: AppUserBean: @SessionScoped;
    
    @Autowired
    UserService AppUserBean.userService;
    
    @Autowired
    PersonDAO AppUserBean.personDAO;
    
    private String AppUserBean.name = "AppUsers";
    
    private AppUser AppUserBean.appUser;
    
    private List<AppUser> AppUserBean.allAppUsers;
    
    private boolean AppUserBean.dataVisible = false;
    
    private List<String> AppUserBean.columns;
    
    private HtmlPanelGrid AppUserBean.createPanelGrid;
    
    private HtmlPanelGrid AppUserBean.editPanelGrid;
    
    private HtmlPanelGrid AppUserBean.viewPanelGrid;
    
    private boolean AppUserBean.createDialogVisible = false;
    
    private List<AppRole> AppUserBean.selectedRoles;
    
    @PostConstruct
    public void AppUserBean.init() {
        columns = new ArrayList<String>();
        columns.add("creationDate");
        columns.add("updateDate");
        columns.add("username");
        columns.add("password");
        columns.add("loginAttempts");
    }
    
    public String AppUserBean.getName() {
        return name;
    }
    
    public List<String> AppUserBean.getColumns() {
        return columns;
    }
    
    public List<AppUser> AppUserBean.getAllAppUsers() {
        return allAppUsers;
    }
    
    public void AppUserBean.setAllAppUsers(List<AppUser> allAppUsers) {
        this.allAppUsers = allAppUsers;
    }
    
    public String AppUserBean.findAllAppUsers() {
        allAppUsers = userService.findAllAppUsers();
        dataVisible = !allAppUsers.isEmpty();
        return null;
    }
    
    public boolean AppUserBean.isDataVisible() {
        return dataVisible;
    }
    
    public void AppUserBean.setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }
    
    public HtmlPanelGrid AppUserBean.getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }
    
    public void AppUserBean.setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }
    
    public HtmlPanelGrid AppUserBean.getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }
    
    public void AppUserBean.setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }
    
    public HtmlPanelGrid AppUserBean.getViewPanelGrid() {
        return populateViewPanel();
    }
    
    public void AppUserBean.setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }
    
    public AppUser AppUserBean.getAppUser() {
        if (appUser == null) {
            appUser = new AppUser();
        }
        return appUser;
    }
    
    public void AppUserBean.setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
    
    public List<Person> AppUserBean.completePerson(String query) {
        List<Person> suggestions = new ArrayList<Person>();
        for (Person person : personDAO.findAll()) {
            String personStr = String.valueOf(person.getCreationDate() +  " "  + person.getUpdateDate() +  " "  + person.getNames() +  " "  + person.getSurnames());
            if (personStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(person);
            }
        }
        return suggestions;
    }
    
    public List<AppRole> AppUserBean.getSelectedRoles() {
        return selectedRoles;
    }
    
    public void AppUserBean.setSelectedRoles(List<AppRole> selectedRoles) {
        if (selectedRoles != null) {
            appUser.setRoles(new HashSet<AppRole>(selectedRoles));
        }
        this.selectedRoles = selectedRoles;
    }
    
    public List<UserStatus> AppUserBean.completeUserStatus(String query) {
        List<UserStatus> suggestions = new ArrayList<UserStatus>();
        for (UserStatus userStatus : UserStatus.values()) {
            if (userStatus.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(userStatus);
            }
        }
        return suggestions;
    }
    
    public String AppUserBean.onEdit() {
        if (appUser != null && appUser.getRoles() != null) {
            selectedRoles = new ArrayList<AppRole>(appUser.getRoles());
        }
        return null;
    }
    
    public boolean AppUserBean.isCreateDialogVisible() {
        return createDialogVisible;
    }
    
    public void AppUserBean.setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }
    
    public String AppUserBean.displayList() {
        createDialogVisible = false;
        findAllAppUsers();
        return "appUser";
    }
    
    public String AppUserBean.displayCreateDialog() {
        appUser = new AppUser();
        createDialogVisible = true;
        return "appUser";
    }
    
    public String AppUserBean.persist() {
        String message = "";
        if (appUser.getId() != null) {
            userService.updateAppUser(appUser);
            message = "message_successfully_updated";
        } else {
            userService.saveAppUser(appUser);
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "AppUser");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllAppUsers();
    }
    
    public String AppUserBean.delete() {
        userService.deleteAppUser(appUser);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "AppUser");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllAppUsers();
    }
    
    public void AppUserBean.reset() {
        appUser = null;
        selectedRoles = null;
        createDialogVisible = false;
    }
    
    public void AppUserBean.handleDialogClose(CloseEvent event) {
        reset();
    }
    
}
