package com.spit.Spit.API.comment;

import com.spit.Spit.API.Comment.CommentRepository;
import com.spit.Spit.API.Comment.CommentService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @InjectMocks
    private CommentService subject;

    @Mock
    private CommentRepository commentRepository;
}
