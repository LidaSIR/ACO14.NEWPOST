package newpost.view.frame;

import newpost.controller.interfaces.IClientController;
import newpost.controller.interfaces.ILoginController;
import newpost.controller.interfaces.IManagerController;
import newpost.model.office.User;
import newpost.model.office.UserType;
import newpost.utils.support.SupportController;
import newpost.utils.support.SupportForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lida on 18.08.2016.
 */
public class LoginFrame extends JFrame {

    private ILoginController loginController;
    private IManagerController managerController;
    private IClientController clientController;

    private JTextField login;
    private JTextField password;
    private JLabel incorrectPass;

    public LoginFrame(ILoginController loginController, IManagerController managerController, IClientController clientController) throws HeadlessException {

        this.clientController = clientController;
        this.managerController = managerController;
        this.loginController = loginController;

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

    private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            User user = loginController.findUser(login.getText(), password.getText());
            if (user.getUserType() == UserType.MANAGER) {
                ManagerView managerFrame = new ManagerView(managerController,clientController);
                setVisible(false);
            } else if (user.getUserType() == UserType.CLIENT) {
                ClientView clientView = new ClientView(clientController, user);
                setVisible(false);
            } else if(user.getUserType() == UserType.SUPPORT) {
                new SupportForm(new SupportController());
            } else {
                login.setText("");
                password.setText("");
                incorrectPass.setText("invalid login or password");
                incorrectPass.setForeground(Color.RED);
            }

        }
    }
}
