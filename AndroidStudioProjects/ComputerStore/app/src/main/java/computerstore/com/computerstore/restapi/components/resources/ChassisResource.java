package computerstore.com.computerstore.restapi.components.resources;

import java.io.Serializable;

/**
 * Created by Aidem on 2016/05/08.
 */
public class ChassisResource implements Serializable {

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

    public double getPrice() {
        return Price;
    }

    public int getStock() {
        return Stock;
    }

    private ChassisResource(){

    }

    public ChassisResource(Builder builder)
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

        public Builder id(Long id) {
            return this;
        }


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

        public Builder ChassisResource (ChassisResource chassis)
        {
            this.ProductNumber = chassis.ProductNumber;
            this.Description = chassis.Description;
            this.Stock = chassis.Stock;
            this.Price = chassis.Price;
            return this;
        }

        public ChassisResource build() {
            return new ChassisResource(this);
        }


    }
}
