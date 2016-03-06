/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aiden.exercise2;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author 211121614
 */
public class ArraysContentTest {
        public ArraysContentTest (){}
    
     @Test
     public void add() {
     ArraysContent ac = new ArraysContent();
     int [] result = ac.add(new int[]{1,2,3});

     Assert.assertArrayEquals(new int[]{1,2,3}, result);
         
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
