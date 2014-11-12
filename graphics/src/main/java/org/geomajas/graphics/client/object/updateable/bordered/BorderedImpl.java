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
package org.geomajas.graphics.client.object.updateable.bordered;

import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.updateable.Updateable;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Implementation of {@link Bordered} role with the {@link Updateable} interface.
 * The label is a {@link MarginBaseRectangle} that is positioned based on the borders of a {@link Draggable},
 * extended by a margin.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class BorderedImpl extends BaseGraphicsObject implements Bordered, Updateable {

	private Draggable draggable;

	private MarginBaseRectangle borderRect;

	private int margin;

	public BorderedImpl(Draggable draggable, int margin) {
		this.draggable = draggable;
		this.margin = margin;
		borderRect = new MarginBaseRectangle(0, 0, 0, 0, margin);
		borderRect.setFillColor("#CCFF99");
		borderRect.setStrokeColor("#CCCC99");

		addRole(Bordered.TYPE, this);

		onUpdate();
	}

	@Override
	public VectorObject asObject() {
		return borderRect.asObject();
	}

	@Override
	public void onUpdate() {
		borderRect.setUserBounds(draggable.getUserBounds());
	}

	@Override
	public Object cloneObject() {
		return new BorderedImpl(draggable, margin);
	}

	@Override
	public void setOpacity(double opacity) {
		borderRect.setOpacity(opacity);
	}

	@Override
	public Strokable getStrokable() {
		return borderRect.getRole(Strokable.TYPE);
	}

	@Override
	public Fillable getFillable() {
		return borderRect.getRole(Fillable.TYPE);
	}

	public int getMargin() {
		return margin;
	}
}
