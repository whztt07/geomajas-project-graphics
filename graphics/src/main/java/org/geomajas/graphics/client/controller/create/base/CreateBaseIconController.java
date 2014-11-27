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
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.controller.create.CreateController;
import org.geomajas.graphics.client.controller.create.CreateIconController;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.base.BaseIcon;
import org.geomajas.graphics.client.operation.AddOperation;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.object.updateable.anchored.MarkerShape;
import org.geomajas.graphics.client.widget.CreateIconChoicePopup;

import java.util.List;

/**
 * Controller that creates a {@link BaseIcon}.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class CreateBaseIconController extends CreateController<BaseIcon>
		implements MouseUpHandler, CreateIconController {

	private HandlerRegistration registration;

	private List<String> hrefs;

	private int width;

	private int height;

	private Coordinate clickPosition;

	protected CreateIconChoicePopup popup = new CreateIconChoicePopup(this, null, null);

	public CreateBaseIconController(GraphicsService graphicsService, int width, int height, List<String> hrefs) {
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
		super.setActive(active);
		if (isActive()) {
			registration = getObjectContainer().addMouseUpHandler(this);
		} else {
			if (registration != null) {
				registration.removeHandler();
				registration = null;
			}
		}
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		clickPosition = getUserCoordinate(event);
		popup.show(event.getClientX(), event.getClientY());
	}

	protected void addObject(BaseIcon result) {
		execute(new AddOperation(result));
	}
	
	protected BaseIcon createIcon() {
		return new BaseIcon(0, 0, getWidth(), getHeight(), getHrefs().get(0));
	}

	protected BaseIcon createIcon(Coordinate coordinate) {
		return this.createIcon(coordinate, null);
	}
	
	protected BaseIcon createIcon(Coordinate coordinate, String href) {
		return new BaseIcon(coordinate.getX(), coordinate.getY(), getWidth(),
				getHeight(), href != null ? href : getHrefs().get(0));
	}

	public void createIconInContainer(String iconUrl, MarkerShape markerShape) {
		BaseIcon result = createIcon(clickPosition, iconUrl);
		result.getRole(Draggable.TYPE).setUserPosition(clickPosition);
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
