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
package org.geomajas.graphics.client.controller.drag;

import com.google.gwt.event.dom.client.MouseMoveEvent;
import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.controller.UpdateHandlerGraphicsController;
import org.geomajas.graphics.client.object.base.BaseRectangle;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.operation.DragOperation;
import org.geomajas.graphics.client.operation.GraphicsOperation;
import org.geomajas.graphics.client.service.GraphicsService;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link AbstractDragHandler} with a {@link BaseRectangle} for an invisible mask.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class GraphicsObjectDragHandler extends AbstractDragHandler {
	
	private BaseRectangle invisbleMaskGraphicsObject;

	public GraphicsObjectDragHandler(GraphicsObject object, GraphicsService service,
			UpdateHandlerGraphicsController graphicsController) {
		super(object, service, graphicsController);
	}

	@Override
	public void update() {
		invisbleMaskGraphicsObject.getRole(Draggable.TYPE).setPosition(getDraggable().getPosition());
	}

	@Override
	protected VectorObject createInvisibleMask() {
		// make an invisible mask that is a rectangle of the bounds of the draggable.
		Bbox bbox = getDraggable().getBounds();
		invisbleMaskGraphicsObject = new BaseRectangle(bbox.getX(), bbox.getY(), bbox.getWidth(), bbox.getHeight());
		invisbleMaskGraphicsObject.setOpacity(0);
		return invisbleMaskGraphicsObject.asObject();
	}

	@Override
	protected GraphicsObject createDraggingMask() {
		GraphicsObject maskObject = (GraphicsObject) getObject().cloneObject();
		maskObject.setOpacity(0.5);
		maskObject.getRole(Draggable.TYPE).setPosition(getBeginPositionUser());
		return maskObject;
	}

	@Override
	protected Coordinate getObjectPosition() {
		return getDraggable().getPosition();
	}

	@Override
	protected GraphicsOperation createGraphicsOperation(Coordinate before,
			Coordinate after) {
		return new DragOperation(getObject(), before, after);
	}

	@Override
	protected void mouseMoveContent(MouseMoveEvent event) {
		getDraggingMask().getRole(Draggable.TYPE).setPosition(
				getNewPosition(event.getClientX(), event.getClientY()));
	}

	public GraphicsObject getInvisbleMaskGraphicsObject() {
		return invisbleMaskGraphicsObject;
	}

	public Draggable getDraggable() {
		return getObject().getRole(Draggable.TYPE);
	}

}


