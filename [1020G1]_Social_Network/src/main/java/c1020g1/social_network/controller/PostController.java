package c1020g1.social_network.controller;


import c1020g1.social_network.model.Post;
import c1020g1.social_network.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("http://localhost:4200")
public class PostController {

    @Autowired
    PostService postService ;

    /** get Post Of User by ID
       author: DungHA
     * */
    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Post>> getPost(@PathVariable("id") Integer id) {
        System.out.println("Fetching Post with id " + id);
        List<Post> post = postService.findPostByPostId(id);
        if (post == null) {
            System.out.println("Post with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }



}
