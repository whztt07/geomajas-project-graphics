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
package org.geomajas.graphics.client.controller.create.base;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.controller.create.CreateController;
import org.geomajas.graphics.client.object.base.BaseText;
import org.geomajas.graphics.client.operation.AddOperation;
import org.geomajas.graphics.client.service.GraphicsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller that creates a {@link BaseText}.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class CreateBaseTextController extends CreateController<BaseText> implements MouseUpHandler {

	private HandlerRegistration registration;

	private TextPopup popup = new TextPopup();

	protected EnterHandler handler = new EnterHandler();

	private List<HandlerRegistration> popupRegs = new ArrayList<HandlerRegistration>();

	private Coordinate position;

	public CreateBaseTextController(GraphicsService graphicsService) {
		super(graphicsService);
	}

	@Override
	public void setActive(boolean active) {
		super.setActive(active);
		if (active) {
			registration = getObjectContainer().addMouseUpHandler(this);
		} else {
			if (registration != null) {
				registration.removeHandler();
				registration = null;
			}
		}
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {	
		position = getUserCoordinate(event);
		popup.clearAndShow(event.getClientX(), event.getClientY());
		popupRegs.add(popup.addCloseHandler(handler));
		popupRegs.add(popup.addDomHandler(handler, KeyDownEvent.getType()));
		DOM.setCapture(popup.getElement());
	}
	
	public void clearPopup() {
		DOM.releaseCapture(popup.getElement());
		popup.hide();
		for (HandlerRegistration reg : popupRegs) {
			reg.removeHandler();
		}
		popupRegs.clear();
	}

	protected BaseText createText(String text) {
		return CreateBaseTextController.createTextDefault(text, position);
	}
	
	public static BaseText createTextDefault(String text, Coordinate position) {
		BaseText result = new BaseText(0, 0, text);
		result.setUserPosition(position);
		result.setFontColor("black");
		return result;
	}

	protected void addObject(BaseText result) {
		if (result == null) {
			execute(null);
			return;
		}
		execute(new AddOperation(result));
	}

	protected Coordinate getClickPosition() {
		return position;
	}
	
	protected String getPopupText() {
		return popup.getText();
	}
	
	/**
	 * 
	 */
	private static class TextPopup extends PopupPanel {
		
		private TextBox box;

		public TextPopup() {
			super(true);
			box = new TextBox();
			box.setVisibleLength(10);
			setWidget(box);
		}

		public void clearAndShow(final int clientX, final int clientY) {
			setText("");
			setPopupPositionAndShow(new PositionCallback() {
				
				@Override
				public void setPosition(int offsetWidth, int offsetHeight) {
					setPopupPosition(clientX, clientY - offsetHeight);
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {
						
						@Override
						public void execute() {
							box.setFocus(true);
						}
					});
				}
			});
		}

		public String getText() {
			return box.getText();
		}
		
		public void setText(String text) {
			box.setText(text);
		}

	}
	
	/**
	 * Handles ENTER key event.
	 * 
	 * @author Jan De Moerloose
	 * 
	 */
	protected class EnterHandler implements KeyDownHandler, CloseHandler<PopupPanel> {

		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				String text = popup.getText();
				if (text != null && !text.isEmpty()) {
					addObject(createText(text));
				}
				clearPopup();
			}
		}

		@Override
		public void onClose(CloseEvent<PopupPanel> event) {
			clearPopup();
		}


	}
}
