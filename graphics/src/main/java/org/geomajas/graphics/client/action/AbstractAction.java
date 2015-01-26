/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2015 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client.action;

import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Basic abstract implementation of {@link org.geomajas.graphics.client.action.Action}.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public abstract class AbstractAction implements Action {

	private GraphicsService service;
	
	private String iconUrl;

	private String label;

	protected AbstractAction() {
		label = getDefaultLabel();
	}

	protected abstract String getDefaultLabel() ;

	// default: it applies to every object
	@Override
	public boolean supports(GraphicsObject object) {
		return true;
	}

	@Override
	public void setService(GraphicsService service) {
		this.service = service;
	}

	@Override
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public void setIconUrl(String url) {
		this.iconUrl = url;
	}

	@Override
	public String getIconUrl() {
		return iconUrl;
	}

	protected GraphicsService getService() {
		return service;
	}
}
