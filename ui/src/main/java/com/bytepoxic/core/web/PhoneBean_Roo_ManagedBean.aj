// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.web;

import com.bytepoxic.core.dao.PersonDAO;
import com.bytepoxic.core.dao.PhoneDAO;
import com.bytepoxic.core.model.Person;
import com.bytepoxic.core.model.Phone;
import com.bytepoxic.core.model.PhoneType;
import com.bytepoxic.core.web.PhoneBean;
import com.bytepoxic.core.web.converter.PersonConverter;
import com.bytepoxic.core.web.util.MessageFactory;
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
import org.springframework.beans.factory.annotation.Autowired;

privileged aspect PhoneBean_Roo_ManagedBean {
    
    declare @type: PhoneBean: @ManagedBean(name = "phoneBean");
    
    declare @type: PhoneBean: @SessionScoped;
    
    @Autowired
    PhoneDAO PhoneBean.phoneDAO;
    
    @Autowired
    PersonDAO PhoneBean.personDAO;
    
    private String PhoneBean.name = "Phones";
    
    private Phone PhoneBean.phone;
    
    private List<Phone> PhoneBean.allPhones;
    
    private boolean PhoneBean.dataVisible = false;
    
    private List<String> PhoneBean.columns;
    
    private HtmlPanelGrid PhoneBean.createPanelGrid;
    
    private HtmlPanelGrid PhoneBean.editPanelGrid;
    
    private HtmlPanelGrid PhoneBean.viewPanelGrid;
    
    private boolean PhoneBean.createDialogVisible = false;
    
    @PostConstruct
    public void PhoneBean.init() {
        columns = new ArrayList<String>();
        columns.add("phoneNumber");
    }
    
    public String PhoneBean.getName() {
        return name;
    }
    
    public List<String> PhoneBean.getColumns() {
        return columns;
    }
    
    public List<Phone> PhoneBean.getAllPhones() {
        return allPhones;
    }
    
    public void PhoneBean.setAllPhones(List<Phone> allPhones) {
        this.allPhones = allPhones;
    }
    
    public String PhoneBean.findAllPhones() {
        allPhones = phoneDAO.findAll();
        dataVisible = !allPhones.isEmpty();
        return null;
    }
    
    public boolean PhoneBean.isDataVisible() {
        return dataVisible;
    }
    
    public void PhoneBean.setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }
    
    public HtmlPanelGrid PhoneBean.getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }
    
    public void PhoneBean.setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }
    
    public HtmlPanelGrid PhoneBean.getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }
    
    public void PhoneBean.setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }
    
    public HtmlPanelGrid PhoneBean.getViewPanelGrid() {
        return populateViewPanel();
    }
    
    public void PhoneBean.setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }
    
    public HtmlPanelGrid PhoneBean.populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel phoneNumberCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        phoneNumberCreateOutput.setFor("phoneNumberCreateInput");
        phoneNumberCreateOutput.setId("phoneNumberCreateOutput");
        phoneNumberCreateOutput.setValue("Phone Number:");
        htmlPanelGrid.getChildren().add(phoneNumberCreateOutput);
        
        InputTextarea phoneNumberCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        phoneNumberCreateInput.setId("phoneNumberCreateInput");
        phoneNumberCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{phoneBean.phone.phoneNumber}", String.class));
        LengthValidator phoneNumberCreateInputValidator = new LengthValidator();
        phoneNumberCreateInputValidator.setMaximum(64);
        phoneNumberCreateInput.addValidator(phoneNumberCreateInputValidator);
        phoneNumberCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(phoneNumberCreateInput);
        
        Message phoneNumberCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        phoneNumberCreateInputMessage.setId("phoneNumberCreateInputMessage");
        phoneNumberCreateInputMessage.setFor("phoneNumberCreateInput");
        phoneNumberCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(phoneNumberCreateInputMessage);
        
        OutputLabel typeCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        typeCreateOutput.setFor("typeCreateInput");
        typeCreateOutput.setId("typeCreateOutput");
        typeCreateOutput.setValue("Type:");
        htmlPanelGrid.getChildren().add(typeCreateOutput);
        
        AutoComplete typeCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        typeCreateInput.setId("typeCreateInput");
        typeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{phoneBean.phone.type}", PhoneType.class));
        typeCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{phoneBean.completeType}", List.class, new Class[] { String.class }));
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
        ownerCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{phoneBean.phone.owner}", Person.class));
        ownerCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{phoneBean.completeOwner}", List.class, new Class[] { String.class }));
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
    
    public HtmlPanelGrid PhoneBean.populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel phoneNumberEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        phoneNumberEditOutput.setFor("phoneNumberEditInput");
        phoneNumberEditOutput.setId("phoneNumberEditOutput");
        phoneNumberEditOutput.setValue("Phone Number:");
        htmlPanelGrid.getChildren().add(phoneNumberEditOutput);
        
        InputTextarea phoneNumberEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        phoneNumberEditInput.setId("phoneNumberEditInput");
        phoneNumberEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{phoneBean.phone.phoneNumber}", String.class));
        LengthValidator phoneNumberEditInputValidator = new LengthValidator();
        phoneNumberEditInputValidator.setMaximum(64);
        phoneNumberEditInput.addValidator(phoneNumberEditInputValidator);
        phoneNumberEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(phoneNumberEditInput);
        
        Message phoneNumberEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        phoneNumberEditInputMessage.setId("phoneNumberEditInputMessage");
        phoneNumberEditInputMessage.setFor("phoneNumberEditInput");
        phoneNumberEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(phoneNumberEditInputMessage);
        
        OutputLabel typeEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        typeEditOutput.setFor("typeEditInput");
        typeEditOutput.setId("typeEditOutput");
        typeEditOutput.setValue("Type:");
        htmlPanelGrid.getChildren().add(typeEditOutput);
        
        AutoComplete typeEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        typeEditInput.setId("typeEditInput");
        typeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{phoneBean.phone.type}", PhoneType.class));
        typeEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{phoneBean.completeType}", List.class, new Class[] { String.class }));
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
        ownerEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{phoneBean.phone.owner}", Person.class));
        ownerEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{phoneBean.completeOwner}", List.class, new Class[] { String.class }));
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
    
    public HtmlPanelGrid PhoneBean.populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText phoneNumberLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        phoneNumberLabel.setId("phoneNumberLabel");
        phoneNumberLabel.setValue("Phone Number:");
        htmlPanelGrid.getChildren().add(phoneNumberLabel);
        
        InputTextarea phoneNumberValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        phoneNumberValue.setId("phoneNumberValue");
        phoneNumberValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{phoneBean.phone.phoneNumber}", String.class));
        phoneNumberValue.setReadonly(true);
        phoneNumberValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(phoneNumberValue);
        
        HtmlOutputText typeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        typeLabel.setId("typeLabel");
        typeLabel.setValue("Type:");
        htmlPanelGrid.getChildren().add(typeLabel);
        
        HtmlOutputText typeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        typeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{phoneBean.phone.type}", String.class));
        htmlPanelGrid.getChildren().add(typeValue);
        
        HtmlOutputText ownerLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        ownerLabel.setId("ownerLabel");
        ownerLabel.setValue("Owner:");
        htmlPanelGrid.getChildren().add(ownerLabel);
        
        HtmlOutputText ownerValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        ownerValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{phoneBean.phone.owner}", Person.class));
        ownerValue.setConverter(new PersonConverter());
        htmlPanelGrid.getChildren().add(ownerValue);
        
        return htmlPanelGrid;
    }
    
    public Phone PhoneBean.getPhone() {
        if (phone == null) {
            phone = new Phone();
        }
        return phone;
    }
    
    public void PhoneBean.setPhone(Phone phone) {
        this.phone = phone;
    }
    
    public List<PhoneType> PhoneBean.completeType(String query) {
        List<PhoneType> suggestions = new ArrayList<PhoneType>();
        for (PhoneType phoneType : PhoneType.values()) {
            if (phoneType.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(phoneType);
            }
        }
        return suggestions;
    }
    
    public List<Person> PhoneBean.completeOwner(String query) {
        List<Person> suggestions = new ArrayList<Person>();
        for (Person person : personDAO.findAll()) {
            String personStr = String.valueOf(person.getCreationDate() +  " "  + person.getUpdateDate() +  " "  + person.getNames() +  " "  + person.getSurnames());
            if (personStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(person);
            }
        }
        return suggestions;
    }
    
    public String PhoneBean.onEdit() {
        return null;
    }
    
    public boolean PhoneBean.isCreateDialogVisible() {
        return createDialogVisible;
    }
    
    public void PhoneBean.setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }
    
    public String PhoneBean.displayList() {
        createDialogVisible = false;
        findAllPhones();
        return "phone";
    }
    
    public String PhoneBean.displayCreateDialog() {
        phone = new Phone();
        createDialogVisible = true;
        return "phone";
    }
    
    public String PhoneBean.persist() {
        String message = "";
        if (phone.getId() != null) {
            phoneDAO.save(phone);
            message = "message_successfully_updated";
        } else {
            phoneDAO.save(phone);
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Phone");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllPhones();
    }
    
    public String PhoneBean.delete() {
        phoneDAO.delete(phone);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Phone");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllPhones();
    }
    
    public void PhoneBean.reset() {
        phone = null;
        createDialogVisible = false;
    }
    
    public void PhoneBean.handleDialogClose(CloseEvent event) {
        reset();
    }
    
}
