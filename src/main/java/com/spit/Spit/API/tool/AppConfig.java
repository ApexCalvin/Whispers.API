package com.spit.Spit.API.tool;

import com.spit.Spit.API.model.Account;
import com.spit.Spit.API.repository.AccountRepository;
import com.spit.Spit.API.model.Comment;
import com.spit.Spit.API.repository.CommentRepository;
import com.spit.Spit.API.model.Hashtag;
import com.spit.Spit.API.repository.HashtagRepository;
import com.spit.Spit.API.repository.LikeRepository;
import com.spit.Spit.API.model.Like_;
import com.spit.Spit.API.model.Post;
import com.spit.Spit.API.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;

@Configuration
public class AppConfig {

    @Autowired
    PostRepository postRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    HashtagRepository hashtagRepository;

    private boolean runAppConfig = true;

    @PostConstruct
    public void setup(){
        if (!runAppConfig) return;

        Account user1 = new Account("John", "Homelander");
        Account user2 = new Account("Kevin", "The Deep");
        Account user3 = new Account("Reggie", "A-Train");
        Account user4 = new Account("Klara", "Stormfront");
        Account user5 = new Account("Marvin", "Mother's Milk");

        Post cooking = new Post(new Date(1669084800000L), user1, "Just tried a new recipe for garlic butter shrimp, and it turned out amazing! Who else loves experimenting in the kitchen?");
        Comment c1 = new Comment(cooking, user2, "That sounds delicious! Do you mind sharing the recipe? I'm always looking for new ideas.");
        Comment c2 = new Comment(cooking, user1, "Of course! Here's the recipe: garlic, butter, shrimp, salt, pepper, and oregano.");
        Comment c3 = new Comment(cooking, user3, "Thanks for sharing! I'll definitely give it a try this weekend. Any tips for getting it just right?");
        Comment c4 = new Comment(cooking, user4, "That looks amazing! I've been looking for new seafood recipes. Gonna try this soon.");
        Comment c5 = new Comment(cooking, user1, "You're welcome! For perfection, make sure to have enough butter. Enjoy your cooking!");

        Post fitness = new Post(new Date(1669139700000L), user3, "Hit a new personal record in deadlifts today! Feeling strong and motivated. What's your favorite workout to stay fit?");
        Comment f1 = new Comment(fitness, user2,"Wow, that's impressive! I love doing high-intensity interval training workouts for that cardio burn. Keeps things interesting. Keep it up!");
        Comment f2 = new Comment(fitness, user3,"Thanks! High-intensity interval training is great too, keeps the heart rate up. Let's motivate each other to stay on track!");
        Comment f3 = new Comment(fitness, user1,"I've been struggling with consistency. Any tips on staying motivated?");
        Comment f4 = new Comment(fitness, user2,"Consistency is key! Find a workout buddy, and set achievable goals. You got this!");

        Post tech = new Post(new Date(1669171500000L), user4, "Just got my hands on the latest smartphone. The camera quality is mind-blowing! Anyone else excited about new tech releases?");
        Comment t1 = new Comment(tech, user5, "Oh, I've been eyeing that phone! How's the camera in low light?");
        Comment t2 = new Comment(tech, user4, "It's incredible! Even in low light, the photos are sharp. Worth the upgrade.");
        Comment t3 = new Comment(tech, user1, "I'm due for an upgrade. What are the standout features?");
        Comment t4 = new Comment(tech, user4, "The new calendar app is a game-changer. Makes daily tasks so much smoother.");

        Post travel = new Post(new Date(1669225800000L), user5, "Just returned from a backpacking trip in Europe. The landscapes were breathtaking. What's your dream travel destination?");
        Comment r1 = new Comment(travel, user1, "Your photos are stunning! My dream destination is Iceland. Hopefully, I'll visit someday.");
        Comment r2 = new Comment(travel, user5, "Thanks! Your dream destination sounds amazing. Keep the travel spirit alive!.");
        Comment r3 = new Comment(travel, user2, "I've always wanted to explore London. Any travel tips?");
        Comment r4 = new Comment(travel, user1, "Absolutely! When you go, make sure to bring binoculars. It enhances the experience.");

        Post movie = new Post(new Date(1669257600000L), user2, "Just watched Inception. The plot twists had me on the edge of my seat! What's the last movie that blew your mind?");
        Comment m1 = new Comment(movie, user3, "That movie was intense! The ending left me with so many questions. I recently watched Fight Club – highly recommend it for the plot twists!");
        Comment m2 = new Comment(movie, user2, "Right? The ending was unexpected. I'll add Fight Club to my watchlist, thanks for the recommendation!");
        Comment m3 = new Comment(movie, user4, "I'm a huge fan of The Prestige. The cinematography is incredible, and the storyline is gripping..");
        Comment m4 = new Comment(movie, user3, "Oh, I love that one too! The visuals are stunning. Have you seen Memento? It's by the same director.");
        Comment m5 = new Comment(movie, user4, " Yes, it's amazing! That director always delivers.");

        Post filler = new Post(new Date(1669084800000L), user1, "-filler-");
        Post filler2 = new Post(new Date(1669084800000L), user1, "-filler-");
        Post filler3 = new Post(new Date(1669084800000L), user1, "-filler-");
        Post filler4 = new Post(new Date(1669084800000L), user1, "-filler-");
        Post filler5 = new Post(new Date(1669084800000L), user1, "-filler-");
        Post filler6 = new Post(new Date(1669084800000L), user1, "-filler-");

        accountRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
        postRepository.saveAll(Arrays.asList(
                                                filler, filler2, filler3, filler4, filler5, filler6,
                                                cooking, fitness, tech, travel, movie
                                            ));
        commentRepository.saveAll(Arrays.asList(    c1, c2, c3, c4, c5,
                                                    f1, f2, f3, f4,
                                                    t1, t2, t3, t4,
                                                    r1, r2, r3, r4,
                                                    m1, m2, m3, m4, m5
                                                ));

        Like_ like1 = new Like_();
        like1.setPost(cooking);
        like1.setAccount(user4);

        Like_ like2 = new Like_();
        like2.setPost(fitness);
        like2.setAccount(user4);

        Like_ like3 = new Like_();
        like3.setPost(cooking);
        like3.setAccount(user1);

        Like_ like4 = new Like_();
        like4.setPost(tech);
        like4.setAccount(user1);

        likeRepository.saveAll(Arrays.asList(like1, like2, like3, like4));

        Hashtag hashtag = new Hashtag();
        hashtag.setName("first");
        Hashtag hashtag2 = new Hashtag();
        hashtag2.setName("second");
        travel.setHashtags(Set.of(hashtag, hashtag2));
        tech.setHashtags(Set.of(hashtag));
        hashtagRepository.saveAll(Arrays.asList(hashtag, hashtag2));
        postRepository.saveAll(Arrays.asList(travel, tech));

    }
}
