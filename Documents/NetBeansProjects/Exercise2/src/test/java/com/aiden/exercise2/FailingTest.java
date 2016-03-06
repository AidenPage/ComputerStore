/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aiden.exercise2;

import com.aiden.exercise2.*;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author 211121614
 */
public class FailingTest {
    
    public FailingTest (){}
    
     @Test(expectedExceptions=ExceptionInInitializerError.class)
     public void add() {
     Failing fail = new Failing();
     String result = fail.add("Fail");
     Assert.assertEquals("Pass", result);
         
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
