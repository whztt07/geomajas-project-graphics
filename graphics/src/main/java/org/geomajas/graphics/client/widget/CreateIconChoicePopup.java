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
package org.geomajas.graphics.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import org.geomajas.graphics.client.controller.create.CreateIconController;
import org.geomajas.graphics.client.object.updateable.AnchoredIcon;
import org.geomajas.graphics.client.resource.GraphicsResource;
import org.geomajas.graphics.client.object.updateable.anchored.MarkerShape;
import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.Shape;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Popup window with icon and marker choice.
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

	private DrawingArea previewArea;

	/* constructor elements */
	private final CreateIconController controller;

	private List<String> hrefsChoiceList;

	private List<MarkerShape> markerShapesChoiceList;

	/* selected elements */
	private String selectedIconUrl;

	private MarkerShape selectedMarkerShape;

	/* default value, used in case of clean slate */
	private MarkerShape defaultMarkerShape;

	private String defaultIconUrl;

	//TODO: put these values in a util class or default data provider
	private final List<String> defaultIconurls = new ArrayList<String>(Arrays.asList(
			GWT.getModuleBaseURL() + "image/sun.jpg", GWT.getModuleBaseURL() + "image/cloud.png"));

	//TODO: put these values in a util class or default data provider
	private final List<MarkerShape> defaultMarkershapes = new ArrayList<MarkerShape>(
			Arrays.asList(MarkerShape.values()));

	/* configuration popup layout */

	/**
	 * Size of icons to choose from. In pixels.
	 */
	private int choiceListImageSize = 20;

	private int imagePerRow = 5;

	private int previewImageWidth;

	private int previewImageHeight;

	/* show selection with border */

	/**
	 * keep the click icons for selection
	 */
	private Map<String, ClickableIconImage> icons = new HashMap<String, ClickableIconImage>();

	private Map<MarkerShape, ClickableMarkerShape> markers = new HashMap<MarkerShape, ClickableMarkerShape>();

	private String noMarkerStyle = "noMarker";

	/**
	 * Popup for choosing icons and markers from a given list.
	 *
	 * @param controllerInput
	 * @param hrefs list of url String values for icons to choose from. The first one will be default value.
	 *                 In case of null, a default list will be used.
	 * @param markerShapes list of marker Shapes to choose from. The first one will be default value. In case of null,
	 *                     a default list will be used.
	 */
	public CreateIconChoicePopup(CreateIconController controllerInput, List<String> hrefs,
								 List<MarkerShape> markerShapes) {
		this.controller = controllerInput;
		UIBINDER.createAndBindUi(this);
		iconChoiceTablePanel.setStyleName("iconCreationChoiceTable");

		okButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				controller.createIconInContainer(selectedIconUrl, selectedMarkerShape);
				hide();
			}

		});

		// preview panel
		previewArea = new DrawingArea(40, 80);
		previewPanel.setWidget(previewArea);

		// set icons choice
		setIconChoiceList(hrefs);

		// set markers choice
		setMarkerChoiceList(markerShapes);
	}

	/* popup display*/

	public void show(int clientX, int clientY) {
		if (onlyOneChoice()) {
			controller.createIconInContainer(selectedIconUrl, selectedMarkerShape);
		} else {
			dialog.setPopupPosition(clientX, clientY);
			dialog.show();
		}
	}

	public void hide() {
		dialog.hide();
	}

	public void clear() {
		setSelectedIconUrl(null);
		setSelectedMarkerShape(null);
		if (previewArea != null) {
			previewArea.clear();
		}
	}

	/* setters */

	public void setPreviewImageWidth(int previewImageWidth) {
		this.previewImageWidth = previewImageWidth;
	}

	public void setPreviewImageHeight(int previewImageHeight) {
		this.previewImageHeight = previewImageHeight;
	}

	public void setDefaultIconUrl(String defaultIconUrl) {
		selectedIconUrl = defaultIconUrl;
		updatePreview();
	}

	/**
	 * Set size of the icons and markers in the choice list. Must be at least 12.
	 * @param choiceListImageSize
	 */
	public void setChoiceListImageSize(int choiceListImageSize) {
		this.choiceListImageSize = Math.max(choiceListImageSize, 12);
		updateIconChoice();
		updateMarkerChoice();
	}

	public void setMarkerSectionVisible(boolean visible) {
		previewCaptionPanel.setVisible(visible);
		if (visible) {
			iconChoiceTablePanel.removeStyleDependentName(noMarkerStyle);
		} else {
			iconChoiceTablePanel.addStyleDependentName(noMarkerStyle);
		}
	}

	public void setIconChoiceList(List<String> urls) {
		if (urls != null && urls.size() > 0) {
			hrefsChoiceList = urls;
		} else {
			hrefsChoiceList = defaultIconurls;
		}
		defaultIconUrl = hrefsChoiceList.get(0);
		updateIconChoice();
	}

	public void setMarkerChoiceList(List<MarkerShape> markerShapes) {
		if (markerShapes != null && markerShapes.size() > 0) {
			markerShapesChoiceList = markerShapes;
		} else {
			markerShapesChoiceList = defaultMarkershapes;
		}
		defaultMarkerShape = markerShapesChoiceList.get(0);
		updateMarkerChoice();
	}

	/* selection and emphasis */

	protected void setSelectedIconUrl(String iconUrl) {
		this.selectedIconUrl = iconUrl != null ? iconUrl : defaultIconUrl;
		emphasizeSelectedIconInChoiceList(selectedIconUrl);
		updatePreview();
	}

	protected void setSelectedMarkerShape(MarkerShape markerShape) {
		this.selectedMarkerShape = markerShape != null ? markerShape : defaultMarkerShape;
		emphasizeSelectedMarkerInChoiceList(selectedMarkerShape);
		updatePreview();
	}

	private void emphasizeSelectedIconInChoiceList(String iconUrl) {
		for (Map.Entry<String, ClickableIconImage> entry : icons.entrySet()) {
			entry.getValue().setSelected(entry.getKey().equals(iconUrl));
		}
	}

	private void emphasizeSelectedMarkerInChoiceList(MarkerShape shape) {
		for (Map.Entry<MarkerShape, ClickableMarkerShape> entry : markers.entrySet()) {
			entry.getValue().setSelected(entry.getKey().equals(shape));
		}
	}

	/* update elements */

	public void updateIconChoice() {
		iconsPanel.clear();
		icons.clear();
		Set<String> urlSet = new LinkedHashSet<String>(hrefsChoiceList);
		for (String url : urlSet) {
			ClickableIconImage clickImage = getClickableIconImage(url);
			icons.put(url, clickImage);
			iconsPanel.add(clickImage);
		}
		setSelectedIconUrl(defaultIconUrl);
	}

	protected ClickableIconImage getClickableIconImage(String url) {
		return new ClickableIconImage(url);
	}

	public void updateMarkerChoice() {
		markersPanel.clear();
		markers.clear();

		DrawingArea drawingArea = new DrawingArea((choiceListImageSize + 5 ) * imagePerRow, choiceListImageSize + 5);
		drawingArea.getElement().getStyle().setMargin(5, Unit.PX);
		int amountOfMarkers = 0;
		Set<MarkerShape> markerSet = new LinkedHashSet<MarkerShape>(markerShapesChoiceList);
		for (MarkerShape markerShape : markerSet) {
			ClickableMarkerShape marker = getClickableMarkerShape(markerShape);
			drawingArea.add(translateMarker(marker.asVectorObject(), amountOfMarkers++));
			markers.put(markerShape, marker);
		}
		markersPanel.setWidget(drawingArea);
		setSelectedMarkerShape(defaultMarkerShape);
	}

	protected ClickableMarkerShape getClickableMarkerShape(MarkerShape markerShape) {
		return new ClickableMarkerShape(markerShape);
	}

	protected void updatePreview() {
		previewArea.clear();

		// icon
		Coordinate iconPosition = new Coordinate(previewArea.getWidth() / 2, 20);
		Coordinate markerPosition  = new Coordinate(iconPosition);
		markerPosition.setY(markerPosition.getY() + 40);
		AnchoredIcon previewIcon = new AnchoredIcon(iconPosition, previewImageWidth,
				previewImageHeight, selectedIconUrl, markerPosition, selectedMarkerShape);
		previewArea.add(previewIcon.asObject());
	}

	/* private methods */

	private boolean onlyOneChoice() {
		return (icons.size() == 1 && (!previewCaptionPanel.isVisible() || markers.size() == 1));
	}

	/**
	 * used for displaying marker SVG elements in a drawing area.
	 * @param shape
	 * @param amountOfMarkers
	 * @return
	 */
	private VectorObject translateMarker(VectorObject shape, int amountOfMarkers) {
		if (amountOfMarkers % imagePerRow != 0) {
			int size = choiceListImageSize + 6;
			shape.setTranslation(size * (amountOfMarkers % imagePerRow), size * (amountOfMarkers / imagePerRow));
		}
		return shape;
	}

	/**
	 * Shows an icon in the popup window.
	 * 
	 * @author Jan Venstermans
	 * 
	 */
	protected class ClickableIconImage implements IsWidget {

		private Image iconImage;

		public ClickableIconImage(String iconUrl) {
			iconImage = new Image(iconUrl);
			iconImage.setHeight(choiceListImageSize + "px");
			iconImage.addStyleName(GraphicsResource.INSTANCE.css().iconsPanelImage());
			iconImage.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					setSelectedIconUrl(iconImage.getUrl());
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
	protected class ClickableMarkerShape implements MouseOverHandler, MouseOutHandler {

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
			simpleShape = shape.getMarkerShape(choiceListImageSize / 2, choiceListImageSize / 2,
					choiceListImageSize - 8);
			simpleShape.setFixedSize(true);
			simpleShape.setFillColor("#FF6600");
			simpleShape.setStrokeColor("#FF6600");
			simpleShape.setFillOpacity(0.7);
			
			rectangle = new Rectangle(0, 0, choiceListImageSize, choiceListImageSize);
			rectangle.setStrokeOpacity(0);
			rectangle.setStrokeWidth(1);
			rectangle.setStrokeColor("black");
			rectangle.setFillOpacity(0);
			rectangle.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					setSelectedMarkerShape(shape);
				}

			});
			rectangle.addMouseOverHandler(this);
			rectangle.addMouseOutHandler(this);
			rectangle.getElement().getStyle().setCursor(Cursor.POINTER);
			
			group = new Group();
			group.add(simpleShape);
			group.add(rectangle);
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
