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

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.base.BaseText;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.object.updateable.anchored.Anchored;
import org.geomajas.graphics.client.object.updateable.anchored.AnchoredImpl;
import org.geomajas.graphics.client.object.updateable.anchored.MarkerShape;
import org.geomajas.graphics.client.object.updateable.bordered.Bordered;
import org.geomajas.graphics.client.object.updateable.bordered.BorderedImpl;
import org.geomajas.graphics.client.object.updateable.wrapper.DraggableWrapperForUpdateable;
import org.geomajas.graphics.client.object.updateable.wrapper.TextableWrapperForUpdateable;
import org.geomajas.graphics.client.render.RenderableList;
import org.geomajas.graphics.client.util.CopyUtil;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link UpdateableGroupGraphicsObject}
 * that shows a text centered on a {@link org.geomajas.graphics.client.object.base.BaseRectangle},
 * and with an {@link org.geomajas.graphics.client.object.updateable.anchored.AnchoredImpl}.
 *
 * @author Jan Venstermans
 *
 */
public class AnchoredBorderedText extends UpdateableGroupGraphicsObject {

	private RenderableList renderableList;

	private BaseText baseText;

	private BorderedImpl bordered;

	private AnchoredImpl anchored;

	public AnchoredBorderedText(Coordinate textPosition, String text, int margin,  Coordinate anchorCoordinate,
								MarkerShape markerShape) {
		// create base graphics objects
		baseText = new BaseText(textPosition.getX(), textPosition.getY(), text);
		bordered = new BorderedImpl(baseText, margin);
		anchored = new AnchoredImpl(baseText, anchorCoordinate, markerShape);

		// register updateables
		addUpdateable(bordered);
		addUpdateable(anchored);

		// register roles of group object
		addRole(Textable.TYPE, new TextableWrapperForUpdateable(baseText, this));
		addRole(Draggable.TYPE, new DraggableWrapperForUpdateable(baseText, this));
		addRole(Strokable.TYPE, bordered.getStrokable());
		addRole(Fillable.TYPE, bordered.getFillable());
		addRole(Bordered.TYPE, bordered);
		addRole(Anchored.TYPE, anchored);

		// register render order
		renderableList = Graphics.getRenderElementFactory().createRenderableList();
		renderableList.addRenderable(anchored);
		renderableList.addRenderable(bordered);
		renderableList.addRenderable(baseText);
	}

	@Override
	public Object cloneObject() {
		AnchoredBorderedText clone = new AnchoredBorderedText(new Coordinate(baseText.getUserX(),
				baseText.getUserY()), baseText.getLabel(), bordered.getMargin(), anchored.getAnchorPosition(),
				anchored.getMarkerShape());
		CopyUtil.copyAnchoredProperties(this.getRole(Anchored.TYPE), clone.getRole(Anchored.TYPE));
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
