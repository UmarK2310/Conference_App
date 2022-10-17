package com.khangroup.conferencedemo.controllers;

import com.khangroup.conferencedemo.models.Speaker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.khangroup.conferencedemo.repositories.SpeakerRepository;
import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {
    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> list(){
        return speakerRepository.findAll();
    }
    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id){
        return speakerRepository.getOne(id);

    }
    @PostMapping
    public Speaker create(@RequestBody final Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //need to check for children data as well
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id,@RequestBody Speaker speaker){
        //TODO:Add validation that a;; attributes are passed in ,otherwise return 400 bad payload
        Speaker existingSpeaker = speakerRepository.getOne(id);
        BeanUtils.copyProperties(speaker,existingSpeaker,"speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }


}
