package c1020g1.social_network.service;
import c1020g1.social_network.model.ParentComment;


import java.util.List;


public interface CommentService {
    /** get parentComment by ID
     *  author: DungHA
     * */
    List<ParentComment> findParentCommentByParentCommentId(Integer id);
}
