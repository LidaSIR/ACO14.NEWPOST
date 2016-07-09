package newpost.controller;

import newpost.db.AppDataContainer;

/**
 * Created by Serhii Fursenko on 08.07.2016.
 * fyrsenko@gmail.com
 */
public class DirectorController {

    private AppDataContainer appDataContainer;

    public DirectorController(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    public AppDataContainer getAppDataContainer() {
        return appDataContainer;
    }

    public void setAppDataContainer(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }
}
