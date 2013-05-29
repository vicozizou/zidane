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
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.validator.LengthValidator;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
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
        columns.add("labelKey");
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
    
    public HtmlPanelGrid AppRoleBean.populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel labelKeyCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        labelKeyCreateOutput.setFor("labelKeyCreateInput");
        labelKeyCreateOutput.setId("labelKeyCreateOutput");
        labelKeyCreateOutput.setValue("Label Key:");
        htmlPanelGrid.getChildren().add(labelKeyCreateOutput);
        
        InputTextarea labelKeyCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        labelKeyCreateInput.setId("labelKeyCreateInput");
        labelKeyCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appRoleBean.appRole.labelKey}", String.class));
        LengthValidator labelKeyCreateInputValidator = new LengthValidator();
        labelKeyCreateInputValidator.setMaximum(128);
        labelKeyCreateInput.addValidator(labelKeyCreateInputValidator);
        labelKeyCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(labelKeyCreateInput);
        
        Message labelKeyCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        labelKeyCreateInputMessage.setId("labelKeyCreateInputMessage");
        labelKeyCreateInputMessage.setFor("labelKeyCreateInput");
        labelKeyCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(labelKeyCreateInputMessage);
        
        OutputLabel nameCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        nameCreateOutput.setFor("nameCreateInput");
        nameCreateOutput.setId("nameCreateOutput");
        nameCreateOutput.setValue("Name:");
        htmlPanelGrid.getChildren().add(nameCreateOutput);
        
        InputTextarea nameCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        nameCreateInput.setId("nameCreateInput");
        nameCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appRoleBean.appRole.name}", String.class));
        LengthValidator nameCreateInputValidator = new LengthValidator();
        nameCreateInputValidator.setMaximum(32);
        nameCreateInput.addValidator(nameCreateInputValidator);
        nameCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(nameCreateInput);
        
        Message nameCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        nameCreateInputMessage.setId("nameCreateInputMessage");
        nameCreateInputMessage.setFor("nameCreateInput");
        nameCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(nameCreateInputMessage);
        
        OutputLabel descriptionCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        descriptionCreateOutput.setFor("descriptionCreateInput");
        descriptionCreateOutput.setId("descriptionCreateOutput");
        descriptionCreateOutput.setValue("Description:");
        htmlPanelGrid.getChildren().add(descriptionCreateOutput);
        
        InputTextarea descriptionCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        descriptionCreateInput.setId("descriptionCreateInput");
        descriptionCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appRoleBean.appRole.description}", String.class));
        LengthValidator descriptionCreateInputValidator = new LengthValidator();
        descriptionCreateInputValidator.setMaximum(128);
        descriptionCreateInput.addValidator(descriptionCreateInputValidator);
        descriptionCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(descriptionCreateInput);
        
        Message descriptionCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        descriptionCreateInputMessage.setId("descriptionCreateInputMessage");
        descriptionCreateInputMessage.setFor("descriptionCreateInput");
        descriptionCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(descriptionCreateInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid AppRoleBean.populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel labelKeyEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        labelKeyEditOutput.setFor("labelKeyEditInput");
        labelKeyEditOutput.setId("labelKeyEditOutput");
        labelKeyEditOutput.setValue("Label Key:");
        htmlPanelGrid.getChildren().add(labelKeyEditOutput);
        
        InputTextarea labelKeyEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        labelKeyEditInput.setId("labelKeyEditInput");
        labelKeyEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appRoleBean.appRole.labelKey}", String.class));
        LengthValidator labelKeyEditInputValidator = new LengthValidator();
        labelKeyEditInputValidator.setMaximum(128);
        labelKeyEditInput.addValidator(labelKeyEditInputValidator);
        labelKeyEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(labelKeyEditInput);
        
        Message labelKeyEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        labelKeyEditInputMessage.setId("labelKeyEditInputMessage");
        labelKeyEditInputMessage.setFor("labelKeyEditInput");
        labelKeyEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(labelKeyEditInputMessage);
        
        OutputLabel nameEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        nameEditOutput.setFor("nameEditInput");
        nameEditOutput.setId("nameEditOutput");
        nameEditOutput.setValue("Name:");
        htmlPanelGrid.getChildren().add(nameEditOutput);
        
        InputTextarea nameEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        nameEditInput.setId("nameEditInput");
        nameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appRoleBean.appRole.name}", String.class));
        LengthValidator nameEditInputValidator = new LengthValidator();
        nameEditInputValidator.setMaximum(32);
        nameEditInput.addValidator(nameEditInputValidator);
        nameEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(nameEditInput);
        
        Message nameEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        nameEditInputMessage.setId("nameEditInputMessage");
        nameEditInputMessage.setFor("nameEditInput");
        nameEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(nameEditInputMessage);
        
        OutputLabel descriptionEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        descriptionEditOutput.setFor("descriptionEditInput");
        descriptionEditOutput.setId("descriptionEditOutput");
        descriptionEditOutput.setValue("Description:");
        htmlPanelGrid.getChildren().add(descriptionEditOutput);
        
        InputTextarea descriptionEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        descriptionEditInput.setId("descriptionEditInput");
        descriptionEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appRoleBean.appRole.description}", String.class));
        LengthValidator descriptionEditInputValidator = new LengthValidator();
        descriptionEditInputValidator.setMaximum(128);
        descriptionEditInput.addValidator(descriptionEditInputValidator);
        descriptionEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(descriptionEditInput);
        
        Message descriptionEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        descriptionEditInputMessage.setId("descriptionEditInputMessage");
        descriptionEditInputMessage.setFor("descriptionEditInput");
        descriptionEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(descriptionEditInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid AppRoleBean.populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText labelKeyLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        labelKeyLabel.setId("labelKeyLabel");
        labelKeyLabel.setValue("Label Key:");
        htmlPanelGrid.getChildren().add(labelKeyLabel);
        
        InputTextarea labelKeyValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        labelKeyValue.setId("labelKeyValue");
        labelKeyValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appRoleBean.appRole.labelKey}", String.class));
        labelKeyValue.setReadonly(true);
        labelKeyValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(labelKeyValue);
        
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
