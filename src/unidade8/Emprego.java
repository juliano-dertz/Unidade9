/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidade8;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Juliano
 */

@Entity
@Table(name = "job")

public class Emprego implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "minsalary")
    private Double minsalary;

    @Column(name = "maxsalary")
    private Double maxsalary;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the minsalary
     */
    public Double getMinsalary() {
        return minsalary;
    }

    /**
     * @param minsalary the minsalary to set
     */
    public void setMinsalary(Double minsalary) {
        this.minsalary = minsalary;
    }

    /**
     * @return the maxsalary
     */
    public Double getMaxsalary() {
        return maxsalary;
    }

    /**
     * @param maxsalary the maxsalary to set
     */
    public void setMaxsalary(Double maxsalary) {
        this.maxsalary = maxsalary;
    }

    
}
