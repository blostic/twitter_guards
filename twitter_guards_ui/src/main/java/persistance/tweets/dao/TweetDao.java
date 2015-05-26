package persistance.tweets.dao;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import org.mongodb.morphia.query.Query;
import persistance.DbsManager;
import persistance.tweets.entity.Tweet;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public Collection<Tweet> getTweets(String campaignName, List<String> keywords){
        Query q = tweetDAO.createQuery().filter("routeName in", keywords.stream().map(k -> campaignName + "_" + k).collect(Collectors.toList()));

        return q.asList();
    }

    public Collection<Tweet> getTweets(String campaignName, String keyword){
        return getTweets(campaignName, Arrays.asList(keyword));
    }

}
