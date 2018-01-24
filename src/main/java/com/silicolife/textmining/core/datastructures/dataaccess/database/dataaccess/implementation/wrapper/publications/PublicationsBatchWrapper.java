package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.publications;

import java.util.ArrayList;
import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.documents.PublicationImpl;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.IPublicationExternalSourceLink;
import com.silicolife.textmining.core.interfaces.core.document.labels.IPublicationLabel;
import com.silicolife.textmining.core.interfaces.core.document.structure.IPublicationField;

public class PublicationsBatchWrapper {
	
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
		String category = publications.getPubCategory();
		if (category == null)
			category = "";
		String type = publications.getPubType();
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
		 * 
		 * External Ids, labels and fields are ignored!
		 * 
		 */
		List<IPublicationExternalSourceLink> externalIDsSource_ = new ArrayList<IPublicationExternalSourceLink>();
		List<IPublicationField> fullTextfields_ = new ArrayList<IPublicationField>();
		List<IPublicationLabel> labels_ = new ArrayList<IPublicationLabel>();

		/*
		 * create publication
		 */
		PublicationImpl publication_ = new PublicationImpl(id, title, authors, category, yearDate, fullDate, status, journal, volume, issue, pages, abstractSection, externalLink,
				freefulltextAvailable, notes, relativePath,type, externalIDsSource_, fullTextfields_, labels_);

		return publication_;
		
	}
	
	public static Publications convertToDaemonStructure(IPublication publications_) {
		/*
		 * get publications parameters
		 */
		Long id = publications_.getId();
		String title = publications_.getTitle();
		if(title.trim().equals(""))
			title = null;
		String authors = publications_.getAuthors();
		if(authors.trim().equals(""))
			authors = null;
		String category = publications_.getCategory();
		if(category.trim().equals(""))
			category = null;
		String type = publications_.getType();
		if(type.trim().equals(""))
			type = null;
		String yearDate = publications_.getYeardate();
		if(yearDate.trim().equals(""))
			yearDate = null;
		String fullDate = publications_.getFulldate();
		if(fullDate.trim().equals(""))
			fullDate = null;
		String status = publications_.getStatus();
		if(status.trim().equals(""))
			status = null;
		String journal = publications_.getJournal();
		if(journal.trim().equals(""))
			journal = null;
		String volume = publications_.getVolume();
		if(volume.trim().equals(""))
			volume = null;
		String issue = publications_.getIssue();
		if(issue.trim().equals(""))
			issue = null;
		String pages = publications_.getPages();
		if(pages.trim().equals(""))
			pages = null;
		String abstractSection = publications_.getAbstractSection();
		if(abstractSection.trim().equals(""))
			abstractSection = null;
		String externalLink = publications_.getExternalLink();
		if(externalLink.trim().equals(""))
			externalLink = null;
		Boolean freefulltextAvailable = publications_.isFreeFullText();

		String notes = publications_.getNotes();
		if(notes.trim().equals(""))
			notes = null;
		String relativePath = publications_.getRelativePath();
		if(relativePath.trim().equals(""))
			relativePath = null;

		Publications publication = new Publications();
		publication.setPubId(id);
		publication.setPubTitle(title);
		publication.setPubAuthors(authors);
		publication.setPubCategory(category);
		publication.setPubType(type);
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
