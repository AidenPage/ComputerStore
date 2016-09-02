package computerstore.com.computerstore.domain.employees;

/**
 * Created by Aidem on 2016/04/17.
 */
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
public class Employees implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int EmpID;
    private String EmpName;
    private String EmpSurname;
    private String EmpJob;

    public Long getId() {
        return id;
    }

    public int getEmpID() {
        return EmpID;
    }

    public String getEmpName() {
        return EmpName;
    }

    public String getEmpSurname() {
        return EmpSurname;
    }

    public String getEmpJob() {
        return EmpJob;
    }

    private Employees(){

    }

    private Employees (Builder builder)
    {
        this.id = builder.id;
        this.EmpID = builder.EmpID;
        this.EmpName = builder.EmpName;
        this.EmpSurname = builder.EmpSurname;
        this.EmpJob = builder.EmpJob;
    }

    public static class Builder
    {
        private Long id;
        private int EmpID;
        private String EmpName;
        private String EmpSurname;
        private String EmpJob;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder empID(int EmpID) {
            this.EmpID = EmpID;
            return this;
        }

        public Builder empName(String EmpName) {
            this.EmpName = EmpName;
            return this;
        }

        public Builder empSurname(String EmpSurname) {
            this.EmpSurname = EmpSurname;
            return this;
        }

        public Builder empJob(String EmpJob) {
            this.EmpJob = EmpJob;
            return this;
        }

        public Builder Employees (Employees emp)
        {
            this.id = emp.id;
            this.EmpID = emp.EmpID;
            this.EmpName = emp.EmpName;
            this.EmpSurname = emp.EmpSurname;
            this.EmpJob = emp.EmpJob;
            return this;
        }

        public Employees build ()
        {
            return new Employees(this);
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
        if (!(object instanceof Employees)) {
            return false;
        }
        Employees other = (Employees) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
