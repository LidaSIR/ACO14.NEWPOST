package newpost.view;

import newpost.controller.ManagerController;
import newpost.db.AppDataContainer;
import newpost.db.InitDB;
import newpost.exceptions.ValidationException;
import newpost.model.common.Passport;
import newpost.validator.ValidationManagerControllerProxy;
import newpost.validator.Validator;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Serhii Fursenko on 15.08.16.
 * mail to fyrsenko@gmail.com
 */
public class ManagerView extends JFrame {

    public static void main(String[] args) {

        ManagerView managerView = new ManagerView();
        AppDataContainer appDataContainer = new AppDataContainer();
        InitDB.initDB(appDataContainer);

        managerView.showManagerView(appDataContainer);
    }

    public void showManagerView(AppDataContainer appDataContainer) {

        JFrame managerFrame = new JFrame("Manager view");
        managerFrame.setSize(650, 600);
        //managerFrame.setResizable(false);
        managerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managerFrame.setLocation(100, 100);
        managerFrame.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent panelAddClient = new JPanel();
        panelAddClient.setLayout(new BorderLayout());
        panelAddClient.add(new JLabel("Please input data for add new client", SwingConstants.CENTER), BorderLayout.NORTH);


        JComponent contentPanelAddClient = new JPanel(null);
        //JComponent contentPanelAddClient = new JPanel();
        //contentPanelAddClient.setLayout(new BoxLayout(contentPanelAddClient, BoxLayout.PAGE_AXIS));
        panelAddClient.add(contentPanelAddClient, BorderLayout.CENTER);

        JPanel fullNamePanel = new JPanel();
        fullNamePanel.setLayout(new BorderLayout());
        JTextField textFieldFullName = new JTextField();
        textFieldFullName.setToolTipText("Example: Ivan Ivanov");
        //textFieldFullName.setPreferredSize(new Dimension(200,10));
        textFieldFullName.setMaximumSize(new Dimension(200,10));
        fullNamePanel.add(textFieldFullName);
        fullNamePanel.setBorder(new CompoundBorder(new EmptyBorder(12,12,12,12), new TitledBorder("Full name:")));
        fullNamePanel.setBounds(20,15,550,70);
        contentPanelAddClient.add(fullNamePanel);

        JPanel passportPanel = new JPanel();
        passportPanel.setLayout(new BorderLayout());
        JTextField passportTextField = new JTextField();
        passportTextField.setToolTipText("Example: PP087789");
        passportPanel.add(passportTextField);
        passportPanel.setBorder(new CompoundBorder(new EmptyBorder(12,12,12,12), new TitledBorder("Passport:")));
        passportPanel.setBounds(20,85,550,70);
        contentPanelAddClient.add(passportPanel);

        JPanel mailPanel = new JPanel();
        mailPanel.setLayout(new BorderLayout());
        JTextField mailTextField = new JTextField();
        mailTextField.setToolTipText("example@domain.com");
        mailPanel.add(mailTextField);
        mailPanel.setBorder(new CompoundBorder(new EmptyBorder(12,12,12,12), new TitledBorder("Email:")));
        mailPanel.setBounds(20,155,550,70);
        contentPanelAddClient.add(mailPanel);

        JPanel phonePanel = new JPanel();
        phonePanel.setLayout(new BorderLayout());
        JTextField phoneTextField = new JTextField();
        phoneTextField.setToolTipText("0664567891");
        phonePanel.add(phoneTextField);
        phonePanel.setBorder(new CompoundBorder(new EmptyBorder(12,12,12,12), new TitledBorder("Phone:")));
        phonePanel.setBounds(20,225,550,70);
        contentPanelAddClient.add(phonePanel);

        JButton addClientButton = new JButton("Add new client");
        addClientButton.setSize(150,30);
        panelAddClient.add(addClientButton, BorderLayout.SOUTH);

        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button is pressed");

                ValidationManagerControllerProxy validationManagerControllerProxy =
                        new ValidationManagerControllerProxy(new ManagerController(appDataContainer),new Validator());
                try {
                    validationManagerControllerProxy.addClient(new Passport(textFieldFullName.getText(), passportTextField.getText()),
                            phoneTextField.getText(), mailTextField.getText());
                    JOptionPane.showMessageDialog(new JFrame(), "Done!");
                } catch (ValidationException e1) {
                    JOptionPane.showMessageDialog(new JFrame(), e1);
                }



            }
        });








        JComponent panelAddTicket = new JPanel();


        JComponent panelSearch = new JPanel();

        managerFrame.add(tabbedPane);

        tabbedPane.add("Add client", panelAddClient);
        tabbedPane.add("Add client", panelAddTicket);
        tabbedPane.add("Search", panelSearch);

        managerFrame.setVisible(true);
    }


}
