package newpost.db;

import com.google.gson.Gson;
import com.sun.deploy.association.utility.AppAssociationReader;
import newpost.controller.Creator;
import newpost.model.Client;
import newpost.model.PostTicket;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

/**
 * Created by macaque on 16.07.2016.
 */
public class InitDB {

    //private AppDataContainer appDataContainer;
    static final int COUNT = 10;


    public static void createDB(AppDataContainer appDataContainer){

        for (int i = 0; i < COUNT; i++) {
            appDataContainer.getClients().add(Creator.clientCreator());
            appDataContainer.getTickets().add(Creator.ticketCreator(
                    appDataContainer.getClients().get(
                            (int)(Math.random()*appDataContainer.getClients().size()))));
        }
    }

    public static void saveDBToFileasJson(AppDataContainer appDataContainer, String location){
        Gson gson = new Gson();
        String resJson = gson.toJson(appDataContainer);
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(location));
            printWriter.println(resJson);
            System.out.println("write was ok");
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadDB(){

    }


}
