package com.bbs.daoImpl;

import com.bbs.dao.SubForumDao;
import com.bbs.hibernate.factory.BaseHibernateDAO;
import com.bbs.model.SubForum;

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
 * SubForum entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bbs.model.SubForum
 * @author MyEclipse Persistence Tools
 */
public class SubForumDaoImpl extends BaseHibernateDAO implements SubForumDao{
	private static final Logger log = LoggerFactory
			.getLogger(SubForumDaoImpl.class);
	// property constants
	public static final String TITLE = "title";
	public static final String INFO = "info";

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.SubFormDaoImpl#save(com.bbs.model.SubForum)
	 */
	@Override
	public void save(SubForum transientInstance) {
		log.debug("saving SubForum instance");
		try {
			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			session.save(transientInstance);
			transaction.commit();
			session.close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	
}