package c1020g1.social_network.controller;

import c1020g1.social_network.model.*;
import c1020g1.social_network.service.post.PostService;
import c1020g1.social_network.service.post_image.PostImageService;
import c1020g1.social_network.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private PostImageService postImageService;

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
        User userFromDb = userService.getUserById(userId);

        if (userFromDb == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<Post> result = postService.getAllPostInNewsFeed(userId);

        if (result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/image/{postId}")
    public ResponseEntity<List<PostImage>> findAllImageByPostId(@PathVariable("postId") Integer postId) {
        Post postFromDb = postService.getPostById(postId);

        if (postFromDb == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<PostImage> listPostImage = postService.getAllImageByPostId(postId);

        if (listPostImage.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(listPostImage, HttpStatus.OK);
    }

    /**
     * @author SonPH
     * create post
     */
    @PostMapping("")
    @Transactional
    public ResponseEntity<Void> createPost(@Validated @RequestBody PostDTO postDTO, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        postDTO.getPost().setPostPublished(new Timestamp(System.currentTimeMillis()));
        postDTO.getPost().setPostContent(postService.encodeStringUrl(postDTO.getPost().getPostContent()));
        postService.createPost(postDTO.getPost());
        Post postTemp = postService.getRecentPostByUserId(postDTO.getPost().getUser().getUserId());
        for (String image : postDTO.getPostImages()) {
            postImageService.createPostImage(postTemp.getPostId(), image);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{postId}").buildAndExpand(postTemp).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * @author SonPH
     * edit post
     */
    @PutMapping("/{postId}")
    @Transactional
    public ResponseEntity<PostEditDTO> editPost(@PathVariable("postId") Integer postId, @Validated @RequestBody PostEditDTO postEditDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Post post = postService.getPostById(postId);
        if (post != null) {
            post.setPostContent(postService.encodeStringUrl(postEditDTO.getPost().getPostContent()));
            post.setPostStatus(post.getPostStatus());
            postService.editPost(post);
            for (PostImage postImage : postEditDTO.getUpdateImages()) {
                postImageService.createPostImage(postId, postImage.getImage());
            }
            for (PostImage postImage : postEditDTO.getDeleteImages()) {
                postImageService.deletePostImage(postImage.getPostImageId());
            }
            return new ResponseEntity<PostEditDTO>(postEditDTO, HttpStatus.OK);

        } else {
            System.out.println("Post with id " + postId + " not found!");
            return new ResponseEntity<PostEditDTO>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @author SonPH
     * get post by postId
     */
    @GetMapping("/{postId}")
    public ResponseEntity<PostEditDTO> getPostById(@PathVariable("postId") Integer postId) {
        Post post = postService.getPostById(postId);
        PostEditDTO postEditDTO = new PostEditDTO();
        if (post != null) {
            post.setPostContent(postService.decodeStringUrl(post.getPostContent()));
            postEditDTO.setPost(post);
            postEditDTO.setPostImages(postImageService.getAllImageByPostId(postId));
            return new ResponseEntity<PostEditDTO>(postEditDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Post> deletePostById(@PathVariable("postId") Integer postId) {
        Post post = postService.getPostById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            postService.deletePostById(postId);
            return new ResponseEntity<Post>(post,HttpStatus.OK);
        }
    }
}

