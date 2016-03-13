/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aiden.persons.config;

import com.aiden.pcstore.impl.DiscountImpl;
import com.aiden.pcstore.impl.PaymentImpl;
import com.aiden.pcstore.impl.ReturnsImpl;
import com.aiden.persons.interfaces.CalInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Aidem
 */
@Configuration
public class AppConfig {
    
     @Bean(name = "discountClass")
    public CalInterface getDiscountService() {
        return new DiscountImpl();
    }

    @Bean(name = "paymentClass")
    public CalInterface getPaymentService() {
        return new PaymentImpl();
    }
    
     @Bean(name = "returnsClass")
    public CalInterface getReturnsService() {
        return new ReturnsImpl();
    }

}