/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth0.samples.authapi.Tercero;

/**
 *
 * @author jose
 */
public class UserTercero {
    private Long userId;
    private Long terceroId;
    
    protected UserTercero () {}
    
    public UserTercero (Long userId, Long terceroId) {
        this.userId = userId;
        this.terceroId = terceroId;
    }
    
    public void setUserId (Long userId) {
        this.userId = userId;
    }
    
    public Long getUserId () {
        return userId;
    }
    
    public void setTerceroId (Long terceroId) {
        this.terceroId = terceroId;
    }
    
    public Long getTerceroId () {
        return terceroId;
    }
}
