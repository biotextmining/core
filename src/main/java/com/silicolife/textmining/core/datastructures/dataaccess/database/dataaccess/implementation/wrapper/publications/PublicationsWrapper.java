package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.publications;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationFields;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.documents.PublicationImpl;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.IPublicationExternalSourceLink;
import com.silicolife.textmining.core.interfaces.core.document.labels.IPublicationLabel;
import com.silicolife.textmining.core.interfaces.core.document.structure.IPublicationField;

/**
 * Class to transform anote2 Publications structures to daemon Publications
 * structures and vice-verse
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class PublicationsWrapper {

	public static PublicationImpl convertToAnoteStructure(Publications publications) {
		/*
		 * get publications parameters
		 */
		Long id = publications.getPubId();
		String title = publications.getPubTitle();
		if (title == null)
			title = "";
		String authors = publications.getPubAuthors();
		if (authors == null)
			authors = "";
		String type = publications.getPubCategory();
		if (type == null)
			type = "";
		String yearDate = null;
		if (publications.getPubYeardate() != null)
			yearDate = String.valueOf(publications.getPubYeardate());
		else
			yearDate = "";
		String fullDate = publications.getPubFulldate();
		if (fullDate == null)
			fullDate = "";
		String status = publications.getPubStatus();
		if (status == null)
			status = "";
		String journal = publications.getPubJournal();
		if (journal == null)
			journal = "";
		String volume = publications.getPubVolume();
		if (volume == null)
			volume = "";
		String issue = publications.getPubIssue();
		if (issue == null)
			issue = "";
		String pages = publications.getPubPages();
		if (pages == null)
			pages = "";
		String abstractSection = publications.getPubAbstract();
		if (abstractSection == null)
			abstractSection = "";
		String externalLink = publications.getPubExternalLinks();
		boolean freefulltextAvailable = false;
		if (publications.getPubFreeFullText() != null)
			freefulltextAvailable = publications.getPubFreeFullText();
		String notes = publications.getPubNotes();
		if (notes == null)
			notes = "";
		String relativePath = publications.getPubRelativePath();
		if (relativePath == null)
			relativePath = "";

		/*
		 * wrapper publications has sources
		 */
		Set<PublicationHasSources> publicationsHasSources = publications.getPublicationHasSourceses();
		List<IPublicationExternalSourceLink> externalIDsSource_ = new ArrayList<IPublicationExternalSourceLink>();
		// if (publicationsHasSources.size() > 0) {
		// externalIDsSource_ = new ArrayList<IPublicationExternalSourceLink>();
		for (PublicationHasSources pubSource : publicationsHasSources) {
			IPublicationExternalSourceLink pubExternal_ = PublicationsSourceWrapper.convertToAnoteStructure(pubSource);
			externalIDsSource_.add(pubExternal_);
		}
		// }
		/*
		 * wrapper publications has fields
		 */
		Set<PublicationFields> publicationsHasFields = publications.getPublicationFieldses();
		List<IPublicationField> fullTextfields_ = new ArrayList<IPublicationField>();
		// if (publicationsHasFields.size() > 0) {
		// fullTextfields_ = new ArrayList<IPublicationField>();
		for (PublicationFields pubField : publicationsHasFields) {
			IPublicationField pubField_ = PublicationsFieldsWrapper.convertToAnoteStructure(pubField);
			fullTextfields_.add(pubField_);
		}
		// }
		/*
		 * wrapper publications has labels
		 */
		Set<PublicationHasLabels> publicationsHasLabels = publications.getPublicationHasLabelses();
		List<IPublicationLabel> labels_ = new ArrayList<IPublicationLabel>();
		// if (publicationsHasLabels.size() > 0) {
		// labels_ = new ArrayList<IPublicationLabel>();
		for (PublicationHasLabels pubLabel : publicationsHasLabels) {
			IPublicationLabel pubLabel_ = PublicationsLabelsWrapper.convertToAnoteStructure(pubLabel.getPublicationLabels());
			labels_.add(pubLabel_);
		}
		// }
		/*
		 * create publication
		 */
		PublicationImpl publication_ = new PublicationImpl(id, title, authors, type, yearDate, fullDate, status, journal, volume, issue, pages, abstractSection, externalLink,
				freefulltextAvailable, notes, relativePath, externalIDsSource_, fullTextfields_, labels_);

		return publication_;
	}

	public static Publications convertToDaemonStructure(IPublication publications_) {
		/*
		 * get publications parameters
		 */
		Long id = publications_.getId();
		String title = publications_.getTitle();
		if(title == null || title.isEmpty())
			title = null;
		String authors = publications_.getAuthors();
		if(authors == null || authors.isEmpty())
			authors = null;
		String type = publications_.getType();
		if(type == null || type.isEmpty())
			type = null;
		String yearDate = publications_.getYeardate();
		if(yearDate==null || yearDate.isEmpty())
			yearDate = null;
		String fullDate = publications_.getFulldate();
		if(fullDate == null | fullDate.isEmpty())
			fullDate = null;
		String status = publications_.getStatus();
		if(status == null || status.isEmpty())
			status = null;
		String journal = publications_.getJournal();
		if(journal == null || journal.isEmpty())
			journal = null;
		String volume = publications_.getVolume();
		if(volume == null || volume.isEmpty())
			volume = null;
		String issue = publications_.getIssue();
		if(issue ==  null || issue.isEmpty())
			issue = null;
		String pages = publications_.getPages();
		if(pages == null || pages.isEmpty())
			pages = null;
		String abstractSection = publications_.getAbstractSection();
		if(abstractSection ==null || abstractSection.trim().isEmpty())
			abstractSection = null;
		String externalLink = publications_.getExternalLink();
		if(externalLink == null || externalLink.trim().isEmpty())
			externalLink = null;
		Boolean freefulltextAvailable = publications_.isFreeFullText();

		String notes = publications_.getNotes();
		if(notes == null || notes.trim().isEmpty())
			notes = null;
		String relativePath = publications_.getRelativePath();
		if(relativePath == null || relativePath.trim().isEmpty())
			relativePath = null;

		Publications publication = new Publications();
		publication.setPubId(id);
		publication.setPubTitle(title);
		publication.setPubAuthors(authors);
		publication.setPubCategory(type);
		if (yearDate != null) {
			publication.setPubYeardate(Integer.parseInt(yearDate));
		}
		publication.setPubFulldate(fullDate);
		publication.setPubStatus(status);
		publication.setPubJournal(journal);
		publication.setPubVolume(volume);
		publication.setPubIssue(issue);
		publication.setPubPages(pages);
		publication.setPubAbstract(abstractSection);
		publication.setPubExternalLinks(externalLink);
		publication.setPubFreeFullText(freefulltextAvailable);
		publication.setPubNotes(notes);
		publication.setPubRelativePath(relativePath);

		return publication;

	}
}
