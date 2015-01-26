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
package org.geomajas.graphics.client.shape;

import org.geomajas.geometry.Bbox;
import org.geomajas.graphics.client.render.AnchoredImage;

public class MockAnchoredImage implements AnchoredImage {

	private double userX;
	private double userY;
	private int x;
	private int y;
	private double userWidth;
	private int width;
	private double userHeight;
	private int height;
	private String href;
	private double anchorX;
	private double anchorY;
	private Bbox userBounds;
	private Bbox bounds;
	private boolean preserveAspectRatio;
	private double opacity;

	@Override
	public void setUserX(double userX) {
		this.userX = userX;
		setUserBoundsValues(userX, null, null, null);
	}

	@Override
	public void setUserY(double userY) {
		this.userY = userY;
		setUserBoundsValues(null, userY, null, null);
	}

	@Override
	public double getUserX() {
		return userX;
	}

	@Override
	public double getUserY() {
		return userY;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public String getHref() {
		return href;
	}

	@Override
	public double getAnchorX() {
		return anchorX;
	}

	@Override
	public double getAnchorY() {
		return anchorY;
	}

	@Override
	public Bbox getUserBounds() {
		return userBounds;
	}

	@Override
	public Bbox getBounds() {
		return bounds;
	}

	@Override
	public boolean isPreserveAspectRatio() {
		return preserveAspectRatio;
	}

	@Override
	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}

	@Override
	public double getUserWidth() {
		return userWidth;
	}

	public void setUserWidth(double userWidth) {
		this.userWidth = userWidth;
		setUserBoundsValues(null, null, userWidth, null);
	}

	public void setWidth(int width) {
		this.width = width;
		setBoundsValues(null, null, width, null);
	}

	@Override
	public double getUserHeight() {
		return userHeight;
	}

	public void setUserHeight(double userHeight) {
		this.userHeight = userHeight;
		setUserBoundsValues(null, null, null, userHeight);
	}

	public void setHeight(int height) {
		this.height = height;
		setBoundsValues(null, null, null, height);
	}

	public void setX(int x) {
		this.x = x;
		setBoundsValues(x, null, null, null);
	}

	public void setY(int y) {
		this.y = y;
		setBoundsValues(null, y, null, null);
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setAnchorX(double anchorX) {
		this.anchorX = anchorX;
	}

	public void setAnchorY(double anchorY) {
		this.anchorY = anchorY;
	}

	public void setUserBounds(Bbox userBounds) {
		this.userBounds = userBounds;
	}


	public void setBounds(Bbox bounds) {
		this.bounds = bounds;
	}

	public void setPreserveAspectRatio(boolean preserveAspectRatio) {
		this.preserveAspectRatio = preserveAspectRatio;
	}

	public double getOpacity() {
		return opacity;
	}


	private void setUserBoundsValues(Double userX, Double userY, Double userWidth, Double userHeight) {
		if (userBounds == null) {
			userBounds = new Bbox();
		}
		if (userX != null) {
			userBounds.setX(userX);
		}
		if (userY != null) {
			userBounds.setY(userY);
		}
		if (userWidth != null) {
			userBounds.setWidth(userWidth);
		}
		if (userHeight != null) {
			userBounds.setHeight(userHeight);
		}
	}

	private void setBoundsValues(Integer x, Integer y, Integer width, Integer height) {
		if (bounds == null) {
			bounds = new Bbox();
		}
		if (x != null) {
			bounds.setX(x);
		}
		if (y != null) {
			bounds.setY(y);
		}
		if (width != null) {
			bounds.setWidth(width);
		}
		if (height != null) {
			bounds.setHeight(height);
		}
	}
}
