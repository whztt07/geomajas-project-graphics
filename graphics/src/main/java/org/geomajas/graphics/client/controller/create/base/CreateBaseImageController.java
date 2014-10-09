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
package org.geomajas.graphics.client.controller.create.base;

import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.geomajas.graphics.client.controller.create.CreateController;
import org.geomajas.graphics.client.object.Draggable;
import org.geomajas.graphics.client.object.base.BaseImage;
import org.geomajas.graphics.client.operation.AddOperation;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Controller that creates a {@link BaseImage}.
 *
 * @author Jan De Moerloose
 *
 */
public class CreateBaseImageController extends CreateController<BaseImage> implements MouseUpHandler {

	private boolean active;

	private HandlerRegistration registration;

	private String href;

	private int width;

	private int height;

	public CreateBaseImageController(GraphicsService graphicsService) {
		super(graphicsService);
	}

	public CreateBaseImageController(GraphicsService graphicsService, int width, int height, String href) {
		super(graphicsService);
		setHref(href);
		setHeight(height);
		setWidth(width);
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

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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
		BaseImage result = new BaseImage(0, 0, width, height, href);
		result.getRole(Draggable.TYPE).setPosition(getUserCoordinate(event));
		execute(new AddOperation(result));
	}

}