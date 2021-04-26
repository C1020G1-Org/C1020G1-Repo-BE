package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.Post;
import c1020g1.social_network.repository.PostRepository;
import c1020g1.social_network.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository ;


    @Override
    /** get Post Of User by ID
     *  author: DungHA
     * */
    public List<Post> findPostByPostId(Integer id) {
        return postRepository.findPostByPostId(id);
    }
}
