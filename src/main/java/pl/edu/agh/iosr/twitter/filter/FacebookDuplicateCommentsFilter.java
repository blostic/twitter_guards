package pl.edu.agh.iosr.twitter.filter;

import facebook4j.Comment;
import org.apache.camel.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import pl.edu.agh.iosr.twitter.model.facebook.FacebookComment;

import java.util.List;

/**
 * Created by radoslawdyrda on 05.06.2015.
 */
public class FacebookDuplicateCommentsFilter {

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean isCommentDuplicated(@Body Comment comment){
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("commentId").is(comment.getId()));
        long count = mongoTemplate.count(findQuery, FacebookComment.class);
        return count > 0 ? true : false;
    }
}
