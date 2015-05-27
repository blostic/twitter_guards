package ui.timeline.editor.steps;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.geo.GeoJson;
import org.mongodb.morphia.geo.Point;
import org.mongodb.morphia.geo.Polygon;

import persistance.campaign.entity.Campaign;

import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MapClickListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolyline;
import com.vaadin.ui.Alignment;

public class InterestedArea extends EditorStep {

	private static final long serialVersionUID = 1L;
	private List<LatLon> coordinates;

	public InterestedArea() {
		GoogleMap map = new GoogleMap("");
		map.setWidth("800px");
		map.setHeight("500px");
		map.setId("google-map");
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

	@Override
	public void updateCampaign(Campaign campaign) {
		Point[] points = new Point[coordinates.size() + 1];
		for (int i = 0; i < points.length - 1; i++) {
			LatLon point = coordinates.get(i);
			points[i] = GeoJson.point(point.getLat(), point.getLon());
		}
		if (points.length > 1 ) {
			points[points.length -1 ] = points[0];
			Polygon polygon = GeoJson.polygon(points);
			campaign.setPolygon(polygon);			
		}
	}
	
}
