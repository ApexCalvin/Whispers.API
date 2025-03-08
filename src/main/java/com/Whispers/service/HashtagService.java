package com.Whispers.service;

import com.Whispers.repository.HashtagRepository;
import com.Whispers.model.Hashtag;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    public HashtagService(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    public Set<Hashtag> createHashtags(List<String> hashtags) {
        return hashtags.stream().distinct().map(this::validateAndSaveHashtag).collect(Collectors.toSet());
    }

    private Hashtag validateAndSaveHashtag(String hashtagName) {// don't expose methods as public just for testing. test the logic from the caller public method
        Hashtag existingHashtag = getHashtagByName(hashtagName);

        if(existingHashtag == null) {
            Hashtag hashtagToSave = new Hashtag();
            hashtagToSave.setName(hashtagName);
            hashtagRepository.save(hashtagToSave);
            return hashtagToSave;
        }
        return existingHashtag;
    }

    public Hashtag getHashtagById(Long id) {
        return hashtagRepository.findById(id).orElse(null);
    }

    public Hashtag getHashtagByName(String name) {
        return hashtagRepository.findByName(name);
    }

    public List<Hashtag> getAllHashtags() {
        return hashtagRepository.findAll();
    }

    public Boolean isNewHashtag(String name) {
        return getHashtagByName(name) == null;
    }
}
