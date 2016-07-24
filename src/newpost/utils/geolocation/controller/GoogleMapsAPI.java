package newpost.utils.geolocation.controller;

/**
 * Created by serhii on 20.12.15.
 */
public interface GoogleMapsAPI {

    Location findLocation(String unformatted);

    Location findLocation(String country, String city, String street, String houseNum);

    double getDistance(Location pointA, Location pointB);

}
