package newpost.view;

import newpost.controller.ClientController;
import newpost.controller.ManagerController;
import newpost.db.AppDataContainer;
import newpost.exceptions.ValidationException;
import newpost.model.common.Address;
import newpost.model.common.Passport;
import newpost.model.common.Product;
import newpost.model.common.Size;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.model.office.TicketStatus;
import newpost.validator.ValidationManagerControllerProxy;
import newpost.validator.Validator;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Serhii Fursenko on 15.08.16.
 * mail to fyrsenko@gmail.com
 */
public class ManagerView extends JFrame {

    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 500;
    private static final String TITLE = "Manager view";
    private static final String[] COLUMN_NAMES = new String[]{"ID", "Full name", "Phone", "Email"};

    private AppDataContainer appDataContainer;
    private ValidationManagerControllerProxy validationManagerControllerProxy;
    private ManagerController managerController;
    private Map<String, Product> productsMap;


    public ManagerView(AppDataContainer appDataContainer) throws HeadlessException {
        this.appDataContainer = appDataContainer;
        this.managerController = new ManagerController(appDataContainer);
        this.validationManagerControllerProxy = new ValidationManagerControllerProxy(managerController, new Validator());
        this.productsMap = new HashMap<>();
    }

    public void showManagerView() {

        JFrame managerFrame = createMainFrame();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Add client", createAddClientTab());
        tabbedPane.add("Create ticket", createAddTicketTab());
        tabbedPane.add("Search", createSearchTab());

        managerFrame.add(tabbedPane);

        managerFrame.setVisible(true);
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

    private JPanel createAddClientTab() {

        JPanel panelAddClient = new JPanel();
        panelAddClient.setLayout(new BorderLayout());
        panelAddClient.add(new JLabel("Please input data for add new client", SwingConstants.CENTER), BorderLayout.NORTH);

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

        // Create button "Add client"
        JButton addClientButton = new JButton("Add new client",
                (new ImageIcon(new ImageIcon("resources/icons/plus.png").getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH))));
        addClientButton.setSize(150, 30);
        panelAddClient.add(addClientButton, BorderLayout.SOUTH);

        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validationManagerControllerProxy.addClient(new Passport(textFieldFullName.getText(), passportTextField.getText()),
                            phoneTextField.getText(), mailTextField.getText());
                    JOptionPane.showMessageDialog(new JFrame(), "Client added!", "NEW POST",JOptionPane.INFORMATION_MESSAGE);

                    // Clean text fields
                    textFieldFullName.setText("");
                    passportTextField.setText("");
                    phoneTextField.setText("");
                    mailTextField.setText("");

                } catch (ValidationException e1) {
                    JOptionPane.showMessageDialog(new JFrame(), e1, "NEW POST",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panelAddClient.add(contentPanelAddClient, BorderLayout.CENTER);

        return panelAddClient;
    }

    private JPanel createAddTicketTab() {

        JPanel panelCreateTicket = new JPanel();
        panelCreateTicket.setLayout(new BorderLayout());
        panelCreateTicket.add(new JLabel("Please input data for create new ticket", SwingConstants.CENTER), BorderLayout.NORTH);

        JComponent contentPanelCreateTicket = new JPanel(null);

        // Create input field for client phone
        JTextField textFieldClientPhoneTicketTab = new JTextField();
        JPanel clientPhonePanel = createPanel("Example: 0664540934", "Client phone:", 20, 15, textFieldClientPhoneTicketTab);
        contentPanelCreateTicket.add(clientPhonePanel);

        JButton checkButton = new JButton("Check client in database",
                (new ImageIcon(new ImageIcon("resources/icons/idCard.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH))));
        checkButton.setBounds(165, 85, 250, 30);
        checkButton.addActionListener(e -> {
            try {
                Client client = validationManagerControllerProxy.getClient(textFieldClientPhoneTicketTab.getText());
                JOptionPane.showMessageDialog(new JFrame(), "Client found!\nClient name: " + client.getPassport().getFullname());
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(new JFrame(), "Client not found! Please create before");
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
                    Client client = validationManagerControllerProxy.getClient(textFieldClientPhoneTicketTab.getText());
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
                JOptionPane.showMessageDialog(new JFrame(), "Something wrong!\n" + ex);
            }
        });
        panelCreateTicket.add(createTicketButton, BorderLayout.SOUTH);

        // Add all content to "Create ticket" tab
        panelCreateTicket.add(contentPanelCreateTicket);


        return panelCreateTicket;
    }

    private JPanel createSearchTab() {

        // Create Search Tab
        JPanel panelSearch = new JPanel();
        panelSearch.setLayout(new BorderLayout());
        panelSearch.add(new JLabel("Please input data for search", SwingConstants.CENTER), BorderLayout.NORTH);


        JComponent contentPanelSearch = new JPanel(null);

        // Create input field for search
        JTextField textFieldSearchKey = new JTextField();
        JPanel searchKeyPanel = createPanel("Please input client name/phone/ticket number", "Search key", 20, 15, textFieldSearchKey);
        contentPanelSearch.add(searchKeyPanel);

        JRadioButton radioButtonName = new JRadioButton("By name");
        JRadioButton radioButtonPhone = new JRadioButton("By phone");
        JRadioButton radioButtonTicketNumber = new JRadioButton("By ticket id");

        ButtonGroup searchFilterRadioButtonGroup = new ButtonGroup();
        searchFilterRadioButtonGroup.add(radioButtonName);
        searchFilterRadioButtonGroup.add(radioButtonPhone);
        searchFilterRadioButtonGroup.add(radioButtonTicketNumber);

        JPanel filterPanel = new JPanel(new FlowLayout(SwingConstants.HORIZONTAL));
        filterPanel.setBounds(60, 80, 550, 45);
        filterPanel.add(radioButtonName);
        filterPanel.add(radioButtonPhone);
        filterPanel.add(radioButtonTicketNumber);


        String data[][] = {};
        JTable table = new JTable(data, COLUMN_NAMES);
        table.setModel(new DefaultTableModel(data, COLUMN_NAMES));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(2, 112, 590, 300);
        scrollPane.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new TitledBorder("Result")));

        contentPanelSearch.add(scrollPane);

        JButton searchButton = new JButton("Search",
                (new ImageIcon(new ImageIcon("resources/icons/search.png").getImage().getScaledInstance(15,15,Image.SCALE_SMOOTH))));
        searchButton.addActionListener(e -> {
            String radioButtonText = getSelectedButtonText(searchFilterRadioButtonGroup);
            if (radioButtonText == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Please select search filter");
                return;
            }
            switch (radioButtonText) {
                case "By name":
                    List<PostTicket> postTicketList2 = managerController.findByOwnerName(textFieldSearchKey.getText());
                    if (postTicketList2 == null) {
                        JOptionPane.showMessageDialog(new JFrame(), "Nothing found");
                    }
                    showResult(table, postTicketList2);
                    break;

                case "By phone":
                    List<PostTicket> postTicketList = managerController.showTicketByClientPhone(textFieldSearchKey.getText());
                    if (postTicketList == null) {
                        JOptionPane.showMessageDialog(new JFrame(), "Nothing with this phone");
                    }
                    showResult(table, postTicketList);
                    break;

                case "By ticket id":
                    PostTicket postTicket = new ClientController(appDataContainer).showTicketById(textFieldSearchKey.getText());
                    if (postTicket == null) {
                        JOptionPane.showMessageDialog(new JFrame(), "Doesn't find ticket");
                        showResult(table, null);
                        return;
                    }
                    List<PostTicket> postTicketList1 = new ArrayList<>();
                    postTicketList1.add(postTicket);
                    showResult(table, postTicketList1);
                    break;

            }
        });

        filterPanel.add(searchButton);
        contentPanelSearch.add(filterPanel);
        panelSearch.add(contentPanelSearch);

        JButton cancelButton = new JButton("Cancel ticket",
                (new ImageIcon(new ImageIcon("resources/icons/error.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH))));
        cancelButton.addActionListener(e -> {
            try {
                Object ticketId = table.getValueAt(table.getSelectedRow(), 0);
                managerController.cancelTicket(Integer.parseInt((String) ticketId));
                JOptionPane.showMessageDialog(new JFrame(), "Ticket canceled!");
                showResult(table, null);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new JFrame(), "Please select ticket");
            }
        });

        JButton showInfoButton = new JButton("Show info",
                (new ImageIcon(new ImageIcon("resources/icons/info.png").getImage().getScaledInstance(23,23,Image.SCALE_SMOOTH))));
        showInfoButton.addActionListener(e -> {
            try {
                Object ticketId = table.getValueAt(table.getSelectedRow(), 0);
                PostTicket postTicket = managerController.filterTicketById((String) ticketId);
                System.out.println(postTicket);
                //TODO add new frame
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new JFrame(), "Please select ticket");
            }
        });

        JPanel buttonsPanelOnSearchTab = new JPanel(new GridLayout(1, 2));
        buttonsPanelOnSearchTab.add(showInfoButton, BorderLayout.SOUTH);
        buttonsPanelOnSearchTab.add(cancelButton, BorderLayout.SOUTH);
        panelSearch.add(buttonsPanelOnSearchTab, BorderLayout.SOUTH);


        return panelSearch;
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

    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }


    private static void showResult(JTable table, List<PostTicket> postTicketList) {

        String data[] = new String[4];
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);

        if (postTicketList == null) {
            data[0] = "";
            data[1] = "";
            data[2] = "";
            data[3] = "";
            defaultTableModel.addRow(data);
        } else {
            for (int i = 0; i < postTicketList.size(); i++) {
                if (postTicketList.get(i).getStatus() == TicketStatus.CANCELED) continue;
                data[0] = postTicketList.get(i).getId();
                data[1] = postTicketList.get(i).getClient().getPassport().getFullname();
                data[2] = postTicketList.get(i).getClient().getPhone();
                data[3] = postTicketList.get(i).getClient().getMail();
                defaultTableModel.addRow(data);
            }
        }

        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.setModel(defaultTableModel);
        defaultTableModel.fireTableDataChanged();
    }

    private JTextField createTextField(int length, String toolTip) {

        JTextField textField = new JTextField(length);
        textField.setToolTipText(toolTip);
        return  textField;
    }
}
