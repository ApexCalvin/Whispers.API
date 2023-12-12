package com.spit.Spit.API.tool;


import com.spit.Spit.API.dto.CreateCommentDTO;
import com.spit.Spit.API.dto.CreatePostDTO;
import com.spit.Spit.API.model.Account;
import com.spit.Spit.API.dto.CreateAccountDTO;
import com.spit.Spit.API.model.Hashtag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class DtoMapper {

    public static Account fromCreateAccountDTO(CreateAccountDTO createAccountDTO){
        Account account = new Account();
        account.setName(createAccountDTO.getName());
        account.setHandle(createAccountDTO.getHandle());
        return account;
    }

    public static CreateCommentDTO buildCreateCommentDTO(String message, Long postId, Long accountId) {
        CreateCommentDTO createCommentDTO = new CreateCommentDTO();
        createCommentDTO.setMessage(message);
        createCommentDTO.setPostId(postId);
        createCommentDTO.setAccountId(accountId);
        return createCommentDTO;
    }

    public static CreatePostDTO buildCreatePostDTO(long accountId, String message, ArrayList<String> hashtags) {
        CreatePostDTO createPostDTO = new CreatePostDTO();
        createPostDTO.setAccountId(accountId);
        createPostDTO.setMessage(message);
        createPostDTO.setHashtags(hashtags);
        return createPostDTO;
    }

    public static Set<Hashtag> buildHashtagSet(String... names) {
        return Arrays.stream(names)
                .map(name -> {
                    Hashtag hashtag = new Hashtag();
                    hashtag.setName(name);
                    return hashtag;
                })
                .collect(Collectors.toSet());
    }
}
