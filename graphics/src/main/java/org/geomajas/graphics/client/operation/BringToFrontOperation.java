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
package org.geomajas.graphics.client.operation;

import com.google.gwt.user.client.ui.Widget;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.service.GraphicsService;
import org.vaadin.gwtgraphics.client.VectorObjectContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Operation that brings an object to the front.
 * 
 * @author Jan De Moerloose
 * 
 */
public class BringToFrontOperation implements GraphicsOperation {

	private GraphicsObject object;
	
	private GraphicsService service;
	
	private List<GraphicsObject> externalLabels = new ArrayList<GraphicsObject>();

	private VectorObjectContainer parent;

	private int beforeIndex;

	public BringToFrontOperation(GraphicsObject object, GraphicsService service) {
		this.object = object;
		this.service = service;
	}

	@Override
	public void execute() {
		externalLabels.clear();
		Widget w = object.asObject().getParent();
		if (w instanceof VectorObjectContainer) {
			parent = (VectorObjectContainer) w;
			beforeIndex = parent.indexOf(object.asObject());
			parent.bringToFront(object.asObject());
		}
	}

	@Override
	public void undo() {
		if (parent != null) {
			if (externalLabels != null && externalLabels.size() > 0) {
				for (GraphicsObject exLab : externalLabels) {
					parent.insert(exLab.asObject(), beforeIndex);
				}
			}
			parent.insert(object.asObject(), beforeIndex);
		}
	}

	@Override
	public GraphicsObject getObject() {
		return object;
	}

	@Override
	public Type getType() {
		return Type.UPDATE;
	}

}
