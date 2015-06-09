package persistance;

import persistance.campaign.entity.Campaign;
import persistance.facebook.entity.FacebookComment;
import persistance.tweets.entity.Tweet;
import persistance.user.entity.User;


public class BaseConfig {
	
	public static void createDbsStructore() {
		createCollection(Tweet.class);
		createCollection(Campaign.class);
		createCollection(User.class);
		createCollection(FacebookComment.class);
	}
	
	public static void createCollection(Class<?> collectionEntityClass) {
        DbsManager.getDatastore().getDB().getCollection(collectionEntityClass.getName());
        DbsManager.getMorphia().map(collectionEntityClass);
	}
	
}
