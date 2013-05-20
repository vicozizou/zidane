// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.model;

import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.AppUserDataOnDemand;
import com.bytepoxic.core.model.Gender;
import com.bytepoxic.core.model.IdentificationType;
import com.bytepoxic.core.model.NationalityDataOnDemand;
import com.bytepoxic.core.model.PlaceDataOnDemand;
import com.bytepoxic.core.model.UserStatus;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect AppUserDataOnDemand_Roo_DataOnDemand {
    
    declare @type: AppUserDataOnDemand: @Component;
    
    private Random AppUserDataOnDemand.rnd = new SecureRandom();
    
    private List<AppUser> AppUserDataOnDemand.data;
    
    @Autowired
    PlaceDataOnDemand AppUserDataOnDemand.placeDataOnDemand;
    
    @Autowired
    NationalityDataOnDemand AppUserDataOnDemand.nationalityDataOnDemand;
    
    public AppUser AppUserDataOnDemand.getNewTransientAppUser(int index) {
        AppUser obj = new AppUser();
        setBirthday(obj, index);
        setCreationDate(obj, index);
        setDaysToExpire(obj, index);
        setDeleted(obj, index);
        setFirstnames(obj, index);
        setGender(obj, index);
        setIdentification(obj, index);
        setIdentificationType(obj, index);
        setLastLoginDate(obj, index);
        setLastnames(obj, index);
        setLoginAttempts(obj, index);
        setPassword(obj, index);
        setUpdateDate(obj, index);
        setUserStatus(obj, index);
        setUsername(obj, index);
        return obj;
    }
    
    public void AppUserDataOnDemand.setBirthday(AppUser obj, int index) {
        Date birthday = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setBirthday(birthday);
    }
    
    public void AppUserDataOnDemand.setCreationDate(AppUser obj, int index) {
        Date creationDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreationDate(creationDate);
    }
    
    public void AppUserDataOnDemand.setDaysToExpire(AppUser obj, int index) {
        Integer daysToExpire = new Integer(index);
        obj.setDaysToExpire(daysToExpire);
    }
    
    public void AppUserDataOnDemand.setDeleted(AppUser obj, int index) {
        Boolean deleted = Boolean.TRUE;
        obj.setDeleted(deleted);
    }
    
    public void AppUserDataOnDemand.setFirstnames(AppUser obj, int index) {
        String firstnames = "firstnames_" + index;
        if (firstnames.length() > 128) {
            firstnames = firstnames.substring(0, 128);
        }
        obj.setFirstnames(firstnames);
    }
    
    public void AppUserDataOnDemand.setGender(AppUser obj, int index) {
        Gender gender = Gender.class.getEnumConstants()[0];
        obj.setGender(gender);
    }
    
    public void AppUserDataOnDemand.setIdentification(AppUser obj, int index) {
        String identification = "identification_" + index;
        if (identification.length() > 64) {
            identification = new Random().nextInt(10) + identification.substring(1, 64);
        }
        obj.setIdentification(identification);
    }
    
    public void AppUserDataOnDemand.setIdentificationType(AppUser obj, int index) {
        IdentificationType identificationType = IdentificationType.class.getEnumConstants()[0];
        obj.setIdentificationType(identificationType);
    }
    
    public void AppUserDataOnDemand.setLastLoginDate(AppUser obj, int index) {
        Date lastLoginDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setLastLoginDate(lastLoginDate);
    }
    
    public void AppUserDataOnDemand.setLastnames(AppUser obj, int index) {
        String lastnames = "lastnames_" + index;
        if (lastnames.length() > 128) {
            lastnames = lastnames.substring(0, 128);
        }
        obj.setLastnames(lastnames);
    }
    
    public void AppUserDataOnDemand.setLoginAttempts(AppUser obj, int index) {
        Integer loginAttempts = new Integer(index);
        obj.setLoginAttempts(loginAttempts);
    }
    
    public void AppUserDataOnDemand.setPassword(AppUser obj, int index) {
        String password = "password_" + index;
        if (password.length() > 64) {
            password = password.substring(0, 64);
        }
        obj.setPassword(password);
    }
    
    public void AppUserDataOnDemand.setUpdateDate(AppUser obj, int index) {
        Date updateDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdateDate(updateDate);
    }
    
    public void AppUserDataOnDemand.setUserStatus(AppUser obj, int index) {
        UserStatus userStatus = UserStatus.class.getEnumConstants()[0];
        obj.setUserStatus(userStatus);
    }
    
    public void AppUserDataOnDemand.setUsername(AppUser obj, int index) {
        String username = "username_" + index;
        if (username.length() > 32) {
            username = new Random().nextInt(10) + username.substring(1, 32);
        }
        obj.setUsername(username);
    }
    
    public AppUser AppUserDataOnDemand.getSpecificAppUser(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        AppUser obj = data.get(index);
        Long id = obj.getId();
        return AppUser.findAppUser(id);
    }
    
    public AppUser AppUserDataOnDemand.getRandomAppUser() {
        init();
        AppUser obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return AppUser.findAppUser(id);
    }
    
    public boolean AppUserDataOnDemand.modifyAppUser(AppUser obj) {
        return false;
    }
    
    public void AppUserDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = AppUser.findAppUserEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'AppUser' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<AppUser>();
        for (int i = 0; i < 10; i++) {
            AppUser obj = getNewTransientAppUser(i);
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
