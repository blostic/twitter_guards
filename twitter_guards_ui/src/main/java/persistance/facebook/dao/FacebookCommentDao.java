package persistance.facebook.dao;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import persistance.DbsManager;
import persistance.facebook.entity.FacebookComment;
import persistance.tweets.dao.TweetDao;
import persistance.tweets.entity.Emotion;

import java.util.Collection;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by radoslawdyrda on 09.06.2015.
 */
public class FacebookCommentDao extends BasicDAO<FacebookComment, ObjectId> {

    public final static FacebookCommentDao dao = new FacebookCommentDao();

    public static FacebookCommentDao get(){ return dao; }

    public FacebookCommentDao() {
        super(FacebookComment.class, DbsManager.getDatastore());
    }

    public FacebookCommentDao(Class<FacebookComment> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    public Collection<FacebookComment> getComments(String campaignName, String pageId, Emotion emotion){

        Pattern regexp = Pattern.compile(campaignName + "_" + pageId + "*");

        Query q = dao.createQuery().filter("capaignName", regexp);

        if(emotion != null){
            q.filter("emotion =", emotion);
        }

        return q.asList();
    }

    public Collection<FacebookComment> getComments(String campaignName){
        return getComments(campaignName, StringUtils.EMPTY, null);
    }

    public Collection<FacebookComment> getComments(String campaignName, Emotion emotion){
        return getComments(campaignName, StringUtils.EMPTY, emotion);
    }

    public Collection<FacebookComment> getComments(String camapignName, String pageId, Emotion emotion, Date date){
        Collection<FacebookComment> ret = getComments(camapignName, pageId, emotion);

        return ret.stream().filter(t -> DateUtils.isSameDay(t.getCreationDate(), date)).collect(Collectors.toList());
    }
}
