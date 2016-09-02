package computerstore.com.computerstore.restapi.components.resources;

import java.io.Serializable;

/**
 * Created by Aidem on 2016/05/08.
 */
public class MemoryResource implements Serializable {
    
    private String ProductNumber;
    private String Description;
    private int Stock;
    private double Price;
    

    public String getProductNumber() {
        return ProductNumber;
    }

    public String getDescription() {
        return Description;
    }

    public int getStock() {
        return Stock;
    }

    public double getPrice() {
        return Price;
    }

    private MemoryResource(){

    }

    private MemoryResource(Builder builder)
    {
        this.ProductNumber = builder.ProductNumber;
        this.Description = builder.Description;
        this.Stock = builder.Stock;
        this.Price = builder.Price;
    }

    public static class Builder {
        private String ProductNumber;
        private String Description;
        private int Stock;
        private double Price;


        public Builder productNumber(String ProductNumber) {
            this.ProductNumber = ProductNumber;
            return this;
        }

        public Builder description(String Description) {
            this.Description = Description;
            return this;
        }

        public Builder stock(int Stock) {
            this.Stock = Stock;
            return this;
        }

        public Builder price(double Price) {
            this.Price = Price;
            return this;
        }

        public Builder MemoryResource (MemoryResource chassis)
        {
            this.ProductNumber = chassis.ProductNumber;
            this.Description = chassis.Description;
            this.Stock = chassis.Stock;
            this.Price = chassis.Price;
            return this;
        }

        public MemoryResource build() {
            return new MemoryResource(this);
        }


    }
}
