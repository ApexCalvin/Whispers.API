package com.spit.Spit.API.hashtag;

import com.spit.Spit.API.post.Post;
import com.spit.Spit.API.post.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    private final PostRepository postRepository;

    public HashtagService(HashtagRepository hashtagRepository, PostRepository postRepository) {
        this.hashtagRepository = hashtagRepository;
        this.postRepository = postRepository;
    }

    public void createHashtags(Long postId, List<String> hashtags) {
        if(!hashtags.isEmpty()) {
            for (String hashtag : hashtags) {
                validateAndSaveHashtag(postId, hashtag);
            }
        }
    }

    public void validateAndSaveHashtag(Long postId, String hashtagName) {
        boolean newHashtag = isNewHashtag(hashtagName);

        //grab existing hashtag or null
        Hashtag hashtagToSave = getHashtagByName(hashtagName);;

        if(newHashtag) { //else, write over & save new hashtag
            hashtagToSave.setName(hashtagName);
            hashtagRepository.save(hashtagToSave);
        }

        Post post = postRepository.findById(postId).orElse(null);
        post.getHashtags().add(hashtagToSave);
        postRepository.save(post);

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

    public List<Hashtag> getAllHashtagsByPostId(Long id) {

        return null;
    }

    public void deleteHashtagById(Long id) {

        Hashtag hashtag = this.getHashtagById(id);

        //List<Post> posts = postRepository.getAllPostsByHashtagNameDesc(hashtag.getName()); //TODO

        // for each post, get hashtag list

        // for each hashtag list, if name = hashtag, then remove hashtag from list

        // after all related hashtags are removed from hashtag lists from posts, then delete hashtag

        hashtagRepository.deleteById(id);
    }

    public Boolean isNewHashtag(String name) {
        return getHashtagByName(name) == null;
    }
}
