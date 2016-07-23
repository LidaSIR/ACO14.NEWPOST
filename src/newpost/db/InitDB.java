package newpost.db;

import com.google.gson.Gson;
import newpost.controller.DataInitFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Helps init db by test data
 * Use only once when we need test data
 */
public class InitDB {

    //private AppDataContainer appDataContainer;
    static final int COUNT = 10;


    public static void createDB(AppDataContainer appDataContainer){

        for (int i = 0; i < COUNT; i++) {
            appDataContainer.getClients().add(DataInitFactory.clientCreator());
            appDataContainer.getTickets().add(DataInitFactory.ticketCreator(
                    appDataContainer.getClients().get(
                            (int) (Math.random() * appDataContainer.getClients().size()))));
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
