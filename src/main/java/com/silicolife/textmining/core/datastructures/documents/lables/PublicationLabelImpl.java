package com.silicolife.textmining.core.datastructures.documents.lables;

import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.interfaces.core.document.labels.IPublicationLabel;

public class PublicationLabelImpl implements IPublicationLabel {

	private long id;
	private String label;

	public PublicationLabelImpl() {

	}

	public PublicationLabelImpl(long id, String label) {
		this.id = id;
		this.label = label;
	}

	public PublicationLabelImpl(String label) {
		this(GenerateRandomId.generateID(), label);
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String toString() {
		return label;
	}

	@Override
	public int hashCode() {
		return label.trim().hashCode();
	}

	@Override
	public boolean equals(Object pubLabel) {
		if (pubLabel instanceof PublicationLabelImpl) {
			PublicationLabelImpl other = (PublicationLabelImpl) pubLabel;
			if (label.trim().equals(other.label.trim())) {
				return true;
			}
		}
		return false;
	}
}
