/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jose.samples.api.userperson;

/**
 *
 * @author jose
 */
public class UserPerson {
    private Long userId;
    private Long personId;
    
    protected UserPerson() {}
    
    public UserPerson(Long userId, Long personId) {
        this.userId = userId;
        this.personId = personId;
    }
    
    public void setUserId (Long userId) {
        this.userId = userId;
    }
    
    public Long getUserId () {
        return userId;
    }
    
    public void setTerceroId (Long personId) {
        this.personId = personId;
    }
    
    public Long getTerceroId () {
        return personId;
    }
}
