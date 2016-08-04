package com.silicolife.textmining.core.datastructures.documents.query;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Properties;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.documents.PublicationImpl;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.relevance.IQueryPublicationRelevance;
import com.silicolife.textmining.core.interfaces.core.document.relevance.RelevanceTypeEnum;
import com.silicolife.textmining.core.interfaces.process.IR.IIRSearch;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;
import com.silicolife.textmining.core.interfaces.process.IR.IQueryOriginType;

/**
 * That class saves information about parameters of one Query to
 * {@link IIRSearch} process
 * 
 * @author Hugo Costa
 * 
 *
 */
public class QueryImpl extends Observable implements IQuery {

	private long id;
	@JsonDeserialize(as = QueryOriginTypeImpl.class)
	private IQueryOriginType queryOriginType;
	private Date date;
	private String keywords;
	private String organism;
	private String completeQuery;
	private String name;
	private String notes;
	private Properties properties;
	private int publicationsSize;
	private int availableAbstracts;
	@JsonDeserialize(contentAs = QueryPublicationRelevanceImpl.class)
	private Map<Long, IQueryPublicationRelevance> publicationsRelevance;
	@JsonDeserialize(contentAs = PublicationImpl.class)
	protected List<IPublication> publications;

	public QueryImpl() {
	}

	public QueryImpl(long id, IQueryOriginType queryOriginType, Date date, String keywords, String organism, String completeQuery, int publicationsSize, int availableAbstracts,
			String name, String notes, Map<Long, IQueryPublicationRelevance> publicationsRelevance, Properties properties) {
		this.id = id;
		this.queryOriginType = queryOriginType;
		this.date = date;
		this.keywords = keywords;
		this.organism = organism;
		this.completeQuery = completeQuery;
		this.publicationsSize = publicationsSize;
		this.availableAbstracts = availableAbstracts;
		this.name = name;
		this.notes = notes;
		this.properties = properties;
		this.publicationsRelevance = publicationsRelevance;
		this.publications = null;
	}

	public QueryImpl(IQueryOriginType queryOriginType, Date date, String keywords, String organism, String completeQuery, int publicationsSize, int availableAbstracts,
			String name, String notes, Map<Long, IQueryPublicationRelevance> publicationsRelevance, Properties properties) {
		this(GenerateRandomId.generateID(), queryOriginType, date, keywords, organism, completeQuery, publicationsSize, availableAbstracts, name, notes, publicationsRelevance,
				properties);
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public IQueryOriginType getQueryOriginType() {
		return queryOriginType;
	}

	public void setQueryOriginType(IQueryOriginType queryOriginType) {
		this.queryOriginType = queryOriginType;
	}

	@Override
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Override
	public String getOrganism() {
		return organism;
	}

	public void setOrganism(String organism) {
		this.organism = organism;
	}

	@Override
	public String getCompleteQuery() {
		return completeQuery;
	}

	public void setCompleteQuery(String completeQuery) {
		this.completeQuery = completeQuery;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public int getPublicationsSize() {
		if (publications != null)
		{
			return publications.size();
		}
		else
			return publicationsSize;
	}

	public void setPublicationsSize(int publicationsSize) {
		this.publicationsSize = publicationsSize;
	}

	@Override
	public int getAvailableAbstracts() {
		return availableAbstracts;
	}

	public void setAvailableAbstracts(int availableAbstracts) {
		this.availableAbstracts = availableAbstracts;
	}

	@Override
	public Map<Long, IQueryPublicationRelevance> getPublicationsRelevance() {
		return publicationsRelevance;
	}

	public void setPublicationsRelevance(Map<Long, IQueryPublicationRelevance> publicationsRelevance) {
		this.publicationsRelevance = publicationsRelevance;
	}

	@JsonIgnore
	@Override
	public synchronized List<IPublication> getPublications() throws ANoteException {
		if (publications == null) {
			publications = InitConfiguration.getDataAccess().getQueryPublications(this);
		}

		return publications;
	}

	public void setPublications(List<IPublication> publications) {
		this.publications = publications;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		if (this.name != null && this.name.length() > 0) {
			str.append("Query Name : " + this.name + "  \n");
			return str.toString();
		}
		str.append("Query (ID) :" + this.id + "  \n");
		if (this.keywords != null && this.keywords.length() > 0)
			str.append("Keywords : " + this.keywords + "   \n");
		if (this.organism != null && this.organism.length() > 0)
			str.append("Organism : " + this.organism + "  \n");
		if (this.date != null)
			str.append("Date : " + this.date.toString() + "  \n");
		return str.toString();

	}

	/**
	 * Add Outside Publication to a Query
	 * 
	 * @param publication
	 * @param publicationType
	 * @param file
	 * @param relevance
	 * @throws SQLException
	 * @throws DatabaseLoadDriverException
	 * @throws IOException
	 * @throws DaemonException
	 */
	public void addOusidePublication(IPublication publication, File file, RelevanceTypeEnum relevance) throws ANoteException, IOException {
		if (file != null)
			publication.addPDFFile(file);
		publicationsSize++;
		if (publication.getAbstractSection() != null && publication.getAbstractSection().length() > 0)
			availableAbstracts++;
		this.publications.add(publication);
		Set<IPublication> publications = new HashSet<>();
		publications.add(publication);
		InitConfiguration.getDataAccess().addPublications(publications);
		InitConfiguration.getDataAccess().addQueryPublications(this, publications);
		updatePublicationRelevance(publication, relevance);
		InitConfiguration.getDataAccess().updateQuery(this);
	}

	/**
	 * Add Outside Publication (that aren´t in DB yet) to a Query
	 * 
	 * @param publication
	 * @param publicationType
	 * @param file
	 * @param relevance
	 * @throws SQLException
	 * @throws DatabaseLoadDriverException
	 * @throws DaemonException
	 * @throws IOException
	 */
	public void addOusidePublications(Set<IPublication> publications) throws ANoteException {
		for (IPublication publication : publications) {
			publicationsSize++;
			if (publication.getAbstractSection() != null && publication.getAbstractSection().length() > 0)
				availableAbstracts++;
			if (!publicationsRelevance.containsKey(publication.getId()))
				publicationsRelevance.put(publication.getId(), new QueryPublicationRelevanceImpl());
		}
		this.publications.addAll(publications);
		InitConfiguration.getDataAccess().addPublications(publications);
		InitConfiguration.getDataAccess().addQueryPublications(this, publications);
		InitConfiguration.getDataAccess().updateQuery(this);
	}

	/**
	 * Add a List of Publictions already in Database to Query
	 * 
	 * 
	 * @param publications
	 * @throws SQLException
	 * @throws DatabaseLoadDriverException
	 * @throws DaemonException
	 */
	public void addInsidePublications(List<IPublication> publications) throws ANoteException {
		List<IPublication> publicationAlreayInQuery = getPublications();
		Set<Long> publicationIDs = new HashSet<Long>();
		for (IPublication pubQ : publicationAlreayInQuery)
			publicationIDs.add(pubQ.getId());
		Set<IPublication> publicationsToInsert = new HashSet<>();
		for (IPublication publication : publications) {
			if (!publicationIDs.contains(publication.getId())) {
				publicationsToInsert.add(publication);
				publicationsSize++;
				if (publication.getAbstractSection() != null && publication.getAbstractSection().length() > 0)
					availableAbstracts++;
			}
			if (!publicationsRelevance.containsKey(publication.getId()))
				publicationsRelevance.put(publication.getId(), new QueryPublicationRelevanceImpl());
		}
		InitConfiguration.getDataAccess().addQueryPublications(this, publicationsToInsert);
		InitConfiguration.getDataAccess().updateQuery(this);
	}

	/**
	 * Update {@link IPublication} {@link RelevanceTypeEnum}
	 * 
	 * @param publication
	 * @param relevance
	 * @throws SQLException
	 * @throws DatabaseLoadDriverException
	 * @throws DaemonException
	 */
	public void updatePublicationRelevance(IPublication publication, RelevanceTypeEnum newRelevance) throws ANoteException {
		if (!publicationsRelevance.containsKey(publication.getId()))
			publicationsRelevance.put(publication.getId(), new QueryPublicationRelevanceImpl());
		publicationsRelevance.get(publication.getId()).setRelevance(newRelevance);
		InitConfiguration.getDataAccess().updateQueryDocumentRelevance(publication, this, publicationsRelevance.get(publication.getId()));
	}

	/**
	 * Get Document Relevance given Document database ID
	 * 
	 * @param documentID
	 * @return
	 * @throws SQLException
	 * @throws DatabaseLoadDriverException
	 * @throws DaemonException
	 */
	public IQueryPublicationRelevance getDocumentRelevanceType(long documentID) {
		return publicationsRelevance.get(documentID);
	}

	public void notifyViewObserver() {
		this.setChanged();
		notifyObservers();
	}
}
