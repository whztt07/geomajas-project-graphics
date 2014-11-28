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

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.controller.create.CreateController;
import org.geomajas.graphics.client.object.base.BaseCircle;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.role.cache.FillableCache;
import org.geomajas.graphics.client.object.role.cache.StrokableCache;
import org.geomajas.graphics.client.object.updateable.anchored.TwoPointsLine;
import org.geomajas.graphics.client.operation.AddOperation;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.service.objectcontainer.RenderObjectContainer;
import org.geomajas.graphics.client.util.CopyUtil;

/**
 * Generic controller that allows to drag a rectangle on the map,
 * passing the rectangle as bounds to a {@link org.geomajas.graphics.client.object.GraphicsObject}.
 *
 * @author Jan Venstermans
 *
 */
public class CreateBaseCircleByRadiusController
		extends CreateController<BaseCircle> implements MouseDownHandler, MouseMoveHandler, MouseUpHandler {

	private BaseCircle dragResizable;

	private Coordinate begin;

	private TwoPointsLine tempPath;

	/**
	 * Our own container.
	 */
	private RenderObjectContainer container;

	private HandlerRegistration registration;

	private Strokable pathStrokable = new StrokableCache();

	private Strokable circleStrokable = new StrokableCache();

	private Fillable circleFillable = new FillableCache();

	public CreateBaseCircleByRadiusController(GraphicsService graphicsService) {
		super(graphicsService);
		container = createContainer();
		// default values of fillable, strokable
		getCircleStrokable().setStrokeColor("black");
		getCircleStrokable().setStrokeOpacity(1.0);
		getCircleStrokable().setStrokeWidth(1);
		getCircleFillable().setFillOpacity(0.5);
		getCircleFillable().setFillColor("white");
		getPathStrokable().setStrokeColor("black");
		getPathStrokable().setStrokeOpacity(1.0);
		getPathStrokable().setStrokeWidth(1);
	}

	@Override
	public void setActive(boolean active) {
		super.setActive(active);
		if (active) {
			container = createContainer();
			registration = getObjectContainer().addMouseDownHandler(this);
		} else {
			if (container != null) {
				removeContainer(container);
			}
			container = null;
			if (registration != null) {
				registration.removeHandler();
				registration = null;
			}
		}
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		begin = getUserCoordinate(event);
		if (dragResizable == null) {
			//circle
			dragResizable = createObjectWithoutBounds();
			dragResizable.getRole(Resizable.TYPE).setUserBounds(new Bbox(begin.getX(), begin.getY(), 0, 0));
			dragResizable.asObject().addMouseMoveHandler(this);
			dragResizable.asObject().addMouseUpHandler(this);
			CopyUtil.copyStrokableProperties(circleStrokable, dragResizable);
			CopyUtil.copyFillableProperties(circleFillable, dragResizable);
			container.add(dragResizable);

			//line
			tempPath = new TwoPointsLine(new Coordinate(begin), new Coordinate(begin));
			CopyUtil.copyStrokableProperties(pathStrokable, tempPath);
			container.add(tempPath);
		}
		DOM.setCapture(dragResizable.asObject().getElement());
	}

	@Override
	public void onMouseMove(MouseMoveEvent event) {
		Coordinate end = getUserCoordinate(event);
		dragResizable.getRole(Resizable.TYPE).setUserBounds(getCircleBounds(end));
		tempPath.setCoordinates(new Coordinate(begin), new Coordinate(end));
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		BaseCircle result = createObjectWithoutBounds();
		result.getRole(Resizable.TYPE).setUserBounds(dragResizable.getRole(Resizable.TYPE).getUserBounds());
		CopyUtil.copyStrokableProperties(circleStrokable, result);
		CopyUtil.copyFillableProperties(circleFillable, result);
		DOM.releaseCapture(dragResizable.asObject().getElement());
		container.remove(dragResizable);
		container.remove(tempPath);
		dragResizable = null;
		tempPath = null;
		execute(new AddOperation(result));
	}

	public Fillable getCircleFillable() {
		return circleFillable;
	}

	public Strokable getCircleStrokable() {
		return circleStrokable;
	}

	public Strokable getPathStrokable() {
		return pathStrokable;
	}

	//--------------------------------------------------------------
	// private methods
	//--------------------------------------------------------------

	private BaseCircle createObjectWithoutBounds() {
		BaseCircle ellipse = new BaseCircle(0, 0, 0);
		return ellipse;
	}

	private Bbox getCircleBounds(Coordinate end) {
		double xDiff = begin.getX() - end.getX();
		double yDiff = begin.getY() - end.getY();
		double radius = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
		return new Bbox(begin.getX() - radius, begin.getY() - radius, 2 * radius, 2 * radius);
	}
}
