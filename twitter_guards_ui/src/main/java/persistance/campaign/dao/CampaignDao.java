package persistance.campaign.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import persistance.DbsManager;
import persistance.campaign.entity.Campaign;

public class CampaignDao extends BasicDAO<Campaign, ObjectId> {

    public final static CampaignDao campaignDAO = new CampaignDao();

    public static CampaignDao get(){
        return campaignDAO;
    }

    public CampaignDao() {
        super(Campaign.class, DbsManager.getDatastore());
    }

	public CampaignDao(Class<Campaign> entityClass, Datastore ds) {
		super(entityClass, ds);
	}
    
	public List<Campaign> getAll(){
        return DbsManager.getDatastore().find(Campaign.class).asList();
    }

	public List<Campaign> getByUserId(ObjectId userId) {
		return DbsManager.getDatastore().find(Campaign.class).field("userId").equal(userId).asList();
	}
}
