package c1020g1.social_network.service.post;

import c1020g1.social_network.model.Post;
import c1020g1.social_network.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

//    @Override
//    public List<Post> getAllPostInWall(Integer userId) {
//        return postRepository.getAllPostInWall(userId);
//    }
//
//    @Override
//    public List<Post> getAllPostInGroupUser(Integer userId) {
//        return postRepository.getAllPostInGroupUser(userId);
//    }
//
//    @Override
//    public List<Post> getAllPostOfFriendUser(Integer userId) {
//        return postRepository.getAllPostOfFriendUser(userId);
//    }

    @Override
    public List<Post> getAllPostInNewsFeed(Integer userId) {
        List<Post> postsInWall = postRepository.getAllPostInWall(userId);

        List<Post> postsInGroupUser = postRepository.getAllPostInGroupUser(userId);

        List<Post> postsOfFriendUser = postRepository.getAllPostOfFriendUser(userId);

        List<Post> postsInNewsFeed = Stream.of(postsInWall, postsInGroupUser, postsOfFriendUser)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        postsInNewsFeed.sort(Comparator.comparing(Post::getPostPublished));

        return postsInNewsFeed;
    }
}
