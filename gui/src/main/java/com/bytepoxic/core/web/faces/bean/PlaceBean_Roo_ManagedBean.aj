// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.web.faces.bean;

import com.bytepoxic.core.model.Location;
import com.bytepoxic.core.model.Place;
import com.bytepoxic.core.service.LocationService;
import com.bytepoxic.core.web.faces.bean.PlaceBean;
import com.bytepoxic.core.web.faces.bean.converter.LocationConverter;
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
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.spinner.Spinner;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;

privileged aspect PlaceBean_Roo_ManagedBean {
    
    declare @type: PlaceBean: @ManagedBean(name = "placeBean");
    
    declare @type: PlaceBean: @SessionScoped;
    
    @Autowired
    LocationService PlaceBean.locationService;
    
    private String PlaceBean.name = "Places";
    
    private Place PlaceBean.place;
    
    private List<Place> PlaceBean.allPlaces;
    
    private boolean PlaceBean.dataVisible = false;
    
    private List<String> PlaceBean.columns;
    
    private HtmlPanelGrid PlaceBean.createPanelGrid;
    
    private HtmlPanelGrid PlaceBean.editPanelGrid;
    
    private HtmlPanelGrid PlaceBean.viewPanelGrid;
    
    private boolean PlaceBean.createDialogVisible = false;
    
    @PostConstruct
    public void PlaceBean.init() {
        columns = new ArrayList<String>();
        columns.add("name");
        columns.add("primaryAddress");
        columns.add("secondaryAddress");
        columns.add("latitude");
        columns.add("longitude");
    }
    
    public String PlaceBean.getName() {
        return name;
    }
    
    public List<String> PlaceBean.getColumns() {
        return columns;
    }
    
    public List<Place> PlaceBean.getAllPlaces() {
        return allPlaces;
    }
    
    public void PlaceBean.setAllPlaces(List<Place> allPlaces) {
        this.allPlaces = allPlaces;
    }
    
    public String PlaceBean.findAllPlaces() {
        allPlaces = Place.findAllPlaces();
        dataVisible = !allPlaces.isEmpty();
        return null;
    }
    
    public boolean PlaceBean.isDataVisible() {
        return dataVisible;
    }
    
    public void PlaceBean.setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }
    
    public HtmlPanelGrid PlaceBean.getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }
    
    public void PlaceBean.setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }
    
    public HtmlPanelGrid PlaceBean.getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }
    
    public void PlaceBean.setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }
    
    public HtmlPanelGrid PlaceBean.getViewPanelGrid() {
        return populateViewPanel();
    }
    
    public void PlaceBean.setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }
    
    public HtmlPanelGrid PlaceBean.populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel nameCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        nameCreateOutput.setFor("nameCreateInput");
        nameCreateOutput.setId("nameCreateOutput");
        nameCreateOutput.setValue("Name:");
        htmlPanelGrid.getChildren().add(nameCreateOutput);
        
        InputTextarea nameCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        nameCreateInput.setId("nameCreateInput");
        nameCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.name}", String.class));
        LengthValidator nameCreateInputValidator = new LengthValidator();
        nameCreateInputValidator.setMaximum(64);
        nameCreateInput.addValidator(nameCreateInputValidator);
        nameCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(nameCreateInput);
        
        Message nameCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        nameCreateInputMessage.setId("nameCreateInputMessage");
        nameCreateInputMessage.setFor("nameCreateInput");
        nameCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(nameCreateInputMessage);
        
        OutputLabel locationCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        locationCreateOutput.setFor("locationCreateInput");
        locationCreateOutput.setId("locationCreateOutput");
        locationCreateOutput.setValue("Location:");
        htmlPanelGrid.getChildren().add(locationCreateOutput);
        
        AutoComplete locationCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        locationCreateInput.setId("locationCreateInput");
        locationCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.location}", Location.class));
        locationCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{placeBean.completeLocation}", List.class, new Class[] { String.class }));
        locationCreateInput.setDropdown(true);
        locationCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "location", String.class));
        locationCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{location.creationDate} #{location.updateDate} #{location.name} #{location.code}", String.class));
        locationCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{location}", Location.class));
        locationCreateInput.setConverter(new LocationConverter());
        locationCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(locationCreateInput);
        
        Message locationCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        locationCreateInputMessage.setId("locationCreateInputMessage");
        locationCreateInputMessage.setFor("locationCreateInput");
        locationCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(locationCreateInputMessage);
        
        OutputLabel primaryAddressCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        primaryAddressCreateOutput.setFor("primaryAddressCreateInput");
        primaryAddressCreateOutput.setId("primaryAddressCreateOutput");
        primaryAddressCreateOutput.setValue("Primary Address:");
        htmlPanelGrid.getChildren().add(primaryAddressCreateOutput);
        
        InputTextarea primaryAddressCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        primaryAddressCreateInput.setId("primaryAddressCreateInput");
        primaryAddressCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.primaryAddress}", String.class));
        LengthValidator primaryAddressCreateInputValidator = new LengthValidator();
        primaryAddressCreateInputValidator.setMaximum(256);
        primaryAddressCreateInput.addValidator(primaryAddressCreateInputValidator);
        primaryAddressCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(primaryAddressCreateInput);
        
        Message primaryAddressCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        primaryAddressCreateInputMessage.setId("primaryAddressCreateInputMessage");
        primaryAddressCreateInputMessage.setFor("primaryAddressCreateInput");
        primaryAddressCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(primaryAddressCreateInputMessage);
        
        OutputLabel secondaryAddressCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        secondaryAddressCreateOutput.setFor("secondaryAddressCreateInput");
        secondaryAddressCreateOutput.setId("secondaryAddressCreateOutput");
        secondaryAddressCreateOutput.setValue("Secondary Address:");
        htmlPanelGrid.getChildren().add(secondaryAddressCreateOutput);
        
        InputTextarea secondaryAddressCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        secondaryAddressCreateInput.setId("secondaryAddressCreateInput");
        secondaryAddressCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.secondaryAddress}", String.class));
        LengthValidator secondaryAddressCreateInputValidator = new LengthValidator();
        secondaryAddressCreateInputValidator.setMaximum(256);
        secondaryAddressCreateInput.addValidator(secondaryAddressCreateInputValidator);
        secondaryAddressCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(secondaryAddressCreateInput);
        
        Message secondaryAddressCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        secondaryAddressCreateInputMessage.setId("secondaryAddressCreateInputMessage");
        secondaryAddressCreateInputMessage.setFor("secondaryAddressCreateInput");
        secondaryAddressCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(secondaryAddressCreateInputMessage);
        
        OutputLabel latitudeCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        latitudeCreateOutput.setFor("latitudeCreateInput");
        latitudeCreateOutput.setId("latitudeCreateOutput");
        latitudeCreateOutput.setValue("Latitude:");
        htmlPanelGrid.getChildren().add(latitudeCreateOutput);
        
        InputText latitudeCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        latitudeCreateInput.setId("latitudeCreateInput");
        latitudeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.latitude}", Double.class));
        latitudeCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(latitudeCreateInput);
        
        Message latitudeCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        latitudeCreateInputMessage.setId("latitudeCreateInputMessage");
        latitudeCreateInputMessage.setFor("latitudeCreateInput");
        latitudeCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(latitudeCreateInputMessage);
        
        OutputLabel longitudeCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        longitudeCreateOutput.setFor("longitudeCreateInput");
        longitudeCreateOutput.setId("longitudeCreateOutput");
        longitudeCreateOutput.setValue("Longitude:");
        htmlPanelGrid.getChildren().add(longitudeCreateOutput);
        
        InputText longitudeCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        longitudeCreateInput.setId("longitudeCreateInput");
        longitudeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.longitude}", Double.class));
        longitudeCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(longitudeCreateInput);
        
        Message longitudeCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        longitudeCreateInputMessage.setId("longitudeCreateInputMessage");
        longitudeCreateInputMessage.setFor("longitudeCreateInput");
        longitudeCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(longitudeCreateInputMessage);
        
        OutputLabel zoomCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        zoomCreateOutput.setFor("zoomCreateInput");
        zoomCreateOutput.setId("zoomCreateOutput");
        zoomCreateOutput.setValue("Zoom:");
        htmlPanelGrid.getChildren().add(zoomCreateOutput);
        
        Spinner zoomCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        zoomCreateInput.setId("zoomCreateInput");
        zoomCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.zoom}", Integer.class));
        zoomCreateInput.setRequired(false);
        
        htmlPanelGrid.getChildren().add(zoomCreateInput);
        
        Message zoomCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        zoomCreateInputMessage.setId("zoomCreateInputMessage");
        zoomCreateInputMessage.setFor("zoomCreateInput");
        zoomCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(zoomCreateInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid PlaceBean.populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel nameEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        nameEditOutput.setFor("nameEditInput");
        nameEditOutput.setId("nameEditOutput");
        nameEditOutput.setValue("Name:");
        htmlPanelGrid.getChildren().add(nameEditOutput);
        
        InputTextarea nameEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        nameEditInput.setId("nameEditInput");
        nameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.name}", String.class));
        LengthValidator nameEditInputValidator = new LengthValidator();
        nameEditInputValidator.setMaximum(64);
        nameEditInput.addValidator(nameEditInputValidator);
        nameEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(nameEditInput);
        
        Message nameEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        nameEditInputMessage.setId("nameEditInputMessage");
        nameEditInputMessage.setFor("nameEditInput");
        nameEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(nameEditInputMessage);
        
        OutputLabel locationEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        locationEditOutput.setFor("locationEditInput");
        locationEditOutput.setId("locationEditOutput");
        locationEditOutput.setValue("Location:");
        htmlPanelGrid.getChildren().add(locationEditOutput);
        
        AutoComplete locationEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        locationEditInput.setId("locationEditInput");
        locationEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.location}", Location.class));
        locationEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{placeBean.completeLocation}", List.class, new Class[] { String.class }));
        locationEditInput.setDropdown(true);
        locationEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "location", String.class));
        locationEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{location.creationDate} #{location.updateDate} #{location.name} #{location.code}", String.class));
        locationEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{location}", Location.class));
        locationEditInput.setConverter(new LocationConverter());
        locationEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(locationEditInput);
        
        Message locationEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        locationEditInputMessage.setId("locationEditInputMessage");
        locationEditInputMessage.setFor("locationEditInput");
        locationEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(locationEditInputMessage);
        
        OutputLabel primaryAddressEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        primaryAddressEditOutput.setFor("primaryAddressEditInput");
        primaryAddressEditOutput.setId("primaryAddressEditOutput");
        primaryAddressEditOutput.setValue("Primary Address:");
        htmlPanelGrid.getChildren().add(primaryAddressEditOutput);
        
        InputTextarea primaryAddressEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        primaryAddressEditInput.setId("primaryAddressEditInput");
        primaryAddressEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.primaryAddress}", String.class));
        LengthValidator primaryAddressEditInputValidator = new LengthValidator();
        primaryAddressEditInputValidator.setMaximum(256);
        primaryAddressEditInput.addValidator(primaryAddressEditInputValidator);
        primaryAddressEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(primaryAddressEditInput);
        
        Message primaryAddressEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        primaryAddressEditInputMessage.setId("primaryAddressEditInputMessage");
        primaryAddressEditInputMessage.setFor("primaryAddressEditInput");
        primaryAddressEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(primaryAddressEditInputMessage);
        
        OutputLabel secondaryAddressEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        secondaryAddressEditOutput.setFor("secondaryAddressEditInput");
        secondaryAddressEditOutput.setId("secondaryAddressEditOutput");
        secondaryAddressEditOutput.setValue("Secondary Address:");
        htmlPanelGrid.getChildren().add(secondaryAddressEditOutput);
        
        InputTextarea secondaryAddressEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        secondaryAddressEditInput.setId("secondaryAddressEditInput");
        secondaryAddressEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.secondaryAddress}", String.class));
        LengthValidator secondaryAddressEditInputValidator = new LengthValidator();
        secondaryAddressEditInputValidator.setMaximum(256);
        secondaryAddressEditInput.addValidator(secondaryAddressEditInputValidator);
        secondaryAddressEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(secondaryAddressEditInput);
        
        Message secondaryAddressEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        secondaryAddressEditInputMessage.setId("secondaryAddressEditInputMessage");
        secondaryAddressEditInputMessage.setFor("secondaryAddressEditInput");
        secondaryAddressEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(secondaryAddressEditInputMessage);
        
        OutputLabel latitudeEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        latitudeEditOutput.setFor("latitudeEditInput");
        latitudeEditOutput.setId("latitudeEditOutput");
        latitudeEditOutput.setValue("Latitude:");
        htmlPanelGrid.getChildren().add(latitudeEditOutput);
        
        InputText latitudeEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        latitudeEditInput.setId("latitudeEditInput");
        latitudeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.latitude}", Double.class));
        latitudeEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(latitudeEditInput);
        
        Message latitudeEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        latitudeEditInputMessage.setId("latitudeEditInputMessage");
        latitudeEditInputMessage.setFor("latitudeEditInput");
        latitudeEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(latitudeEditInputMessage);
        
        OutputLabel longitudeEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        longitudeEditOutput.setFor("longitudeEditInput");
        longitudeEditOutput.setId("longitudeEditOutput");
        longitudeEditOutput.setValue("Longitude:");
        htmlPanelGrid.getChildren().add(longitudeEditOutput);
        
        InputText longitudeEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        longitudeEditInput.setId("longitudeEditInput");
        longitudeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.longitude}", Double.class));
        longitudeEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(longitudeEditInput);
        
        Message longitudeEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        longitudeEditInputMessage.setId("longitudeEditInputMessage");
        longitudeEditInputMessage.setFor("longitudeEditInput");
        longitudeEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(longitudeEditInputMessage);
        
        OutputLabel zoomEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        zoomEditOutput.setFor("zoomEditInput");
        zoomEditOutput.setId("zoomEditOutput");
        zoomEditOutput.setValue("Zoom:");
        htmlPanelGrid.getChildren().add(zoomEditOutput);
        
        Spinner zoomEditInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        zoomEditInput.setId("zoomEditInput");
        zoomEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.zoom}", Integer.class));
        zoomEditInput.setRequired(false);
        
        htmlPanelGrid.getChildren().add(zoomEditInput);
        
        Message zoomEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        zoomEditInputMessage.setId("zoomEditInputMessage");
        zoomEditInputMessage.setFor("zoomEditInput");
        zoomEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(zoomEditInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid PlaceBean.populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText nameLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        nameLabel.setId("nameLabel");
        nameLabel.setValue("Name:");
        htmlPanelGrid.getChildren().add(nameLabel);
        
        InputTextarea nameValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        nameValue.setId("nameValue");
        nameValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.name}", String.class));
        nameValue.setReadonly(true);
        nameValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(nameValue);
        
        HtmlOutputText locationLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        locationLabel.setId("locationLabel");
        locationLabel.setValue("Location:");
        htmlPanelGrid.getChildren().add(locationLabel);
        
        HtmlOutputText locationValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        locationValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.location}", Location.class));
        locationValue.setConverter(new LocationConverter());
        htmlPanelGrid.getChildren().add(locationValue);
        
        HtmlOutputText primaryAddressLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        primaryAddressLabel.setId("primaryAddressLabel");
        primaryAddressLabel.setValue("Primary Address:");
        htmlPanelGrid.getChildren().add(primaryAddressLabel);
        
        InputTextarea primaryAddressValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        primaryAddressValue.setId("primaryAddressValue");
        primaryAddressValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.primaryAddress}", String.class));
        primaryAddressValue.setReadonly(true);
        primaryAddressValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(primaryAddressValue);
        
        HtmlOutputText secondaryAddressLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        secondaryAddressLabel.setId("secondaryAddressLabel");
        secondaryAddressLabel.setValue("Secondary Address:");
        htmlPanelGrid.getChildren().add(secondaryAddressLabel);
        
        InputTextarea secondaryAddressValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        secondaryAddressValue.setId("secondaryAddressValue");
        secondaryAddressValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.secondaryAddress}", String.class));
        secondaryAddressValue.setReadonly(true);
        secondaryAddressValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(secondaryAddressValue);
        
        HtmlOutputText latitudeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        latitudeLabel.setId("latitudeLabel");
        latitudeLabel.setValue("Latitude:");
        htmlPanelGrid.getChildren().add(latitudeLabel);
        
        HtmlOutputText latitudeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        latitudeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.latitude}", String.class));
        htmlPanelGrid.getChildren().add(latitudeValue);
        
        HtmlOutputText longitudeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        longitudeLabel.setId("longitudeLabel");
        longitudeLabel.setValue("Longitude:");
        htmlPanelGrid.getChildren().add(longitudeLabel);
        
        HtmlOutputText longitudeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        longitudeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.longitude}", String.class));
        htmlPanelGrid.getChildren().add(longitudeValue);
        
        HtmlOutputText zoomLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        zoomLabel.setId("zoomLabel");
        zoomLabel.setValue("Zoom:");
        htmlPanelGrid.getChildren().add(zoomLabel);
        
        HtmlOutputText zoomValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        zoomValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{placeBean.place.zoom}", String.class));
        htmlPanelGrid.getChildren().add(zoomValue);
        
        return htmlPanelGrid;
    }
    
    public Place PlaceBean.getPlace() {
        if (place == null) {
            place = new Place();
        }
        return place;
    }
    
    public void PlaceBean.setPlace(Place place) {
        this.place = place;
    }
    
    public List<Location> PlaceBean.completeLocation(String query) {
        List<Location> suggestions = new ArrayList<Location>();
        for (Location location : locationService.findAllLocations()) {
            String locationStr = String.valueOf(location.getCreationDate() +  " "  + location.getUpdateDate() +  " "  + location.getName() +  " "  + location.getCode());
            if (locationStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(location);
            }
        }
        return suggestions;
    }
    
    public String PlaceBean.onEdit() {
        return null;
    }
    
    public boolean PlaceBean.isCreateDialogVisible() {
        return createDialogVisible;
    }
    
    public void PlaceBean.setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }
    
    public String PlaceBean.displayList() {
        createDialogVisible = false;
        findAllPlaces();
        return "place";
    }
    
    public String PlaceBean.displayCreateDialog() {
        place = new Place();
        createDialogVisible = true;
        return "place";
    }
    
    public String PlaceBean.persist() {
        String message = "";
        if (place.getId() != null) {
            place.merge();
            message = "message_successfully_updated";
        } else {
            place.persist();
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Place");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllPlaces();
    }
    
    public String PlaceBean.delete() {
        place.remove();
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Place");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllPlaces();
    }
    
    public void PlaceBean.reset() {
        place = null;
        createDialogVisible = false;
    }
    
    public void PlaceBean.handleDialogClose(CloseEvent event) {
        reset();
    }
    
}
