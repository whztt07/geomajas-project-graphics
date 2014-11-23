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
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.render.AnchoredImage;
import org.geomajas.graphics.client.util.FlipState;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link BaseGraphicsObject} for an image.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class BaseImage extends BaseGraphicsObject implements Resizable, Draggable {

	private AnchoredImage anchoredImage;

	public BaseImage(double userX, double userY, int width, int height, String href, boolean preserveRatio) {
		this(Graphics.getRenderElementFactory().createAnchoredImage(userX, userY, width, height, href,
				preserveRatio, 0, 0));
	}

	public BaseImage(AnchoredImage image) {
		this.anchoredImage = image;
		addRole(Resizable.TYPE, this);
		addRole(Draggable.TYPE, this);
	}

	@Override
	public void setUserPosition(Coordinate position) {
		anchoredImage.setUserX(position.getX());
		anchoredImage.setUserY(position.getY());
	}

	@Override
	public Coordinate getUserPosition() {
		return new Coordinate(anchoredImage.getUserX(), anchoredImage.getUserY());
	}

	@Override
	public Object cloneObject() {
		return new BaseImage(anchoredImage.getUserX(), anchoredImage.getUserY(),
				anchoredImage.getWidth(), anchoredImage.getHeight(), anchoredImage.getHref(),
				anchoredImage.isPreserveAspectRatio());
	}

	@Override
	public void flip(FlipState state) {
		// symmetric
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		anchoredImage.setUserX(bounds.getX());
		anchoredImage.setUserY(bounds.getY());
		anchoredImage.setUserWidth(bounds.getWidth());
		anchoredImage.setUserHeight(bounds.getHeight());
	}

	@Override
	public boolean isPreserveRatio() {
		return anchoredImage.isPreserveAspectRatio();
	}

	@Override
	public boolean isAutoHeight() {
		return false;
	}

	@Override
	public Bbox getUserBounds() {
		return anchoredImage.getUserBounds();
	}

	@Override
	public Bbox getBounds() {
		return anchoredImage.getBounds();
	}

	@Override
	public VectorObject asObject() {
		if (anchoredImage instanceof VectorObject) {
			return (VectorObject) anchoredImage;
		}
		return null;
	}

	@Override
	public void setOpacity(double opacity) {
		anchoredImage.setOpacity(opacity);
	}

	public String getHref() {
		return anchoredImage.getHref();
	}
}