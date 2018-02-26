package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasPublications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.DataProcessStatus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.DataProcessStatusEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ProcessStatusResourceTypesEnum;
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
	}
	
	@Override
	public List<DataProcessStatus> findDataProcessStatusForUserWithDateRangeAndSortByType(ProcessStatusResourceTypesEnum type,Long userId, Date from, Date to, boolean asc, String sortBy){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(DataProcessStatus.class, "dataProcessStatus");
		c.add(Restrictions.eq("dataProcessStatus.dpsTypeResource", type.name()));
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
	}
	
	@Override
	public List<DataProcessStatus> findDataProcessStatusForUserWithDateRangeAndSortByTypePaginated(ProcessStatusResourceTypesEnum type,Long userId, Date from, Date to,Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(DataProcessStatus.class, "dataProcessStatus");
		c.add(Restrictions.eq("dataProcessStatus.dpsTypeResource", type.name()));
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
	}
	
	@Override
	public List<DataProcessStatus> findDataProcessStatusForUserWithSortPaginated(Long userId,Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(DataProcessStatus.class, "dataProcessStatus");
		c.add(Restrictions.eq("dataProcessStatus.authUsers.auId", userId));
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
	}
	
	@Override
	public List<DataProcessStatus> findDataProcessStatusWithSortPaginated(Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(DataProcessStatus.class, "dataProcessStatus");
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
	}
	
	@Override
	public Integer countDataProcessStatus(){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(DataProcessStatus.class, "dataProcessStatus");
		Integer totalResult = ((Number) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		return totalResult;
	}
	
	
	@Override
	public Integer countDataProcessStatusforUser(Long userId){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(DataProcessStatus.class, "dataProcessStatus");
		c.add(Restrictions.eq("dataProcessStatus.authUsers.auId", userId));
		Integer totalResult = ((Number) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		return totalResult;
	}
	
	@Override
	public Integer countRunningOrStartDataProcessStatusforUser(Long userId){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(DataProcessStatus.class, "dataProcessStatus");
		c.add(Restrictions.eq("dataProcessStatus.authUsers.auId", userId));
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.eq("dataProcessStatus.dpsStatus", DataProcessStatusEnum.start.name()));
		disjunction.add(Restrictions.eq("dataProcessStatus.dpsStatus", DataProcessStatusEnum.running.name()));
		c.add(disjunction);
		Integer totalResult = ((Number) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		return totalResult;
	}
	
	@Override
	public List<DataProcessStatus> findDataProcessStatusWithSortPaginated(ProcessStatusResourceTypesEnum type,Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(DataProcessStatus.class, "dataProcessStatus");
		c.add(Restrictions.eq("dataProcessStatus.dpsTypeResource", type.name()));
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
	}
	
	@Override
	public List<DataProcessStatus> findDataProcessStatusForUserWithSortPaginated(
			ProcessStatusResourceTypesEnum type,Long userId,Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(DataProcessStatus.class, "dataProcessStatus");
		c.add(Restrictions.eq("dataProcessStatus.authUsers.auId", userId));
		c.add(Restrictions.eq("dataProcessStatus.dpsTypeResource", type.name()));
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
	}
	
	@Override
	public Integer countDataProcessStatus(ProcessStatusResourceTypesEnum type){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(DataProcessStatus.class, "dataProcessStatus");
		c.add(Restrictions.eq("dataProcessStatus.dpsTypeResource", type.name()));
		Integer totalResult = ((Number) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		return totalResult;
	}
	
	
	@Override
	public Integer countDataProcessStatusforUser(Long userId, ProcessStatusResourceTypesEnum type){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(DataProcessStatus.class, "dataProcessStatus");
		c.add(Restrictions.eq("dataProcessStatus.authUsers.auId", userId));
		c.add(Restrictions.eq("dataProcessStatus.dpsTypeResource", type.name()));
		Integer totalResult = ((Number) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		return totalResult;
	}
	
}
