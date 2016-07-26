package newpost.controller;

import newpost.controller.interfaces.IPostController;
import newpost.db.AppDataContainer;
import newpost.model.common.Address;
import newpost.model.office.PostOffice;
import newpost.utils.geolocation.MapFramefork.ShowMapMarkerFrame;
import newpost.utils.geolocation.controller.GoogleMapsAPI;
import newpost.utils.geolocation.controller.GoogleMapsAPIImpl;
import newpost.utils.geolocation.controller.Location;

import javax.swing.*;
import java.util.ArrayList;
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
        Location myLocation  = googleMapsAPI.findLocation("Україна", "Київ", "Вулиця Старокиївська", "10");

        List<Location> locations = new ArrayList();

        List<PostOffice> postOffices = appDataContainer.getPostOffices();

        for (PostOffice pst: postOffices){
            Address addr = pst.getAddress();
            locations.add(googleMapsAPI.findLocation("Ukraine", addr.getCity(), addr.getStreet(), addr.getHouseNum()));
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {new ShowMapMarkerFrame(myLocation, locations);
            }
        });


    }
}
