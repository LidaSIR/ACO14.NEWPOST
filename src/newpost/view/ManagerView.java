package newpost.view;

import newpost.controller.ManagerController;
import newpost.db.AppDataContainer;
import newpost.exceptions.ValidationException;
import newpost.model.common.Address;
import newpost.model.common.Passport;
import newpost.model.common.Product;
import newpost.model.common.Size;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.validator.ValidationManagerControllerProxy;
import newpost.validator.Validator;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Serhii Fursenko on 15.08.16.
 * mail to fyrsenko@gmail.com
 */
public class ManagerView extends JFrame {

    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 500;
    private static final String TITLE = "Manager view";

    private AppDataContainer appDataContainer;
    private ValidationManagerControllerProxy validationManagerControllerProxy;
    private Map<String, Product> productsMap;


    public ManagerView(AppDataContainer appDataContainer) throws HeadlessException {
        this.appDataContainer = appDataContainer;
        this.validationManagerControllerProxy = new ValidationManagerControllerProxy(new ManagerController(appDataContainer), new Validator());
        this.productsMap = new HashMap<>();
    }

    public void showManagerView() {

        // Create new JFrame
        JFrame managerFrame = new JFrame(TITLE);
        managerFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
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

                try {
                    validationManagerControllerProxy.addClient(new Passport(textFieldFullName.getText(), passportTextField.getText()),
                            phoneTextField.getText(), mailTextField.getText());
                    JOptionPane.showMessageDialog(new JFrame(), "Done!");
                    Client client = validationManagerControllerProxy.getClient(phoneTextField.getText());

                    // Clean text fields
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
        JTextField textFieldClientPhoneTicketTab = new JTextField();
        JPanel clientPhonePanel = createPanel("Example: 0664540934", "Client phone:", 20, 15, textFieldClientPhoneTicketTab);
        contentPanelCreateTicket.add(clientPhonePanel);

        JButton checkButton = new JButton("Check client in database");
        checkButton.setBounds(165, 85, 250, 30);
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = null;
                try {
                    client = validationManagerControllerProxy.getClient(textFieldClientPhoneTicketTab.getText());
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), ex);
                }
                if (client == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Client not found! Please create before");
                    phoneTextField.setText(textFieldClientPhoneTicketTab.getText());
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
        JTextField textFieldSendToCity = new JTextField(14);
        JLabel labelSendToStreet = new JLabel("Street:");
        JTextField textFieldSendToStreet = new JTextField(14);
        JLabel labelSendToHouseNumber = new JLabel("#:");
        JTextField textFieldSendToHouseNumber = new JTextField(4);
        sendToPanel.add(sendToCity);
        sendToPanel.add(textFieldSendToCity);
        sendToPanel.add(labelSendToStreet);
        sendToPanel.add(textFieldSendToStreet);
        sendToPanel.add(labelSendToHouseNumber);
        sendToPanel.add(textFieldSendToHouseNumber);

        JPanel addProductsPanel = new JPanel(new GridLayout(2, 1));
        addProductsPanel.setBounds(20, 180, 550, 150);
        addProductsPanel.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new TitledBorder("Add products:")));

        JComboBox productJList = new JComboBox<>(productsMap.keySet().toArray());

        JPanel panelForProductList = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelForProductList.add(productJList);
        JButton removeProductButton = new JButton("Remove product");
        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    productsMap.remove(productJList.getSelectedItem());
                    productJList.removeItemAt(productJList.getSelectedIndex());
                    JOptionPane.showMessageDialog(new JFrame(), "Product removed!");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Error");
                }

            }
        });
        panelForProductList.add(removeProductButton);
        addProductsPanel.add(panelForProductList);

        JPanel panelForProductInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Create and add elements to "Add product" panel
        JLabel labelProductName = new JLabel("Title:");
        JTextField textFieldProductName = new JTextField(14);
        panelForProductInfo.add(labelProductName);
        panelForProductInfo.add(textFieldProductName);

        JLabel labelPrice = new JLabel("Price:");
        JTextField textFieldPrice = new JTextField(6);
        textFieldPrice.setToolTipText("Example: 100");
        panelForProductInfo.add(labelPrice);
        panelForProductInfo.add(textFieldPrice);

        JLabel labelWeight = new JLabel("Weight:");
        JTextField textFieldWeight = new JTextField(7);
        textFieldWeight.setToolTipText("in grams");
        panelForProductInfo.add(labelWeight);
        panelForProductInfo.add(textFieldWeight);

        JLabel labelSizeX = new JLabel("SizeX:");
        labelSizeX.setToolTipText("in centimeters");
        JTextField textFieldSizeX = new JTextField(4);
        panelForProductInfo.add(labelSizeX);
        panelForProductInfo.add(textFieldSizeX);

        JLabel labelSizeY = new JLabel("Y:");
        JTextField textFieldSizeY = new JTextField(4);
        textFieldSizeY.setToolTipText("in centimeters");
        panelForProductInfo.add(labelSizeY);
        panelForProductInfo.add(textFieldSizeY);

        JLabel labelSizeZ = new JLabel("Z:");
        JTextField textFieldSizeZ = new JTextField(4);
        textFieldSizeZ.setToolTipText("in centimeters");
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
                    JOptionPane.showMessageDialog(new JFrame(), "Product added!");
                    textFieldProductName.setText("");
                    textFieldPrice.setText("");
                    textFieldWeight.setText("");
                    textFieldSizeX.setText("");
                    textFieldSizeY.setText("");
                    textFieldSizeZ.setText("");
                }
            }

            private Product tryParseProduct() {
                try {
                    Client client = validationManagerControllerProxy.getClient(textFieldClientPhoneTicketTab.getText());
                    Product product = new Product(textFieldProductName.getText(),
                            new Size(Integer.parseInt(textFieldSizeX.getText()), Integer.parseInt(textFieldSizeY.getText()),
                                    Integer.parseInt(textFieldSizeZ.getText()), Integer.parseInt(textFieldWeight.getText())),
                            Integer.parseInt(textFieldPrice.getText()), client);
                    return product;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Something wrong!\n"+ex);
                    return null;
                }
            }
        });
        contentPanelCreateTicket.add(addProductButton);

        JButton createTicketButton = new JButton("Create ticket");
        createTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client client = validationManagerControllerProxy.getClient(textFieldClientPhoneTicketTab.getText());
                    Address sendToAddress = new Address(textFieldSendToCity.getText(), textFieldSendToStreet.getText(),
                            textFieldSendToHouseNumber.getText());
                    PostTicket postTicket = validationManagerControllerProxy.createTicket(client, sendToAddress,
                            productsMap.values().stream().collect(Collectors.toList()));

                    JOptionPane.showMessageDialog(new JFrame(), "Done!\n Your ticket number is " + postTicket.getId());
                    productJList.removeAllItems();
                    textFieldSendToCity.setText("");
                    textFieldSendToStreet.setText("");
                    textFieldSendToHouseNumber.setText("");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Something wrong!\n"+ex);
                }
            }
        });
        panelCreateTicket.add(createTicketButton, BorderLayout.SOUTH);


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
