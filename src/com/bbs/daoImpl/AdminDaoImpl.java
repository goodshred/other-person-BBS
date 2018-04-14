package com.bbs.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbs.dao.AdminDao;
import com.bbs.hibernate.factory.BaseHibernateDAO;
import com.bbs.model.Admin;
import com.bbs.model.User;

public class AdminDaoImpl extends BaseHibernateDAO implements AdminDao{
	private static final Logger log = LoggerFactory.getLogger(AdminDao.class);
	// property constants
	public static final String USER_NAME = "userName";
	public static final String PASSWORD = "password";
	public static final String PHOTO_URL = "photoUrl";
	public static final String EMAIL = "email";
	public static final String TYPE = "type";
	public static final String SEX = "sex";

	
	public Admin findById(java.lang.Integer id) {
		log.debug("getting Admin instance with id: " + id);
		try {
			Session session = getSession();
			Admin instance = (Admin) 
					session.get("com.bbs.model.Admin", id);
			session.close();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Admin instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Admin as model where model."
					+ propertyName + "= ?";
			Session session = getSession();
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List list = queryObject.list();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
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

	public List findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}


	@Override
	public List<Admin> login(String username) {
		Session session = getSession();
		String sql = "from Admin admin where admin.userName=?";
		Query query = session.createQuery(sql);
		query.setString(0, username);
		List list = query.list();
		session.flush();
		session.close();
		return list;
	}

	@Override
	public void updateAdmin(Admin admin) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.update(admin);;
		transaction.commit();
		session.flush();
		session.close();
	}

	@Override
	public boolean changePassword(int userId, int oldpasswod, int newpassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Admin getAdminById(Integer adminId) {
		return findById(adminId);
	}

	@Override
	public int getAdminIdByUsername(String username) {
		List<Admin>admins = findByUserName(username);
		if (admins != null && admins.size()>0)
			return admins.get(0).getId();
		return -1;
	}

	@Override
	public int getAdminIdByEmail(String email) {
		List<Admin>admins = findByEmail(email);
		if (admins != null && admins.size()>0)
			return admins.get(0).getId();
		return -1;
	}

	@Override
	public int isExist(Admin admin) {
		if (findByUserName(admin.getUserName()).size()>0)
			return 1;
		else if (findByEmail(admin.getEmail()).size()>0)
			return 2;		
		return -1;
	}

	

}
