/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aiden.pcstore.impl;

import com.aiden.persons.interfaces.CalInterface;

/**
 *
 * @author Aidem
 */
public class DiscountImpl implements CalInterface {
    
    @Override
    public double cal(double price, double payment) {
        return payment - (price * 0.8) ;
    }
    
}
