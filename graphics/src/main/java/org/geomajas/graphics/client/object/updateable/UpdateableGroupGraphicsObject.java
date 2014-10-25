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

import org.geomajas.graphics.client.object.BaseGraphicsObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Extension of {@link BaseGraphicsObject} with {@link Updateable}.
 * Allows to register {@link Updateable}s that will be updated on this object's update.
 *
 * @author Jan Venstermans
 *
 */
public abstract class UpdateableGroupGraphicsObject extends BaseGraphicsObject implements Updateable {

	/**
	 * List of {@link org.geomajas.graphics.client.object.updateable.Updateable} objects that will be
	 * called on {@link this#onUpdate()}.
	 */
	private List<Updateable> updateableList = new ArrayList<Updateable>();

	protected void addUpdateable(Updateable updateable) {
		updateableList.add(updateable);
	}

	@Override
	public void onUpdate() {
		for (Updateable updateable : updateableList) {
			updateable.onUpdate();
		}
	}
}
