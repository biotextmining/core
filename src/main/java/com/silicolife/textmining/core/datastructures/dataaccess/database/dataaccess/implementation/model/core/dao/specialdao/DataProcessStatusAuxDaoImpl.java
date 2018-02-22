package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasPublications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.DataProcessStatus;
import com.silicolife.textmining.core.datastructures.documents.PublicationFieldsEnum;
import com.silicolife.textmining.core.datastructures.general.DataProcessStatusFieldsEnum;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;

@Repository
public class DataProcessStatusAuxDaoImpl implements DataProcessStatusAuxDao{
	
	private SessionFactory sessionFactory;
	
	public DataProcessStatusAuxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<DataProcessStatus> findDataProcessStatusForUser(Long userId){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(DataProcessStatus.class, "dataProcessStatus");
		c.add(Restrictions.eq("dataProcessStatus.authUsers.auId", userId));
		
		List<DataProcessStatus> data = c.list();
		return data;
		//Integer totalResult = ((Number) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}
	
	@Override
	public List<DataProcessStatus> findDataProcessStatusForUserWithDateRange(Long userId, Date from, Date to){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(DataProcessStatus.class, "dataProcessStatus");
		c.add(Restrictions.eq("dataProcessStatus.authUsers.auId", userId));
		c.add(Restrictions.ge("dataProcessStatus.dpsUpdateDate", from));
		c.add(Restrictions.lt("dataProcessStatus.dpsUpdateDate", to));
		
		List<DataProcessStatus> data = c.list();
		return data;
		//Integer totalResult = ((Number) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}
	
	
	@Override
	public List<DataProcessStatus> findDataProcessStatusForUserWithDateRangeAndSort(Long userId, Date from, Date to, boolean asc, String sortBy){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(DataProcessStatus.class, "dataProcessStatus");
		c.add(Restrictions.eq("dataProcessStatus.authUsers.auId", userId));
		c.add(Restrictions.ge("dataProcessStatus.dpsUpdateDate", from));
		c.add(Restrictions.lt("dataProcessStatus.dpsUpdateDate", to));
		if(!DataProcessStatusFieldsEnum.valueOf(sortBy).equals(DataProcessStatusFieldsEnum.none)){
		String uniqueId = DataProcessStatusFieldsEnum.valueOf(sortBy).getUniqueIdentifier();
		String sortAlias = "dataProcessStatus."+uniqueId;
		if(asc)
			c.addOrder(Order.asc(sortAlias));
		else
			c.addOrder(Order.desc(sortAlias));
		}
		
		List<DataProcessStatus> data = c.list();
		return data;
		//Integer totalResult = ((Number) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}
	
	@Override
	public List<DataProcessStatus> findDataProcessStatusForUserWithDateRangeAndSortPaginated(Long userId, Date from, Date to,Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(DataProcessStatus.class, "dataProcessStatus");
		c.add(Restrictions.eq("dataProcessStatus.authUsers.auId", userId));
		c.add(Restrictions.ge("dataProcessStatus.dpsUpdateDate", from));
		c.add(Restrictions.lt("dataProcessStatus.dpsUpdateDate", to));
		if(!DataProcessStatusFieldsEnum.valueOf(sortBy).equals(DataProcessStatusFieldsEnum.none)){
		String uniqueId = DataProcessStatusFieldsEnum.valueOf(sortBy).getUniqueIdentifier();
		String sortAlias = "dataProcessStatus."+uniqueId;
		if(asc)
			c.addOrder(Order.asc(sortAlias));
		else
			c.addOrder(Order.desc(sortAlias));
		}
		c.setFirstResult(paginationIndex);
		c.setMaxResults(paginationSize);
		c.setFetchSize(paginationSize);
		
		List<DataProcessStatus> data = c.list();
		return data;
		//Integer totalResult = ((Number) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}
	
}
