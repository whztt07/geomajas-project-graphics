/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2013 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client.controller;

import java.util.List;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.Draggable;
import org.geomajas.graphics.client.object.GIcon;
import org.geomajas.graphics.client.operation.AddOperation;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.shape.MarkerShape;

import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.geomajas.graphics.client.widget.CreateIconChoicePopup;

/**
 * Controller that creates a {@link GIcon}.
 * 
 * @author Jan De Moerloose
 * 
 */
public class CreateIconController extends CreateController<GIcon> implements MouseUpHandler {

	private boolean active;

	private HandlerRegistration registration;

	private List<String> hrefs;

	private int width;

	private int height;
	
	private Coordinate clickPosition;
	
	protected CreateIconChoicePopup popup = new CreateIconChoicePopup(this, null, null);
	
	public CreateIconController(GraphicsService graphicsService, int width, int height, List<String> hrefs) {
		super(graphicsService);
		setHeight(height);
		setWidth(width);
		setHrefs(hrefs);
		popup.setMarkerSectionVisible(false);
	}

	public List<String> getHrefs() {
		return hrefs;
	}

	public void setHrefs(List<String> hrefs) {
		this.hrefs = hrefs;
		popup.setIconChoiceList(hrefs);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		popup.setPreviewImageWidth(width);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		popup.setPreviewImageHeight(height);
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
		clickPosition = getUserCoordinate(event);
		popup.show(event.getClientX(), event.getClientY());
	}

	protected void addObject(GIcon result) {
		execute(new AddOperation(result));
	}
	
	protected GIcon createIcon() {
		return new GIcon(0, 0, getWidth(), getHeight(), getHrefs().get(0));
	}

	protected GIcon createIcon(Coordinate coordinate) {
		return this.createIcon(coordinate, null);
	}
	
	protected GIcon createIcon(Coordinate coordinate, String href) {
		return new GIcon(coordinate.getX(), coordinate.getY(), getWidth(), 
				getHeight(), href != null ? href : getHrefs().get(0));
	}

	public void createIconInContainer(String iconUrl, MarkerShape markerShape) {
		GIcon result = createIcon(clickPosition, iconUrl);
		result.getRole(Draggable.TYPE).setPosition(clickPosition);
		addObject(result);
	}
	
	protected Coordinate getClickPosition() {
		return clickPosition;
	}

	/**
	 * Set size of the icons and markers in the choice list. Must be at least 12.
	 * @param pixelSize
	 */
	public void setChoiceListImageSize(int pixelSize) {
		popup.setChoiceListImageSize(pixelSize);
	}

}
