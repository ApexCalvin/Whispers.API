package com.spit.Spit.API.hashtag;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hashtag")
public class HashtagController {

    private final HashtagService hashtagService;

    public HashtagController(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }

    @PostMapping
    public ResponseEntity<String> createHashtag(@Valid @RequestBody Hashtag hashtag){
        //did hashtag already exist?
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<> {

    }

    public ResponseEntity<> {

    }

    public ResponseEntity<> {

    }
    //create
    //getbyid
    //delete
    //getallpostsbyhashtagname

}
