package com.spit.Spit.API.hashtag;

import com.spit.Spit.API.account.Account;
import com.spit.Spit.API.post.Post;
import com.spit.Spit.API.post.PostRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    public HashtagService(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    public Set<Hashtag> createHashtags(Long postId, List<String> hashtags) {
        Set<Hashtag> hashtagsToSave = new HashSet<>();
        if(!hashtags.isEmpty()) {
            for (String hashtag : hashtags) {
                hashtagsToSave.add(validateAndSaveHashtag(postId, hashtag));
            }
        }
        return hashtagsToSave;
    }

    public Hashtag validateAndSaveHashtag(Long postId, String hashtagName) {
        boolean newHashtag = isNewHashtag(hashtagName);

        //grab existing hashtag or null
        Hashtag hashtagToSave = getHashtagByName(hashtagName);;

        if(newHashtag) { //else, write over & save new hashtag
            hashtagToSave.setName(hashtagName);
            hashtagRepository.save(hashtagToSave);
        }

        return hashtagToSave;
        //return new ResponseEntity<>(HttpStatus.CREATED);
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
    }

    public Boolean isNewHashtag(String name) {
        return getHashtagByName(name) == null;
    }
}
