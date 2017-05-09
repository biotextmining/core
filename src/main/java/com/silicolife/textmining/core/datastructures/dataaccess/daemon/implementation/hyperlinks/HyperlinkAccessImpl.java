package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.hyperlinks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.RestClientAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.datastructures.general.SourceImpl;
import com.silicolife.textmining.core.datastructures.hyperlink.HyperLinkMenuItemImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;
import com.silicolife.textmining.core.interfaces.core.hyperlink.IHyperLinkMenuItem;

/**
 * Class which implements all Hyperlink daemon access methods
 * 
 * @author Joel Azevedo Costa
 *
 */
public class HyperlinkAccessImpl extends RestClientAccess {

	/**
	 * Connect to daemon and update hyperlink menu item
	 * 
	 * @param corpus
	 * @return
	 * @throws DaemonException 
	 */
	public Boolean updateSourcesHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.put("hyperlink/updateSourcesHyperLinkMenuItem", responseType, hyperLinkMenuItem);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * Connect to daemon and get all hyper link menus
	 * 
	 * @return
	 * @throws DaemonException 
	 */
	public List<IHyperLinkMenuItem> getAllHyperLinkMenuItems() throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<HyperLinkMenuItemImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<HyperLinkMenuItemImpl>>>() {};
		ResponseEntity<DaemonResponse<List<HyperLinkMenuItemImpl>>> response = webClient.get("hyperlink/getAllHyperLinkMenuItems", responseType);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			List<IHyperLinkMenuItem> returned = new ArrayList<IHyperLinkMenuItem>();
			List<HyperLinkMenuItemImpl> hyper = response.getBody().getContent();
			for (HyperLinkMenuItemImpl obj : hyper) {
				returned.add(obj);
			}
			return returned;
		}
	}
	
	
	
	/**
	 * Connect to daemon and get all sources
	 * 
	 * @return
	 * @throws DaemonException 
	 */
	public List<ISource> getAllSources() throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<SourceImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<SourceImpl>>>() {};
		ResponseEntity<DaemonResponse<List<SourceImpl>>> response = webClient.get("hyperlink/getAllSources", responseType);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			List<ISource> returned = new ArrayList<ISource>();
			List<SourceImpl> sources = response.getBody().getContent();
			for (SourceImpl obj : sources) {
				returned.add(obj);
			}
			return returned;
		}
	}
	

	/**
	 * Connect to daemon and get hyper link menu item
	 * 
	 * @param corpusId
	 * @return
	 * @throws DaemonException 
	 */
	public IHyperLinkMenuItem getHyperLinkMenuItemById(long hyperLinkId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<HyperLinkMenuItemImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<HyperLinkMenuItemImpl>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("hyperLinkId", hyperLinkId);
		
		ResponseEntity<DaemonResponse<HyperLinkMenuItemImpl>> response = webClient.get("hyperlink/getHyperLinkMenuItemById", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			IHyperLinkMenuItem hyper = response.getBody().getContent();
			return hyper;
		}
	}

	
	/**
	 * Get hyper link menu item for source
	 * 
	 * @param sourceId
	 * @return
	 * @throws DaemonException
	 */
	public List<IHyperLinkMenuItem> getHyperLinkMenuItemsForSource(long sourceId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<HyperLinkMenuItemImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<HyperLinkMenuItemImpl>>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("sourceId", sourceId);
		
		ResponseEntity<DaemonResponse<List<HyperLinkMenuItemImpl>>> response = webClient.get("hyperlink/getHyperLinkMenuItemsForSource", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			List<IHyperLinkMenuItem> returned = new ArrayList<IHyperLinkMenuItem>();
			List<HyperLinkMenuItemImpl> hyper = response.getBody().getContent();
			for (HyperLinkMenuItemImpl obj : hyper) {
				returned.add(obj);
			}
			return returned;
		}
	}

	public Long getNextHyperLinkMenuItemID() throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Long>> responseType = new ParameterizedTypeReference<DaemonResponse<Long>>() {};
		ResponseEntity<DaemonResponse<Long>> response = webClient.get("hyperlink/getNextHyperLinkMenuItemID", responseType);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			Long returned = response.getBody().getContent();
			return returned;
		}
	}

	public Boolean addHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("hyperlink/addHyperLinkMenuItem", responseType, hyperLinkMenuItem);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
		
	}

	public Boolean removeHyperLinkMenuItem(long menuId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("menuId", menuId);
		
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.get("hyperlink/removeHyperLinkMenuItem", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}

	public Boolean updateHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("hyperlink/updateHyperLinkMenuItem", responseType, hyperLinkMenuItem);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}
}
