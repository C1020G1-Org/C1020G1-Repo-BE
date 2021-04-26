package c1020g1.social_network.service;

import c1020g1.social_network.model.Post;

import java.util.List;


public interface PostService {
    /** get Post Of User by ID
     *  author: DungHA
     * */
    List<Post> findPostByPostId(Integer id);
}
