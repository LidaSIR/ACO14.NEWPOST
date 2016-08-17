package newpost.view;

import newpost.controller.ManagerController;
import newpost.db.AppDataContainer;
import newpost.db.InitDB;
import newpost.exceptions.ValidationException;
import newpost.model.common.Passport;
import newpost.model.common.Product;
import newpost.model.common.Size;
import newpost.model.office.Client;
import newpost.validator.ValidationManagerControllerProxy;
import newpost.validator.Validator;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

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

        // Create new JFrame
        JFrame managerFrame = new JFrame("Manager view");
        managerFrame.setSize(600, 600);
        managerFrame.setResizable(false);
        managerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managerFrame.setLocation(100, 100);
        managerFrame.setLocationRelativeTo(null);

        // Create new tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create "Add client" tab
        JComponent panelAddClient = new JPanel();
        panelAddClient.setLayout(new BorderLayout());
        panelAddClient.add(new JLabel("Please input data for add new client", SwingConstants.CENTER), BorderLayout.NORTH);

        // Create "Create ticket" tab
        JComponent panelCreateTicket = new JPanel();
        panelCreateTicket.setLayout(new BorderLayout());
        panelCreateTicket.add(new JLabel("Please input data for create new ticket", SwingConstants.CENTER), BorderLayout.NORTH);

        // Create panel for content on "Add client" tab
        JComponent contentPanelAddClient = new JPanel(null);

        // Create input field for full name
        JTextField textFieldFullName = new JTextField();
        JPanel fullNamePanel = createPanel("Example: Ivan Ivanov", "Full name:", 20, 15, textFieldFullName);
        contentPanelAddClient.add(fullNamePanel);

        // Create input field for passport info
        JTextField passportTextField = new JTextField();
        JPanel passportPanel = createPanel("Example: PP087789", "Passport:", 20, 85, passportTextField);
        contentPanelAddClient.add(passportPanel);

        // Create input field for mail
        JTextField mailTextField = new JTextField();
        JPanel mailPanel = createPanel("example@domain.com", "Email:", 20, 155, mailTextField);
        contentPanelAddClient.add(mailPanel);

        // Create input field for phone number
        JTextField phoneTextField = new JTextField();
        JPanel phonePanel = createPanel("0664567891", "Phone:", 20, 225, phoneTextField);
        contentPanelAddClient.add(phonePanel);

        // Create button "Add client" to send form
        JButton addClientButton = new JButton("Add new client");
        addClientButton.setSize(150, 30);
        panelAddClient.add(addClientButton, BorderLayout.SOUTH);

        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button is pressed");

                ValidationManagerControllerProxy validationManagerControllerProxy =
                        new ValidationManagerControllerProxy(new ManagerController(appDataContainer), new Validator());
                try {
                    validationManagerControllerProxy.addClient(new Passport(textFieldFullName.getText(), passportTextField.getText()),
                            phoneTextField.getText(), mailTextField.getText());
                    JOptionPane.showMessageDialog(new JFrame(), "Done!");
                    textFieldFullName.setText("");
                    passportTextField.setText("");
                    phoneTextField.setText("");
                    mailTextField.setText("");
                    tabbedPane.setSelectedComponent(panelCreateTicket);

                } catch (ValidationException e1) {
                    JOptionPane.showMessageDialog(new JFrame(), e1);
                }


            }
        });

        // Add all content to "Add client" tab
        panelAddClient.add(contentPanelAddClient, BorderLayout.CENTER);

        ////////////////////////////////////////////////////////////
        // Create panel for content on "Create ticket" tab
        ///////////////////////////////////////////////////////////
        JComponent contentPanelCreateTicket = new JPanel(null);

        // Create input field for client phone
        JTextField textFieldClientPhone = new JTextField();
        JPanel clientPhonePanel = createPanel("Example: 0664540934", "Client phone:", 20, 15, textFieldClientPhone);
        contentPanelCreateTicket.add(clientPhonePanel);
        JButton checkButton = new JButton("Check client in database");
        checkButton.setBounds(165, 80, 250, 30);
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new ManagerController(appDataContainer).getClient(textFieldClientPhone.getText());
                if (client == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Client not found! Please create before");
                    phoneTextField.setText(textFieldClientPhone.getText());
                    tabbedPane.setSelectedComponent(panelAddClient);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Client found!\nClient name: " + client.getPassport().getFullname());
                }
            }
        });
        contentPanelCreateTicket.add(checkButton);

        // Create input field for Address

        JPanel sendToPanel = new JPanel(new FlowLayout(SwingConstants.HORIZONTAL));
        sendToPanel.setBounds(20, 110, 550, 70);
        sendToPanel.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new TitledBorder("Send to address:")));

        JLabel sendToCity = new JLabel("City:");
        JTextField textFieldSendTo = new JTextField(14);
        JLabel labelSendToStreet = new JLabel("Street:");
        JTextField textFieldSendToStreet = new JTextField(14);
        JLabel labelSendToHouseNumber = new JLabel("#:");
        JTextField textFieldSendToHouseNumber = new JTextField(4);
        sendToPanel.add(sendToCity);
        sendToPanel.add(textFieldSendTo);
        sendToPanel.add(labelSendToStreet);
        sendToPanel.add(textFieldSendToStreet);
        sendToPanel.add(labelSendToHouseNumber);
        sendToPanel.add(textFieldSendToHouseNumber);

        JPanel addProductsPanel = new JPanel(new GridLayout(2, 1));
        addProductsPanel.setBounds(20, 180, 550, 150);
        addProductsPanel.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new TitledBorder("Add products:")));
        Map<String, Product> productsMap = new HashMap<>();
        JComboBox productJList = new JComboBox<>(productsMap.keySet().toArray());
        productJList.setEditable(true);

        JPanel panelForProductList = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelForProductList.add(productJList);
        JButton removeProductButton = new JButton("Remove product");
        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = (String) productJList.getSelectedItem();
                    productsMap.remove(productJList.getSelectedItem());
                    productJList.removeItemAt(productJList.getSelectedIndex());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Error");
                }

            }
        });
        panelForProductList.add(removeProductButton);
        addProductsPanel.add(panelForProductList);

        JPanel panelForProductInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));


        JLabel labelProductName = new JLabel("Title:");
        JTextField textFieldProductName = new JTextField(14);
        JLabel labelPrice = new JLabel("Price:");
        JTextField textFieldPrice = new JTextField(6);
        textFieldPrice.setToolTipText("Example: 100");
        JLabel labelSizeX = new JLabel("SizeX:");
        labelSizeX.setToolTipText("in centimeters");
        JTextField textFieldSizeX = new JTextField(4);
        JLabel labelSizeY = new JLabel("Y:");
        JTextField textFieldSizeY = new JTextField(4);
        textFieldSizeY.setToolTipText("in centimeters");
        JLabel labelSizeZ = new JLabel("Z:");
        JTextField textFieldSizeZ = new JTextField(4);
        textFieldSizeZ.setToolTipText("in centimeters");
        JLabel labelWeight = new JLabel("Weight:");
        textFieldSizeZ.setToolTipText("in kilograms");
        JTextField textFieldWeight = new JTextField(7);

        panelForProductInfo.add(labelProductName);
        panelForProductInfo.add(textFieldProductName);
        panelForProductInfo.add(labelPrice);
        panelForProductInfo.add(textFieldPrice);
        panelForProductInfo.add(labelWeight);
        panelForProductInfo.add(textFieldWeight);
        panelForProductInfo.add(labelSizeX);
        panelForProductInfo.add(textFieldSizeX);
        panelForProductInfo.add(labelSizeY);
        panelForProductInfo.add(textFieldSizeY);
        panelForProductInfo.add(labelSizeZ);
        panelForProductInfo.add(textFieldSizeZ);


        addProductsPanel.add(panelForProductInfo);

        contentPanelCreateTicket.add(sendToPanel);
        contentPanelCreateTicket.add(addProductsPanel);

        JButton addProductButton = new JButton("Add product");
        addProductButton.setBounds(165, 330, 250, 30);
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = tryParseProduct();
                if(product!=null) {
                    productsMap.put(product.getName(), product);
                    productJList.addItem(product.getName());// = new JComboBox<>(productsMap.keySet().toArray());
                }
            }

            private Product tryParseProduct() {
                try {
                    Client client = new ManagerController(appDataContainer).getClient(textFieldClientPhone.getText());
                    Product product = new Product(textFieldProductName.getText(),
                            new Size(Integer.parseInt(textFieldSizeX.getText()), Integer.parseInt(textFieldSizeY.getText()),
                                    Integer.parseInt(textFieldSizeZ.getText()), Integer.parseInt(textFieldWeight.getText())),
                            Integer.parseInt(textFieldPrice.getText()), client);
                    return product;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Somethong wrong!\n"+ex);
                    return null;
                }
            }
        });
        contentPanelCreateTicket.add(addProductButton);



        // Add all content to "Create ticket" tab
        panelCreateTicket.add(contentPanelCreateTicket);


        JComponent panelSearch = new JPanel();


        managerFrame.add(tabbedPane);
        tabbedPane.add("Add client", panelAddClient);
        tabbedPane.add("Create ticket", panelCreateTicket);
        tabbedPane.add("Search", panelSearch);

        managerFrame.setVisible(true);
    }

    private JPanel createPanel(String example, String title, int x, int y, JTextField textField) {

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        textField.setToolTipText(example);
        textField.setMaximumSize(new Dimension(200, 10));
        jPanel.add(textField);
        jPanel.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new TitledBorder(title)));
        jPanel.setBounds(x, y, 550, 70);
        return jPanel;

    }
}
