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
package org.geomajas.graphics.client.render;

import org.vaadin.gwtgraphics.client.VectorObject;

import java.util.ArrayList;
import java.util.List;

public class MockRenderableList implements RenderableList {

	private List<Renderable> renderables = new ArrayList<Renderable>();


	@Override
	public void addRenderable(Renderable renderable) {
		renderables.add(renderable);
	}

	@Override
	public List<Renderable> getRenderableList() {
		return renderables;
	}

	@Override
	public VectorObject asObject() {
		return null;
	}

	@Override
	public void setOpacity(double opacity) {

	}
}
