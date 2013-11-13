package com.bytepoxic.core.web.facesbean.place;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bytepoxic.core.model.Location;
import com.bytepoxic.core.service.LocationService;
import com.bytepoxic.core.throwing.WebCoreException;
import com.bytepoxic.core.util.MessageUtils;
import com.bytepoxic.core.web.facesbean.form.ClassicListBean;


@Component("listLocationsBean")
@Scope("request")
public class ListLocationsBean extends ClassicListBean {
	private static Log logger = LogFactory.getLog(ListLocationsBean.class);

	@Autowired
	private LocationService locationService;
	private TreeNode locations;

	@PostConstruct
	public void onLoad() {
		refreshLocations();
		setShowRemove(false);
	}

	public TreeNode getLocations() {
		return locations;
	}

	public void setLocations(TreeNode locations) {
		this.locations = locations;
	}

	private void refreshLocations() {
		if (logger.isDebugEnabled()) {
			logger.debug("Getting location list");
		}
		Location root = null;
		try {
			root = locationService.getLocationTree();
			if (root == null) {
				root = locationService.buildLocationTree(null);
			}
		} catch (WebCoreException e) {
			logger.error("Could not get locations", e);
			MessageUtils.addErrorMessage("msg", "app.entity.generic.list_error", new String[] { "places.locations.entities_name" });
		}
		if (root != null) {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Found root location %s", root));
			}
			locations = new DefaultTreeNode(root, null);
			buildTree(root, locations);
		} else {
			logger.warn("Could not find any main location");
		}
	}

	private void buildTree(Location root, TreeNode attach) {
		if (root != null && root.getChildren() != null) {
			for(Location child : root.getChildren()) {
				TreeNode node = new DefaultTreeNode(child, attach);
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Tree node created to bind %s with %s", root, attach));
				}
				buildTree(child, node);
			}
		}
	}

	public void refresh() {
		refreshLocations();
	}

	@Override
	protected String getEntityName() {
		return "location";
	}

	@Override
	protected String getSubDomain() {
		return "places";
	}
}
