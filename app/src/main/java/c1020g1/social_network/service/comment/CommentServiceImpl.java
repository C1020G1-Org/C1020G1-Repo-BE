package c1020g1.social_network.service.comment;

import c1020g1.social_network.model.ChildComment;
import c1020g1.social_network.model.ParentComment;
import c1020g1.social_network.repository.ChildCommentRepository;
import c1020g1.social_network.repository.ParentCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private ParentCommentRepository parentCommentRepository;

    @Autowired
    private ChildCommentRepository childCommentRepository;

    // methods for parent-comment

    /**
     * Author : CaoLPT
     * get all parent comment
     * @param postId
     */
    @Override
    public List<ParentComment> getAllParentCommentByPostId(Integer postId) {
        List<ParentComment> result = parentCommentRepository.getAllParentCommentByPostId(postId);

        result.sort(Comparator.comparing(ParentComment::getCommentTime));

        return result;
    }

    /**
     * Author : CaoLPT
     * create parent comment
     * @param parentComment
     */
    @Override
    @Transactional
    public void createParentComment(ParentComment parentComment) {
        parentCommentRepository.createParentComment(parentComment.getContent(),
                parentComment.getCommentImage(),
                parentComment.getPost().getPostId(),
                parentComment.getUser().getUserId(),
                parentComment.getCommentTime());
    }

    /**
     * Author : CaoLPT
     * edit parent comment
     * @param parentComment
     */
    @Override
    @Transactional
    public void editParentComment(ParentComment parentComment) {
        parentCommentRepository.editParentComment(parentComment.getContent(),
                parentComment.getCommentImage(),
                parentComment.getParentCommentId()
        );
    }

    /**
     * Author : CaoLPT
     * get parent comment
     * @param parentCommentId
     * @return
     */
    @Override
    public ParentComment getParentCommentById(Integer parentCommentId) {
        return parentCommentRepository.getParentCommentById(parentCommentId);
    }

    /**
     * Author : CaoLPT
     * delete parent comment
     * @param parentCommentId
     */
    @Transactional
    @Override
    public void removeParentComment(Integer parentCommentId) {
        parentCommentRepository.removeParentComment(parentCommentId);
    }


    // methods for child-comment

    /**
     * Author : CaoLPT
     * get all child comments
     * @param parentCommentId
     */
    @Override
    public List<ChildComment> getAllChildCommentByParentCommentId(Integer parentCommentId) {
        List<ChildComment> result = childCommentRepository.getAllChildCommentByParentCommentId(parentCommentId);

        result.sort(Comparator.comparing(ChildComment::getCommentTime));

        return result;
    }

    /**
     * Author : CaoLPT
     * get child comment
     * @param childCommentId
     */
    @Override
    public ChildComment getChildCommentById(Integer childCommentId) {
        return childCommentRepository.getChildCommentById(childCommentId);
    }

    /**
     * Author : CaoLPT
     * create child comment
     * @param childComment
     */
    @Transactional
    @Override
    public void createChildComment(ChildComment childComment) {
        childCommentRepository.createChildComment(childComment.getContent(),
                childComment.getCommentImage(),
                childComment.getParentComment().getParentCommentId(),
                childComment.getUser().getUserId(),
                childComment.getCommentTime());
    }

    /**
     * Author : CaoLPT
     * edit child comment
     * @param childComment
     */
    @Transactional
    @Override
    public void editChildComment(ChildComment childComment) {
        childCommentRepository.editChildComment(childComment.getContent(),
                childComment.getCommentImage(),
                childComment.getChildCommentId());
    }

    /**
     * Author : CaoLPT
     * delete child comment
     * @param childCommentId
     */
    @Transactional
    @Override
    public void removeChildComment(Integer childCommentId) {
        childCommentRepository.removeChildComment(childCommentId);
    }

    @Override
    public ParentComment getRecentParentComment(Integer postId) {
        return parentCommentRepository.getRecentParentComment(postId);
    }

    @Override
    public ChildComment getRecentChildComment(Integer parentCommentId) {
        return childCommentRepository.getRecentChildComment(parentCommentId);
    }


}
