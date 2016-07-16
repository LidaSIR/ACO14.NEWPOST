package newpost.db;

import newpost.controller.Creator;
import newpost.model.Client;
import newpost.model.PostTicket;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

/**
 * Created by macaque on 16.07.2016.
 */
public class InitDB {

    //private AppDataContainer appDataContainer;
    final int COUNT = 10;


    public void createDB(AppDataContainer appDataContainer){

        for (int i = 0; i < COUNT; i++) {
            appDataContainer.getClients().add(Creator.clientCreator());
            appDataContainer.getTickets().add(Creator.ticketCreator(
                    appDataContainer.getClients().get(
                            (int)(Math.random()*appDataContainer.getClients().size()))));
        }
    }

    public void saveDB(){

    }

    public void loadDB(){

    }


}
