package c1020g1.social_network.controller;

import c1020g1.social_network.model.Post;
import c1020g1.social_network.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

//    @GetMapping("/wall/{id}")
//    public ResponseEntity<List<Post>> findAllPostInWall(@PathVariable("id") Integer userId){
//        return  new ResponseEntity<>(postService.getAllPostInWall(userId), HttpStatus.OK);
//    }
//
//    @GetMapping("/group/{id}")
//    public ResponseEntity<List<Post>> findAllPostInGroupUser(@PathVariable("id") Integer userId){
//        return  new ResponseEntity<>(postService.getAllPostInGroupUser(userId), HttpStatus.OK);
//    }
//
//    @GetMapping("/friend/{id}")
//    public ResponseEntity<List<Post>> findAllPostOfFriendUser(@PathVariable("id") Integer userId){
//        return  new ResponseEntity<>(postService.getAllPostOfFriendUser(userId), HttpStatus.OK);
//    }

    @GetMapping("/newsfeed/{userId}")
    public ResponseEntity<List<Post>> findAllPostInNewsFeed(@PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(postService.getAllPostInNewsFeed(userId), HttpStatus.OK);
    }
}
