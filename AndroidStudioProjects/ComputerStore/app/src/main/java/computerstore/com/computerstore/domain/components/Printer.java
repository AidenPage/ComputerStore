package computerstore.com.computerstore.domain.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Aidem
 */
@Entity
public class Printer implements Serializable {

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

    public int getStock() {
        return Stock;
    }

    public double getPrice() {
        return Price;
    }


    private Printer(){

    }

    private Printer(Builder builder)
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

        public Builder id(Long id){
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

        public Builder Printer (Printer chassis)
        {
            this.id = chassis.id;
            this.ProductNumber = chassis.ProductNumber;
            this.Description = chassis.Description;
            this.Stock = chassis.Stock;
            this.Price = chassis.Price;
            return this;
        }

        public Printer build() {
            return new Printer(this);
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Printer)) {
            return false;
        }
        Printer other = (Printer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}

