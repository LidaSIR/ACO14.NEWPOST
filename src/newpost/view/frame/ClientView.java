package newpost.view.frame;

import newpost.controller.ClientController;
import newpost.db.AppDataContainer;
import newpost.model.common.Address;
import newpost.model.common.Product;
import newpost.model.common.Size;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.validator.ValidationClientControllerProxy;
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
 * Created by sasha on 18.08.2016.
 */
public class ClientView extends JFrame {

    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 500;
    private static final String TITLE = "make order";
    private Map<String, Product> productsMap;

    private AppDataContainer appDataContainer;
    private ValidationClientControllerProxy validationClientControllerProxy;
    private ClientController clientController;
    private Client client;

    public ClientView(AppDataContainer appDataContainer, Client client){
        this.appDataContainer = appDataContainer;
        this.clientController = new ClientController(appDataContainer);
        this.client = client;
        this.validationClientControllerProxy = new ValidationClientControllerProxy(clientController, new Validator());
        productsMap = new HashMap<>();
        JFrame main = createMainFrame();
        main.add(createClientInfoPanel(this.client));
        main.add(createAddTicketTab());
        main.setVisible(true);
    }

    private static JFrame createMainFrame() {
        JFrame managerFrame = new JFrame(TITLE);
        managerFrame.setIconImage(new ImageIcon("resources/icons/managerViewIcon.png").getImage());
        managerFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        managerFrame.setResizable(false);
        managerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managerFrame.setLocation(100, 100);
        managerFrame.setLocationRelativeTo(null);

        return managerFrame;
    }


    private  JPanel createClientInfoPanel(Client client) {
        JPanel panelClientInfo = new JPanel(null);

        panelClientInfo.setLayout(new GridLayout(2,2));
        panelClientInfo.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new TitledBorder("Client info")));
        panelClientInfo.setBounds(20, 15, 550, 100);
        panelClientInfo.add(new JLabel("full name: "+ client.getPassport().getFullname()));
        panelClientInfo.add(new JLabel("passport Id: "+ client.getPassport().getNumber()));
        panelClientInfo.add(new JLabel("phone number: "+ client.getPhone()));
        panelClientInfo.add(new JLabel("email: "+ client.getMail()));


        return panelClientInfo;
    }

    private JPanel createAddTicketTab() {

        JPanel panelCreateTicket = new JPanel();
        panelCreateTicket.setLayout(new BorderLayout());
        panelCreateTicket.add(new JLabel("Please input data for create new ticket", SwingConstants.CENTER), BorderLayout.NORTH);

        JComponent contentPanelCreateTicket = new JPanel(null);


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
        JButton removeProductButton = new JButton("Remove product",
                (new ImageIcon(new ImageIcon("resources/icons/error.png").getImage().getScaledInstance(15,15,Image.SCALE_SMOOTH))));
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
        JTextField textFieldPrice = createTextField(6, "Example: 100");
        panelForProductInfo.add(labelPrice);
        panelForProductInfo.add(textFieldPrice);

        JLabel labelWeight = new JLabel("Weight:");
        JTextField textFieldWeight = createTextField(7, "in grams");
        panelForProductInfo.add(labelWeight);
        panelForProductInfo.add(textFieldWeight);

        JLabel labelSizeX = new JLabel("SizeX:");
        JTextField textFieldSizeX = createTextField(4, "in centimeters");
        panelForProductInfo.add(labelSizeX);
        panelForProductInfo.add(textFieldSizeX);

        JLabel labelSizeY = new JLabel("Y:");
        JTextField textFieldSizeY = createTextField(4, "in centimeters");
        panelForProductInfo.add(labelSizeY);
        panelForProductInfo.add(textFieldSizeY);

        JLabel labelSizeZ = new JLabel("Z:");
        JTextField textFieldSizeZ = createTextField(4, "in centimeters");
        panelForProductInfo.add(labelSizeZ);
        panelForProductInfo.add(textFieldSizeZ);

        addProductsPanel.add(panelForProductInfo);

        contentPanelCreateTicket.add(sendToPanel);
        contentPanelCreateTicket.add(addProductsPanel);

        JButton addProductButton = new JButton("Add product",
                (new ImageIcon(new ImageIcon("resources/icons/plus.png").getImage().getScaledInstance(23,23,Image.SCALE_SMOOTH))));
        addProductButton.setBounds(165, 330, 250, 30);
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = tryParseProduct();
                if (product != null) {
                    productsMap.put(product.getName(), product);
                    productJList.addItem(product.getName());// = new JComboBox<>(productsMap.keySet().toArray());
                    JOptionPane.showMessageDialog(new JFrame(), "Product " + product.getName() + " added!");
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

                    Product product = new Product(textFieldProductName.getText(),
                            new Size(Integer.parseInt(textFieldSizeX.getText()), Integer.parseInt(textFieldSizeY.getText()),
                                    Integer.parseInt(textFieldSizeZ.getText()), Integer.parseInt(textFieldWeight.getText())),
                            Integer.parseInt(textFieldPrice.getText()), client);
                    return product;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Something wrong!\n" + ex);
                    return null;
                }
            }
        });
        contentPanelCreateTicket.add(addProductButton);

        JButton createTicketButton = new JButton("Create ticket",
                (new ImageIcon(new ImageIcon("resources/icons/archive.png").getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH))));
        createTicketButton.addActionListener(e -> {
            try {

                Address sendToAddress = new Address(textFieldSendToCity.getText(), textFieldSendToStreet.getText(),
                        textFieldSendToHouseNumber.getText());
                PostTicket postTicket = validationClientControllerProxy.makeOrder(client, sendToAddress,
                        productsMap.values().stream().collect(Collectors.toList()));

                JOptionPane.showMessageDialog(new JFrame(), "Done!\n Your ticket number is " + postTicket.getId());
                productJList.removeAllItems();
                textFieldSendToCity.setText("");
                textFieldSendToStreet.setText("");
                textFieldSendToHouseNumber.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new JFrame(), "Something wrong!\n" + ex);
            }
        });
        panelCreateTicket.add(createTicketButton, BorderLayout.SOUTH);

        // Add all content to "Create ticket" tab
        panelCreateTicket.add(contentPanelCreateTicket);


        return panelCreateTicket;
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

    private JTextField createTextField(int length, String toolTip) {

        JTextField textField = new JTextField(length);
        textField.setToolTipText(toolTip);
        return  textField;
    }



}
