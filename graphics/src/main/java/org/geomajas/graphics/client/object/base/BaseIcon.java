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
import org.geomajas.graphics.client.render.AnchoredImage;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link BaseGraphicsObject} for an icon.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class BaseIcon extends BaseGraphicsObject implements Draggable {

	private AnchoredImage anchoredImage;

	public BaseIcon(double userX, double userY, int width, int height, String href) {
		this(Graphics.getRenderElementFactory().createAnchoredImage(userX, userY,
				width, height, href, true, 0.5, 0.5));
	}

	public BaseIcon(AnchoredImage anchoredImage) {
		this.anchoredImage = anchoredImage;
		addRole(Draggable.TYPE, this);
	}

	@Override
	public VectorObject asObject() {
		if (anchoredImage instanceof VectorObject) {
			return (VectorObject) anchoredImage;
		}
		return null;
	}

	@Override
	public void setUserPosition(Coordinate imageAnchorPosition) {
		anchoredImage.setUserX(imageAnchorPosition.getX());
		anchoredImage.setUserY(imageAnchorPosition.getY());
	}

	@Override
	public Coordinate getUserPosition() {
		return new Coordinate(anchoredImage.getUserX(), anchoredImage.getUserY());
	}

	@Override
	public void setOpacity(double opacity) {
		try {
			anchoredImage.setOpacity(opacity);
		} catch (Exception e) {
			// do nothing
		}
	}

	@Override
	public Bbox getUserBounds() {
		return anchoredImage.getUserBounds();
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		// can't do this
	}

	@Override
	public Bbox getBounds() {
		return anchoredImage.getBounds();
	}

	public Object cloneObject() {
		return new BaseIcon(anchoredImage.getUserX(), anchoredImage.getUserY(),
				anchoredImage.getWidth(), anchoredImage.getHeight(), anchoredImage.getHref());
	}

	public String getHref() {
		return anchoredImage.getHref();
	}

}
