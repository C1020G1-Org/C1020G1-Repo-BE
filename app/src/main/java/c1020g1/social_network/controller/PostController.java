package c1020g1.social_network.controller;

import c1020g1.social_network.model.*;
import c1020g1.social_network.service.user.UserService;
import c1020g1.social_network.service.post.PostService;
import c1020g1.social_network.service.post_image.PostImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    /**
     * Author : CaoLPT
     * get all posts in news feed of user
     *
     * @param userId
     * @param pageable
     */
    @GetMapping("/newsfeed/{userId}")
    public ResponseEntity<Page<Post>> findAllPostInNewsFeed(@PathVariable("userId") Integer userId, @PageableDefault(size = 3) Pageable pageable) {
        User userFromDb = userService.getUserById(userId);

        if (userFromDb == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Page<Post> result = postService.getAllPostInNewsFeed(userId, pageable);

        if (result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        for (Post post : result.getContent()) {
            post.setPostContent(postService.decodeStringUrl(post.getPostContent()));
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Author : CaoLPT
     * find all images of the post
     *
     * @param postId
     */
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
    public ResponseEntity<Post> createPost(@Validated @RequestBody PostDTO postDTO, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        postDTO.getPost().setPostPublished(new Timestamp(System.currentTimeMillis()));
        postDTO.getPost().setPostContent(postService.encodeStringUrl(postDTO.getPost().getPostContent()));

        if (postDTO.getPost().getGroupSocial() != null){
            postService.createPostInGroup(postDTO.getPost());
        } else {
            postService.createPost(postDTO.getPost());
        }
        Post postTemp = postService.getRecentPostByUserId(postDTO.getPost().getUser().getUserId());
        for (String image : postDTO.getPostImages()) {
            postImageService.createPostImage(postTemp.getPostId(), image);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{postId}").buildAndExpand(postTemp).toUri());
        postTemp.setPostContent(postService.decodeStringUrl(postTemp.getPostContent()));
        return new ResponseEntity<>(postTemp, HttpStatus.CREATED);
    }

    /**
     * Author : SonPH
     * edit post
     *
     * @param postId
     * @param postEditDTO
     * @param bindingResult
     */

    @PutMapping("/{postId}")
    @Transactional
    public ResponseEntity<PostEditDTO> editPost(@PathVariable("postId") Integer postId, @Validated @RequestBody PostEditDTO postEditDTO, BindingResult bindingResult) {
        postEditDTO.getPost().setPostContent(postService.encodeStringUrl(postEditDTO.getPost().getPostContent()));
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Post post = postService.getPostById(postId);
        if (post != null) {
            post.setPostStatus(postEditDTO.getPost().getPostStatus());
            post = postEditDTO.getPost();
            postService.editPost(post);
            for (PostImage postImage : postEditDTO.getUpdateImages()) {
                postImageService.createPostImage(postId, postImage.getImage());
            }
            for (PostImage postImage : postEditDTO.getDeleteImages()) {
                postImageService.deletePostImage(postImage.getPostImageId());
            }

            Post updatePost = postService.getPostById(postId);
            PostEditDTO updatePostEditDTO = new PostEditDTO();
            updatePostEditDTO.setPost(updatePost);
            updatePostEditDTO.setPostImages(postImageService.getAllImageByPostId(postId));
            return new ResponseEntity<>(updatePostEditDTO, HttpStatus.OK);
        } else {
            System.out.println("Post with id " + postId + " not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Author : SonPH
     * get post by ID
     *
     * @param postId
     */
    @GetMapping("/{postId}")
    public ResponseEntity<PostEditDTO> getPostById(@PathVariable("postId") Integer postId) {
        Post post = postService.getPostById(postId);
        PostEditDTO postEditDTO = new PostEditDTO();
        if (post != null) {
            post.setPostContent(postService.decodeStringUrl(post.getPostContent()));
            postEditDTO.setPost(post);
            postEditDTO.setPostImages(postImageService.getAllImageByPostId(postId));
            return new ResponseEntity<>(postEditDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Author : DungHA
     * get all posts in wall of user
     *
     * @param userId
     */
    @GetMapping("/wall/{userId}")
    public ResponseEntity<Page<Post>> getAllPostInWallUser(@PathVariable("userId") Integer userId, @PageableDefault(size = 3) Pageable pageable) {
        Page<Post> postInWall = postService.getAllPostInWallUser(userId, pageable);

        if (postInWall == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        for (Post post : postInWall.getContent()) {
            post.setPostContent(postService.decodeStringUrl(post.getPostContent()));
        }

        return new ResponseEntity<>(postInWall, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Post> deletePostById(@PathVariable("postId") Integer postId) {
        Post post = postService.getPostById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            postService.deletePostById(postId);
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        }
    }
}

