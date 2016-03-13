/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aiden.pcmonitor;

/**
 *
 * @author Aidem
 */
public class Monitor {
    private String brand;
    private String code;
    private String panelType;
    private String resolution;
    
    
    public Monitor() {
    }

    public Monitor(String brand, String panelType, String resolution) {
        this.brand = brand;
        this.panelType = panelType;
        this.resolution = resolution;
    }
    public String getCode()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code = code;
    }
    public String getBrand()
    {
        return brand;
    }
    
    public String getPanelType()
    {
        return panelType;
    }
    
    public String getResolution()
    {
        return resolution;
    }
}
