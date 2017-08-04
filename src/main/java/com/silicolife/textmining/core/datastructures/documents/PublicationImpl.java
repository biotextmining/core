package com.silicolife.textmining.core.datastructures.documents;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.documents.lables.PublicationLabelImpl;
import com.silicolife.textmining.core.datastructures.documents.structure.PublicationFieldImpl;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.init.general.GeneralDefaultSettings;
import com.silicolife.textmining.core.datastructures.init.propertiesmanager.PropertiesManager;
import com.silicolife.textmining.core.datastructures.utils.FileHandling;
import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.IPublicationExternalSourceLink;
import com.silicolife.textmining.core.interfaces.core.document.labels.IPublicationLabel;
import com.silicolife.textmining.core.interfaces.core.document.structure.IPublicationField;

public class PublicationImpl extends Observable implements IPublication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	protected String title;
	protected String authors;
	private String type;
	protected String yeardate;
	private String fulldate;
	private String status;
	protected String journal;
	private String volume;
	private String issue;
	private String pages;
	protected String abstractSection;
	private String externalLink; // publication external URL
	private boolean freeFullText;
	private String relativePath;
	private String notes;
	protected String fullTextContent; // plain text
	private String sourceURL;
	
	@JsonDeserialize(contentAs = PublicationExternalSourceLinkImpl.class)
	private List<IPublicationExternalSourceLink> publicationExternalIDSource;
	@JsonDeserialize(contentAs = PublicationFieldImpl.class)
	private List<IPublicationField> publicationFields;
	@JsonDeserialize(contentAs = PublicationLabelImpl.class)
	private List<IPublicationLabel> publicationLabels;

	// protected String sourceURL;

	public PublicationImpl() {
		this.id = GenerateRandomId.generateID();
		this.title = new String();
		this.authors = new String();
		this.type = new String();
		this.yeardate = new String();
		this.fulldate = new String();
		this.status = new String();
		this.journal = new String();
		this.volume = new String();
		this.issue = new String();
		this.pages = new String();
		this.abstractSection = new String();
		this.externalLink = new String();
		this.freeFullText = false;
		this.fullTextContent = null;
		this.relativePath = new String();
		this.notes = new String();
		this.publicationExternalIDSource = new ArrayList<>();
		this.publicationFields = new ArrayList<>();
		this.publicationLabels = new ArrayList<>();
	}

	public PublicationImpl(IPublication pub) {
		this(pub.getId(), pub.getTitle(), pub.getAuthors(), pub.getType(), pub.getYeardate(), pub.getFulldate(), pub.getStatus(), pub.getJournal(), pub.getVolume(),
				pub.getIssue(), pub.getPages(), pub.getAbstractSection(), pub.getExternalLink(), pub.isFreeFullText(), pub.getNotes(), pub.getRelativePath(), pub
						.getPublicationExternalIDSource(), pub.getPublicationFields(), pub.getPublicationLabels());
		setSourceURL(pub.getSourceURL());
	}

	public PublicationImpl(long id, String title, String authors, String type, String yeardate, String fulldate, String status, String journal, String volume, String issue,
			String pages, String abstractSection, String externalLink, boolean freeFullText, String notes, String relativePath,
			List<IPublicationExternalSourceLink> publicationExternalIDSource, List<IPublicationField> publicationFields, List<IPublicationLabel> publicationLabels) {
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.type = type;
		this.yeardate = yeardate;
		this.fulldate = fulldate;
		this.status = status;
		this.journal = journal;
		this.volume = volume;
		this.issue = issue;
		this.pages = pages;
		this.abstractSection = abstractSection;
		this.externalLink = externalLink;
		this.freeFullText = freeFullText;
		this.fullTextContent = null;
		this.relativePath = relativePath;
		this.notes = notes;
		this.publicationExternalIDSource = publicationExternalIDSource;
		this.publicationFields = publicationFields;
		this.publicationLabels = publicationLabels;
	}

	public PublicationImpl(String title, String authors, String type, String yeardate, String fulldate, String status, String journal, String volume, String issue, String pages,
			String abstractSection, String externalLink, boolean freeFullText, String notes, String relativePath, List<IPublicationExternalSourceLink> publicationExternalIDSource,
			List<IPublicationField> publicationFields, List<IPublicationLabel> publicationLabels) {
		this(GenerateRandomId.generateID(), title, authors, type, yeardate, fulldate, status, journal, volume, issue, pages, abstractSection, externalLink, freeFullText, notes,
				relativePath, publicationExternalIDSource, publicationFields, publicationLabels);
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	@Override
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getYeardate() {
		if(yeardate!=null && yeardate.length()>4)
			return yeardate.substring(0, 4);
		return yeardate;
	}

	public void setYeardate(String yeardate) {
		this.yeardate = yeardate;
	}

	@Override
	public String getFulldate() {
		return fulldate;
	}

	public void setFulldate(String fulldate) {
		this.fulldate = fulldate;
	}

	@Override
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	@Override
	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	@Override
	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	@Override
	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	@Override
	public String getAbstractSection() {
		return abstractSection;
	}

	public void setAbstractSection(String abstractSection) {
		this.abstractSection = abstractSection;
	}

	@Override
	public String getExternalLink() {
		return externalLink;
	}

	@Override
	public void setExternalLink(String externalLink) {
		this.externalLink = externalLink;
	}

	@Override
	public boolean isFreeFullText() {
		return freeFullText;
	}

	public void setFreeFullText(boolean freeFullText) {
		this.freeFullText = freeFullText;
	}

	@Override
	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@JsonIgnore
	@Override
	public String getFullTextContent() {
		if(fullTextContent==null)
		{
			try {
				if(InitConfiguration.getDataAccess() != null)
					fullTextContent = InitConfiguration.getDataAccess().getPublicationFullText(this);
			} catch (ANoteException e) {
				e.printStackTrace();
				return new String();
			}
			if(fullTextContent==null)
			{
				return new String();
			}
		}
		return fullTextContent;
	}

	@JsonIgnore
	public void setFullTextContent(String fullTextContent) {
		this.fullTextContent = fullTextContent;
	}

	@Override
	public List<IPublicationExternalSourceLink> getPublicationExternalIDSource() {
		return publicationExternalIDSource;
	}

	public void setPublicationExternalIDSource(List<IPublicationExternalSourceLink> publicationExternalIDSource) {
		this.publicationExternalIDSource = publicationExternalIDSource;
	}

	@Override
	public List<IPublicationField> getPublicationFields() {
		return publicationFields;
	}

	public void setPublicationFields(List<IPublicationField> publicationFields) {
		this.publicationFields = publicationFields;
	}

	@Override
	public List<IPublicationLabel> getPublicationLabels() {
		return publicationLabels;
	}

	public void setPublicationLabels(List<IPublicationLabel> publicationLabels) {
		this.publicationLabels = publicationLabels;
	}

	@JsonIgnore
	public Boolean isPDFAvailable() {
		String saveDocDirectoty;
		if(InitConfiguration.getPropertyValue(GeneralDefaultSettings.PDFDOCDIRECTORY)!=null)
		{
			saveDocDirectoty = InitConfiguration.getPropertyValue(GeneralDefaultSettings.PDFDOCDIRECTORY);
		}
		else
		{
			saveDocDirectoty = (String) PropertiesManager.getPManager().getProperty(GeneralDefaultSettings.PDFDOCDIRECTORY);
		}
		if(saveDocDirectoty!=null)
		{
			String filePath = saveDocDirectoty + "//" + getRelativePath();
			String fileIDPath = saveDocDirectoty + "//" + String.valueOf(getId()) + ".pdf";
			if (new File(filePath).exists() && new File(filePath).isFile() || new File(fileIDPath).exists() && new File(filePath).isFile()) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		}
		else
			return Boolean.FALSE;
	}

	public String toString() {
		String result = new String();
		result = result + "(" + getId() + ") ";
		if(getYeardate()!=null)
			result = result + " ["+getYeardate()+"] ";
		for (IPublicationExternalSourceLink externalSourceLinks : this.publicationExternalIDSource)
			result = result + externalSourceLinks.getSource() + ": " + externalSourceLinks.getSourceInternalId() + " ";
		if (getTitle() != null && !getTitle().isEmpty())
			result = result + "Title : " + getTitle() + ") ";
		if (getAbstractSection() != null && !getAbstractSection().isEmpty())
			result = result + "Abstract : " + getAbstractSection() + ") ";
		return result;
	}

	@Override
	public void addPDFFile(File file) throws IOException {
		String saveDocDirectoty;
		if(InitConfiguration.getPropertyValue(GeneralDefaultSettings.PDFDOCDIRECTORY)!=null)
		{
			saveDocDirectoty = InitConfiguration.getPropertyValue(GeneralDefaultSettings.PDFDOCDIRECTORY);
		}
		else
		{
			saveDocDirectoty = (String) PropertiesManager.getPManager().getProperty(GeneralDefaultSettings.PDFDOCDIRECTORY);
		}
		if(isPDFAvailable())
		{
			throw new IOException("File already Exists");
		}
		if(saveDocDirectoty!=null)
		{
			String reference = getId() + ".pdf";
			File fileDest = new File(saveDocDirectoty + "//" +reference);
			if (!new File(saveDocDirectoty).exists())
				new File(saveDocDirectoty).mkdir();
			fileDest.createNewFile();
			FileHandling.copy(file, fileDest);
			this.relativePath = reference;
		}
		else
			throw new IOException("To Add A document user needs to configure Docs Directory (General.PDFDirectoryDocuments) in settings");
	}

	public static String getPublicationExternalIDForSource(IPublication publication, String source) {
		for (IPublicationExternalSourceLink links : publication.getPublicationExternalIDSource()) {
			if (links.getSource().equals(source))
				return links.getSourceInternalId().trim();
		}
		return null;
	}
	
	public static Set<String> getPublicationExternalIDSetForSource(IPublication publication, String source) {
		Set<String> out = new HashSet<>();
		for (IPublicationExternalSourceLink links : publication.getPublicationExternalIDSource()) {
			if (links.getSource().equals(source))
				out.add(links.getSourceInternalId().trim());
		}
		return out;
	}

	public static String getPublicationExternalIDsStream(IPublication publication) {
		String result = new String();
		result = result + "ID:" + publication.getId();
		for (IPublicationExternalSourceLink links : publication.getPublicationExternalIDSource()) 
		{
			result = result + " " + links.getSource() + ":" + links.getSourceInternalId();
		}
		return result;
	}

	public static String getLabelsStream(IPublication publication) {
		String result = new String();
		for (IPublicationLabel label : publication.getPublicationLabels()) {
			result = result + " " + label.getLabel();
		}
		return result;
	}

	@Override
	public String getSourceURL() {
		return sourceURL;
	}

	@Override
	public void setSourceURL(String sourceURL) {
		this.sourceURL=sourceURL;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PublicationImpl other = (PublicationImpl) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String toString2() {
		String toString = new String();
		toString = toString + "{" + "\n";
		toString = toString + "id="+id + "\n";
		toString = toString + "publicationExternalIDSource="+publicationExternalIDSource+ "\n";
		toString = toString + "title="+title+ "\n";
		toString = toString + "authors="+authors+ "\n";
		toString = toString + "yeardate="+yeardate+ "\n";
		toString = toString + "fulldate="+fulldate+ "\n";	
		toString = toString + "notes="+notes+ "\n";
		toString = toString + "externalLink="+externalLink+ "\n";
		toString = toString + "abstractSection="+abstractSection+ "\n";
		toString = toString + "}\n";
		return toString;
	}
	
	

}