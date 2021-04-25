package c1020g1.social_network.controller;

<<<<<<< HEAD

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
=======
import c1020g1.social_network.model.Post;

import c1020g1.social_network.model.PostImage;
import c1020g1.social_network.model.User;
import c1020g1.social_network.service.UserService;
import c1020g1.social_network.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Timestamp;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/newsfeed/{userId}")
    public ResponseEntity<Page<Post>> findAllPostInNewsFeed(@PathVariable("userId") Integer userId, @PageableDefault(size = 3) Pageable pageable){
        User userFromDb = userService.getUserById(userId);

        if(userFromDb == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Page<Post> result = postService.getAllPostInNewsFeed(userId, pageable);

        if(result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/image/{postId}")
    public ResponseEntity<List<PostImage>> findAllImageByPostId(@PathVariable("postId") Integer postId){
        Post postFromDb = postService.getPostById(postId);

        if(postFromDb == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<PostImage> listPostImage = postService.getAllImageByPostId(postId);

        if(listPostImage.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(listPostImage, HttpStatus.OK);
    }
  
   @PostMapping("")
    public ResponseEntity<Void> createPost(@Validated @RequestBody Post post, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        if (bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        post.setPostPublished(new Timestamp(System.currentTimeMillis()));
        System.out.println(post);
        postService.createPost(post);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{postId}").buildAndExpand(post.getPostId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> editPost(@PathVariable("postId") Integer postId,@Validated @RequestBody Post post, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Post post1 = postService.getPostById(postId);
        if (post1 == null) {
            System.out.println("Post with id " + postId + " not found!");
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
        post1.setPostContent(post.getPostContent());
        post1.setPostStatus(post.getPostStatus());

        postService.editPost(post1);
        return new ResponseEntity<>(post1, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") Integer postId){
        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }
}

>>>>>>> post_management
