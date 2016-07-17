package newpost.test;

import com.sun.deploy.association.utility.AppAssociationReader;
import newpost.db.AppDataContainer;
import newpost.db.InitDB;

/**
 * Created by macaque on 16.07.2016.
 */
public class TestInitDB {

    public static void main(String[] args) {
        String location = "D:\\java\\ACO14.NEWPOST\\src\\newpost\\test\\test.txt";
        AppDataContainer appDataContainer = new AppDataContainer();
        InitDB.createDB(appDataContainer);
        InitDB.saveDBToFileasJson(appDataContainer, location);
    }
}