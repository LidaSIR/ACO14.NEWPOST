package newpost.controller.interfaces;

import newpost.model.office.User;

/**
 * Created by serhii on 21.08.16.
 */
public interface ILoginController {
    User findUser(String login, String password);
}
