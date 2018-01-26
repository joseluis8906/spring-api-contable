/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jose.samples.api.contable.person;

import com.jose.samples.api.contable.user.ApplicationUser;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author jose
 */
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String name;
    
    private String lastname;
    
    private short age;
    
    private String phone_number;
    
    @OneToOne
    private ApplicationUser user;
    
    protected Person() {}
    
    public Person(String name, String lastname, short age, String phone_number) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.phone_number = phone_number;
    }
    
    public String getName () {
        return name;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public String getLastname () {
        return lastname;
    }
    
    public void setLastname (String lastname) {
        this.lastname = lastname;
    }
    
    public short getAge () {
        return age;
    }
    
    public void setAge (short age) {
        this.age = age;
    }
    
    public String getPhone_number () {
        return phone_number;
    }
    
    public void setPhone_number (String phone_number) {
        this.phone_number = phone_number;
    }
    
    public ApplicationUser getUser () {
        return user;
    }
    
    public void setUser (ApplicationUser user) {
        this.user = user;
    }
}
