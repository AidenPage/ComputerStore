package computerstore.com.computerstore.domain.components;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Aidem
 */
@Entity
public class Chassis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ProductNumber;
    private String Description;
    private int Stock;
    private double Price;

    public Long getId() {
        return id;
    }

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

    private Chassis(){

    }

    public Chassis(Builder builder)
    {
        this.id = builder.id;
        this.ProductNumber = builder.ProductNumber;
        this.Description = builder.Description;
        this.Stock = builder.Stock;
        this.Price = builder.Price;
    }

    public static class Builder {
        private Long id;
        private String ProductNumber;
        private String Description;
        private int Stock;
        private double Price;

        public Builder id(Long id) {
            this.id = id;
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

        public Builder Chassis (Chassis chassis)
        {
            this.id = chassis.id;
            this.ProductNumber = chassis.ProductNumber;
            this.Description = chassis.Description;
            this.Stock = chassis.Stock;
            this.Price = chassis.Price;
            return this;
        }

        public Chassis build() {
            return new Chassis(this);
        }


    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not
        if (!(object instanceof Chassis)) {
            return false;
        }
        Chassis other = (Chassis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Chassis{" + "id=" + id + '}';
    }



}
