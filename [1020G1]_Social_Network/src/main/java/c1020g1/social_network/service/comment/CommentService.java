package c1020g1.social_network.service.comment;

import c1020g1.social_network.model.ChildComment;
import c1020g1.social_network.model.ParentComment;

import java.util.List;

public interface CommentService {

    // methods for parent-comment

    /**
     * Author : CaoLPT
     * get all parent comment
     * @param postId
     */
    List<ParentComment> getAllParentCommentByPostId(Integer postId);

    /**
     * Author : CaoLPT
     * create parent comment
     * @param parentComment
     */
    void createParentComment(ParentComment parentComment);

    /**
     * Author : CaoLPT
     * edit parent comment
     * @param parentComment
     */
    void editParentComment(ParentComment parentComment);

    /**
     * Author : CaoLPT
     * get parent comment
     * @param parentCommentId
     * @return
     */
    ParentComment getParentCommentById(Integer parentCommentId);

    /**
     * Author : CaoLPT
     * delete parent comment
     * @param parentCommentId
     */
    void removeParentComment(Integer parentCommentId);

    // methods for child-comment

    /**
     * Author : CaoLPT
     * get all child comments
     * @param parentCommentId
     */
    List<ChildComment> getAllChildCommentByParentCommentId(Integer parentCommentId);

    /**
     * Author : CaoLPT
     * get child comment
     * @param childCommentId
     */
    ChildComment getChildCommentById(Integer childCommentId);

    /**
     * Author : CaoLPT
     * create child comment
     * @param childComment
     */
    void createChildComment(ChildComment childComment);

    /**
     * Author : CaoLPT
     * edit child comment
     * @param childComment
     */
    void editChildComment(ChildComment childComment);

    /**
     * Author : CaoLPT
     * delete child comment
     * @param childCommentId
     */
    void removeChildComment(Integer childCommentId);

}
