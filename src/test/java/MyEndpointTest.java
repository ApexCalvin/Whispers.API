import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spit.Spit.API.Application;
import com.spit.Spit.API.dto.CreatePostDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class MyEndpointTest { //Requires AppConfig True and clean database

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllLikes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/likes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(4));
    }

    @Test
    public void getLikeById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/likes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getAllPosts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/post"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(5));
    }

    @Test
    public void createPost() throws Exception {
        CreatePostDTO tested = new CreatePostDTO(1L, "Tested", new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(tested)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Post has been successfully saved."));
    }

    private String toJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
