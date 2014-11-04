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
package org.geomajas.graphics.client.object.updateable.wrapper;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.updateable.Updateable;

/**
 * Wrapper around a {@link Draggable} that will trigger
 * {@link org.geomajas.graphics.client.object.updateable.Updateable#onUpdate()}
 * on certain {@link Draggable} method calls.
 *
 * @author Jan Venstermans
 *
 */
public class DraggableWrapperForUpdateable implements Draggable {

	private Draggable delegate;

	private Updateable updateable;

	public DraggableWrapperForUpdateable(Draggable delegate, Updateable updateable) {
		this.delegate = delegate;
		this.updateable = updateable;
	}

	@Override
	public void setPosition(Coordinate position) {
		delegate.setPosition(position);
		updateable.onUpdate();
	}

	@Override
	public Coordinate getPosition() {
		return delegate.getPosition();
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
	public void setUserBounds(Bbox bbox) {
		delegate.setUserBounds(bbox);
		updateable.onUpdate();
	}
}
