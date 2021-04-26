package c1020g1.social_network.controller;

import c1020g1.social_network.model.ParentComment;
import c1020g1.social_network.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("http://localhost:4200")
public class CommentController {
    @Autowired
    CommentService commentService ;
/**
 *
 * */
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ParentComment>> getParentComment(@PathVariable("id") Integer id) {
        System.out.println("Fetching Comment with id " + id);
        List<ParentComment> parentComments = commentService.findParentCommentByParentCommentId(id);
        if (parentComments == null) {
            System.out.println("Comment with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(parentComments, HttpStatus.OK);
    }

}
