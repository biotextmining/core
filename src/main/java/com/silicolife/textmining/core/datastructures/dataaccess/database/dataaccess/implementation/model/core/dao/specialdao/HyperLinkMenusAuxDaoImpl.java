package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkMenus;

@Repository
public class HyperLinkMenusAuxDaoImpl implements HyperLinkMenusAuxDao {

	private SessionFactory sessionFactory;
	
	@Autowired
	public HyperLinkMenusAuxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Long getNextHyperLinkMenusItemID() {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(HyperLinkMenus.class);
		c.addOrder(Order.desc("hylId"));
		c.setMaxResults(1);
		HyperLinkMenus lastMenuItem = (HyperLinkMenus)c.uniqueResult();
		long lastid = lastMenuItem.getHylId();
		lastid++;
		return lastid;
	}

}
