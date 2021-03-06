// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.web.faces.bean;

import com.bytepoxic.core.model.AppRole;
import com.bytepoxic.core.service.UserService;
import com.bytepoxic.core.web.faces.bean.AppRoleBean;
import com.bytepoxic.core.web.faces.bean.util.MessageFactory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;

privileged aspect AppRoleBean_Roo_ManagedBean {
    
    declare @type: AppRoleBean: @ManagedBean(name = "appRoleBean");
    
    declare @type: AppRoleBean: @SessionScoped;
    
    @Autowired
    UserService AppRoleBean.userService;
    
    private String AppRoleBean.name = "AppRoles";
    
    private AppRole AppRoleBean.appRole;
    
    private List<AppRole> AppRoleBean.allAppRoles;
    
    private boolean AppRoleBean.dataVisible = false;
    
    private List<String> AppRoleBean.columns;
    
    private HtmlPanelGrid AppRoleBean.createPanelGrid;
    
    private HtmlPanelGrid AppRoleBean.editPanelGrid;
    
    private HtmlPanelGrid AppRoleBean.viewPanelGrid;
    
    private boolean AppRoleBean.createDialogVisible = false;
    
    @PostConstruct
    public void AppRoleBean.init() {
        columns = new ArrayList<String>();
        columns.add("name");
        columns.add("description");
    }
    
    public String AppRoleBean.getName() {
        return name;
    }
    
    public List<String> AppRoleBean.getColumns() {
        return columns;
    }
    
    public List<AppRole> AppRoleBean.getAllAppRoles() {
        return allAppRoles;
    }
    
    public void AppRoleBean.setAllAppRoles(List<AppRole> allAppRoles) {
        this.allAppRoles = allAppRoles;
    }
    
    public String AppRoleBean.findAllAppRoles() {
        allAppRoles = userService.findAllAppRoles();
        dataVisible = !allAppRoles.isEmpty();
        return null;
    }
    
    public boolean AppRoleBean.isDataVisible() {
        return dataVisible;
    }
    
    public void AppRoleBean.setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }
    
    public HtmlPanelGrid AppRoleBean.getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }
    
    public void AppRoleBean.setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }
    
    public HtmlPanelGrid AppRoleBean.getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }
    
    public void AppRoleBean.setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }
    
    public HtmlPanelGrid AppRoleBean.getViewPanelGrid() {
        return populateViewPanel();
    }
    
    public void AppRoleBean.setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }
    
    public HtmlPanelGrid AppRoleBean.populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText nameLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        nameLabel.setId("nameLabel");
        nameLabel.setValue("Name:");
        htmlPanelGrid.getChildren().add(nameLabel);
        
        InputTextarea nameValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        nameValue.setId("nameValue");
        nameValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appRoleBean.appRole.name}", String.class));
        nameValue.setReadonly(true);
        nameValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(nameValue);
        
        HtmlOutputText descriptionLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        descriptionLabel.setId("descriptionLabel");
        descriptionLabel.setValue("Description:");
        htmlPanelGrid.getChildren().add(descriptionLabel);
        
        InputTextarea descriptionValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        descriptionValue.setId("descriptionValue");
        descriptionValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appRoleBean.appRole.description}", String.class));
        descriptionValue.setReadonly(true);
        descriptionValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(descriptionValue);
        
        return htmlPanelGrid;
    }
    
    public AppRole AppRoleBean.getAppRole() {
        if (appRole == null) {
            appRole = new AppRole();
        }
        return appRole;
    }
    
    public void AppRoleBean.setAppRole(AppRole appRole) {
        this.appRole = appRole;
    }
    
    public String AppRoleBean.onEdit() {
        return null;
    }
    
    public boolean AppRoleBean.isCreateDialogVisible() {
        return createDialogVisible;
    }
    
    public void AppRoleBean.setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }
    
    public String AppRoleBean.displayList() {
        createDialogVisible = false;
        findAllAppRoles();
        return "appRole";
    }
    
    public String AppRoleBean.displayCreateDialog() {
        appRole = new AppRole();
        createDialogVisible = true;
        return "appRole";
    }
    
    public String AppRoleBean.persist() {
        String message = "";
        if (appRole.getId() != null) {
            userService.updateAppRole(appRole);
            message = "message_successfully_updated";
        } else {
            userService.saveAppRole(appRole);
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "AppRole");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllAppRoles();
    }
    
    public String AppRoleBean.delete() {
        userService.deleteAppRole(appRole);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "AppRole");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllAppRoles();
    }
    
    public void AppRoleBean.reset() {
        appRole = null;
        createDialogVisible = false;
    }
    
    public void AppRoleBean.handleDialogClose(CloseEvent event) {
        reset();
    }
    
}
