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
public class ReturnsImpl implements CalInterface {
    
    @Override
    public double cal(double price, double days) {
        if(days <= 1)
            return price;
        else if(days == 2)
            return price * 0.88;
        else if(days == 3)
            return price * 0.86;
        else if(days == 4)
            return price * 0.84;
        else if(days == 5)
            return price * 0.82;
        else
            return price * 0.8;
    }
}
