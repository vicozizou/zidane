// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.model;

import com.bytepoxic.core.model.Email;
import com.bytepoxic.core.model.EmailDataOnDemand;
import com.bytepoxic.core.model.EmailIntegrationTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect EmailIntegrationTest_Roo_IntegrationTest {
    
    declare @type: EmailIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: EmailIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: EmailIntegrationTest: @Transactional;
    
    @Autowired
    EmailDataOnDemand EmailIntegrationTest.dod;
    
    @Test
    public void EmailIntegrationTest.testCountEmails() {
        Assert.assertNotNull("Data on demand for 'Email' failed to initialize correctly", dod.getRandomEmail());
        long count = Email.countEmails();
        Assert.assertTrue("Counter for 'Email' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void EmailIntegrationTest.testFindEmail() {
        Email obj = dod.getRandomEmail();
        Assert.assertNotNull("Data on demand for 'Email' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Email' failed to provide an identifier", id);
        obj = Email.findEmail(id);
        Assert.assertNotNull("Find method for 'Email' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Email' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void EmailIntegrationTest.testFindAllEmails() {
        Assert.assertNotNull("Data on demand for 'Email' failed to initialize correctly", dod.getRandomEmail());
        long count = Email.countEmails();
        Assert.assertTrue("Too expensive to perform a find all test for 'Email', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Email> result = Email.findAllEmails();
        Assert.assertNotNull("Find all method for 'Email' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Email' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void EmailIntegrationTest.testFindEmailEntries() {
        Assert.assertNotNull("Data on demand for 'Email' failed to initialize correctly", dod.getRandomEmail());
        long count = Email.countEmails();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Email> result = Email.findEmailEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Email' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Email' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void EmailIntegrationTest.testFlush() {
        Email obj = dod.getRandomEmail();
        Assert.assertNotNull("Data on demand for 'Email' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Email' failed to provide an identifier", id);
        obj = Email.findEmail(id);
        Assert.assertNotNull("Find method for 'Email' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyEmail(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Email' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void EmailIntegrationTest.testMergeUpdate() {
        Email obj = dod.getRandomEmail();
        Assert.assertNotNull("Data on demand for 'Email' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Email' failed to provide an identifier", id);
        obj = Email.findEmail(id);
        boolean modified =  dod.modifyEmail(obj);
        Integer currentVersion = obj.getVersion();
        Email merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Email' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void EmailIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'Email' failed to initialize correctly", dod.getRandomEmail());
        Email obj = dod.getNewTransientEmail(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Email' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Email' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'Email' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void EmailIntegrationTest.testRemove() {
        Email obj = dod.getRandomEmail();
        Assert.assertNotNull("Data on demand for 'Email' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Email' failed to provide an identifier", id);
        obj = Email.findEmail(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Email' with identifier '" + id + "'", Email.findEmail(id));
    }
    
}
