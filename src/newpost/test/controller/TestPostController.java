package newpost.test.controller;

import newpost.controller.IPostController;
import newpost.controller.PostController;

import java.util.regex.PatternSyntaxException;

/**
 * Created by Vladislav on 24.07.2016.
 */
public class TestPostController {

    public static void main(String[] args) {

        IPostController postController = new PostController(null);
        postController.showOfficesOnMap();
    }

}
