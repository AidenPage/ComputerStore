package computerstore.com.computerstore.factories.components;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import junit.framework.Assert;

import org.junit.Test;
import computerstore.com.computerstore.factories.components.MemoryFactory;
import computerstore.com.computerstore.domain.components.Memory;
import junit.framework.Assert;

public class MemoryTest{

    private MemoryFactory factory;

    @Test
    public void testMemoryCreation() throws Exception {
        Memory memory = factory.createMemory("HX318C10FWK2/8",50,"HyperX FURY DDR3 8GB",1175.95);
        Assert.assertEquals(memory.getDescription(),"HyperX FURY DDR3 8GB");
        Assert.assertEquals(memory.getProductNumber(),"HX318C10FWK2/8");
    }

    @Test
    public void testMemoryUpdate() throws Exception {
        Memory memory = factory.createMemory("HX318C10FWK2/8",50,"HyperX FURY DDR3 8GB",1175.95);
        Assert.assertEquals(memory.getDescription(),"HyperX FURY DDR3 8GB");
        Assert.assertEquals(memory.getProductNumber(),"HX318C10FWK2/8");

        // Updated Description

        Memory updateMemory = new Memory.Builder()
                .Memory(memory)
                .price(1299.00)
                .build();

        Assert.assertEquals(updateMemory.getPrice(),1299.00);
        Assert.assertEquals(memory.getProductNumber(),updateMemory.getProductNumber());




    }
}
