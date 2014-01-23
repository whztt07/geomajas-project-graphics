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
package org.geomajas.graphics.client.service;



/**
 * Extension of {@link GraphicsController} with
 * {@link VisibleOnActiveGraphicsController#setControllerElementsVisible(boolean)} method.
 *
 * @author Jan Venstermans
 *
 */
public interface VisibleOnActiveGraphicsController extends GraphicsController {

	void setControllerElementsVisible(boolean visible);
}