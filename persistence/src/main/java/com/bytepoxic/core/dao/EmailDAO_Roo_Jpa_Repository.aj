// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.dao;

import com.bytepoxic.core.dao.EmailDAO;
import com.bytepoxic.core.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect EmailDAO_Roo_Jpa_Repository {
    
    declare parents: EmailDAO extends JpaRepository<Email, Long>;
    
    declare parents: EmailDAO extends JpaSpecificationExecutor<Email>;
    
    declare @type: EmailDAO: @Repository;
    
}
