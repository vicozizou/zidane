// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.dao;

import com.bytepoxic.core.dao.NationalityDAO;
import com.bytepoxic.core.model.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect NationalityDAO_Roo_Jpa_Repository {
    
    declare parents: NationalityDAO extends JpaRepository<Nationality, Long>;
    
    declare parents: NationalityDAO extends JpaSpecificationExecutor<Nationality>;
    
    declare @type: NationalityDAO: @Repository;
    
}