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
package org.geomajas.graphics.client.controller;


/**
 * Extension of {@link GraphicsController} with
 * {@link GraphicsControllerWithVisibleElement#setControllerElementsVisible(boolean)} method.
 * This is used for a {@link GraphicsController} that has controller elements that can be set visilbe/invisible.
 *
 * @author Jan Venstermans
 *
 */
public interface GraphicsControllerWithVisibleElement extends GraphicsController {

	void setControllerElementsVisible(boolean visible);
}
