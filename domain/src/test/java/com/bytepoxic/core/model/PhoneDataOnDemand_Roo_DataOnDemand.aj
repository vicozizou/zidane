// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.model;

import com.bytepoxic.core.model.Person;
import com.bytepoxic.core.model.Phone;
import com.bytepoxic.core.model.PhoneDataOnDemand;
import com.bytepoxic.core.model.PhoneType;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect PhoneDataOnDemand_Roo_DataOnDemand {
    
    declare @type: PhoneDataOnDemand: @Component;
    
    private Random PhoneDataOnDemand.rnd = new SecureRandom();
    
    private List<Phone> PhoneDataOnDemand.data;
    
    public Phone PhoneDataOnDemand.getNewTransientPhone(int index) {
        Phone obj = new Phone();
        setOwner(obj, index);
        setPhoneNumber(obj, index);
        setType(obj, index);
        return obj;
    }
    
    public void PhoneDataOnDemand.setOwner(Phone obj, int index) {
        Person owner = null;
        obj.setOwner(owner);
    }
    
    public void PhoneDataOnDemand.setPhoneNumber(Phone obj, int index) {
        String phoneNumber = "phoneNumber_" + index;
        if (phoneNumber.length() > 64) {
            phoneNumber = phoneNumber.substring(0, 64);
        }
        obj.setPhoneNumber(phoneNumber);
    }
    
    public void PhoneDataOnDemand.setType(Phone obj, int index) {
        PhoneType type = PhoneType.class.getEnumConstants()[0];
        obj.setType(type);
    }
    
    public Phone PhoneDataOnDemand.getSpecificPhone(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Phone obj = data.get(index);
        Long id = obj.getId();
        return Phone.findPhone(id);
    }
    
    public Phone PhoneDataOnDemand.getRandomPhone() {
        init();
        Phone obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Phone.findPhone(id);
    }
    
    public boolean PhoneDataOnDemand.modifyPhone(Phone obj) {
        return false;
    }
    
    public void PhoneDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Phone.findPhoneEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Phone' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Phone>();
        for (int i = 0; i < 10; i++) {
            Phone obj = getNewTransientPhone(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
