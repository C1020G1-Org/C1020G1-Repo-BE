package c1020g1.social_network.service.comment;

import c1020g1.social_network.model.ChildComment;
import c1020g1.social_network.model.ParentComment;

import java.util.List;

public interface CommentService {

    // methods for parent-comment
    List<ParentComment> getAllParentCommentByPostId(Integer postId);

    void createParentComment(ParentComment parentComment);

    void editParentComment(ParentComment parentComment);

    ParentComment getParentCommentById(Integer parentCommentId);

    void removeParentComment(Integer parentCommentId);

    // methods for child-comment
    List<ChildComment> getAllChildCommentByParentCommentId(Integer parentCommentId);

    ChildComment getChildCommentById(Integer childCommentId);

    void createChildComment(ChildComment childComment);

    void editChildComment(ChildComment childComment);

    void removeChildComment(Integer childCommentId);

}
