// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.model;

import com.bytepoxic.core.model.Person;
import com.bytepoxic.core.model.Phone;
import com.bytepoxic.core.model.PhoneType;

privileged aspect Phone_Roo_JavaBean {
    
    public String Phone.getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void Phone.setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public PhoneType Phone.getType() {
        return this.type;
    }
    
    public void Phone.setType(PhoneType type) {
        this.type = type;
    }
    
    public Person Phone.getOwner() {
        return this.owner;
    }
    
    public void Phone.setOwner(Person owner) {
        this.owner = owner;
    }
    
}
