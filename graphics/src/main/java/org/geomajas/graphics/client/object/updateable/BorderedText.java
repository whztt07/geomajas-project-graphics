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
package org.geomajas.graphics.client.object.updateable;

import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.base.BaseText;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.object.updateable.bordered.Bordered;
import org.geomajas.graphics.client.object.updateable.bordered.BorderedImpl;
import org.geomajas.graphics.client.object.updateable.wrapper.DraggableWrapperForUpdateable;
import org.geomajas.graphics.client.object.updateable.wrapper.TextableWrapperForUpdateable;
import org.geomajas.graphics.client.render.RenderableList;
import org.geomajas.graphics.client.util.CopyUtil;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link org.geomajas.graphics.client.object.updateable.UpdateableGroupGraphicsObject}
 * that shows a text centered on a {@link org.geomajas.graphics.client.object.base.BaseRectangle}.
 *
 * @author Jan Venstermans
 *
 */
public class BorderedText extends UpdateableGroupGraphicsObject {

	private RenderableList renderableList;

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
		addRole(Bordered.TYPE, bordered);

		// register render order
		renderableList = Graphics.getRenderElementFactory().createRenderableList();
		renderableList.addRenderable(bordered);
		renderableList.addRenderable(baseText);
	}

	@Override
	public Object cloneObject() {
		BorderedText clone = new BorderedText(baseText.getUserX(),
				baseText.getUserY(), baseText.getLabel(), bordered.getMargin());
		CopyUtil.copyTextableProperties(this.getRole(Textable.TYPE), clone.getRole(Textable.TYPE));
		CopyUtil.copyBorderedProperties(this.getRole(Bordered.TYPE), clone.getRole(Bordered.TYPE));
		return clone;
	}

	//---------------------------------
	// render section
	//---------------------------------

	@Override
	public VectorObject asObject() {
		return renderableList.asObject();
	}

	@Override
	public void setOpacity(double opacity) {
		renderableList.setOpacity(opacity);
	}
}
