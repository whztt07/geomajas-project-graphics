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

import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.updateable.AnchoredUpdateable;

/**
 * Operation that anchors an object.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class AnchoredUpdateableStyleOperation implements GraphicsOperation {

	private int beforeStrokeWidth;

	private String beforeStrokeColor;

	private double beforeStrokeOpacity;

	private String beforePointColor;

	private double beforePointOpacity;

	private int afterStrokeWidth;

	private String afterStrokeColor;

	private double afterStrokeOpacity;

	private String afterPointColor;

	private double afterPointOpacity;

	private GraphicsObject anchored;

	public AnchoredUpdateableStyleOperation(GraphicsObject anchored, int beforeStrokeWidth,
			String beforeStrokeColor, double beforeStrokeOpacity, String beforePointColor, double beforePointOpacity,
			int afterStrokeWidth, String afterStrokeColor, double afterStrokeOpacity, String afterPointColor,
			double afterPointOpacity) {
		this.anchored = anchored;
		this.beforeStrokeWidth = beforeStrokeWidth;
		this.beforeStrokeColor = beforeStrokeColor;
		this.beforeStrokeOpacity = beforeStrokeOpacity;
		this.beforePointColor = beforePointColor;
		this.beforePointOpacity = beforePointOpacity;
		this.afterStrokeWidth = afterStrokeWidth;
		this.afterStrokeColor = afterStrokeColor;
		this.afterStrokeOpacity = afterStrokeOpacity;
		this.afterPointColor = afterPointColor;
		this.afterPointOpacity = afterPointOpacity;
	}

	@Override
	public void execute() {
		asAnchored().getAnchorLineStrokable().setStrokeWidth(afterStrokeWidth);
		asAnchored().getAnchorLineStrokable().setStrokeColor(afterStrokeColor);
		asAnchored().getAnchorLineStrokable().setStrokeOpacity(afterStrokeOpacity);
		asAnchored().getAnchorMarkerShapeStrokable().setStrokeColor(afterPointColor);
		asAnchored().getAnchorMarkerShapeFillable().setFillColor(afterPointColor);
		asAnchored().getAnchorMarkerShapeStrokable().setStrokeOpacity(afterPointOpacity);
		asAnchored().getAnchorMarkerShapeFillable().setFillOpacity(afterPointOpacity);
	}

	@Override
	public void undo() {
		asAnchored().getAnchorLineStrokable().setStrokeWidth(beforeStrokeWidth);
		asAnchored().getAnchorLineStrokable().setStrokeColor(beforeStrokeColor);
		asAnchored().getAnchorLineStrokable().setStrokeOpacity(beforeStrokeOpacity);
		asAnchored().getAnchorMarkerShapeStrokable().setStrokeColor(beforePointColor);
		asAnchored().getAnchorMarkerShapeFillable().setFillColor(beforePointColor);
		asAnchored().getAnchorMarkerShapeStrokable().setStrokeOpacity(beforePointOpacity);
		asAnchored().getAnchorMarkerShapeFillable().setFillOpacity(beforePointOpacity);
	}

	private AnchoredUpdateable asAnchored() {
		return anchored.getRole(AnchoredUpdateable.TYPE);
	}

	@Override
	public GraphicsObject getObject() {
		return anchored;
	}

	@Override
	public Type getType() {
		return Type.UPDATE;
	}

}
