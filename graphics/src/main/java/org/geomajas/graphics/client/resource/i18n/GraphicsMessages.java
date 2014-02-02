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
package org.geomajas.graphics.client.resource.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * I18n messages for graphics project.
 * 
 * @author Jan De Moerloose
 * 
 */
public interface GraphicsMessages extends Messages {

	/* Action labels */

	String actionLabelBringToFront();

	String actionLabelDelete();

	String actionLabelDuplicate();

	String actionLabelToggleLabel();

	String actionLabelAddTextAnchor();

	/* Editor labels */

	String editorLabelTextable();

	String editorLabelLabeled();

	String editorLabelResizableAnchorStyle();

	String editorLabelStrokeFillStyle();

	String editorLabelTemplateLabeled();

	/* dfault labels on creation */ //TODO delete this functionality
	String defaultLabelRectangle();

	String defaultLabelPathPolygon();

	String defaultLabelPathLine();

	String defaultLabelEllipse();

	String defaultLabelImage();

	String loremIpsum();

	/* create icon labels */

	String createIconChoicePopupChooseIcon();

	String createIconChoicePopupChooseMarker();

	String createIconChoicePopupPreview();

	String createIconChoicePopupOkButton();


	String popupMenuEditorDialogButtonOk();
	String popupMenuEditorDialogButtonApply();
	String popupMenuEditorDialogButtonUndo();
	String popupMenuEditorDialogButtonCancel();

	String editorStrokableLabelWidth();
	String editorStrokableLabelColor();
	String editorStrokableLabelOpacity();

	String editorFillableLabelColor();
	String editorFillableLabelOpacity();

	String editorTextableLabelText();
	String editorTextableLabelColor();
	String editorTextableLabelSize();
	String editorTextableLabelFontFamily();

	String editorAnchoredLabelLineWidth();
	String editorAnchoredLabelLineColor();
	String editorAnchoredLabelLineOpacity();

	String editorAnchoredLabelPointPosition();
	String editorAnchoredLabelPointPositionX();
	String editorAnchoredLabelPointPositionY();
	String editorAnchoredLabelPointColor();
	String editorAnchoredLabelPointOpacity();

	String editorButtonColorPicker();

	/* Exceptions */

	String exceptionStrokeWidthIllegalArgument();

	String exceptionValidationColorTextBox();

	String exceptionValidationDoubleIllegalArgument();

	String exceptionValidationIntegerIllegalArgument();

	/* eventualy remove this? */
	String editorLabelAnchorStrokeDefault();

	String editorLabelAnchorPointDefault();

	String templateLabeledDefaultPrefixText();
}
