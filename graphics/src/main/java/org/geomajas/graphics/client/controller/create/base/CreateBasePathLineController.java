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

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.controller.create.CreateController;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.render.BasePathLine;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.service.objectcontainer.RenderObjectContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller that creates a {@link BasePathLine}.
 *
 * @author Jan De Moerloose
 *
 */
public class CreateBasePathLineController extends CreateController<BasePathLine>
		implements MouseDownHandler, MouseMoveHandler, DoubleClickHandler, Fillable {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	protected BasePathLine path;

	protected BasePathLine dragLine;

	private BasePathLine previewPath;

	private RenderObjectContainer container;

	private boolean showPreview;

	private String fillColor = "#CCFF66";

	private double fillOpacity;

	private String captureCursor;

	public CreateBasePathLineController(GraphicsService graphicsService) {
		super(graphicsService);
		fillOpacity = 1;
		showPreview = false;
		container = createContainer();
	}

	@Override
	public void setActive(boolean active) {
		super.setActive(active);
		if (isActive()) {
			container = createContainer();
			registrations.add(getObjectContainer().addMouseDownHandler(this));
		} else {
			for (HandlerRegistration r : registrations) {
				r.removeHandler();
			}
			registrations.clear();
			path = null;
			if (container != null) {
				removeContainer(container);
			}
			container = null;
		}
	}

	@Override
	public void onMouseMove(MouseMoveEvent event) {
		if (path != null) {
			Coordinate c1 = path.getLastCoordinate();
			Coordinate c2 = getUserCoordinate(event);
			if (showPreview) {
				previewPath.moveCoordinate(c2, previewPath.getCoordinateCount() - 1);
			}
			dragLine.setCoordinates(new Coordinate[] { c1, c2 });
		}
	}

	@Override
	public void onDoubleClick(DoubleClickEvent event) {
		release(path.asObject().getElement());
		container.clear();
		addObject(path);
		dragLine = null;
		previewPath = null;
		path = null;
		event.stopPropagation();
	}

	public boolean isClosedPath() {
		return true;
	}

	protected void capture(Element element, Cursor cursor) {
		DOM.setCapture(element);
		captureCursor = RootPanel.getBodyElement().getStyle().getCursor();
		RootPanel.getBodyElement().getStyle().setCursor(cursor);
	}

	protected void release(Element element) {
		DOM.releaseCapture(element);
		RootPanel.getBodyElement().getStyle().setProperty("cursor", captureCursor);
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		if (isSingleClick(event.getNativeEvent())) {
			if (path == null) {
				path = createPath();
				path.setCoordinates(new Coordinate[] { getUserCoordinate(event) });
				// we can show a preview of the filled path if necessary
				if (showPreview) {
					previewPath = createPath();
					previewPath.setCoordinates(new Coordinate[] { getUserCoordinate(event) });
					// add the preview extra point !
					previewPath.addCoordinate(new Coordinate(getUserCoordinate(event)));
					previewPath.setFillOpacity(previewPath.getFillOpacity() * 0.7);
					container.add(previewPath);
				}
				// we have to show our intermediate result !
				container.add(path);
				// start the drag line, captures all events from now !
				if (dragLine == null) {
					dragLine = createPath();
					dragLine.setStrokeOpacity(1);
					container.add(dragLine);
				}
				Coordinate c1 = path.getLastCoordinate();
				Coordinate c2 = getUserCoordinate(event);
				dragLine.setCoordinates(new Coordinate[] { c1, c2 });
				dragLine.asObject().addMouseMoveHandler(this);
				dragLine.asObject().addMouseDownHandler(this);
				dragLine.asObject().addDoubleClickHandler(this);
				capture(dragLine.asObject().getElement(), Cursor.CROSSHAIR);
			} else {
				path.addCoordinate(getUserCoordinate(event));
				if (showPreview) {
					previewPath.addCoordinate(getUserCoordinate(event));
				}
			}
		}
	}

	private native boolean isSingleClick(NativeEvent event) /*-{
															return !event.detail || event.detail==1;
															}-*/;

	protected BasePathLine createPath() {
		BasePathLine path = new BasePathLine(new Coordinate(0, 0));
		return path;
	}

	@Override
	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

	@Override
	public void setFillOpacity(double fillOpacity) {
		this.fillOpacity = fillOpacity;
	}

	@Override
	public String getFillColor() {
		return fillColor;
	}

	@Override
	public double getFillOpacity() {
		return fillOpacity;
	}

}
