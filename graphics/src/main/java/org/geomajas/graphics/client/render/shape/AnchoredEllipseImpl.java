/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2015 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client.render.shape;

import org.geomajas.graphics.client.render.AnchoredEllipse;
import org.vaadin.gwtgraphics.client.shape.Ellipse;

/**
 * A non-scaling text that is anchored to its world space location on a specific pixel or anchor location (useful for
 * location markers).
 * 
 * @author Jan De Moerloose
 * 
 */
public class AnchoredEllipseImpl extends Ellipse implements AnchoredEllipse {
	public AnchoredEllipseImpl(double userX, double userY, double userRadiusX, double userRadiusY) {
		super(userX, userY, userRadiusX, userRadiusY);
	}
}