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
package org.geomajas.graphics.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.controller.CreateIconController;
import org.geomajas.graphics.client.object.GIcon;
import org.geomajas.graphics.client.object.anchor.Anchored;
import org.geomajas.graphics.client.object.anchor.ResizableAnchorer;
import org.geomajas.graphics.client.resource.GraphicsResource;
import org.geomajas.graphics.client.shape.AnchoredImage;
import org.geomajas.graphics.client.shape.MarkerShape;
import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.Shape;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Popup window with icon and marker choice, used by
 * {@link org.geomajas.graphics.client.controller.CreateIconController} and
 * {@link org.geomajas.graphics.client.controller.CreateAnchoredIconController}.
 * 
 * @author Jan Venstermans
 * 
 */
public class CreateIconChoicePopup {

	private static final Binder UIBINDER = GWT.create(Binder.class);

	/**
	 * UI binder.
	 * 
	 */
	interface Binder extends UiBinder<PopupPanel, CreateIconChoicePopup> {

	}

	@UiField
	protected PopupPanel dialog;

	@UiField
	protected FlowPanel iconsPanel;

	@UiField
	protected SimplePanel markersPanel;

	@UiField
	protected FlowPanel markersPanel2;

	@UiField
	protected SimplePanel previewPanel;

	@UiField
	protected Button okButton;

	@UiField
	protected HTMLPanel iconChoiceTablePanel;

	@UiField
	protected CaptionPanel previewCaptionPanel;

	private final CreateIconController controller;

	private int imagePerRow = 5;

	/**
	 * Size of icons to choose from. In pixels.
	 */
	private int imageChoiceIconSize = 20;

	private int previewImageWidth;

	private int previewImageHeight;

	private int amountOfMarkers = -1;

	// default value, used in case of clean slate
	private MarkerShape defaultMarkerShape = MarkerShape.SQUARE;

	private String defaultIconUrl = GWT.getModuleBaseURL() + "image/sun.jpg";

	/**
	 * selected icon
	 */
	private String iconUrl;

	/**
	 * selected marker
	 */
	private MarkerShape markerShape;

	/**
	 * keep the click icons for selection
	 */
	private Map<String, ClickableIconImage> icons = new HashMap<String, ClickableIconImage>();

	private Map<MarkerShape, ClickableMarkerShape> markers = new HashMap<MarkerShape, ClickableMarkerShape>();

	private GIcon previewIcon;

	private DrawingArea previewArea;

	private String noMarkerStyle = "noMarker";

	protected void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
		updatePreviewAndSelection();
	}

	protected void setMarkerShape(MarkerShape markerShape) {
		this.markerShape = markerShape;
		updatePreviewAndSelection();
	}

	public CreateIconChoicePopup(CreateIconController controllerInput, List<String> hrefs) {
		this.controller = controllerInput;
		UIBINDER.createAndBindUi(this);
		iconChoiceTablePanel.setStyleName("iconCreationChoiceTable");
		okButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				controller.createIconInContainer(iconUrl, markerShape);
				hide();
			}

		});

		// preview panel
		previewArea = new DrawingArea(40, 80);
		previewPanel.setWidget(previewArea);

		// set icons choice
		setIconsToChooseFrom(hrefs);

		// markers choice
		DrawingArea drawingArea = new DrawingArea((imageChoiceIconSize + 5 ) * imagePerRow, imageChoiceIconSize + 5);
		ClickableMarkerShape marker1 = new ClickableMarkerShape(MarkerShape.SQUARE);
		drawingArea.add(translateMarker(marker1.asVectorObject()));
		ClickableMarkerShape marker2 = new ClickableMarkerShape(MarkerShape.CIRCLE);
		drawingArea.add(translateMarker(marker2.asVectorObject()));
		ClickableMarkerShape marker3 = new ClickableMarkerShape(MarkerShape.CROSS);
		drawingArea.add(translateMarker(marker3.asVectorObject()));
		markers.put(MarkerShape.SQUARE, marker1);
		markers.put(MarkerShape.CIRCLE, marker2);
		markers.put(MarkerShape.CROSS, marker3);

		markersPanel.setWidget(drawingArea);
		drawingArea.getElement().getStyle().setMargin(5, Unit.PX);
	}

	protected void updatePreviewAndSelection() {
		previewArea.clear();
		if (iconUrl != null) {
			selectIcon(iconUrl);
			previewIcon = new GIcon(new AnchoredImage(0, 0, previewImageWidth,
					previewImageHeight, iconUrl, 0.5, 0.5));
			Coordinate iconPosition = new Coordinate(previewArea.getWidth() / 2, 20);
			previewIcon.setPosition(iconPosition);
			if (markerShape != null) {
				selectMarker(markerShape);
				previewIcon.addRole(new ResizableAnchorer(new Coordinate(0, 0), markerShape));
				Coordinate markerPosition  = new Coordinate(iconPosition);
				markerPosition.setY(markerPosition.getY() + 40);
				previewIcon.getRole(Anchored.TYPE).setAnchorPosition(markerPosition);
			}
			previewArea.add(previewIcon.asObject());
		}
	}

	private void selectIcon(String iconUrl) {
		 for (Map.Entry<String, ClickableIconImage> entry : icons.entrySet()) {
			entry.getValue().setSelected(entry.getKey().equals(iconUrl));
		 }
	}

	private void selectMarker(MarkerShape shape) {
		for (Map.Entry<MarkerShape, ClickableMarkerShape> entry : markers.entrySet()) {
			entry.getValue().setSelected(entry.getKey().equals(shape));
		}
	}

	public void show(int clientX, int clientY) {
		if (onlyOneChoice()) {
			controller.createIconInContainer(iconUrl, markerShape);
		} else {
			dialog.setPopupPosition(clientX, clientY);
			dialog.show();
		}
	}

	public void hide() {
		dialog.hide();
	}

	private boolean onlyOneChoice() {
	  return (icons.size() == 1 && (!previewCaptionPanel.isVisible() || markers.size() == 1));
	}

	public void clear() {
		iconUrl = defaultIconUrl;
		markerShape = defaultMarkerShape;
		if (previewArea != null) {
			previewArea.clear();
		}
	}

	// translate so it will be
	private VectorObject translateMarker(VectorObject shape) {
		if (amountOfMarkers % imagePerRow != 0) {
			int size = imageChoiceIconSize + 6;
			shape.setTranslation(size * (amountOfMarkers % imagePerRow), size * (amountOfMarkers / imagePerRow));
		}
		return shape;
	}

	protected void raiseMarkerCount() {
		amountOfMarkers++;
	}

	public void setMarkerSectionVisible(boolean visible) {
		previewCaptionPanel.setVisible(visible);
		if (visible) {
			iconChoiceTablePanel.removeStyleDependentName(noMarkerStyle);
		} else {
			iconChoiceTablePanel.addStyleDependentName(noMarkerStyle);
		}

	}

	public void setIconsToChooseFrom(List<String> urls) {
		clear();
		iconsPanel.clear();
		icons.clear();
		// if urls is empty, fill with some default values
		if (urls == null || urls.size() == 0) {
			urls = new ArrayList<String>();
			urls.add(GWT.getModuleBaseURL() + "image/cloud.png");
			urls.add(GWT.getModuleBaseURL() + "image/sun.jpg");
		}
		Set<String> urlSet = new LinkedHashSet<String>();
		if (urls != null) {
			urlSet.addAll(urls);
		}
		for (String url : urlSet) {
			ClickableIconImage clickImage = new ClickableIconImage(url);
			icons.put(url, clickImage);
			iconsPanel.add(clickImage);
		}
		// use first url value as default
		if (urls != null) {
			setDefaultIconUrl(urls.get(0));
		}
	}

	public void setPreviewImageWidth(int previewImageWidth) {
		this.previewImageWidth = previewImageWidth;
	}

	public void setPreviewImageHeight(int previewImageHeight) {
		this.previewImageHeight = previewImageHeight;
	}

	public void setDefaultIconUrl(String defaultIconUrl) {
	   	iconUrl = defaultIconUrl;
		updatePreviewAndSelection();
	}

	/**
	 * Shows an icon in the popup window.
	 * 
	 * @author Jan Venstermans
	 * 
	 */
	private class ClickableIconImage implements IsWidget {

		private Image iconImage;

		public ClickableIconImage(String iconUrl) {
			iconImage = new Image(iconUrl);
			iconImage.setHeight(imageChoiceIconSize + "px");
			iconImage.addStyleName(GraphicsResource.INSTANCE.css().iconsPanelImage());
			iconImage.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					setIconUrl(iconImage.getUrl());
				}

			});
		}

		public Widget asWidget() {
			return iconImage;
		}

		public void setSelected(boolean selected) {
			// style depenedent names currently not covered by gwt CssResource
			// solution: add and remove separate style names
			if (selected) {
				iconImage.removeStyleName(GraphicsResource.INSTANCE.css().iconsPanelImage());
				iconImage.addStyleName(GraphicsResource.INSTANCE.css().iconsPanelImageSelected());
			} else {
				iconImage.addStyleName(GraphicsResource.INSTANCE.css().iconsPanelImage());
				iconImage.removeStyleName(GraphicsResource.INSTANCE.css().iconsPanelImageSelected());
			}
		}
	}

	/**
	 * Shows a marker in the popup window. Contains 2 shape files: simpleShape is the shape that will be drawn in the
	 * popup window; markerShape is a cloneable Shape that will be passed to the Anchor constructor.
	 * 
	 * @author Jan Venstermans
	 * 
	 */
	private class ClickableMarkerShape implements MouseOverHandler, MouseOutHandler, MouseDownHandler {

		/**
		 * enum value refering to the shape of the marker.
		 */
		private MarkerShape shape;

		/**
		 * the shape itself.
		 */
		private Shape simpleShape;

		/**
		 * click area.
		 */
		private Rectangle rectangle;

		private Group group;

		private boolean selected;

		public ClickableMarkerShape(MarkerShape markerEnum) {
			shape = markerEnum;
			simpleShape = shape.getMarkerShape(imageChoiceIconSize / 2, imageChoiceIconSize / 2,
					imageChoiceIconSize - 8);
			simpleShape.setFixedSize(true);
			simpleShape.setFillColor("#FF6600");
			simpleShape.setStrokeColor("#FF6600");
			simpleShape.setFillOpacity(0.7);
			
			rectangle = new Rectangle(0, 0, imageChoiceIconSize, imageChoiceIconSize);
			rectangle.setStrokeOpacity(0);
			rectangle.setStrokeWidth(1);
			rectangle.setStrokeColor("black");
			rectangle.setFillOpacity(0);
			rectangle.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					// Bbox bounds = getBoxOfSimpleShape();
					// Coordinate coord = new Coordinate(event.getClientX(),event.getClientY());
					// System.out.println(bounds.toString() + " " +coord.toString());
					setMarkerShape(shape);
				}

			});
			rectangle.addMouseOverHandler(this);
			rectangle.addMouseOutHandler(this);
			rectangle.getElement().getStyle().setCursor(Cursor.POINTER);
			
			group = new Group();
			group.add(simpleShape);
			group.add(rectangle);
			raiseMarkerCount();
		}

		@Override
		public void onMouseOut(MouseOutEvent event) {
			if (!selected) {
				rectangle.setStrokeOpacity(0);
			}
		}

		@Override
		public void onMouseOver(MouseOverEvent event) {
			if (!selected) {
				rectangle.setStrokeOpacity(0.5);
			}
		}

		public VectorObject asVectorObject() {
			return group;
		}

		@Override
		public void onMouseDown(MouseDownEvent event) {
			setMarkerShape(markerShape);
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
			if (selected) {
				rectangle.setStrokeOpacity(1);
			} else {
				rectangle.setStrokeOpacity(0);
			}
		}
	}
}
