package pl.edu.agh.iosr.twitter.dto.assembler;

import org.mongodb.morphia.geo.Point;
import org.mongodb.morphia.geo.PointBuilder;
import twitter4j.GeoLocation;

/**
 * Created by radoslawdyrda on 25.05.2015.
 */
public class PointAssembler {

    public static Point convert(GeoLocation geoLocation){
        PointBuilder pb = new PointBuilder();
        if(geoLocation != null) {
            pb.latitude(geoLocation.getLatitude());
            pb.longitude(geoLocation.getLongitude());
        }
        return pb.build();
    }
}
