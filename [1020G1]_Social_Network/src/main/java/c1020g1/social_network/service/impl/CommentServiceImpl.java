package c1020g1.social_network.service.impl;



import c1020g1.social_network.model.ParentComment;
import c1020g1.social_network.repository.CommentRepository;
import c1020g1.social_network.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Override
    /** get parentComment by ID
     *  author: DungHA
     * */
    public List<ParentComment> findParentCommentByParentCommentId(Integer id) {
        return commentRepository.findParentCommentByParentCommentId(id);
    }
}
