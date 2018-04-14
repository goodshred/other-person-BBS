package com.bbs.daoImpl;

import com.bbs.dao.BlackListDao;
import com.bbs.hibernate.factory.BaseHibernateDAO;
import com.bbs.model.BlackList;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * BlackList entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bbs.model.BlackList
 * @author MyEclipse Persistence Tools
 */
public class BlackListDaoImpl extends BaseHibernateDAO implements BlackListDao {
	private static final Logger log = LoggerFactory
			.getLogger(BlackListDaoImpl.class);
	// property constants
	public static final String LEVEL = "level";

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.BlackListDao#save(com.bbs.model.BlackList)
	 */
	@Override
	public void save(BlackList transientInstance) {
		log.debug("saving BlackList instance");
		try {
			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			session.save(transientInstance);
			transaction.commit();
			session.flush();
			session.close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	

	public BlackList findById(java.lang.Integer id) {
		log.debug("getting BlackList instance with id: " + id);
		try {
			Session session = getSession();
			BlackList instance = (BlackList) session.get(
					"com.bbs.model.BlackList", id);
			session.flush();
			session.close();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BlackList instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BlackList as model where model."
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

	public List findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}
	
	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.BlackListDao#getLevel(int)
	 */
	@Override
	public int getLevel(int userId){
		String sql = "from BlackList bl where bl.user.id = ?";
		Session session = getSession();
		Query query = session.createQuery(sql);
		query.setInteger(0, userId);
		List list = query.list();
	
		if (list != null && list.size()>0){
			BlackList blackList = (BlackList) list.get(0);
			session.flush();
			session.close();
			return blackList.getLevel();
		}
		else {
			session.flush();
			session.close();
		}
		
		return -1;//不存在
		 
	}
	
	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.BlackListDao#update(com.bbs.model.BlackList)
	 */
	@Override
	public void update(BlackList list){
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		String sql = "from BlackList list where list.user.id = ?";
		Query query = session.createQuery(sql);
		query.setInteger(0, list.getUser().getId());
		BlackList blackList = (BlackList) query.list().get(0);
		blackList.setLevel(list.getLevel());
		session.update(blackList);
		
		transaction.commit();
		session.flush();
		session.close();
	}



	@Override
	public void remove(int userId) {
		int level = getLevel(userId);
		Session session = getSession();
		if (level>0){
			Transaction transaction = session.beginTransaction();
			String sql = "from BlackList list where list.user.id = ?";
			Query query = session.createQuery(sql);
			query.setInteger(0,userId);
			BlackList blackList = (BlackList) query.list().get(0);
			session.delete(blackList);
			transaction.commit();
		}
		session.flush();
		session.close();
		
		
	}
	
	
	
	

	

	
}