package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProcesses;

@Repository
public class ClustersProcessAuxDaoImpl implements ClustersAuxDao {

	private SessionFactory sessionFactory;

	@Autowired
	public ClustersProcessAuxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClusterProcesses> findClustersByQueryId(Long queryId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(ClusterProcesses.class, "clusters");
		c.setFetchMode("clusters.queryHasClusterProcesseses", FetchMode.JOIN);
		c.createAlias("clusters.queryHasClusterProcesseses", "queriesHasClusters");
		c.add(Restrictions.eq("queriesHasClusters.id.qhcQueryId", queryId));
		c.add(Restrictions.eq("clusters.clpActive", true));

		List<ClusterProcesses> clustersProcesses = c.list();
		return clustersProcesses;
	}

}
