package persistance.user.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import persistance.DbsManager;
import persistance.user.entity.User;

public class UserDao extends BasicDAO<User, ObjectId> {

    public final static UserDao UserDAO = new UserDao();

    public static UserDao get(){
        return UserDAO;
    }

    public UserDao() {
        super(User.class, DbsManager.getDatastore());
    }

	public UserDao(Class<User> entityClass, Datastore ds) {
		super(entityClass, ds);
	}
    
	public List<User> getAll(){
        return DbsManager.getDatastore().find(User.class).asList();
    }

	public boolean checkIfEmailInDbs(String email){
		return DbsManager.getDatastore().find(User.class).criteria("email").equal(email).getQuery().get() != null;
	}
	
}
