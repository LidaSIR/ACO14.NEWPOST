package newpost.utils;

import newpost.db.AppDataContainer;
import newpost.db.InitDB;

/**
 * Created by serhii on 20.08.16.
 */
public class InitDataBaseScript {
    public static void main(String[] args) {
        AppDataContainer appDataContainer = new AppDataContainer();
        InitDB.initDB(appDataContainer);
        InitDB.saveDBToFileAsJson(appDataContainer);
    }
}
