package Like;

import com.spit.Spit.API.Like.LikeController;
import com.spit.Spit.API.Like.LikeService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LikeControllerTest {
    @InjectMocks
    private LikeController subject;

    @Mock
    private LikeService likeService;


}
