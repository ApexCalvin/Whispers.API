package com.spit.Spit.API.hashtag;

import com.spit.Spit.API.account.Account;
import com.spit.Spit.API.post.Post;
import com.spit.Spit.API.post.PostRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
//        Set<Hashtag> hashtagsToSave = new HashSet<>();
//        for (String hashtag : hashtags) {
//            hashtagsToSave.add(validateAndSaveHashtag(hashtag));
//        }
//        return hashtagsToSave;
        return hashtags.stream().map(this::validateAndSaveHashtag).collect(Collectors.toSet());
    }

    private Hashtag validateAndSaveHashtag( String hashtagName) {
        //grab existing hashtag or null
        Hashtag existingHashtag = getHashtagByName(hashtagName);;

        if(existingHashtag == null) {
            Hashtag hashtagToSave = new Hashtag();
            //else, write over & save new hashtag
            hashtagToSave.setName(hashtagName);
            return hashtagRepository.save(hashtagToSave);
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

    public void deleteHashtagById(Long id) {
        hashtagRepository.deleteById(id);
    } //TODO

    public Boolean isNewHashtag(String name) {
        return getHashtagByName(name) == null;
    }
}
