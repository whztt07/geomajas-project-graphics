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
package org.geomajas.graphics.client.object.updateable;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.RoleType;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.updateable.anchored.TwoPointsLine;

/**
 * Simplified {@link org.geomajas.graphics.client.object.anchor.Anchored} role.
 * TODO: merge Anchored and AnchoredUpdateable roles.
 * 
 * @author Jan De Moerloose
 * 
 */
public interface AnchoredUpdateable extends Updateable {

	RoleType<AnchoredUpdateable> TYPE = new RoleType<AnchoredUpdateable>("AnchoredUpdateable");

	/* anchor line */

	Strokable getAnchorLineStrokable();

	void setAnchorLineDashStyle(TwoPointsLine.LineDashStyle dashStyle);

	/* anchor marker shape */

	Strokable getAnchorMarkerShapeStrokable();

	Fillable getAnchorMarkerShapeFillable();

	void setAnchorPosition(Coordinate position);

	Coordinate getAnchorPosition();

	void setAnchorVisible(boolean visible);
//
//	Shape getAnchorPointShape();
//
//	void setAnchorPointShape(Shape shape);
//
//	MarkerShape getMarkerShape();
}
