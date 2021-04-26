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

    @Override
    public List<ParentComment> getAllParentCommentByPostId(Integer postId) {
        List<ParentComment> result = parentCommentRepository.getAllParentCommentByPostId(postId);

        result.sort(Comparator.comparing(ParentComment::getCommentTime));

        return result;
    }

    @Override
    @Transactional
    public void createParentComment(ParentComment parentComment) {
        parentCommentRepository.createParentComment(parentComment.getContent(),
                parentComment.getCommentImage(),
                parentComment.getPost().getPostId(),
                parentComment.getUser().getUserId(),
                parentComment.getCommentTime());
    }

    @Override
    @Transactional
    public void editParentComment(ParentComment parentComment) {
        parentCommentRepository.editParentComment(parentComment.getContent(),
                                                    parentComment.getCommentImage(),
                                                    parentComment.getParentCommentId()
                                                    );
    }

    @Override
    public ParentComment getParentCommentById(Integer parentCommentId) {
        return parentCommentRepository.getParentCommentById(parentCommentId);
    }

    @Transactional
    @Override
    public void removeParentComment(Integer parentCommentId) {
        parentCommentRepository.removeParentComment(parentCommentId);
    }


    // methods for child-comment

    @Override
    public List<ChildComment> getAllChildCommentByParentCommentId(Integer parentCommentId) {
        List<ChildComment> result = childCommentRepository.getAllChildCommentByParentCommentId(parentCommentId);

        result.sort(Comparator.comparing(ChildComment::getCommentTime));

        return result;
    }

    @Override
    public ChildComment getChildCommentById(Integer childCommentId) {
       return childCommentRepository.getChildCommentById(childCommentId);
    }

    @Transactional
    @Override
    public void createChildComment(ChildComment childComment) {
        childCommentRepository.createChildComment(childComment.getContent(),
                                                    childComment.getCommentImage(),
                                                    childComment.getParentComment().getParentCommentId(),
                                                    childComment.getUser().getUserId(),
                                                    childComment.getCommentTime());
    }

    @Transactional
    @Override
    public void editChildComment(ChildComment childComment) {
        childCommentRepository.editChildComment(childComment.getContent(),
                                                childComment.getCommentImage(),
                                                childComment.getChildCommentId());
    }

    @Transactional
    @Override
    public void removeChildComment(Integer childCommentId) {
        childCommentRepository.removeChildComment(childCommentId);
    }


}
