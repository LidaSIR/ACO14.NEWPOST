package newpost.controller;

import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;
import newpost.db.AppDataContainer;
import newpost.model.common.Address;
import newpost.model.office.PostOffice;
import newpost.utils.geolocation.MapFramefork.ShowMapMarkerFrame;
import newpost.utils.geolocation.controller.GoogleMapsAPI;
import newpost.utils.geolocation.controller.GoogleMapsAPIImpl;
import newpost.utils.geolocation.controller.Location;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vladislav on 24.07.2016.
 */
public class PostController implements IPostController {

    private AppDataContainer appDataContainer;

    public PostController(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    @Override
    public void showOfficesOnMap() {
        //todo create class post office and change ticket and db
        GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
        Location myLocation  = googleMapsAPI.findLocation("Україна", "Київ", "Старокиєвська", "10");

        List<Location> locations = new ArrayList();
        locations.add(googleMapsAPI.findLocation("Україна", "Київ", "Бульва Лесі Українки", "5"));

        /*for (PostOffice pst : appDataContainer.getPostOffices()) {
            Address addr  = pst.getAddress();
            locations.add(googleMapsAPI.findLocation("Україна", addr.getCity(), addr.getStreet(), addr.getHouseNum()));
        }
*/
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShowMapMarkerFrame(myLocation, locations);
            }
        });


    }
}
