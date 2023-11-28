package com.spit.Spit.API.hashtag;

import com.spit.Spit.API.post.Post;
import com.spit.Spit.API.post.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    //private final PostRepository postRepository;

    public HashtagService(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    public void createHashtag(Hashtag hashtag) {
        hashtagRepository.save(hashtag);
    }

    public void createHashtag(List<Hashtag> hashtag) {
        hashtagRepository.saveAll(hashtag);
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

        Hashtag hashtag = this.getHashtagById(id);

        //List<Post> posts = postRepository.getAllPostsByHashtagNameDesc(hashtag.getName());

        // for each post, get hashtag list

        // for each hashtag list, if name = hashtag, then remove hashtag from list

        // after all related hashtags are removed from hashtag lists from posts, then delete hashtag

        hashtagRepository.deleteById(id);
    }

    public Boolean isHashtagAvailable(String name) {
        return getHashtagByName(name) == null;
    }
}
