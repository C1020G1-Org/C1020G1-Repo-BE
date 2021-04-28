package c1020g1.social_network.service.post;

import c1020g1.social_network.model.Post;

import java.util.List;

public interface PostService {
//    List<Post> getAllPostInWall(Integer userId);
//
//    List<Post> getAllPostInGroupUser(Integer userId);
//
//    List<Post> getAllPostOfFriendUser(Integer userId);

    List<Post> getAllPostInNewsFeed(Integer userId);
}
