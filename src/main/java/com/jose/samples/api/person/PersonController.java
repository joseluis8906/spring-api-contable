/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jose.samples.api.person;

import com.jose.samples.api.userperson.UserPerson;
import com.jose.samples.api.user.ApplicationUser;
import com.jose.samples.api.user.ApplicationUserRepository;
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
public class PersonController {
    private PersonRepository personRepository;
    private ApplicationUserRepository applicationUserRepository;
    
    public PersonController(PersonRepository personRepository,
							ApplicationUserRepository applicationUserRepository) {
        
        this.personRepository = personRepository;
        this.applicationUserRepository = applicationUserRepository;
    }
    
    @PostMapping
    public void add (@RequestBody Person person) {
        personRepository.save(person);
    }
    
    @GetMapping
    public List<Person> list () {
        return personRepository.findAll();
    }
    
    @PutMapping
    public void changeUser (@RequestBody UserPerson userPerson) {
        ApplicationUser user = applicationUserRepository.findOne(userPerson.getUserId());
        Assert.notNull(user, "User not found");
        Person person = personRepository.findOne(userPerson.getTerceroId());
        Assert.notNull(user, "Person not found");
        person.setUser(user);
        personRepository.save(person);
    }
    
    @PutMapping("/{id}")
    public void edit (@PathVariable Long id, @RequestBody Person person) {
        Person existingPerson = personRepository.findOne(id);
        Assert.notNull(existingPerson, "Person not found");
        existingPerson.setName(person.getName());
        existingPerson.setLastname(person.getLastname());
        existingPerson.setAge(person.getAge());
        existingPerson.setPhone_number(person.getPhone_number());
        personRepository.save(existingPerson);
    }
    
    @DeleteMapping("/{id}")
    public void delete (@PathVariable long id) {
        personRepository.delete(id);
    }
}
