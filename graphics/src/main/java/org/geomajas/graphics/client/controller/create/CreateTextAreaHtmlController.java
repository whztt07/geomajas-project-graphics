/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2014 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client.controller.create;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.GTextAreaHtml;
import org.geomajas.graphics.client.resource.GraphicsResource;
import org.geomajas.graphics.client.service.GraphicsService;

import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Controller that creates a {@link GTextAreaHtml}.
 * 
 * @author Jan Venstermans
 * 
 */
public class CreateTextAreaHtmlController extends CreateController<GTextAreaHtml> implements MouseUpHandler {

	private boolean active;

	private HandlerRegistration registration;

	public CreateTextAreaHtmlController(GraphicsService graphicsService) {
		super(graphicsService);
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;
		if (active) {
			registration = getObjectContainer().addMouseUpHandler(this);
		} else {
			if (registration != null) {
				registration.removeHandler();
				registration = null;
			}
		}
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		Coordinate position = getUserCoordinate(event);
		GTextAreaHtml textAreaHtml = new GTextAreaHtml(position.getX(), position.getY(), 100, 100,
				GraphicsResource.MESSAGES.loremIpsum());
		addObject(textAreaHtml);
	}
}
