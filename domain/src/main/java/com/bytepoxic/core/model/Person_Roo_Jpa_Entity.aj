// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.model;

import com.bytepoxic.core.model.Person;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

privileged aspect Person_Roo_Jpa_Entity {
    
    declare @type: Person: @Entity;
    
    declare @type: Person: @Inheritance(strategy = InheritanceType.JOINED);
    
}
