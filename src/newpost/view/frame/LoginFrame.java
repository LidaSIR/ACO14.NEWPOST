package newpost.view.frame;

import newpost.controller.LoginController;
import newpost.db.AppDataContainer;
import newpost.model.office.Client;
import newpost.model.office.Employee;
import newpost.model.office.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lida on 18.08.2016.
 */
public class LoginFrame extends JFrame {

    private AppDataContainer appDataContainer;
    private LoginController loginController;

    private JTextField login;
    private JTextField password;
    private JLabel incorrectPass;

    public LoginFrame(AppDataContainer appDataContainer) throws HeadlessException {

        this.appDataContainer = appDataContainer;
        this.loginController = new LoginController(appDataContainer);

        setTitle("Authorization");
        setSize(350, 125);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        setVisible(true);

    }

    void init() {
        login = new JTextField("");
        password = new JPasswordField("");
        setLayout(new GridLayout(3, 2));

        getContentPane().add(new JLabel("login:"));
        getContentPane().add(login);
        getContentPane().add(new JLabel("password:"));
        getContentPane().add(password);

        JButton okButton = new JButton("OK");
        okButton.setMnemonic('O');
        okButton.setToolTipText("press after typing login and password");
        okButton.addActionListener(new MyActionListener());
        getContentPane().add(okButton);

        incorrectPass = new JLabel("",SwingConstants.CENTER);
        getContentPane().add(incorrectPass);

    }
    private class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            User user = loginController.loginFrame(login.getText(),password.getText());
            if (user instanceof Employee) {
                ManagerView managerFrame = new ManagerView(appDataContainer);
                managerFrame.showManagerView();
                setVisible(false);
            }
               else if (user instanceof Client){
                    ClientView clientView = new ClientView(appDataContainer, (Client) user);
                    setVisible(false);
            } else {
                login.setText("");
                password.setText("");
                incorrectPass.setText("invalid login or password");
                incorrectPass.setForeground(Color.red);
            }

        }
    }
}
