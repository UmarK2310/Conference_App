package com.khangroup.conferencedemo.controllers;

import java.util.List;
import com.khangroup.conferencedemo.models.Session;
import com.khangroup.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list() {
        List<Session> sessionList =sessionRepository.findAll();
        return sessionList;
    }

    @GetMapping
    @RequestMapping( "{id}")
    public Session get(@PathVariable Long id){
        return sessionRepository.getOne(id);
    }

    @PostMapping
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //need to check for children records before deleting
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value ="{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id,@RequestBody Session session) {
        //PUT - copies all but PATCH copies only specifics
        //TODO: Add validation that all attributes are passed in,otherwise return a 400 bad payload
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession,"session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }


}
