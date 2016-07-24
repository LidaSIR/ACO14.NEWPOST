package newpost.utils.geolocation.MapFramefork;

import freemarker.template.TemplateException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebErrorEvent;
import javafx.scene.web.WebView;

//import ua.artcode.taxi.utils.IOUtils;
//import ua.artcode.taxi.utils.StringUtils;
//import ua.artcode.taxi.utils.geolocation.controller.Location;
//import ua.artcode.taxi.utils.template.TemplateEngineUtils;


import newpost.utils.geolocation.*;
import newpost.utils.geolocation.MapFramefork.utils.TemplateEngineUtils;
import newpost.utils.geolocation.controller.Location;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by serhii on 24.04.16.
 */
public class ShowMapMarkerFrame extends JFrame {

    public static final String MARKER_TEMPLATE_PATH = "googlemapmarker.ftlh";

    private final List<Location> otherLocations;
    private final Location myLocation;

    public ShowMapMarkerFrame(Location myLocation, List<Location> otherLocation) throws HeadlessException {
        this.myLocation = myLocation;
        this.otherLocations = otherLocation;
        setSize(600, 600);
        JFXPanel panel = new JFXPanel();
        add(panel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        Platform.runLater(() -> InitializerFX(panel));
    }

    private void InitializerFX(JFXPanel panel) {
        final WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();

        try {
//            String fileContent = new String(IOUtils.readBytes(MARKER_TEMPLATE_PATH, 4000));
            Map map = new HashMap<>();
            map.put("myLocationLat", myLocation.getLat());
            map.put("myLocationLng", myLocation.getLng());
            map.put("zoom", 10);
            //map.put("locations", otherLocations);

            String strLocations = "[" + String.join(",", otherLocations.stream().map(Location::toString).collect(Collectors.toList())) + "]";
            map.put("locations", strLocations);

            String formattedTemplate = TemplateEngineUtils.merge(MARKER_TEMPLATE_PATH, map);
            webEngine.loadContent(formattedTemplate);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create
        Scene scene = new Scene(webView, 600, 600, javafx.scene.paint.Color.web("#666970"));

        panel.setScene(scene);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShowMapMarkerFrame(new Location(50.4538759, 30.5064151, "my location"),
                        Arrays.asList(new Location(50.4603181,30.5223264, "driver1"),
                                new Location(50.4292824, 30.536174, "driver2")));
            }
        });
    }
}