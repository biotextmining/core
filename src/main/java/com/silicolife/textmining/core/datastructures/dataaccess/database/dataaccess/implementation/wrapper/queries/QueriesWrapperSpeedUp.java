package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.queries;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryTypes;
import com.silicolife.textmining.core.datastructures.documents.query.QueryImpl;
import com.silicolife.textmining.core.datastructures.documents.query.QueryOriginTypeImpl;
import com.silicolife.textmining.core.interfaces.core.document.relevance.IQueryPublicationRelevance;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;
import com.silicolife.textmining.core.interfaces.process.IR.IQueryOriginType;

/**
 * 
 * Class to transform anote2 Query structures to daemon Query structures and
 * vice-verse
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class QueriesWrapperSpeedUp {

	public static QueryImpl convertToAnoteStructure(Queries queries) {
		/*
		 * create query type
		 */
		Long queryTypeId = queries.getQueryTypes().getQtId();
		String queryTypeDesc = queries.getQueryTypes().getQtDescription();
		if (queryTypeDesc == null)
			queryTypeDesc = "";

		QueryOriginTypeImpl queryType_ = new QueryOriginTypeImpl(queryTypeId, queryTypeDesc);

		/*
		 * create query
		 */
		Long id = queries.getQuId();
		Date date = queries.getQuQueryDate();
		String keywords = queries.getQuKeywords();
		String organism = queries.getQuOrganism();
		String completeQuery = queries.getQuCompleteQuery();
		Integer publicationsSize = queries.getQuMatchingPublications();
		Integer availableAbstract = queries.getQuAvailableAbstracts();
		String name = queries.getQuQueryName();
		String notes = queries.getQuNotes();

		if (keywords == null)
			keywords = "";
		if (organism == null)
			organism = "";
		if (completeQuery == null)
			completeQuery = "";
		if (publicationsSize == null)
			publicationsSize = 0;
		if (availableAbstract == null)
			availableAbstract = 0;
		if (name == null)
			name = "";
		if (notes == null)
			notes = "";

		QueryImpl query_ = new QueryImpl(id, queryType_, date, keywords, organism, completeQuery, publicationsSize, availableAbstract, name, notes,
				new HashMap<Long, IQueryPublicationRelevance>(), new Properties());

		return query_;
	}

	public static Queries convertToDaemonStructure(IQuery query_) {
		/*
		 * get parameters
		 */
		Long id = query_.getId();
		Date date = query_.getDate();
		String keywords = query_.getKeywords();
		String organism = query_.getOrganism();
		String completeQuery = query_.getCompleteQuery();
		Integer publicationsSize = query_.getPublicationsSize();
		Integer availableAbstract = query_.getAvailableAbstracts();
		String queryName = query_.getName();
		String notes = query_.getNotes();
		Boolean active = true;

		if (keywords.trim().equals(""))
			keywords = null;
		if (organism.trim().equals(""))
			organism = null;
		if (completeQuery.trim().equals(""))
			completeQuery = null;
		if (publicationsSize == 0)
			publicationsSize = null;
		if (availableAbstract == 0)
			availableAbstract = null;
		if (queryName.trim().equals(""))
			queryName = null;
		if (notes.trim().equals(""))
			notes = null;

		/*
		 * create query type
		 */
		IQueryOriginType queryType = query_.getQueryOriginType();
		QueryTypes queryTypeDaemon = null;
		if (queryType != null) {
			Long queryTypeId = queryType.getId();
			String querytypeDesc = queryType.getOrigin();
			if (querytypeDesc.trim() == "")
				querytypeDesc = null;
			queryTypeDaemon = new QueryTypes(queryTypeId);
			queryTypeDaemon.setQtDescription(querytypeDesc);
		}

		/*
		 * create query
		 */
		Queries query = new Queries(id, queryTypeDaemon, date, keywords, active);
		/*
		 * create query properties (empty because speed up)
		 */
		Set<QueryProperties> queriesProperties = new HashSet<QueryProperties>();
		/*
		 * set query data
		 */
		query.setQuOrganism(organism);
		query.setQuCompleteQuery(completeQuery);
		query.setQuMatchingPublications(publicationsSize);
		query.setQuAvailableAbstracts(availableAbstract);
		query.setQuQueryName(queryName);
		query.setQuNotes(notes);
		query.setQueryPropertieses(queriesProperties);

		return query;
	}
}
