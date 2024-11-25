package com.test.entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

/**
 *
 * @author Ronny Purba
 */
@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByName", query = "SELECT e FROM Employee e WHERE e.name = :name"),
    @NamedQuery(name = "Employee.findByPicture", query = "SELECT e FROM Employee e WHERE e.picture = :picture"),
    @NamedQuery(name = "Employee.findByReportToId", query = "SELECT e FROM Employee e WHERE e.reportToId = :reportToId"),
    @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotEmpty(message= "Name is mandatory")
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50)
    private String picture;
    @Column(name = "report_to_id")
    private Integer reportToId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @NotEmpty(message= "Position is mandatory")
    @Column(length = 20, nullable = false)
    private String positionCode;

    public Employee() {
    }

    public Employee(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getReportToId() {
        return reportToId;
    }

    public void setReportToId(Integer reportToId) {
        this.reportToId = reportToId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.Employee[ id=" + id + " ]";
    }
    
}
