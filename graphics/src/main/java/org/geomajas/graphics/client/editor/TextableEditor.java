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
package org.geomajas.graphics.client.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.mogaleaf.client.common.widgets.ColorHandler;
import com.mogaleaf.client.common.widgets.SimpleColorPicker;
import org.geomajas.graphics.client.object.ExternalLabel;
import org.geomajas.graphics.client.object.GText;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.Labeled;
import org.geomajas.graphics.client.object.role.RoleType;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.operation.LabelOperation;
import org.geomajas.graphics.client.resource.GraphicsResource;
import org.geomajas.graphics.client.resource.i18n.GraphicsMessages;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.util.textbox.ColorTextBoxValidator;

/**
 * {@link org.geomajas.graphics.client.editor.Editor} for the {@link org.geomajas.graphics.client.object.role.Labeled} role.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class TextableEditor extends AbstractRoleEditor<Textable> {

	private static final Binder UIBINDER = GWT.create(Binder.class);

	/**
	 * UI binder.
	 *
	 */
	interface Binder extends UiBinder<HTMLPanel, TextableEditor> {

	}

	@UiField
	protected HTMLPanel totalPanel;

	@UiField
	protected TextArea labelBox;

	@UiField
	protected Button fillColorButton;

	@UiField
	protected ColorTextBoxValidator fillColorValidator;

	@UiField
	protected TextBox fontSize;

	@UiField
	protected TextBox fontFamily;

	private SimpleColorPicker colorPicker;

	public TextableEditor() {
		UIBINDER.createAndBindUi(this);
		totalPanel.setStyleName("popupWindow", true);
	}

	@Override
	protected RoleType<Textable> getType() {
		return Textable.TYPE;
	}

	@Override
	public Widget asWidget() {
		return totalPanel;
	}

	@Override
	public void setObject(GraphicsObject object) {
		super.setObject(object);
		setRoleObjectValuesToWidget();
	}

	@Override
	protected void setRoleObject(Textable roleObject) {
		super.setRoleObject(roleObject);
		setRoleObjectValuesToWidget();
	}

	protected void setRoleObjectValuesToWidget() {
		Textable textable = getRoleObject();
		if (textable != null) {
			labelBox.setVisibleLines(Math.min(30, Math.max(textable.getLabel().length() / 50, 1)));
			labelBox.setText(textable.getLabel());
			fillColorValidator.setLabel(textable.getFontColor());
			fontSize.setText(textable.getFontSize() + "");
			fontFamily.setText(textable.getFontFamily());
		}
	}

	public void onOk() {
		Textable textable = getRoleObject();
		if (textable != null) {
			String beforeLabel = textable.getLabel();
			String beforeColor = textable.getFontColor();
			int beforeSize = textable.getFontSize();
			String beforeFont = textable.getFontFamily();
			getService().execute(new LabelOperation(getObject(), null, beforeLabel, beforeColor, beforeSize,
					beforeFont, labelBox
					.getText(), fillColorValidator.getLabel(), Integer.parseInt(fontSize.getText()), fontFamily
					.getText()));
		}
	}

	@Override
	public String getLabel() {
		return GraphicsResource.MESSAGES.editorLabelTextable();
	}

	@Override
	public boolean validate() {
		boolean valid = true;
		if (!fillColorValidator.isValid()) {
			valid = false;
		}
		// only if renderable is labeled, there should always be text
		// This is the case for GText
		// TODO make more generic
		if (getObject() instanceof GText && !(getObject() instanceof ExternalLabel)) {

			if (((GText) getObject()).getRole(Textable.TYPE).getLabel().isEmpty()) {
				valid = false;
			}
		}
		return valid;
	}

	@UiHandler("fillColorButton")
	public void showFillColorChoice(ClickEvent e) {
		colorPicker = new SimpleColorPicker();
		colorPicker.addListner(new ColorHandler() {

			@Override
			public void newColorSelected(String color) {
				fillColorValidator.setLabel(color);
			}
		});
		int left = totalPanel.getAbsoluteLeft() + totalPanel.getOffsetWidth() + 10;
		int top = totalPanel.getAbsoluteTop() + totalPanel.getOffsetHeight() / 2;
		colorPicker.setPopupPosition(left, top);
		colorPicker.show();
	}

}
