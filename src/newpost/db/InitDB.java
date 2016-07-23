package newpost.db;

import com.google.gson.Gson;
import newpost.controller.Creator;

import java.io.*;

/**
 * Helps init db by test data
 * Use only once when we need test data
 */
public class InitDB {

    //private AppDataContainer appDataContainer;
    static final int COUNT = 10;


    public static void initDB(AppDataContainer appDataContainer){

        for (int i = 0; i < COUNT; i++) {
            appDataContainer.getClients().add(Creator.clientCreator());
            appDataContainer.getTickets().add(Creator.ticketCreator(
                    appDataContainer.getClients().get(
                            (int)(Math.random()*appDataContainer.getClients().size()))));
        }
    }

    public static void saveDBToFileAsJson(AppDataContainer appDataContainer, String location){
        Gson gson = new Gson();
        String resJson = gson.toJson(appDataContainer);
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(location),true);
            printWriter.println(resJson +  "\n");
            System.out.println("write was ok");
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String loadDB(String location) throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(location));
            return bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return null;
    }

    public static AppDataContainer loadDBAsJson(String location) throws IOException {
        Gson gson = new Gson();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(location));
            return gson.fromJson(bufferedReader.readLine(), AppDataContainer.class);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return null;
    }


}
