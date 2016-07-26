package newpost.test.controller;

import newpost.controller.interfaces.IPostController;
import newpost.controller.PostController;

/**
 * Created by Vladislav on 24.07.2016.
 */
public class TestPostController {

    public static void main(String[] args) {

        IPostController postController = new PostController(null);
        postController.showOfficesOnMap();
    }

}
