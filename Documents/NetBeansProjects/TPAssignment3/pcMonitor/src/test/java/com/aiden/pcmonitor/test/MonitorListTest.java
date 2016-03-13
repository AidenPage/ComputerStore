/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aiden.pcmonitor.test;

import com.aiden.pcmonitor.Monitor;
import java.util.ArrayList;
import java.util.List;
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
public class MonitorListTest {
    
    private Monitor monitor;
    
    public MonitorListTest() {
    }

    @Test
    public void TestmonitorList() {
     List monitorList = new ArrayList<Monitor>();
         monitor = new Monitor("LG","IPS","1920x1080");

         monitorList.add(monitor);

         monitor = new Monitor("Samsung","TN","1600x900");

         monitorList.add(monitor);

         Assert.assertEquals(monitorList.size(), 2, "The elements weren't added");
         Assert.assertNotNull(monitorList);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
