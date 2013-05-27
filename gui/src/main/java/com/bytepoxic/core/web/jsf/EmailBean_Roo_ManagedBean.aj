// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.web.jsf;

import com.bytepoxic.core.model.Email;
import com.bytepoxic.core.model.EmailType;
import com.bytepoxic.core.model.Person;
import com.bytepoxic.core.web.jsf.EmailBean;
import com.bytepoxic.core.web.jsf.converter.PersonConverter;
import com.bytepoxic.core.web.jsf.util.MessageFactory;
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
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;

privileged aspect EmailBean_Roo_ManagedBean {
    
    declare @type: EmailBean: @ManagedBean(name = "emailBean");
    
    declare @type: EmailBean: @SessionScoped;
    
    private String EmailBean.name = "Emails";
    
    private Email EmailBean.email;
    
    private List<Email> EmailBean.allEmails;
    
    private boolean EmailBean.dataVisible = false;
    
    private List<String> EmailBean.columns;
    
    private HtmlPanelGrid EmailBean.createPanelGrid;
    
    private HtmlPanelGrid EmailBean.editPanelGrid;
    
    private HtmlPanelGrid EmailBean.viewPanelGrid;
    
    private boolean EmailBean.createDialogVisible = false;
    
    @PostConstruct
    public void EmailBean.init() {
        columns = new ArrayList<String>();
        columns.add("emailAddress");
    }
    
    public String EmailBean.getName() {
        return name;
    }
    
    public List<String> EmailBean.getColumns() {
        return columns;
    }
    
    public List<Email> EmailBean.getAllEmails() {
        return allEmails;
    }
    
    public void EmailBean.setAllEmails(List<Email> allEmails) {
        this.allEmails = allEmails;
    }
    
    public String EmailBean.findAllEmails() {
        allEmails = Email.findAllEmails();
        dataVisible = !allEmails.isEmpty();
        return null;
    }
    
    public boolean EmailBean.isDataVisible() {
        return dataVisible;
    }
    
    public void EmailBean.setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }
    
    public HtmlPanelGrid EmailBean.getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }
    
    public void EmailBean.setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }
    
    public HtmlPanelGrid EmailBean.getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }
    
    public void EmailBean.setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }
    
    public HtmlPanelGrid EmailBean.getViewPanelGrid() {
        return populateViewPanel();
    }
    
    public void EmailBean.setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }
    
    public HtmlPanelGrid EmailBean.populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel emailAddressCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        emailAddressCreateOutput.setFor("emailAddressCreateInput");
        emailAddressCreateOutput.setId("emailAddressCreateOutput");
        emailAddressCreateOutput.setValue("Email Address:");
        htmlPanelGrid.getChildren().add(emailAddressCreateOutput);
        
        InputTextarea emailAddressCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        emailAddressCreateInput.setId("emailAddressCreateInput");
        emailAddressCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{emailBean.email.emailAddress}", String.class));
        LengthValidator emailAddressCreateInputValidator = new LengthValidator();
        emailAddressCreateInputValidator.setMaximum(64);
        emailAddressCreateInput.addValidator(emailAddressCreateInputValidator);
        emailAddressCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(emailAddressCreateInput);
        
        Message emailAddressCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        emailAddressCreateInputMessage.setId("emailAddressCreateInputMessage");
        emailAddressCreateInputMessage.setFor("emailAddressCreateInput");
        emailAddressCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(emailAddressCreateInputMessage);
        
        OutputLabel typeCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        typeCreateOutput.setFor("typeCreateInput");
        typeCreateOutput.setId("typeCreateOutput");
        typeCreateOutput.setValue("Type:");
        htmlPanelGrid.getChildren().add(typeCreateOutput);
        
        AutoComplete typeCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        typeCreateInput.setId("typeCreateInput");
        typeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{emailBean.email.type}", EmailType.class));
        typeCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{emailBean.completeType}", List.class, new Class[] { String.class }));
        typeCreateInput.setDropdown(true);
        typeCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(typeCreateInput);
        
        Message typeCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        typeCreateInputMessage.setId("typeCreateInputMessage");
        typeCreateInputMessage.setFor("typeCreateInput");
        typeCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(typeCreateInputMessage);
        
        OutputLabel ownerCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        ownerCreateOutput.setFor("ownerCreateInput");
        ownerCreateOutput.setId("ownerCreateOutput");
        ownerCreateOutput.setValue("Owner:");
        htmlPanelGrid.getChildren().add(ownerCreateOutput);
        
        AutoComplete ownerCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        ownerCreateInput.setId("ownerCreateInput");
        ownerCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{emailBean.email.owner}", Person.class));
        ownerCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{emailBean.completeOwner}", List.class, new Class[] { String.class }));
        ownerCreateInput.setDropdown(true);
        ownerCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "owner", String.class));
        ownerCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{owner.creationDate} #{owner.updateDate} #{owner.names} #{owner.surnames}", String.class));
        ownerCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{owner}", Person.class));
        ownerCreateInput.setConverter(new PersonConverter());
        ownerCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(ownerCreateInput);
        
        Message ownerCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        ownerCreateInputMessage.setId("ownerCreateInputMessage");
        ownerCreateInputMessage.setFor("ownerCreateInput");
        ownerCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(ownerCreateInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid EmailBean.populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel emailAddressEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        emailAddressEditOutput.setFor("emailAddressEditInput");
        emailAddressEditOutput.setId("emailAddressEditOutput");
        emailAddressEditOutput.setValue("Email Address:");
        htmlPanelGrid.getChildren().add(emailAddressEditOutput);
        
        InputTextarea emailAddressEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        emailAddressEditInput.setId("emailAddressEditInput");
        emailAddressEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{emailBean.email.emailAddress}", String.class));
        LengthValidator emailAddressEditInputValidator = new LengthValidator();
        emailAddressEditInputValidator.setMaximum(64);
        emailAddressEditInput.addValidator(emailAddressEditInputValidator);
        emailAddressEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(emailAddressEditInput);
        
        Message emailAddressEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        emailAddressEditInputMessage.setId("emailAddressEditInputMessage");
        emailAddressEditInputMessage.setFor("emailAddressEditInput");
        emailAddressEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(emailAddressEditInputMessage);
        
        OutputLabel typeEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        typeEditOutput.setFor("typeEditInput");
        typeEditOutput.setId("typeEditOutput");
        typeEditOutput.setValue("Type:");
        htmlPanelGrid.getChildren().add(typeEditOutput);
        
        AutoComplete typeEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        typeEditInput.setId("typeEditInput");
        typeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{emailBean.email.type}", EmailType.class));
        typeEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{emailBean.completeType}", List.class, new Class[] { String.class }));
        typeEditInput.setDropdown(true);
        typeEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(typeEditInput);
        
        Message typeEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        typeEditInputMessage.setId("typeEditInputMessage");
        typeEditInputMessage.setFor("typeEditInput");
        typeEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(typeEditInputMessage);
        
        OutputLabel ownerEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        ownerEditOutput.setFor("ownerEditInput");
        ownerEditOutput.setId("ownerEditOutput");
        ownerEditOutput.setValue("Owner:");
        htmlPanelGrid.getChildren().add(ownerEditOutput);
        
        AutoComplete ownerEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        ownerEditInput.setId("ownerEditInput");
        ownerEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{emailBean.email.owner}", Person.class));
        ownerEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{emailBean.completeOwner}", List.class, new Class[] { String.class }));
        ownerEditInput.setDropdown(true);
        ownerEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "owner", String.class));
        ownerEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{owner.creationDate} #{owner.updateDate} #{owner.names} #{owner.surnames}", String.class));
        ownerEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{owner}", Person.class));
        ownerEditInput.setConverter(new PersonConverter());
        ownerEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(ownerEditInput);
        
        Message ownerEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        ownerEditInputMessage.setId("ownerEditInputMessage");
        ownerEditInputMessage.setFor("ownerEditInput");
        ownerEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(ownerEditInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid EmailBean.populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText emailAddressLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        emailAddressLabel.setId("emailAddressLabel");
        emailAddressLabel.setValue("Email Address:");
        htmlPanelGrid.getChildren().add(emailAddressLabel);
        
        InputTextarea emailAddressValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        emailAddressValue.setId("emailAddressValue");
        emailAddressValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{emailBean.email.emailAddress}", String.class));
        emailAddressValue.setReadonly(true);
        emailAddressValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(emailAddressValue);
        
        HtmlOutputText typeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        typeLabel.setId("typeLabel");
        typeLabel.setValue("Type:");
        htmlPanelGrid.getChildren().add(typeLabel);
        
        HtmlOutputText typeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        typeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{emailBean.email.type}", String.class));
        htmlPanelGrid.getChildren().add(typeValue);
        
        HtmlOutputText ownerLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        ownerLabel.setId("ownerLabel");
        ownerLabel.setValue("Owner:");
        htmlPanelGrid.getChildren().add(ownerLabel);
        
        HtmlOutputText ownerValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        ownerValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{emailBean.email.owner}", Person.class));
        ownerValue.setConverter(new PersonConverter());
        htmlPanelGrid.getChildren().add(ownerValue);
        
        return htmlPanelGrid;
    }
    
    public Email EmailBean.getEmail() {
        if (email == null) {
            email = new Email();
        }
        return email;
    }
    
    public void EmailBean.setEmail(Email email) {
        this.email = email;
    }
    
    public List<EmailType> EmailBean.completeType(String query) {
        List<EmailType> suggestions = new ArrayList<EmailType>();
        for (EmailType emailType : EmailType.values()) {
            if (emailType.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(emailType);
            }
        }
        return suggestions;
    }
    
    public List<Person> EmailBean.completeOwner(String query) {
        List<Person> suggestions = new ArrayList<Person>();
        for (Person person : Person.findAllPeople()) {
            String personStr = String.valueOf(person.getCreationDate() +  " "  + person.getUpdateDate() +  " "  + person.getNames() +  " "  + person.getSurnames());
            if (personStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(person);
            }
        }
        return suggestions;
    }
    
    public String EmailBean.onEdit() {
        return null;
    }
    
    public boolean EmailBean.isCreateDialogVisible() {
        return createDialogVisible;
    }
    
    public void EmailBean.setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }
    
    public String EmailBean.displayList() {
        createDialogVisible = false;
        findAllEmails();
        return "email";
    }
    
    public String EmailBean.displayCreateDialog() {
        email = new Email();
        createDialogVisible = true;
        return "email";
    }
    
    public String EmailBean.persist() {
        String message = "";
        if (email.getId() != null) {
            email.merge();
            message = "message_successfully_updated";
        } else {
            email.persist();
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Email");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllEmails();
    }
    
    public String EmailBean.delete() {
        email.remove();
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Email");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllEmails();
    }
    
    public void EmailBean.reset() {
        email = null;
        createDialogVisible = false;
    }
    
    public void EmailBean.handleDialogClose(CloseEvent event) {
        reset();
    }
    
}
