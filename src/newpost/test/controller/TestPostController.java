package newpost.test.controller;

import newpost.controller.interfaces.IPostController;
import newpost.controller.PostController;
import newpost.db.AppDataContainer;
import newpost.db.InitDB;

/**
 * Created by Vladislav on 24.07.2016.
 */
public class TestPostController {

    public static void main(String[] args) {

        AppDataContainer appDataContainer = new AppDataContainer();

        InitDB.initDB(appDataContainer);

        IPostController postController = new PostController(appDataContainer);
        postController.showOfficesOnMap();
    }

}
