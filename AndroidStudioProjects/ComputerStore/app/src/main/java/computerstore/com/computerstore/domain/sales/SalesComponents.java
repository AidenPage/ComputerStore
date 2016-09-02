package computerstore.com.computerstore.domain.sales;

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
public class SalesComponents implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ProductNumber;
    private String SaleID;
    private int amount;

    public Long getId() {
        return id;
    }

    public String getProductNumber() {
        return ProductNumber;
    }

    public String getSaleID() {
        return SaleID;
    }

    public int getAmount() {
        return amount;
    }

    private SalesComponents()
    {

    }

    private SalesComponents(Builder builder)
    {

        this.id = builder.id;
        this.ProductNumber = builder.ProductNumber;
        this.SaleID = builder.SaleID;
        this.amount = builder.amount;
    }

    public static class Builder
    {
        private Long id;
        private String ProductNumber;
        private String SaleID;
        private int amount;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder productNumber(String ProductNumber) {
            this.ProductNumber = ProductNumber;
            return this;
        }

        public Builder saleID(String SaleID) {
            this.SaleID = SaleID;
            return this;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder SalesComponents(SalesComponents salesComponents)
        {
            this.id = salesComponents.id;
            this.ProductNumber = salesComponents.ProductNumber;
            this.SaleID = salesComponents.SaleID;
            this.amount = salesComponents.amount;
            return this;
        }

        public SalesComponents build()
        {
            return new SalesComponents(this);
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
        if (!(object instanceof SalesComponents)) {
            return false;
        }
        SalesComponents other = (SalesComponents) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}

