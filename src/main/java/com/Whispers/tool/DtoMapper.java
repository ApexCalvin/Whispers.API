package com.Whispers.tool;

import com.Whispers.dto.CreateCommentDTO;
import com.Whispers.dto.CreatePostDTO;
import com.Whispers.model.Account;
import com.Whispers.dto.CreateAccountDTO;
import com.Whispers.model.Hashtag;

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

    public static CreatePostDTO buildCreatePostDTO(Long accountId, String message, ArrayList<String> hashtags) {
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
