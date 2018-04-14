package com.bbs.daoImpl;

import com.bbs.dao.BestPostDao;
import com.bbs.hibernate.factory.BaseHibernateDAO;
import com.bbs.model.BestPost;
import com.bbs.model.Post;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * BestPost entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bbs.model.BestPost
 * @author MyEclipse Persistence Tools
 */
public class BestPostDaoImpl extends BaseHibernateDAO implements BestPostDao{
	private static final Logger log = LoggerFactory
			.getLogger(BestPostDaoImpl.class);



	



	public BestPost findById(java.lang.Integer id) {
		log.debug("getting BestPost instance with id: " + id);
		try {
			Session session = getSession();
			BestPost instance = (BestPost)session .get(
					"com.bbs.model.BestPost", id);
			session.close();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BestPost instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BestPost as model where model."
					+ propertyName + "= ?";
			Session session = getSession();
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List list = queryObject.list();
			session.close();
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	



	@Override
	public void insert(BestPost post) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		session.save(post);
		beginTransaction.commit();
		 session.flush();
		session.close();
		
	}

	@Override
	public List<BestPost> getBestPosts(int pageIndex, int pageSize) {
		Session session = getSession();
		String sql = "from BestPost post order by post.applyDate desc";
		Query query = session.createQuery(sql);
		int startIndex = (pageIndex -1) * pageSize;
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		List list = query.list();
		 session.flush();
			session.close();
		return list;
	}

	@Override
	public void delete(int postId) {
		BestPost post = findById(postId);
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		session.delete(post);
		beginTransaction.commit();
		 session.flush();
			session.close();
	}

	@Override
	public void updateState(Integer postId, int state) {
		Session session = getSession();
		String sql = "from BestPost p where p.post.id = ?";
		Query query = session.createQuery(sql);
		query.setInteger(0, postId);
		List<BestPost> posts = query.list();
		
		if (posts != null && posts.size()>0){
			BestPost post = posts.get(0);
			post.setState(state);
			Transaction beginTransaction = session.beginTransaction();
			session.update(post);
			beginTransaction.commit();
			 session.flush();
				session.close();
			
		}else {
			 session.flush();
				session.close();
		}
		
		
	}

	@Override
	public List<BestPost> getRecordsByUserId(int userId,int pageIndex,int pageSize) {
		Session session = getSession();
		String sql = "from BestPost p where p.user.id = ?";
		Query query = session.createQuery(sql);
		query.setInteger(0, userId);
		int startIndex = (pageIndex -1) * pageSize;
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		 List<BestPost>  bestPosts = query.list();
		 session.flush();
			session.close();
		return bestPosts;
	}

	@Override
	public boolean isExist(int postId) {
		Session session = getSession();
		String sql = "from BestPost p where p.post.id = ?";
		Query query = session.createQuery(sql);
		query.setInteger(0, postId);
		List<BestPost> posts = query.list();
		session.flush();
		session.close();
		if (posts != null && posts.size()>0){
			return true;
		}
		return false;
	}
}