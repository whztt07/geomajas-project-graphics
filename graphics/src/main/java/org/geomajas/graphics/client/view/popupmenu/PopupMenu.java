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
package org.geomajas.graphics.client.view.popupmenu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import org.geomajas.graphics.client.action.Action;
import org.geomajas.graphics.client.controller.popupmenu.PopupMenuController;
import org.geomajas.graphics.client.resource.GraphicsResource;

/**
 * A popup menu for executing a choice of actions. Some actions may invoke an editor.
 * 
 * @author Jan De Moerloose
 * 
 */
public class PopupMenu implements PopupMenuController.View {

	private static final Binder UIBINDER = GWT.create(Binder.class);

	/**
	 * UI binder.
	 * 
	 */
	interface Binder extends UiBinder<PopupPanel, PopupMenu> {

	}

	private GraphicsResource graphicsResource = GWT.create(GraphicsResource.class);

	@UiField
	protected PopupPanel popupPanel;

	@UiField
	protected FlowPanel menuPanel;

	private PopupMenuController.Handler handler;

	/**
	 * Default constructor.
	 */
	public PopupMenu() {
		popupPanel = UIBINDER.createAndBindUi(this);
	}

	@Override
	public void show(int clientX, int clientY) {
		popupPanel.setPopupPosition(clientX, clientY);
		popupPanel.show();
	}

	@Override
	public void addHandler(PopupMenuController.Handler handler) {
		this.handler = handler;
	}

	@Override
	public void hide() {
		popupPanel.hide();
	}

	@Override
	public void addAction(String label, final Action action) {
		if (action.getIconUrl() != null) {
			Image img = new Image(action.getIconUrl());
			img.setTitle(label);		
			Button button = new Button();
			button.getElement().appendChild(img.getElement());
			button.setTitle(label);
			button.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					handler.onSelectAction(action);
				}
				
			});
			menuPanel.add(button);
		} else {
			Label menuItem = new Label(label);
			menuItem.setStyleName(graphicsResource.css().popupMenuItem());
			menuItem.addMouseUpHandler(new MouseUpHandler() {

				@Override
				public void onMouseUp(MouseUpEvent event) {
					handler.onSelectAction(action);
				}
			});
			menuPanel.add(menuItem);
		}	
	}

}