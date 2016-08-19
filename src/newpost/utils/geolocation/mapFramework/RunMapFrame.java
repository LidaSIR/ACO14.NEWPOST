package newpost.utils.geolocation.mapFramework;

import newpost.utils.geolocation.controller.Location;

import javax.swing.*;
import java.util.Arrays;

/**
 * Created by serhii on 14.08.16.
 */
public class RunMapFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShowMapMarkerFrame(new Location(50.4538759, 30.5064151, "my location"),
                        Arrays.asList(new Location(50.4603181, 30.5223264, "driver1"),
                                new Location(50.4292824, 30.536174, "driver2")));
            }
        });
    }
}
