package com.bytepoxic.core.web.faces.bean;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.validator.LengthValidator;

import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.Person;
import com.bytepoxic.core.model.UserStatus;
import com.bytepoxic.core.web.faces.converter.PersonConverter;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.component.spinner.Spinner;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RequestScoped
@RooJsfManagedBean(entity = AppUser.class, beanName = "appUserBean")
public class AppUserBean {
	@Autowired
    private UserService userService;
    
    @Autowired
    private LocationService locationService;
    
    private String name = "AppUsers";
    private AppUser appUser;
    private List<AppUser> allAppUsers;
    private boolean dataVisible = false;
    private List<String> columns;
    private HtmlPanelGrid createPanelGrid;
    private HtmlPanelGrid editPanelGrid;
    private HtmlPanelGrid viewPanelGrid;
    private boolean createDialogVisible = false;
    private List<Phone> selectedPhones;
    private List<Email> selectedEmails;
    private List<AppRole> selectedRoles;
    
    @PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("names");
        columns.add("surnames");
        columns.add("birthday");
        columns.add("identification");
        columns.add("creationDate");
    }
    
    public String getName() {
        return name;
    }
    
    public List<String> getColumns() {
        return columns;
    }
    
    public List<AppUser> getAllAppUsers() {
        return allAppUsers;
    }
    
    public void setAllAppUsers(List<AppUser> allAppUsers) {
        this.allAppUsers = allAppUsers;
    }
    
    public String findAllAppUsers() {
        allAppUsers = userService.findAllAppUsers();
        dataVisible = !allAppUsers.isEmpty();
        return null;
    }
    
    public boolean isDataVisible() {
        return dataVisible;
    }
    
    public void setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }
    
    public HtmlPanelGrid getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }
    
    public void setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }
    
    public HtmlPanelGrid getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }
    
    public void setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }
    
    public HtmlPanelGrid getViewPanelGrid() {
        return populateViewPanel();
    }
    
    public void setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }
    
    public AppUser getAppUser() {
        if (appUser == null) {
            appUser = new AppUser();
        }
        return appUser;
    }
    
    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
    
    public List<Gender> completeGender(String query) {
        List<Gender> suggestions = new ArrayList<Gender>();
        for (Gender gender : Gender.values()) {
            if (gender.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(gender);
            }
        }
        return suggestions;
    }
    
    public List<IdentificationType> completeIdentificationType(String query) {
        List<IdentificationType> suggestions = new ArrayList<IdentificationType>();
        for (IdentificationType identificationType : IdentificationType.values()) {
            if (identificationType.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(identificationType);
            }
        }
        return suggestions;
    }
    
    public List<Nationality> completeNationality(String query) {
        List<Nationality> suggestions = new ArrayList<Nationality>();
        for (Nationality nationality : locationService.findAllNationalitys()) {
            String nationalityStr = String.valueOf(nationality.getLabelKey() +  " "  + nationality.getName());
            if (nationalityStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(nationality);
            }
        }
        return suggestions;
    }
    
    public List<Place> completeHomePlace(String query) {
        List<Place> suggestions = new ArrayList<Place>();
        for (Place place : locationService.findAllPlaces()) {
            String placeStr = String.valueOf(place.getName() +  " "  + place.getPrimaryAddress() +  " "  + place.getSecondaryAddress() +  " "  + place.getLatitude());
            if (placeStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(place);
            }
        }
        return suggestions;
    }
    
    public List<Place> completeWorkPlace(String query) {
        List<Place> suggestions = new ArrayList<Place>();
        for (Place place : locationService.findAllPlaces()) {
            String placeStr = String.valueOf(place.getName() +  " "  + place.getPrimaryAddress() +  " "  + place.getSecondaryAddress() +  " "  + place.getLatitude());
            if (placeStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(place);
            }
        }
        return suggestions;
    }
    
    public List<Phone> getSelectedPhones() {
        return selectedPhones;
    }
    
    public void setSelectedPhones(List<Phone> selectedPhones) {
        if (selectedPhones != null) {
            appUser.setPhones(new HashSet<Phone>(selectedPhones));
        }
        this.selectedPhones = selectedPhones;
    }
    
    public List<Email> getSelectedEmails() {
        return selectedEmails;
    }
    
    public void setSelectedEmails(List<Email> selectedEmails) {
        if (selectedEmails != null) {
            appUser.setEmails(new HashSet<Email>(selectedEmails));
        }
        this.selectedEmails = selectedEmails;
    }
    
    public List<AppRole> getSelectedRoles() {
        return selectedRoles;
    }
    
    public void setSelectedRoles(List<AppRole> selectedRoles) {
        if (selectedRoles != null) {
            appUser.setRoles(new HashSet<AppRole>(selectedRoles));
        }
        this.selectedRoles = selectedRoles;
    }
    
    public List<UserStatus> completeUserStatus(String query) {
        List<UserStatus> suggestions = new ArrayList<UserStatus>();
        for (UserStatus userStatus : UserStatus.values()) {
            if (userStatus.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(userStatus);
            }
        }
        return suggestions;
    }
    
    public String onEdit() {
        if (appUser != null && appUser.getPhones() != null) {
            selectedPhones = new ArrayList<Phone>(appUser.getPhones());
        }
        if (appUser != null && appUser.getEmails() != null) {
            selectedEmails = new ArrayList<Email>(appUser.getEmails());
        }
        if (appUser != null && appUser.getRoles() != null) {
            selectedRoles = new ArrayList<AppRole>(appUser.getRoles());
        }
        return null;
    }
    
    public boolean isCreateDialogVisible() {
        return createDialogVisible;
    }
    
    public void setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }
    
    public String displayList() {
        createDialogVisible = false;
        findAllAppUsers();
        return "appUser";
    }
    
    public String displayCreateDialog() {
        appUser = new AppUser();
        createDialogVisible = true;
        return "appUser";
    }
    
    public String persist() {
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
    
    public String delete() {
        userService.deleteAppUser(appUser);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "AppUser");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllAppUsers();
    }
    
    public void reset() {
        appUser = null;
        selectedPhones = null;
        selectedEmails = null;
        selectedRoles = null;
        createDialogVisible = false;
    }
    
    public void handleDialogClose(CloseEvent event) {
        reset();
    }
	
	public HtmlPanelGrid populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel deletedCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        deletedCreateOutput.setFor("deletedCreateInput");
        deletedCreateOutput.setId("deletedCreateOutput");
        deletedCreateOutput.setValue("Deleted:");
        htmlPanelGrid.getChildren().add(deletedCreateOutput);
        
        SelectBooleanCheckbox deletedCreateInput = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
        deletedCreateInput.setId("deletedCreateInput");
        deletedCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.deleted}", Boolean.class));
        deletedCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(deletedCreateInput);
        
        Message deletedCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        deletedCreateInputMessage.setId("deletedCreateInputMessage");
        deletedCreateInputMessage.setFor("deletedCreateInput");
        deletedCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(deletedCreateInputMessage);
        
        OutputLabel creationDateCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        creationDateCreateOutput.setFor("creationDateCreateInput");
        creationDateCreateOutput.setId("creationDateCreateOutput");
        creationDateCreateOutput.setValue("Creation Date:");
        htmlPanelGrid.getChildren().add(creationDateCreateOutput);
        
        Calendar creationDateCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        creationDateCreateInput.setId("creationDateCreateInput");
        creationDateCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.creationDate}", Date.class));
        creationDateCreateInput.setNavigator(true);
        creationDateCreateInput.setEffect("slideDown");
        creationDateCreateInput.setPattern("dd/MM/yyyy");
        creationDateCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(creationDateCreateInput);
        
        Message creationDateCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        creationDateCreateInputMessage.setId("creationDateCreateInputMessage");
        creationDateCreateInputMessage.setFor("creationDateCreateInput");
        creationDateCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(creationDateCreateInputMessage);
        
        OutputLabel updateDateCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        updateDateCreateOutput.setFor("updateDateCreateInput");
        updateDateCreateOutput.setId("updateDateCreateOutput");
        updateDateCreateOutput.setValue("Update Date:");
        htmlPanelGrid.getChildren().add(updateDateCreateOutput);
        
        Calendar updateDateCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        updateDateCreateInput.setId("updateDateCreateInput");
        updateDateCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.updateDate}", Date.class));
        updateDateCreateInput.setNavigator(true);
        updateDateCreateInput.setEffect("slideDown");
        updateDateCreateInput.setPattern("dd/MM/yyyy");
        updateDateCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(updateDateCreateInput);
        
        Message updateDateCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        updateDateCreateInputMessage.setId("updateDateCreateInputMessage");
        updateDateCreateInputMessage.setFor("updateDateCreateInput");
        updateDateCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(updateDateCreateInputMessage);
        
        OutputLabel authoritiesCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        authoritiesCreateOutput.setFor("authoritiesCreateInput");
        authoritiesCreateOutput.setId("authoritiesCreateOutput");
        authoritiesCreateOutput.setValue("Authorities:");
        htmlPanelGrid.getChildren().add(authoritiesCreateOutput);
        
        InputText authoritiesCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        authoritiesCreateInput.setId("authoritiesCreateInput");
        authoritiesCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.authorities}", Set.class));
        authoritiesCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(authoritiesCreateInput);
        
        Message authoritiesCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        authoritiesCreateInputMessage.setId("authoritiesCreateInputMessage");
        authoritiesCreateInputMessage.setFor("authoritiesCreateInput");
        authoritiesCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(authoritiesCreateInputMessage);
        
        OutputLabel personCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        personCreateOutput.setFor("personCreateInput");
        personCreateOutput.setId("personCreateOutput");
        personCreateOutput.setValue("Person:");
        htmlPanelGrid.getChildren().add(personCreateOutput);
        
        AutoComplete personCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        personCreateInput.setId("personCreateInput");
        personCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.person}", Person.class));
        personCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{appUserBean.completePerson}", List.class, new Class[] { String.class }));
        personCreateInput.setDropdown(true);
        personCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "person", String.class));
        personCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{person.creationDate} #{person.updateDate} #{person.names} #{person.surnames}", String.class));
        personCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{person}", Person.class));
        personCreateInput.setConverter(new PersonConverter());
        personCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(personCreateInput);
        
        Message personCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        personCreateInputMessage.setId("personCreateInputMessage");
        personCreateInputMessage.setFor("personCreateInput");
        personCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(personCreateInputMessage);
        
        OutputLabel usernameCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        usernameCreateOutput.setFor("usernameCreateInput");
        usernameCreateOutput.setId("usernameCreateOutput");
        usernameCreateOutput.setValue("Username:");
        htmlPanelGrid.getChildren().add(usernameCreateOutput);
        
        InputTextarea usernameCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        usernameCreateInput.setId("usernameCreateInput");
        usernameCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.username}", String.class));
        LengthValidator usernameCreateInputValidator = new LengthValidator();
        usernameCreateInputValidator.setMaximum(32);
        usernameCreateInput.addValidator(usernameCreateInputValidator);
        usernameCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(usernameCreateInput);
        
        Message usernameCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        usernameCreateInputMessage.setId("usernameCreateInputMessage");
        usernameCreateInputMessage.setFor("usernameCreateInput");
        usernameCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(usernameCreateInputMessage);
        
        OutputLabel passwordCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        passwordCreateOutput.setFor("passwordCreateInput");
        passwordCreateOutput.setId("passwordCreateOutput");
        passwordCreateOutput.setValue("Password:");
        htmlPanelGrid.getChildren().add(passwordCreateOutput);
        
        InputTextarea passwordCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        passwordCreateInput.setId("passwordCreateInput");
        passwordCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.password}", String.class));
        LengthValidator passwordCreateInputValidator = new LengthValidator();
        passwordCreateInputValidator.setMaximum(64);
        passwordCreateInput.addValidator(passwordCreateInputValidator);
        passwordCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(passwordCreateInput);
        
        Message passwordCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        passwordCreateInputMessage.setId("passwordCreateInputMessage");
        passwordCreateInputMessage.setFor("passwordCreateInput");
        passwordCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(passwordCreateInputMessage);
        
        HtmlOutputText rolesCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        rolesCreateOutput.setId("rolesCreateOutput");
        rolesCreateOutput.setValue("Roles:");
        htmlPanelGrid.getChildren().add(rolesCreateOutput);
        
        HtmlOutputText rolesCreateInput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        rolesCreateInput.setId("rolesCreateInput");
        rolesCreateInput.setValue("This relationship is managed from the AppRole side");
        htmlPanelGrid.getChildren().add(rolesCreateInput);
        
        Message rolesCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        rolesCreateInputMessage.setId("rolesCreateInputMessage");
        rolesCreateInputMessage.setFor("rolesCreateInput");
        rolesCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(rolesCreateInputMessage);
        
        OutputLabel userStatusCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        userStatusCreateOutput.setFor("userStatusCreateInput");
        userStatusCreateOutput.setId("userStatusCreateOutput");
        userStatusCreateOutput.setValue("User Status:");
        htmlPanelGrid.getChildren().add(userStatusCreateOutput);
        
        AutoComplete userStatusCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        userStatusCreateInput.setId("userStatusCreateInput");
        userStatusCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.userStatus}", UserStatus.class));
        userStatusCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{appUserBean.completeUserStatus}", List.class, new Class[] { String.class }));
        userStatusCreateInput.setDropdown(true);
        userStatusCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(userStatusCreateInput);
        
        Message userStatusCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        userStatusCreateInputMessage.setId("userStatusCreateInputMessage");
        userStatusCreateInputMessage.setFor("userStatusCreateInput");
        userStatusCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(userStatusCreateInputMessage);
        
        OutputLabel loginAttemptsCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        loginAttemptsCreateOutput.setFor("loginAttemptsCreateInput");
        loginAttemptsCreateOutput.setId("loginAttemptsCreateOutput");
        loginAttemptsCreateOutput.setValue("Login Attempts:");
        htmlPanelGrid.getChildren().add(loginAttemptsCreateOutput);
        
        Spinner loginAttemptsCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        loginAttemptsCreateInput.setId("loginAttemptsCreateInput");
        loginAttemptsCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.loginAttempts}", Integer.class));
        loginAttemptsCreateInput.setRequired(false);
        
        htmlPanelGrid.getChildren().add(loginAttemptsCreateInput);
        
        Message loginAttemptsCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        loginAttemptsCreateInputMessage.setId("loginAttemptsCreateInputMessage");
        loginAttemptsCreateInputMessage.setFor("loginAttemptsCreateInput");
        loginAttemptsCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(loginAttemptsCreateInputMessage);
        
        OutputLabel lastLoginDateCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        lastLoginDateCreateOutput.setFor("lastLoginDateCreateInput");
        lastLoginDateCreateOutput.setId("lastLoginDateCreateOutput");
        lastLoginDateCreateOutput.setValue("Last Login Date:");
        htmlPanelGrid.getChildren().add(lastLoginDateCreateOutput);
        
        Calendar lastLoginDateCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        lastLoginDateCreateInput.setId("lastLoginDateCreateInput");
        lastLoginDateCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.lastLoginDate}", Date.class));
        lastLoginDateCreateInput.setNavigator(true);
        lastLoginDateCreateInput.setEffect("slideDown");
        lastLoginDateCreateInput.setPattern("dd/MM/yyyy");
        lastLoginDateCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(lastLoginDateCreateInput);
        
        Message lastLoginDateCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        lastLoginDateCreateInputMessage.setId("lastLoginDateCreateInputMessage");
        lastLoginDateCreateInputMessage.setFor("lastLoginDateCreateInput");
        lastLoginDateCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(lastLoginDateCreateInputMessage);
        
        OutputLabel daysToExpireCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        daysToExpireCreateOutput.setFor("daysToExpireCreateInput");
        daysToExpireCreateOutput.setId("daysToExpireCreateOutput");
        daysToExpireCreateOutput.setValue("Days To Expire:");
        htmlPanelGrid.getChildren().add(daysToExpireCreateOutput);
        
        Spinner daysToExpireCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        daysToExpireCreateInput.setId("daysToExpireCreateInput");
        daysToExpireCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.daysToExpire}", Integer.class));
        daysToExpireCreateInput.setRequired(false);
        
        htmlPanelGrid.getChildren().add(daysToExpireCreateInput);
        
        Message daysToExpireCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        daysToExpireCreateInputMessage.setId("daysToExpireCreateInputMessage");
        daysToExpireCreateInputMessage.setFor("daysToExpireCreateInput");
        daysToExpireCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(daysToExpireCreateInputMessage);
        
        return htmlPanelGrid;
    }
	
	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel deletedEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        deletedEditOutput.setFor("deletedEditInput");
        deletedEditOutput.setId("deletedEditOutput");
        deletedEditOutput.setValue("Deleted:");
        htmlPanelGrid.getChildren().add(deletedEditOutput);
        
        SelectBooleanCheckbox deletedEditInput = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
        deletedEditInput.setId("deletedEditInput");
        deletedEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.deleted}", Boolean.class));
        deletedEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(deletedEditInput);
        
        Message deletedEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        deletedEditInputMessage.setId("deletedEditInputMessage");
        deletedEditInputMessage.setFor("deletedEditInput");
        deletedEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(deletedEditInputMessage);
        
        OutputLabel creationDateEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        creationDateEditOutput.setFor("creationDateEditInput");
        creationDateEditOutput.setId("creationDateEditOutput");
        creationDateEditOutput.setValue("Creation Date:");
        htmlPanelGrid.getChildren().add(creationDateEditOutput);
        
        Calendar creationDateEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        creationDateEditInput.setId("creationDateEditInput");
        creationDateEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.creationDate}", Date.class));
        creationDateEditInput.setNavigator(true);
        creationDateEditInput.setEffect("slideDown");
        creationDateEditInput.setPattern("dd/MM/yyyy");
        creationDateEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(creationDateEditInput);
        
        Message creationDateEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        creationDateEditInputMessage.setId("creationDateEditInputMessage");
        creationDateEditInputMessage.setFor("creationDateEditInput");
        creationDateEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(creationDateEditInputMessage);
        
        OutputLabel updateDateEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        updateDateEditOutput.setFor("updateDateEditInput");
        updateDateEditOutput.setId("updateDateEditOutput");
        updateDateEditOutput.setValue("Update Date:");
        htmlPanelGrid.getChildren().add(updateDateEditOutput);
        
        Calendar updateDateEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        updateDateEditInput.setId("updateDateEditInput");
        updateDateEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.updateDate}", Date.class));
        updateDateEditInput.setNavigator(true);
        updateDateEditInput.setEffect("slideDown");
        updateDateEditInput.setPattern("dd/MM/yyyy");
        updateDateEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(updateDateEditInput);
        
        Message updateDateEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        updateDateEditInputMessage.setId("updateDateEditInputMessage");
        updateDateEditInputMessage.setFor("updateDateEditInput");
        updateDateEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(updateDateEditInputMessage);
        
        OutputLabel authoritiesEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        authoritiesEditOutput.setFor("authoritiesEditInput");
        authoritiesEditOutput.setId("authoritiesEditOutput");
        authoritiesEditOutput.setValue("Authorities:");
        htmlPanelGrid.getChildren().add(authoritiesEditOutput);
        
        InputText authoritiesEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        authoritiesEditInput.setId("authoritiesEditInput");
        authoritiesEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.authorities}", Set.class));
        authoritiesEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(authoritiesEditInput);
        
        Message authoritiesEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        authoritiesEditInputMessage.setId("authoritiesEditInputMessage");
        authoritiesEditInputMessage.setFor("authoritiesEditInput");
        authoritiesEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(authoritiesEditInputMessage);
        
        OutputLabel personEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        personEditOutput.setFor("personEditInput");
        personEditOutput.setId("personEditOutput");
        personEditOutput.setValue("Person:");
        htmlPanelGrid.getChildren().add(personEditOutput);
        
        AutoComplete personEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        personEditInput.setId("personEditInput");
        personEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.person}", Person.class));
        personEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{appUserBean.completePerson}", List.class, new Class[] { String.class }));
        personEditInput.setDropdown(true);
        personEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "person", String.class));
        personEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{person.creationDate} #{person.updateDate} #{person.names} #{person.surnames}", String.class));
        personEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{person}", Person.class));
        personEditInput.setConverter(new PersonConverter());
        personEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(personEditInput);
        
        Message personEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        personEditInputMessage.setId("personEditInputMessage");
        personEditInputMessage.setFor("personEditInput");
        personEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(personEditInputMessage);
        
        OutputLabel usernameEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        usernameEditOutput.setFor("usernameEditInput");
        usernameEditOutput.setId("usernameEditOutput");
        usernameEditOutput.setValue("Username:");
        htmlPanelGrid.getChildren().add(usernameEditOutput);
        
        InputTextarea usernameEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        usernameEditInput.setId("usernameEditInput");
        usernameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.username}", String.class));
        LengthValidator usernameEditInputValidator = new LengthValidator();
        usernameEditInputValidator.setMaximum(32);
        usernameEditInput.addValidator(usernameEditInputValidator);
        usernameEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(usernameEditInput);
        
        Message usernameEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        usernameEditInputMessage.setId("usernameEditInputMessage");
        usernameEditInputMessage.setFor("usernameEditInput");
        usernameEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(usernameEditInputMessage);
        
        OutputLabel passwordEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        passwordEditOutput.setFor("passwordEditInput");
        passwordEditOutput.setId("passwordEditOutput");
        passwordEditOutput.setValue("Password:");
        htmlPanelGrid.getChildren().add(passwordEditOutput);
        
        InputTextarea passwordEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        passwordEditInput.setId("passwordEditInput");
        passwordEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.password}", String.class));
        LengthValidator passwordEditInputValidator = new LengthValidator();
        passwordEditInputValidator.setMaximum(64);
        passwordEditInput.addValidator(passwordEditInputValidator);
        passwordEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(passwordEditInput);
        
        Message passwordEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        passwordEditInputMessage.setId("passwordEditInputMessage");
        passwordEditInputMessage.setFor("passwordEditInput");
        passwordEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(passwordEditInputMessage);
        
        HtmlOutputText rolesEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        rolesEditOutput.setId("rolesEditOutput");
        rolesEditOutput.setValue("Roles:");
        htmlPanelGrid.getChildren().add(rolesEditOutput);
        
        HtmlOutputText rolesEditInput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        rolesEditInput.setId("rolesEditInput");
        rolesEditInput.setValue("This relationship is managed from the AppRole side");
        htmlPanelGrid.getChildren().add(rolesEditInput);
        
        Message rolesEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        rolesEditInputMessage.setId("rolesEditInputMessage");
        rolesEditInputMessage.setFor("rolesEditInput");
        rolesEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(rolesEditInputMessage);
        
        OutputLabel userStatusEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        userStatusEditOutput.setFor("userStatusEditInput");
        userStatusEditOutput.setId("userStatusEditOutput");
        userStatusEditOutput.setValue("User Status:");
        htmlPanelGrid.getChildren().add(userStatusEditOutput);
        
        AutoComplete userStatusEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        userStatusEditInput.setId("userStatusEditInput");
        userStatusEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.userStatus}", UserStatus.class));
        userStatusEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{appUserBean.completeUserStatus}", List.class, new Class[] { String.class }));
        userStatusEditInput.setDropdown(true);
        userStatusEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(userStatusEditInput);
        
        Message userStatusEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        userStatusEditInputMessage.setId("userStatusEditInputMessage");
        userStatusEditInputMessage.setFor("userStatusEditInput");
        userStatusEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(userStatusEditInputMessage);
        
        OutputLabel loginAttemptsEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        loginAttemptsEditOutput.setFor("loginAttemptsEditInput");
        loginAttemptsEditOutput.setId("loginAttemptsEditOutput");
        loginAttemptsEditOutput.setValue("Login Attempts:");
        htmlPanelGrid.getChildren().add(loginAttemptsEditOutput);
        
        Spinner loginAttemptsEditInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        loginAttemptsEditInput.setId("loginAttemptsEditInput");
        loginAttemptsEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.loginAttempts}", Integer.class));
        loginAttemptsEditInput.setRequired(false);
        
        htmlPanelGrid.getChildren().add(loginAttemptsEditInput);
        
        Message loginAttemptsEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        loginAttemptsEditInputMessage.setId("loginAttemptsEditInputMessage");
        loginAttemptsEditInputMessage.setFor("loginAttemptsEditInput");
        loginAttemptsEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(loginAttemptsEditInputMessage);
        
        OutputLabel lastLoginDateEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        lastLoginDateEditOutput.setFor("lastLoginDateEditInput");
        lastLoginDateEditOutput.setId("lastLoginDateEditOutput");
        lastLoginDateEditOutput.setValue("Last Login Date:");
        htmlPanelGrid.getChildren().add(lastLoginDateEditOutput);
        
        Calendar lastLoginDateEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        lastLoginDateEditInput.setId("lastLoginDateEditInput");
        lastLoginDateEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.lastLoginDate}", Date.class));
        lastLoginDateEditInput.setNavigator(true);
        lastLoginDateEditInput.setEffect("slideDown");
        lastLoginDateEditInput.setPattern("dd/MM/yyyy");
        lastLoginDateEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(lastLoginDateEditInput);
        
        Message lastLoginDateEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        lastLoginDateEditInputMessage.setId("lastLoginDateEditInputMessage");
        lastLoginDateEditInputMessage.setFor("lastLoginDateEditInput");
        lastLoginDateEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(lastLoginDateEditInputMessage);
        
        OutputLabel daysToExpireEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        daysToExpireEditOutput.setFor("daysToExpireEditInput");
        daysToExpireEditOutput.setId("daysToExpireEditOutput");
        daysToExpireEditOutput.setValue("Days To Expire:");
        htmlPanelGrid.getChildren().add(daysToExpireEditOutput);
        
        Spinner daysToExpireEditInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        daysToExpireEditInput.setId("daysToExpireEditInput");
        daysToExpireEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.daysToExpire}", Integer.class));
        daysToExpireEditInput.setRequired(false);
        
        htmlPanelGrid.getChildren().add(daysToExpireEditInput);
        
        Message daysToExpireEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        daysToExpireEditInputMessage.setId("daysToExpireEditInputMessage");
        daysToExpireEditInputMessage.setFor("daysToExpireEditInput");
        daysToExpireEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(daysToExpireEditInputMessage);
        
        return htmlPanelGrid;
    }
	
	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel deletedEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        deletedEditOutput.setFor("deletedEditInput");
        deletedEditOutput.setId("deletedEditOutput");
        deletedEditOutput.setValue("Deleted:");
        htmlPanelGrid.getChildren().add(deletedEditOutput);
        
        SelectBooleanCheckbox deletedEditInput = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
        deletedEditInput.setId("deletedEditInput");
        deletedEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.deleted}", Boolean.class));
        deletedEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(deletedEditInput);
        
        Message deletedEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        deletedEditInputMessage.setId("deletedEditInputMessage");
        deletedEditInputMessage.setFor("deletedEditInput");
        deletedEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(deletedEditInputMessage);
        
        OutputLabel creationDateEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        creationDateEditOutput.setFor("creationDateEditInput");
        creationDateEditOutput.setId("creationDateEditOutput");
        creationDateEditOutput.setValue("Creation Date:");
        htmlPanelGrid.getChildren().add(creationDateEditOutput);
        
        Calendar creationDateEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        creationDateEditInput.setId("creationDateEditInput");
        creationDateEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.creationDate}", Date.class));
        creationDateEditInput.setNavigator(true);
        creationDateEditInput.setEffect("slideDown");
        creationDateEditInput.setPattern("dd/MM/yyyy");
        creationDateEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(creationDateEditInput);
        
        Message creationDateEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        creationDateEditInputMessage.setId("creationDateEditInputMessage");
        creationDateEditInputMessage.setFor("creationDateEditInput");
        creationDateEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(creationDateEditInputMessage);
        
        OutputLabel updateDateEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        updateDateEditOutput.setFor("updateDateEditInput");
        updateDateEditOutput.setId("updateDateEditOutput");
        updateDateEditOutput.setValue("Update Date:");
        htmlPanelGrid.getChildren().add(updateDateEditOutput);
        
        Calendar updateDateEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        updateDateEditInput.setId("updateDateEditInput");
        updateDateEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.updateDate}", Date.class));
        updateDateEditInput.setNavigator(true);
        updateDateEditInput.setEffect("slideDown");
        updateDateEditInput.setPattern("dd/MM/yyyy");
        updateDateEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(updateDateEditInput);
        
        Message updateDateEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        updateDateEditInputMessage.setId("updateDateEditInputMessage");
        updateDateEditInputMessage.setFor("updateDateEditInput");
        updateDateEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(updateDateEditInputMessage);
        
        OutputLabel authoritiesEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        authoritiesEditOutput.setFor("authoritiesEditInput");
        authoritiesEditOutput.setId("authoritiesEditOutput");
        authoritiesEditOutput.setValue("Authorities:");
        htmlPanelGrid.getChildren().add(authoritiesEditOutput);
        
        InputText authoritiesEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        authoritiesEditInput.setId("authoritiesEditInput");
        authoritiesEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.authorities}", Set.class));
        authoritiesEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(authoritiesEditInput);
        
        Message authoritiesEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        authoritiesEditInputMessage.setId("authoritiesEditInputMessage");
        authoritiesEditInputMessage.setFor("authoritiesEditInput");
        authoritiesEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(authoritiesEditInputMessage);
        
        OutputLabel personEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        personEditOutput.setFor("personEditInput");
        personEditOutput.setId("personEditOutput");
        personEditOutput.setValue("Person:");
        htmlPanelGrid.getChildren().add(personEditOutput);
        
        AutoComplete personEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        personEditInput.setId("personEditInput");
        personEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.person}", Person.class));
        personEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{appUserBean.completePerson}", List.class, new Class[] { String.class }));
        personEditInput.setDropdown(true);
        personEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "person", String.class));
        personEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{person.creationDate} #{person.updateDate} #{person.names} #{person.surnames}", String.class));
        personEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{person}", Person.class));
        personEditInput.setConverter(new PersonConverter());
        personEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(personEditInput);
        
        Message personEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        personEditInputMessage.setId("personEditInputMessage");
        personEditInputMessage.setFor("personEditInput");
        personEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(personEditInputMessage);
        
        OutputLabel usernameEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        usernameEditOutput.setFor("usernameEditInput");
        usernameEditOutput.setId("usernameEditOutput");
        usernameEditOutput.setValue("Username:");
        htmlPanelGrid.getChildren().add(usernameEditOutput);
        
        InputTextarea usernameEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        usernameEditInput.setId("usernameEditInput");
        usernameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.username}", String.class));
        LengthValidator usernameEditInputValidator = new LengthValidator();
        usernameEditInputValidator.setMaximum(32);
        usernameEditInput.addValidator(usernameEditInputValidator);
        usernameEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(usernameEditInput);
        
        Message usernameEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        usernameEditInputMessage.setId("usernameEditInputMessage");
        usernameEditInputMessage.setFor("usernameEditInput");
        usernameEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(usernameEditInputMessage);
        
        OutputLabel passwordEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        passwordEditOutput.setFor("passwordEditInput");
        passwordEditOutput.setId("passwordEditOutput");
        passwordEditOutput.setValue("Password:");
        htmlPanelGrid.getChildren().add(passwordEditOutput);
        
        InputTextarea passwordEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        passwordEditInput.setId("passwordEditInput");
        passwordEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.password}", String.class));
        LengthValidator passwordEditInputValidator = new LengthValidator();
        passwordEditInputValidator.setMaximum(64);
        passwordEditInput.addValidator(passwordEditInputValidator);
        passwordEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(passwordEditInput);
        
        Message passwordEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        passwordEditInputMessage.setId("passwordEditInputMessage");
        passwordEditInputMessage.setFor("passwordEditInput");
        passwordEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(passwordEditInputMessage);
        
        HtmlOutputText rolesEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        rolesEditOutput.setId("rolesEditOutput");
        rolesEditOutput.setValue("Roles:");
        htmlPanelGrid.getChildren().add(rolesEditOutput);
        
        HtmlOutputText rolesEditInput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        rolesEditInput.setId("rolesEditInput");
        rolesEditInput.setValue("This relationship is managed from the AppRole side");
        htmlPanelGrid.getChildren().add(rolesEditInput);
        
        Message rolesEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        rolesEditInputMessage.setId("rolesEditInputMessage");
        rolesEditInputMessage.setFor("rolesEditInput");
        rolesEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(rolesEditInputMessage);
        
        OutputLabel userStatusEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        userStatusEditOutput.setFor("userStatusEditInput");
        userStatusEditOutput.setId("userStatusEditOutput");
        userStatusEditOutput.setValue("User Status:");
        htmlPanelGrid.getChildren().add(userStatusEditOutput);
        
        AutoComplete userStatusEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        userStatusEditInput.setId("userStatusEditInput");
        userStatusEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.userStatus}", UserStatus.class));
        userStatusEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{appUserBean.completeUserStatus}", List.class, new Class[] { String.class }));
        userStatusEditInput.setDropdown(true);
        userStatusEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(userStatusEditInput);
        
        Message userStatusEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        userStatusEditInputMessage.setId("userStatusEditInputMessage");
        userStatusEditInputMessage.setFor("userStatusEditInput");
        userStatusEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(userStatusEditInputMessage);
        
        OutputLabel loginAttemptsEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        loginAttemptsEditOutput.setFor("loginAttemptsEditInput");
        loginAttemptsEditOutput.setId("loginAttemptsEditOutput");
        loginAttemptsEditOutput.setValue("Login Attempts:");
        htmlPanelGrid.getChildren().add(loginAttemptsEditOutput);
        
        Spinner loginAttemptsEditInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        loginAttemptsEditInput.setId("loginAttemptsEditInput");
        loginAttemptsEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.loginAttempts}", Integer.class));
        loginAttemptsEditInput.setRequired(false);
        
        htmlPanelGrid.getChildren().add(loginAttemptsEditInput);
        
        Message loginAttemptsEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        loginAttemptsEditInputMessage.setId("loginAttemptsEditInputMessage");
        loginAttemptsEditInputMessage.setFor("loginAttemptsEditInput");
        loginAttemptsEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(loginAttemptsEditInputMessage);
        
        OutputLabel lastLoginDateEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        lastLoginDateEditOutput.setFor("lastLoginDateEditInput");
        lastLoginDateEditOutput.setId("lastLoginDateEditOutput");
        lastLoginDateEditOutput.setValue("Last Login Date:");
        htmlPanelGrid.getChildren().add(lastLoginDateEditOutput);
        
        Calendar lastLoginDateEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        lastLoginDateEditInput.setId("lastLoginDateEditInput");
        lastLoginDateEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.lastLoginDate}", Date.class));
        lastLoginDateEditInput.setNavigator(true);
        lastLoginDateEditInput.setEffect("slideDown");
        lastLoginDateEditInput.setPattern("dd/MM/yyyy");
        lastLoginDateEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(lastLoginDateEditInput);
        
        Message lastLoginDateEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        lastLoginDateEditInputMessage.setId("lastLoginDateEditInputMessage");
        lastLoginDateEditInputMessage.setFor("lastLoginDateEditInput");
        lastLoginDateEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(lastLoginDateEditInputMessage);
        
        OutputLabel daysToExpireEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        daysToExpireEditOutput.setFor("daysToExpireEditInput");
        daysToExpireEditOutput.setId("daysToExpireEditOutput");
        daysToExpireEditOutput.setValue("Days To Expire:");
        htmlPanelGrid.getChildren().add(daysToExpireEditOutput);
        
        Spinner daysToExpireEditInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        daysToExpireEditInput.setId("daysToExpireEditInput");
        daysToExpireEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.daysToExpire}", Integer.class));
        daysToExpireEditInput.setRequired(false);
        
        htmlPanelGrid.getChildren().add(daysToExpireEditInput);
        
        Message daysToExpireEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        daysToExpireEditInputMessage.setId("daysToExpireEditInputMessage");
        daysToExpireEditInputMessage.setFor("daysToExpireEditInput");
        daysToExpireEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(daysToExpireEditInputMessage);
        
        return htmlPanelGrid;
    }
	
	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText deletedLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        deletedLabel.setId("deletedLabel");
        deletedLabel.setValue("Deleted:");
        htmlPanelGrid.getChildren().add(deletedLabel);
        
        HtmlOutputText deletedValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        deletedValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.deleted}", String.class));
        htmlPanelGrid.getChildren().add(deletedValue);
        
        HtmlOutputText creationDateLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        creationDateLabel.setId("creationDateLabel");
        creationDateLabel.setValue("Creation Date:");
        htmlPanelGrid.getChildren().add(creationDateLabel);
        
        HtmlOutputText creationDateValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        creationDateValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.creationDate}", Date.class));
        DateTimeConverter creationDateValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        creationDateValueConverter.setPattern("dd/MM/yyyy");
        creationDateValue.setConverter(creationDateValueConverter);
        htmlPanelGrid.getChildren().add(creationDateValue);
        
        HtmlOutputText updateDateLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        updateDateLabel.setId("updateDateLabel");
        updateDateLabel.setValue("Update Date:");
        htmlPanelGrid.getChildren().add(updateDateLabel);
        
        HtmlOutputText updateDateValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        updateDateValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.updateDate}", Date.class));
        DateTimeConverter updateDateValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        updateDateValueConverter.setPattern("dd/MM/yyyy");
        updateDateValue.setConverter(updateDateValueConverter);
        htmlPanelGrid.getChildren().add(updateDateValue);
        
        HtmlOutputText authoritiesLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        authoritiesLabel.setId("authoritiesLabel");
        authoritiesLabel.setValue("Authorities:");
        htmlPanelGrid.getChildren().add(authoritiesLabel);
        
        HtmlOutputText authoritiesValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        authoritiesValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.authorities}", String.class));
        htmlPanelGrid.getChildren().add(authoritiesValue);
        
        HtmlOutputText personLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        personLabel.setId("personLabel");
        personLabel.setValue("Person:");
        htmlPanelGrid.getChildren().add(personLabel);
        
        HtmlOutputText personValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        personValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.person}", Person.class));
        personValue.setConverter(new PersonConverter());
        htmlPanelGrid.getChildren().add(personValue);
        
        HtmlOutputText usernameLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        usernameLabel.setId("usernameLabel");
        usernameLabel.setValue("Username:");
        htmlPanelGrid.getChildren().add(usernameLabel);
        
        InputTextarea usernameValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        usernameValue.setId("usernameValue");
        usernameValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.username}", String.class));
        usernameValue.setReadonly(true);
        usernameValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(usernameValue);
        
        HtmlOutputText passwordLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        passwordLabel.setId("passwordLabel");
        passwordLabel.setValue("Password:");
        htmlPanelGrid.getChildren().add(passwordLabel);
        
        InputTextarea passwordValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        passwordValue.setId("passwordValue");
        passwordValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.password}", String.class));
        passwordValue.setReadonly(true);
        passwordValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(passwordValue);
        
        HtmlOutputText rolesLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        rolesLabel.setId("rolesLabel");
        rolesLabel.setValue("Roles:");
        htmlPanelGrid.getChildren().add(rolesLabel);
        
        HtmlOutputText rolesValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        rolesValue.setId("rolesValue");
        rolesValue.setValue("This relationship is managed from the AppRole side");
        htmlPanelGrid.getChildren().add(rolesValue);
        
        HtmlOutputText userStatusLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        userStatusLabel.setId("userStatusLabel");
        userStatusLabel.setValue("User Status:");
        htmlPanelGrid.getChildren().add(userStatusLabel);
        
        HtmlOutputText userStatusValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        userStatusValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.userStatus}", String.class));
        htmlPanelGrid.getChildren().add(userStatusValue);
        
        HtmlOutputText loginAttemptsLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        loginAttemptsLabel.setId("loginAttemptsLabel");
        loginAttemptsLabel.setValue("Login Attempts:");
        htmlPanelGrid.getChildren().add(loginAttemptsLabel);
        
        HtmlOutputText loginAttemptsValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        loginAttemptsValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.loginAttempts}", String.class));
        htmlPanelGrid.getChildren().add(loginAttemptsValue);
        
        HtmlOutputText lastLoginDateLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        lastLoginDateLabel.setId("lastLoginDateLabel");
        lastLoginDateLabel.setValue("Last Login Date:");
        htmlPanelGrid.getChildren().add(lastLoginDateLabel);
        
        HtmlOutputText lastLoginDateValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        lastLoginDateValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.lastLoginDate}", Date.class));
        DateTimeConverter lastLoginDateValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        lastLoginDateValueConverter.setPattern("dd/MM/yyyy");
        lastLoginDateValue.setConverter(lastLoginDateValueConverter);
        htmlPanelGrid.getChildren().add(lastLoginDateValue);
        
        HtmlOutputText daysToExpireLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        daysToExpireLabel.setId("daysToExpireLabel");
        daysToExpireLabel.setValue("Days To Expire:");
        htmlPanelGrid.getChildren().add(daysToExpireLabel);
        
        HtmlOutputText daysToExpireValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        daysToExpireValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{appUserBean.appUser.daysToExpire}", String.class));
        htmlPanelGrid.getChildren().add(daysToExpireValue);
        
        return htmlPanelGrid;
    }
	
	public void AppUserBean.setSelectedPhones(List<Phone> selectedPhones) {
        if (selectedPhones != null) {
            appUser.setPhones(new HashSet<Phone>(selectedPhones));
        }
        this.selectedPhones = selectedPhones;
    }
    
    public void AppUserBean.setSelectedEmails(List<Email> selectedEmails) {
        if (selectedEmails != null) {
            appUser.setEmails(new HashSet<Email>(selectedEmails));
        }
        this.selectedEmails = selectedEmails;
    }
    
    public void AppUserBean.setSelectedRoles(List<AppRole> selectedRoles) {
        if (selectedRoles != null) {
            appUser.setRoles(new HashSet<AppRole>(selectedRoles));
        }
        this.selectedRoles = selectedRoles;
    }
    
    public void AppUserBean.handleDialogClose(CloseEvent event) {
        reset();
    }
}
