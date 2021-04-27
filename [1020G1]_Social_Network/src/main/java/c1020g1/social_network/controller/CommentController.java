package c1020g1.social_network.controller;

import c1020g1.social_network.model.ChildComment;
import c1020g1.social_network.model.ParentComment;
import c1020g1.social_network.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // methods for parent-comment

    @GetMapping("/parent/{postId}")
    public ResponseEntity<List<ParentComment>> findAllParentCommentByPostId(@PathVariable("postId") Integer postId) {
        return new ResponseEntity<>(commentService.getAllParentCommentByPostId(postId), HttpStatus.OK);
    }

    @PostMapping("/parent")
    public ResponseEntity<ParentComment> createParentComment(@RequestBody ParentComment parentComment) {
        parentComment.setCommentTime(new Timestamp(System.currentTimeMillis()));

        commentService.createParentComment(parentComment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/parent/{parentCommentId}")
    public ResponseEntity<ParentComment> editParentComment(@PathVariable("parentCommentId") Integer parentCommentId, @RequestBody ParentComment parentComment) {
        ParentComment fromDb = commentService.getParentCommentById(parentCommentId);

        if (fromDb == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        commentService.editParentComment(parentComment);

        return new ResponseEntity<>(commentService.getParentCommentById(parentCommentId), HttpStatus.OK);
    }

    @DeleteMapping("/parent/{parentCommentId}")
    public ResponseEntity<ParentComment> deleteParentComment(@PathVariable("parentCommentId") Integer parentCommentId) {
        ParentComment fromDb = commentService.getParentCommentById(parentCommentId);

        if (fromDb == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        commentService.removeParentComment(parentCommentId);

        return new ResponseEntity<>(fromDb, HttpStatus.OK);
    }

    //methods for child-comment

    @GetMapping("/child/{parentCommentId}")
    public ResponseEntity<List<ChildComment>> findAllChildCommentByParentCommentId(@PathVariable("parentCommentId") Integer parentCommentId) {
        return new ResponseEntity<>(commentService.getAllChildCommentByParentCommentId(parentCommentId), HttpStatus.OK);
    }

    @PostMapping("/child")
    public ResponseEntity<ChildComment> createChildComment(@RequestBody ChildComment childComment) {
        commentService.createChildComment(childComment);

        childComment.setCommentTime(new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/child/{childCommentId}")
    public ResponseEntity<ChildComment> editChildComment(@PathVariable("childCommentId") Integer childCommentId, @RequestBody ChildComment childComment) {
        ChildComment fromDb = commentService.getChildCommentById(childCommentId);

        if (fromDb == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        commentService.editChildComment(childComment);

        return new ResponseEntity<>(commentService.getChildCommentById(childCommentId), HttpStatus.OK);
    }

    @DeleteMapping("/child/{childCommentId}")
    public ResponseEntity<ChildComment> deleteChildComment(@PathVariable("childCommentId") Integer childCommentId) {
        ChildComment fromDb = commentService.getChildCommentById(childCommentId);

        if (fromDb == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        commentService.removeChildComment(childCommentId);

        return new ResponseEntity<>(fromDb, HttpStatus.OK);
    }
}
