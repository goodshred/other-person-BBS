package com.bbs.daoImpl;

import com.bbs.dao.UserDao;
import com.bbs.hibernate.factory.BaseHibernateDAO;
import com.bbs.model.User;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bbs.model.User
 * @author MyEclipse Persistence Tools
 */
public class UserDaoImpl extends BaseHibernateDAO implements UserDao {
	private static final Logger log = LoggerFactory.getLogger(UserDao.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String PHOTO_URL = "photoUrl";
	public static final String EMAIL = "email";
	public static final String TYPE = "type";
	public static final String SIGNATURE = "signature";
	public static final String LEVEL = "level";
	public static final String ACTIVE_CODE = "activeCode";
	public static final String HAS_ACTIVE = "hasActive";

//	public void save(User transientInstance) {
//		log.debug("saving User instance");
//		try {
//			getSession().save(transientInstance);
//			log.debug("save successful");
//		} catch (RuntimeException re) {
//			log.error("save failed", re);
//			throw re;
//		}
//	}
//
//	public void delete(User persistentInstance) {
//		log.debug("deleting User instance");
//		try {
//			getSession().delete(persistentInstance);
//			log.debug("delete successful");
//		} catch (RuntimeException re) {
//			log.error("delete failed", re);
//			throw re;
//		}
//	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			Session session = getSession();
			User instance = (User) session.get("com.bbs.model.User", id);
			session.flush();
			session.close();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			Session session = getSession();
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List list = queryObject.list();
			session.flush();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByPhotoUrl(Object photoUrl) {
		return findByProperty(PHOTO_URL, photoUrl);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findBySignature(Object signature) {
		return findByProperty(SIGNATURE, signature);
	}

	public List findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	public List findByActiveCode(Object activeCode) {
		return findByProperty(ACTIVE_CODE, activeCode);
	}

	public List findByHasActive(Object hasActive) {
		return findByProperty(HAS_ACTIVE, hasActive);
	}

	
	

	

	
	@Override
	public boolean regist(User user) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
		session.flush();
		session.close();
		return true;
	}

	
	@Override
	public List<User> login(String username) {
		return findByUsername(username);
	}
	
	public void update(User user){
		System.out.println("更新："+user.getUsername());
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.update(user);
		transaction.commit();
		session.flush();
		session.close();
	}

	@Override
	public boolean changePassword(int userId, int oldpasswod, int newpassword) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.bbs.dao.UserDao#isExist(com.bbs.model.User)
	 */
	@Override
	public int isExist(User user) {
		if (findByUsername(user.getUsername()).size()>0)
			return 1;
		else if (findByEmail(user.getEmail()).size()>0)
			return 2;		
		return -1;
	}

	
	
	

	/* (non-Javadoc)
	 * @see com.bbs.dao.UserDao#activeUser(java.lang.String)
	 */
	@Override
	public int activeUser(String activeCode) {
		List<User> uses = findByActiveCode(activeCode);
		if (uses != null && uses.size()>0){
			User user = uses.get(0);
			if (user.getHasActive() == 0){
				user.setHasActive(1);
				Session session = getSession();
				Transaction transaction = session.beginTransaction();
				session.update(user);
				transaction.commit();
				session.flush();
				session.close();
				return 1;
			}else {
				
				return -1;
			}
				
		}		
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.bbs.dao.UserDao#updateCode(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateCode(String username, String code) {
		
	}

	@Override
	public User getUserById(Integer integer) {
		return  findById(integer);
		
	}

	@Override
	public int getUserByEmail(String email) {
		List<User>users = findByEmail(email);
		if (users != null && users.size()>0)
			return users.get(0).getId();
		return -1;
	}

	@Override
	public int getUserIdByUsername(String username) {
		List<User>users = findByUsername(username);
		if (users != null && users.size()>0)
			return users.get(0).getId();
		return -1;
	}



	@Override
	public List<User> getUserLike(String like) {
		Session session = getSession();
		String sql = "from User user where user.username like ? or user.email like ?";
		Query query = session.createQuery(sql);
		query.setString(0, '%'+like+'%');
		query.setString(1, '%'+like+'%');
		List list = query.list();
		session.flush();
		session.close();
		return list;
	}

	
}