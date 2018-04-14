package com.bbs.daoImpl;

import com.bbs.dao.MainForumDao;
import com.bbs.hibernate.factory.BaseHibernateDAO;
import com.bbs.model.MainForum;

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
 * A data access object (DAO) providing persistence and search support for
 * MainForum entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bbs.model.MainForum
 * @author MyEclipse Persistence Tools
 */
public class MainForumDaoImpl extends BaseHibernateDAO implements MainForumDao {
	private static final Logger log = LoggerFactory
			.getLogger(MainForumDaoImpl.class);
	// property constants
	public static final String TITLE = "title";
	public static final String INFO = "info";

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#save(com.bbs.model.MainForum)
	 */
	@Override
	public void save(MainForum transientInstance) {
		log.debug("saving MainForum instance");
		try {
			Session session = getSession();
			Transaction beginTransaction = session.beginTransaction();
			session.save(transientInstance);
			beginTransaction.commit();
			session.close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#delete(com.bbs.model.MainForum)
	 */
	@Override
	public void delete(MainForum persistentInstance) {
		log.debug("deleting MainForum instance");
		try {
			Session session = getSession();
			session.delete(persistentInstance);
			session.flush();
			session.close();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#findById(java.lang.Integer)
	 */
	@Override
	public MainForum findById(java.lang.Integer id) {
		log.debug("getting MainForum instance with id: " + id);
		try {
			Session session = getSession();
			MainForum instance = (MainForum)session .get(
					"com.bbs.model.MainForum", id);
			session.flush();
			session.close();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MainForum instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MainForum as model where model."
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

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#findByTitle(java.lang.Object)
	 */
	@Override
	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#findByInfo(java.lang.Object)
	 */
	@Override
	public List findByInfo(Object info) {
		return findByProperty(INFO, info);
	}

	

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#merge(com.bbs.model.MainForum)
	 */
	
	public List findAll() {
		log.debug("finding all MainForum instances");
		try {
			String queryString = "from MainForum";
			Session session = getSession();
			Query queryObject = session.createQuery(queryString);
			List list = queryObject.list();
			session.flush();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	
	
	
	

	
}