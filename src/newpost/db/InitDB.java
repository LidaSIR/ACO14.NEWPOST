package newpost.db;

import com.google.gson.Gson;
import newpost.controller.DataInitFactory;
import java.io.*;

/**
 * Helps init db by test data
 * Use only once when we need test data
 */
public class InitDB {

    //private AppDataContainer appDataContainer;
    static final int COUNT = 10;
    static final String DB_LOCATION = "resources/db.json";
    static final String LOG_LOCATION = "logs/logs.txt";


    public static void initDB(AppDataContainer appDataContainer){

        for (int i = 0; i < COUNT; i++) {
            appDataContainer.getClients().add(DataInitFactory.clientCreator());
            appDataContainer.getTickets().add(DataInitFactory.ticketCreator(
                    appDataContainer.getClients().get(
                            (int) (Math.random() * appDataContainer.getClients().size()))));
        }
    }

    public static void saveDBToFileAsJson(AppDataContainer appDataContainer){
        Gson gson = new Gson();
        String resJson = gson.toJson(appDataContainer);
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(DB_LOCATION,true),true);
            printWriter.println(resJson);
            printWriter.close();
            System.out.println("write was successfull");
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveLogsToFile(String logs){
        try {
            PrintWriter printWrite = new PrintWriter(new FileWriter(LOG_LOCATION, true), true);
            printWrite.println(logs);
            printWrite.close();
            System.out.println("write was successfull");
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
        throw new IOException();
    }

    public static AppDataContainer loadDBAsJson(String location) throws IOException {
        Gson gson = new Gson();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(location));
            return gson.fromJson(bufferedReader.readLine(), AppDataContainer.class);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        throw new IOException();
    }


}
