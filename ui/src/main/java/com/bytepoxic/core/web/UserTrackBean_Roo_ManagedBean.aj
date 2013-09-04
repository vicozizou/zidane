// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.web;

import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.TrackingType;
import com.bytepoxic.core.model.UserTrack;
import com.bytepoxic.core.service.UserService;
import com.bytepoxic.core.web.UserTrackBean;
import com.bytepoxic.core.web.converter.AppUserConverter;
import com.bytepoxic.core.web.util.MessageFactory;
import java.util.ArrayList;
import java.util.Date;
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
import javax.faces.convert.DateTimeConverter;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;

privileged aspect UserTrackBean_Roo_ManagedBean {
    
    declare @type: UserTrackBean: @ManagedBean(name = "userTrackBean");
    
    declare @type: UserTrackBean: @SessionScoped;
    
    @Autowired
    UserService UserTrackBean.userService;
    
    private String UserTrackBean.name = "UserTracks";
    
    private UserTrack UserTrackBean.userTrack;
    
    private List<UserTrack> UserTrackBean.allUserTracks;
    
    private boolean UserTrackBean.dataVisible = false;
    
    private List<String> UserTrackBean.columns;
    
    private HtmlPanelGrid UserTrackBean.createPanelGrid;
    
    private HtmlPanelGrid UserTrackBean.editPanelGrid;
    
    private HtmlPanelGrid UserTrackBean.viewPanelGrid;
    
    private boolean UserTrackBean.createDialogVisible = false;
    
    @PostConstruct
    public void UserTrackBean.init() {
        columns = new ArrayList<String>();
        columns.add("trackingDate");
    }
    
    public String UserTrackBean.getName() {
        return name;
    }
    
    public List<String> UserTrackBean.getColumns() {
        return columns;
    }
    
    public List<UserTrack> UserTrackBean.getAllUserTracks() {
        return allUserTracks;
    }
    
    public void UserTrackBean.setAllUserTracks(List<UserTrack> allUserTracks) {
        this.allUserTracks = allUserTracks;
    }
    
    public String UserTrackBean.findAllUserTracks() {
        allUserTracks = userService.findAllUserTracks();
        dataVisible = !allUserTracks.isEmpty();
        return null;
    }
    
    public boolean UserTrackBean.isDataVisible() {
        return dataVisible;
    }
    
    public void UserTrackBean.setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }
    
    public HtmlPanelGrid UserTrackBean.getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }
    
    public void UserTrackBean.setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }
    
    public HtmlPanelGrid UserTrackBean.getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }
    
    public void UserTrackBean.setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }
    
    public HtmlPanelGrid UserTrackBean.getViewPanelGrid() {
        return populateViewPanel();
    }
    
    public void UserTrackBean.setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }
    
    public HtmlPanelGrid UserTrackBean.populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel trackedCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        trackedCreateOutput.setFor("trackedCreateInput");
        trackedCreateOutput.setId("trackedCreateOutput");
        trackedCreateOutput.setValue("Tracked:");
        htmlPanelGrid.getChildren().add(trackedCreateOutput);
        
        AutoComplete trackedCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        trackedCreateInput.setId("trackedCreateInput");
        trackedCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{userTrackBean.userTrack.tracked}", AppUser.class));
        trackedCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{userTrackBean.completeTracked}", List.class, new Class[] { String.class }));
        trackedCreateInput.setDropdown(true);
        trackedCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "tracked", String.class));
        trackedCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{tracked.creationDate} #{tracked.updateDate} #{tracked.names} #{tracked.surnames}", String.class));
        trackedCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{tracked}", AppUser.class));
        trackedCreateInput.setConverter(new AppUserConverter());
        trackedCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(trackedCreateInput);
        
        Message trackedCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        trackedCreateInputMessage.setId("trackedCreateInputMessage");
        trackedCreateInputMessage.setFor("trackedCreateInput");
        trackedCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(trackedCreateInputMessage);
        
        OutputLabel trackingTypeCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        trackingTypeCreateOutput.setFor("trackingTypeCreateInput");
        trackingTypeCreateOutput.setId("trackingTypeCreateOutput");
        trackingTypeCreateOutput.setValue("Tracking Type:");
        htmlPanelGrid.getChildren().add(trackingTypeCreateOutput);
        
        AutoComplete trackingTypeCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        trackingTypeCreateInput.setId("trackingTypeCreateInput");
        trackingTypeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{userTrackBean.userTrack.trackingType}", TrackingType.class));
        trackingTypeCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{userTrackBean.completeTrackingType}", List.class, new Class[] { String.class }));
        trackingTypeCreateInput.setDropdown(true);
        trackingTypeCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(trackingTypeCreateInput);
        
        Message trackingTypeCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        trackingTypeCreateInputMessage.setId("trackingTypeCreateInputMessage");
        trackingTypeCreateInputMessage.setFor("trackingTypeCreateInput");
        trackingTypeCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(trackingTypeCreateInputMessage);
        
        OutputLabel trackingDateCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        trackingDateCreateOutput.setFor("trackingDateCreateInput");
        trackingDateCreateOutput.setId("trackingDateCreateOutput");
        trackingDateCreateOutput.setValue("Tracking Date:");
        htmlPanelGrid.getChildren().add(trackingDateCreateOutput);
        
        Calendar trackingDateCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        trackingDateCreateInput.setId("trackingDateCreateInput");
        trackingDateCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{userTrackBean.userTrack.trackingDate}", Date.class));
        trackingDateCreateInput.setNavigator(true);
        trackingDateCreateInput.setEffect("slideDown");
        trackingDateCreateInput.setPattern("dd/MM/yyyy");
        trackingDateCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(trackingDateCreateInput);
        
        Message trackingDateCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        trackingDateCreateInputMessage.setId("trackingDateCreateInputMessage");
        trackingDateCreateInputMessage.setFor("trackingDateCreateInput");
        trackingDateCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(trackingDateCreateInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid UserTrackBean.populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel trackedEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        trackedEditOutput.setFor("trackedEditInput");
        trackedEditOutput.setId("trackedEditOutput");
        trackedEditOutput.setValue("Tracked:");
        htmlPanelGrid.getChildren().add(trackedEditOutput);
        
        AutoComplete trackedEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        trackedEditInput.setId("trackedEditInput");
        trackedEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{userTrackBean.userTrack.tracked}", AppUser.class));
        trackedEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{userTrackBean.completeTracked}", List.class, new Class[] { String.class }));
        trackedEditInput.setDropdown(true);
        trackedEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "tracked", String.class));
        trackedEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{tracked.creationDate} #{tracked.updateDate} #{tracked.names} #{tracked.surnames}", String.class));
        trackedEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{tracked}", AppUser.class));
        trackedEditInput.setConverter(new AppUserConverter());
        trackedEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(trackedEditInput);
        
        Message trackedEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        trackedEditInputMessage.setId("trackedEditInputMessage");
        trackedEditInputMessage.setFor("trackedEditInput");
        trackedEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(trackedEditInputMessage);
        
        OutputLabel trackingTypeEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        trackingTypeEditOutput.setFor("trackingTypeEditInput");
        trackingTypeEditOutput.setId("trackingTypeEditOutput");
        trackingTypeEditOutput.setValue("Tracking Type:");
        htmlPanelGrid.getChildren().add(trackingTypeEditOutput);
        
        AutoComplete trackingTypeEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        trackingTypeEditInput.setId("trackingTypeEditInput");
        trackingTypeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{userTrackBean.userTrack.trackingType}", TrackingType.class));
        trackingTypeEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{userTrackBean.completeTrackingType}", List.class, new Class[] { String.class }));
        trackingTypeEditInput.setDropdown(true);
        trackingTypeEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(trackingTypeEditInput);
        
        Message trackingTypeEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        trackingTypeEditInputMessage.setId("trackingTypeEditInputMessage");
        trackingTypeEditInputMessage.setFor("trackingTypeEditInput");
        trackingTypeEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(trackingTypeEditInputMessage);
        
        OutputLabel trackingDateEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        trackingDateEditOutput.setFor("trackingDateEditInput");
        trackingDateEditOutput.setId("trackingDateEditOutput");
        trackingDateEditOutput.setValue("Tracking Date:");
        htmlPanelGrid.getChildren().add(trackingDateEditOutput);
        
        Calendar trackingDateEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        trackingDateEditInput.setId("trackingDateEditInput");
        trackingDateEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{userTrackBean.userTrack.trackingDate}", Date.class));
        trackingDateEditInput.setNavigator(true);
        trackingDateEditInput.setEffect("slideDown");
        trackingDateEditInput.setPattern("dd/MM/yyyy");
        trackingDateEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(trackingDateEditInput);
        
        Message trackingDateEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        trackingDateEditInputMessage.setId("trackingDateEditInputMessage");
        trackingDateEditInputMessage.setFor("trackingDateEditInput");
        trackingDateEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(trackingDateEditInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid UserTrackBean.populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText trackedLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        trackedLabel.setId("trackedLabel");
        trackedLabel.setValue("Tracked:");
        htmlPanelGrid.getChildren().add(trackedLabel);
        
        HtmlOutputText trackedValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        trackedValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{userTrackBean.userTrack.tracked}", AppUser.class));
        trackedValue.setConverter(new AppUserConverter());
        htmlPanelGrid.getChildren().add(trackedValue);
        
        HtmlOutputText trackingTypeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        trackingTypeLabel.setId("trackingTypeLabel");
        trackingTypeLabel.setValue("Tracking Type:");
        htmlPanelGrid.getChildren().add(trackingTypeLabel);
        
        HtmlOutputText trackingTypeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        trackingTypeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{userTrackBean.userTrack.trackingType}", String.class));
        htmlPanelGrid.getChildren().add(trackingTypeValue);
        
        HtmlOutputText trackingDateLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        trackingDateLabel.setId("trackingDateLabel");
        trackingDateLabel.setValue("Tracking Date:");
        htmlPanelGrid.getChildren().add(trackingDateLabel);
        
        HtmlOutputText trackingDateValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        trackingDateValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{userTrackBean.userTrack.trackingDate}", Date.class));
        DateTimeConverter trackingDateValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        trackingDateValueConverter.setPattern("dd/MM/yyyy");
        trackingDateValue.setConverter(trackingDateValueConverter);
        htmlPanelGrid.getChildren().add(trackingDateValue);
        
        return htmlPanelGrid;
    }
    
    public UserTrack UserTrackBean.getUserTrack() {
        if (userTrack == null) {
            userTrack = new UserTrack();
        }
        return userTrack;
    }
    
    public void UserTrackBean.setUserTrack(UserTrack userTrack) {
        this.userTrack = userTrack;
    }
    
    public List<AppUser> UserTrackBean.completeTracked(String query) {
        List<AppUser> suggestions = new ArrayList<AppUser>();
        for (AppUser appUser : userService.findAllAppUsers()) {
            String appUserStr = String.valueOf(appUser.getCreationDate() +  " "  + appUser.getUpdateDate() +  " "  + appUser.getNames() +  " "  + appUser.getSurnames());
            if (appUserStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(appUser);
            }
        }
        return suggestions;
    }
    
    public List<TrackingType> UserTrackBean.completeTrackingType(String query) {
        List<TrackingType> suggestions = new ArrayList<TrackingType>();
        for (TrackingType trackingType : TrackingType.values()) {
            if (trackingType.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(trackingType);
            }
        }
        return suggestions;
    }
    
    public String UserTrackBean.onEdit() {
        return null;
    }
    
    public boolean UserTrackBean.isCreateDialogVisible() {
        return createDialogVisible;
    }
    
    public void UserTrackBean.setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }
    
    public String UserTrackBean.displayList() {
        createDialogVisible = false;
        findAllUserTracks();
        return "userTrack";
    }
    
    public String UserTrackBean.displayCreateDialog() {
        userTrack = new UserTrack();
        createDialogVisible = true;
        return "userTrack";
    }
    
    public String UserTrackBean.persist() {
        String message = "";
        if (userTrack.getId() != null) {
            userService.updateUserTrack(userTrack);
            message = "message_successfully_updated";
        } else {
            userService.saveUserTrack(userTrack);
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "UserTrack");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllUserTracks();
    }
    
    public String UserTrackBean.delete() {
        userService.deleteUserTrack(userTrack);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "UserTrack");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllUserTracks();
    }
    
    public void UserTrackBean.reset() {
        userTrack = null;
        createDialogVisible = false;
    }
    
    public void UserTrackBean.handleDialogClose(CloseEvent event) {
        reset();
    }
    
}