package com.bbs.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bbs.dao.FollowcardDao;
import com.bbs.hibernate.factory.BaseHibernateDAO;
import com.bbs.model.Followcard;

public class FollowcardDaoImpl extends BaseHibernateDAO implements FollowcardDao{
	
	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.FollowcardDao#save(com.bbs.model.Followcard)
	 */
	@Override
	public void save(Followcard followcard){
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.save(followcard);
		transaction.commit();
		session.flush();
		session.close();
	}
	
	

}
