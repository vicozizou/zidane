// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.model;

import com.bytepoxic.core.model.UserTrack;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserTrack_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager UserTrack.entityManager;
    
    public static final EntityManager UserTrack.entityManager() {
        EntityManager em = new UserTrack().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long UserTrack.countUserTracks() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserTrack o", Long.class).getSingleResult();
    }
    
    public static List<UserTrack> UserTrack.findAllUserTracks() {
        return entityManager().createQuery("SELECT o FROM UserTrack o", UserTrack.class).getResultList();
    }
    
    public static UserTrack UserTrack.findUserTrack(Long id) {
        if (id == null) return null;
        return entityManager().find(UserTrack.class, id);
    }
    
    public static List<UserTrack> UserTrack.findUserTrackEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserTrack o", UserTrack.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void UserTrack.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UserTrack.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserTrack attached = UserTrack.findUserTrack(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UserTrack.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UserTrack.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UserTrack UserTrack.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserTrack merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
