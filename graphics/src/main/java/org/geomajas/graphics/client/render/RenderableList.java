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

import java.util.List;

/**
 * Contains an ordered list of {@link Renderable} objects.
 *
 * @author Jan Venstermans
 *
 */
public interface RenderableList extends Renderable {

	void addRenderable(Renderable renderable);

	List<Renderable> getRenderableList();
}
