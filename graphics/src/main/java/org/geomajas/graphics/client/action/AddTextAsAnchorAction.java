/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2013 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client.action;

import org.geomajas.graphics.client.controller.CreateTextController;
import org.geomajas.graphics.client.object.GText;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.Resizable;
import org.geomajas.graphics.client.object.anchor.Anchorable;
import org.geomajas.graphics.client.object.anchor.AnchoredToResizable;
import org.geomajas.graphics.client.resource.GraphicsResource;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Action to add a {@link GText} object anchored to an existing {@link GraphicsObject}.
 * 
 * @author Jan Venstermans
 * 
 */
public class AddTextAsAnchorAction extends AbstractAction {

	@Override
	protected String getDefaultLabel() {
		return GraphicsResource.MESSAGES.actionLabelAddTextAnchor();
	}

	@Override
	public boolean supports(GraphicsObject object) {
		return object.hasRole(Anchorable.TYPE) && object instanceof Resizable;
	}

	@Override
	public void execute(GraphicsObject object) {
		//deactivate all controllers
		getService().getMetaController().setActive(false);
		
		//create and activate a custom textcretor controller
		CreateAnchoredOnTextController textCreator = new CreateAnchoredOnTextController(getService(), object);
		textCreator.setActive(true);
	}
	
	/**
	 * This inner class creates a {@link GText} object, with role {@link Anchorable} the master
	 * {@link GraphicsObject}.
	 * 
	 * @author Jan Venstermans
	 * 
	 */
	public class CreateAnchoredOnTextController extends CreateTextController {

		private GraphicsObject object;

		public CreateAnchoredOnTextController(GraphicsService graphicsService, GraphicsObject object) {
			super(graphicsService);
			this.object = object;
		}
		
		@Override
		protected GText createText(String text) {
			setActive(false);
			GText result = super.createText(text);
			result.addRole(new AnchoredToResizable((Resizable) object));
			return result;
		}
	}

}
