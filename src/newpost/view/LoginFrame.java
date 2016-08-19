package newpost.view;

import newpost.controller.interfaces.*;
import newpost.db.AppDataContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lida on 18.08.2016.
 */
public class LoginFrame extends JFrame {

    protected static IClientController clientController;
    protected static IManagerController managerController;
    protected static IEmployeeManagement employeeManagement;
    protected static IMoneyController moneyController;
    protected static IPostController postController;

    private Menu menu;

    private JTextField login;
    private JTextField password;

    private JLabel incorrectPass;

    public LoginFrame(Menu menu) {
        this.menu = menu;
        this.clientController = menu.clientController;
        this.managerController = menu.managerController;
        this.employeeManagement = menu.employeeManagement;
        this.moneyController = menu.moneyController;
        this.postController = menu.postController;


        setTitle("Authorization");
        setSize(350, 125);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }

    void init() {
        login = new JTextField("");
        password = new JPasswordField("");
        setLayout(new GridLayout(2, 2));

        getContentPane().add(new JLabel("login:"));
        getContentPane().add(login);
        getContentPane().add(new JLabel("password:"));
        getContentPane().add(password);


        JButton okButton = new JButton("OK");
        okButton.addActionListener(new MyActionListener());
        getContentPane().add(okButton);

        incorrectPass = new JLabel("Incorrect login or password");
        getContentPane().add(incorrectPass);

    }
    private class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
