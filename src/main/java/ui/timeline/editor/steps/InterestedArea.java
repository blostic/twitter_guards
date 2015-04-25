package ui.timeline.editor.steps;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MapClickListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolyline;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

public class InterestedArea extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private List<LatLon> coordinates;

	public InterestedArea() {
		GoogleMap map = new GoogleMap("");
		map.setWidth("800px");
		map.setHeight("350px");
		
		coordinates = new ArrayList<LatLon>();
		
		map.addMapClickListener(new MapClickListener() {
			
			private static final long serialVersionUID = 1L;
			private GoogleMapPolyline polyline;
			
			@Override
			public void mapClicked(LatLon position) {
				coordinates.add(position);
				map.addMarker(new GoogleMapMarker("Border", position, true));
				if (polyline != null) {
					map.removePolyline(polyline);					
				}
				polyline = new GoogleMapPolyline(coordinates);
				map.addPolyline(polyline);
			}
		});
		addComponent(map);
		setComponentAlignment(map, Alignment.MIDDLE_CENTER);
	}
	
}
