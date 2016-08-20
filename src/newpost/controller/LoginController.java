package newpost.controller;

import newpost.db.AppDataContainer;
import newpost.model.office.User;

/**
 * Created by Anna on 19.08.2016.
 */
public class LoginController {

    private AppDataContainer appDataContainer;

    public LoginController(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    public User loginFrame(String login, String password){
        User user = appDataContainer.getUsers().get(login);
        if ((user != null) && user.getPassword().equals(password)) return user;
        return null;
    }
}
