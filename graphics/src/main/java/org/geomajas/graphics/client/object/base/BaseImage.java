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
package org.geomajas.graphics.client.object.base;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.Draggable;
import org.geomajas.graphics.client.object.Resizable;
import org.geomajas.graphics.client.util.FlipState;
import org.vaadin.gwtgraphics.client.Image;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link BaseGraphicsObject} for an image.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class BaseImage extends BaseGraphicsObject implements Resizable, Draggable {

	private Image image;

	public BaseImage(int x, int y, int width, int height, String href) {
		this(new Image(x, y, width, height, href));
	}

	public BaseImage(Image image) {
		this.image = image;
		addRole(Resizable.TYPE, this);
		addRole(Draggable.TYPE, this);
	}

	@Override
	public void setPosition(Coordinate position) {
		image.setUserX(position.getX());
		image.setUserY(position.getY());
	}

	@Override
	public Coordinate getPosition() {
		return new Coordinate(image.getUserX(), image.getUserY());
	}

	public Object cloneObject() {
		Image mask = new Image(image.getUserX(), image.getUserY(), image.getUserWidth(), image.getUserHeight(),
				image.getHref());
		return new BaseImage(mask);
	}

	@Override
	public void flip(FlipState state) {
		// symmetric
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		image.setUserX(bounds.getX());
		image.setUserY(bounds.getY());
		image.setUserWidth(bounds.getWidth());
		image.setUserHeight(bounds.getHeight());
	}

	@Override
	public boolean isPreserveRatio() {
		return true;
	}

	@Override
	public boolean isAutoHeight() {
		return false;
	}

	@Override
	public Bbox getUserBounds() {
		return new Bbox(image.getUserX(), image.getUserY(), image.getUserWidth(), image.getUserHeight());
	}

	@Override
	public Bbox getBounds() {
		return new Bbox(image.getX(), image.getY(), image.getWidth(), image.getHeight());
	}

	@Override
	public VectorObject asObject() {
		return image;
	}

	@Override
	public void setOpacity(double opacity) {
		// can't do it
	}
}