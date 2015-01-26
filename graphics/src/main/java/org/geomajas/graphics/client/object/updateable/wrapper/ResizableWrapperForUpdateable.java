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
package org.geomajas.graphics.client.object.updateable.wrapper;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.updateable.Updateable;
import org.geomajas.graphics.client.util.FlipState;

/**
 * Wrapper around a {@link Resizable} that will trigger
 * {@link org.geomajas.graphics.client.object.updateable.Updateable#onUpdate()}
 * on certain {@link Resizable} method calls.
 *
 * @author Jan Venstermans
 *
 */
public class ResizableWrapperForUpdateable implements Resizable {

	private Resizable delegate;

	private Updateable updateable;

	public ResizableWrapperForUpdateable(Resizable delegate, Updateable updateable) {
		this.delegate = delegate;
		this.updateable = updateable;
	}

	@Override
	public void flip(FlipState state) {
		delegate.flip(state);
		updateable.onUpdate();
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		delegate.setUserBounds(bounds);
		updateable.onUpdate();
	}

	@Override
	public Bbox getUserBounds() {
		return delegate.getUserBounds();
	}

	@Override
	public Bbox getBounds() {
		return delegate.getBounds();
	}

	@Override
	public boolean isPreserveRatio() {
		return delegate.isPreserveRatio();
	}

	@Override
	public boolean isAutoHeight() {
		return delegate.isAutoHeight();
	}

	@Override
	public Coordinate getUserPosition() {
		return delegate.getUserPosition();
	}

	@Override
	public void setUserPosition(Coordinate position) {
		delegate.setUserPosition(position);
		updateable.onUpdate();
	}

}
