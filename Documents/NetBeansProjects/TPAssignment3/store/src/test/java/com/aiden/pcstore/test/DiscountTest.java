/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aiden.pcstore.test;

import com.aiden.pcstore.impl.DiscountImpl;
import com.aiden.persons.config.AppConfig;
import com.aiden.persons.interfaces.CalInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
public class DiscountTest {
    
    private static ApplicationContext ctx;
    private static CalInterface discount;
            
    public DiscountTest() {
    }

    @Test
    public void test() {
    
        Assert.assertEquals(discount.cal(1000, 1100), 300.0,"Values aren't the same");
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        discount = (CalInterface)ctx.getBean("discountClass");
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
