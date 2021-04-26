package c1020g1.social_network.controller;

<<<<<<< HEAD
=======
import c1020g1.social_network.model.ChildComment;
>>>>>>> 2f10ba501fc9c4b5002eb908358863e91587f569
import c1020g1.social_network.model.ParentComment;
import c1020g1.social_network.model.Post;
import c1020g1.social_network.service.comment.CommentService;
import c1020g1.social_network.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
<<<<<<< HEAD
    CommentService commentService ;

    /** get parentComment by ID
     *  author: DungHA
     * */
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ParentComment>> getParentComment(@PathVariable("id") Integer id) {
        System.out.println("Fetching Comment with id " + id);
        List<ParentComment> parentComments = commentService.findParentCommentByParentCommentId(id);
        if (parentComments == null) {
            System.out.println("Comment with id " + id + " not found");
=======
    private CommentService commentService;

    @Autowired
    private PostService postService;

    // methods for parent-comment

    /**
     * Author : CaoLPT
     * find all parent comment by postID
     * @param postId
     */
    @GetMapping("/parent/{postId}")
    public ResponseEntity<List<ParentComment>> findAllParentCommentByPostId(@PathVariable("postId") Integer postId){
        Post postFromDb = postService.getPostById(postId);

        if(postFromDb == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<ParentComment> listParentComments = commentService.getAllParentCommentByPostId(postId);

        if(listParentComments.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(listParentComments, HttpStatus.OK);
    }

    /**
     * Author : CaoLPT
     * create new parent comment
     * @param parentComment
     * @param bindingResult
     */
    @PostMapping("/parent")
    public ResponseEntity<ParentComment> createParentComment(@Valid @RequestBody ParentComment parentComment,BindingResult bindingResult){
        new ParentComment().validate(parentComment,bindingResult);
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        parentComment.setCommentTime(new Timestamp(System.currentTimeMillis()));

        commentService.createParentComment(parentComment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Author : CaoLPT
     * Edit parent comment
     * @param parentCommentId
     * @param parentComment
     * @param bindingResult
     */
    @PutMapping("/parent/{parentCommentId}")
    public ResponseEntity<ParentComment> editParentComment(@PathVariable("parentCommentId") Integer parentCommentId,@Valid @RequestBody ParentComment parentComment,BindingResult bindingResult){
        ParentComment fromDb = commentService.getParentCommentById(parentCommentId);

        if(fromDb == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        new ParentComment().validate(parentComment,bindingResult);
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        commentService.editParentComment(parentComment);

        ParentComment parentCommentFromDb = commentService.getParentCommentById(parentCommentId);

        return new ResponseEntity<>(parentCommentFromDb,HttpStatus.OK);
    }

    /**
     * Author : CaoLPT
     * delete parent comment
     * @param parentCommentId
     */
    @DeleteMapping("/parent/{parentCommentId}")
    public ResponseEntity<ParentComment> deleteParentComment(@PathVariable("parentCommentId") Integer parentCommentId){
        ParentComment parentCommentFromDb = commentService.getParentCommentById(parentCommentId);

        if(parentCommentFromDb == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        commentService.removeParentComment(parentCommentId);

        return new ResponseEntity<>(parentCommentFromDb,HttpStatus.OK);
    }

    //methods for child-comment

    /**
     * Author : CaoLPT
     * find all child comment by parent comment ID
     * @param parentCommentId
     */
    @GetMapping("/child/{parentCommentId}")
    public ResponseEntity<List<ChildComment>> findAllChildCommentByParentCommentId(@PathVariable("parentCommentId") Integer parentCommentId){
        ParentComment parentCommentFromDb = commentService.getParentCommentById(parentCommentId);

        if(parentCommentFromDb == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<ChildComment> listChildComment = commentService.getAllChildCommentByParentCommentId(parentCommentId);

        if(listChildComment.isEmpty())
>>>>>>> 2f10ba501fc9c4b5002eb908358863e91587f569
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(listChildComment, HttpStatus.OK);
    }

    /**
     * Author : CaoLPT
     * create child comment
     * @param childComment
     * @param bindingResult
     */
    @PostMapping("/child")
    public ResponseEntity<ChildComment> createChildComment(@Valid @RequestBody ChildComment childComment,BindingResult bindingResult){
        new ChildComment().validate(childComment,bindingResult);
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        childComment.setCommentTime(new Timestamp(System.currentTimeMillis()));

        commentService.createChildComment(childComment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Author : CaoLPT
     * edit child comment
     * @param childCommentId
     * @param childComment
     * @param bindingResult
     */
    @PutMapping("/child/{childCommentId}")
    public ResponseEntity<ChildComment> editChildComment(@PathVariable("childCommentId") Integer childCommentId,@Valid @RequestBody ChildComment childComment,BindingResult bindingResult){
        ChildComment fromDb = commentService.getChildCommentById(childCommentId);

        if(fromDb == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        new ChildComment().validate(childComment,bindingResult);
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        commentService.editChildComment(childComment);

        return new ResponseEntity<>(commentService.getChildCommentById(childCommentId), HttpStatus.OK);
    }

    /**
     * Author : CaoLPT
     * delete child comment
     * @param childCommentId
     */
    @DeleteMapping("/child/{childCommentId}")
    public ResponseEntity<ChildComment> deleteChildComment(@PathVariable("childCommentId") Integer childCommentId){
        ChildComment fromDb = commentService.getChildCommentById(childCommentId);

        if(fromDb == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        commentService.removeChildComment(childCommentId);

        return new ResponseEntity<>(fromDb, HttpStatus.OK);
    }

}
