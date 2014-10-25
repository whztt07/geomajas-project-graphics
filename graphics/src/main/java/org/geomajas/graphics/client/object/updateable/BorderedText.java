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

import org.geomajas.graphics.client.object.Draggable;
import org.geomajas.graphics.client.object.base.BaseText;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.object.updateable.bordered.BorderedImpl;
import org.geomajas.graphics.client.object.updateable.wrapper.DraggableWrapperForUpdateable;
import org.geomajas.graphics.client.object.updateable.wrapper.TextableWrapperForUpdateable;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link org.geomajas.graphics.client.object.updateable.UpdateableGroupGraphicsObject}
 * that shows a text centered on a {@link org.geomajas.graphics.client.object.base.BaseRectangle}.
 *
 * @author Jan Venstermans
 *
 */
public class BorderedText extends UpdateableGroupGraphicsObject {

	private Group rootGroup = new Group();

	private BaseText baseText;

	private BorderedImpl bordered;

	public BorderedText(double userX, double userY, String text, int margin) {
		// create base graphics objects
		baseText = new BaseText(userX, userY, text);
		bordered = new BorderedImpl(baseText, margin);

		// register updateables
		addUpdateable(bordered);

		// register roles of group object
		addRole(Textable.TYPE, new TextableWrapperForUpdateable(baseText, this));
		addRole(Draggable.TYPE, new DraggableWrapperForUpdateable(baseText, this));
		addRole(Strokable.TYPE, bordered.getStrokable());
		addRole(Fillable.TYPE, bordered.getFillable());
		addRole(BorderedUpdateable.TYPE, bordered);

		// register render order
		rootGroup.add(bordered.asObject());
		rootGroup.add(baseText.asObject());
	}

	@Override
	public Object cloneObject() {
		BorderedText clone = new BorderedText(baseText.getUserX(),
				baseText.getUserY(), baseText.getLabel(), bordered.getMargin());
		return clone;
	}

	//---------------------------------
	// render section
	//---------------------------------

	@Override
	public VectorObject asObject() {
		return rootGroup;
	}

	@Override
	public void setOpacity(double opacity) {
		rootGroup.setOpacity(opacity);
	}
}
