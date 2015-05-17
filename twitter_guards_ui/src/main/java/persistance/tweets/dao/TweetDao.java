package persistance.tweets.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import persistance.DbsManager;
import persistance.tweets.entity.Tweet;

public class TweetDao extends BasicDAO<Tweet, ObjectId> {

    public final static TweetDao tweetDAO = new TweetDao();

    public static TweetDao get(){
        return tweetDAO;
    }

    public TweetDao() {
        super(Tweet.class, DbsManager.getDatastore());
    }

	public TweetDao(Class<Tweet> entityClass, Datastore ds) {
		super(entityClass, ds);
	}

}
