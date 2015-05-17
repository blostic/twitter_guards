package persistance;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import persistance.config.PropertiesManager;

import com.mongodb.MongoClient;

public class DbsManager {
	private static Datastore datastore;
	private static Morphia morphia;
	private static MongoClient mongoClient;
	
	static {
		try {
			morphia = new Morphia();
			mongoClient = new MongoClient(PropertiesManager.getProperty("database_host"),
					Integer.parseInt(PropertiesManager.getProperty("database_port")));
			datastore = getMorphia().createDatastore(mongoClient, PropertiesManager.getProperty("datastore_name"));
		} catch (UnknownHostException e) {
			throw new RuntimeException("Couldn't connect to mongo");
		}
	}

	public static Datastore getDatastore() {
		return datastore;
	}

	public static Morphia getMorphia() {
		return morphia;
	}

	public static MongoClient getMongoClient() {
		return mongoClient;
	}

}
