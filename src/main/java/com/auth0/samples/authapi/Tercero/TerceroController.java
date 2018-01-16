/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth0.samples.authapi.Tercero;

import com.auth0.samples.authapi.user.ApplicationUser;
import com.auth0.samples.authapi.user.ApplicationUserRepository;
import io.jsonwebtoken.lang.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
/**
 *
 * @author jose
 */
@RestController
@RequestMapping(path="/terceros")
public class TerceroController {
    private TerceroRepository terceroRepository;
    private ApplicationUserRepository applicationUserRepository;
    
    public TerceroController(TerceroRepository terceroRepository, 
                             ApplicationUserRepository applicationUserRepository) {
        
        this.terceroRepository = terceroRepository;
        this.applicationUserRepository = applicationUserRepository;
    }
    
    @PostMapping
    public void add (@RequestBody Tercero tercero) {
        terceroRepository.save(tercero);
    }
    
    @GetMapping
    public List<Tercero> list () {
        return terceroRepository.findAll();
    }
    
    @PutMapping
    public void changeUser (@RequestBody UserTercero userTercero) {
        ApplicationUser user = applicationUserRepository.findOne(userTercero.getUserId());
        Assert.notNull(user, "User not found");
        Tercero tercero = terceroRepository.findOne(userTercero.getTerceroId());
        Assert.notNull(user, "User not found");
        tercero.setUser(user);
        terceroRepository.save(tercero);
    }
    
    @PutMapping("/{id}")
    public void edit (@PathVariable Long id, @RequestBody Tercero tercero) {
        Tercero existingTercero = terceroRepository.findOne(id);
        Assert.notNull(existingTercero, "Tercero not found");
        existingTercero.setName(tercero.getName());
        existingTercero.setLastname(tercero.getLastname());
        existingTercero.setAge(tercero.getAge());
        existingTercero.setPhone_number(tercero.getPhone_number());
        terceroRepository.save(existingTercero);
    }
    
    @DeleteMapping("/{id}")
    public void delete (@PathVariable long id) {
        terceroRepository.delete(id);
    }
}
