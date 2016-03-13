/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aiden.pcmonitor.test;

import com.aiden.pcmonitor.Monitor;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Aidem
 */
public class MonitorMapTest {
    
    private Monitor monitor;
    private Map monitorList;
    
    public MonitorMapTest() {
    }

     @Test
    public void testAnimalMap() {
        
        monitorList.put(monitor.getCode(),monitor);

         monitor = new Monitor();

         monitor = (Monitor) monitorList.get("22EA53VQ");

         Assert.assertEquals(monitor.getBrand(),"LG");
    
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
         monitorList = new HashMap();
        monitor = new Monitor("LG","IPS","1920x1080");
        monitor.setCode("22EA53VQ");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
