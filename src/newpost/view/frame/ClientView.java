package newpost.view.frame;

import newpost.controller.ClientController;
import newpost.db.AppDataContainer;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.model.office.TicketStatus;
import newpost.utils.AppConstants;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


/**
 * Created by pashc on 20.08.2016.
 */
public class ClientView extends JFrame {

    private static final int DEF_HEIGHT = 600;
    private static final int DEF_WIDTH = 600;
    private static final String[] COLUMN_NAMES = new String[]{"ID", "Full name", "Phone", "Email"};
    private AppDataContainer appDataContainer;
    private ClientController clientController;
    private Client client;


    private DefaultListModel ticketListModel = new DefaultListModel();
    private JList ticketList = new JList(ticketListModel);

    public ClientView(AppDataContainer appDataContainer, Client client) {
        this.appDataContainer = appDataContainer;
        this.clientController = new ClientController(appDataContainer);
        this.client = client;


        JFrame main = createMainFrame();
        Box box = Box.createVerticalBox();
        box.add(clientInfoBorder().add(createClientInfoPanel(this.client)));
        box.add(createSearchTab(this.client));
        main.setContentPane(box);
        main.setVisible(true);


    }

    private static JFrame createMainFrame() {
        JFrame mainFrame = new JFrame("CLIENT VIEW");

        mainFrame.setSize(DEF_WIDTH, DEF_HEIGHT);
        mainFrame.setResizable(true);
        mainFrame.setIconImage(new ImageIcon("resources/icons/clientViewIcon.png").getImage());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocation(100, 100);
        mainFrame.setLocationRelativeTo(null);

        return mainFrame;
    }

    private JPanel clientInfoBorder() {
        JPanel panelBordInf = new JPanel();
        panelBordInf.setLayout(new BorderLayout());
        panelBordInf.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new TitledBorder("Client info")));
        panelBordInf.setBounds(20, 15, 550, 100);

        return panelBordInf;
    }

    private JPanel createClientInfoPanel(Client client) {
        JPanel panelClientInfo = new JPanel();
        panelClientInfo.setAutoscrolls(true);

        panelClientInfo.setLayout(new GridLayout(2, 2));

        panelClientInfo.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new TitledBorder("Client info")));
        panelClientInfo.setBounds(20, 15, 550, 100);
        panelClientInfo.add(new JLabel("full name: " + client.getPassport().getFullname()));
        panelClientInfo.add(new JLabel("passport Id: " + client.getPassport().getNumber()));
        panelClientInfo.add(new JLabel("phone number: " + client.getPhone()));
        panelClientInfo.add(new JLabel("email: " + client.getMail()));


        return panelClientInfo;
    }


    private JPanel createSearchTab(Client client) {


        JPanel panelSearch = new JPanel();
        panelSearch.setLayout(new BorderLayout());

        JComponent contentPanelSearch = new JPanel(null);

        JPanel filterPanel = new JPanel(new FlowLayout(SwingConstants.HORIZONTAL));
        filterPanel.setBounds(60, 80, 550, 45);

        String data[][] = {};
        JTable table = new JTable(data, COLUMN_NAMES);
        table.setModel(new DefaultTableModel(data, COLUMN_NAMES));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(2, 112, 590, 300);
        scrollPane.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new TitledBorder("Result")));

        contentPanelSearch.add(scrollPane);

        String location = ManagerView.class.getResource(AppConstants.SEARCH_ICON).getPath();
        JButton searchButton = new JButton("Show tickets",
                (new ImageIcon(new ImageIcon(location).getImage().getScaledInstance(5, 5, Image.SCALE_SMOOTH))));
        searchButton.addActionListener(e -> {
            List<PostTicket> postTicketList2 = clientController.showAllClientTickets(this.client);
            if (postTicketList2 == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Nothing found");
            }
            showResult(table, postTicketList2);
        });

        filterPanel.add(searchButton);
        contentPanelSearch.add(filterPanel);
        panelSearch.add(contentPanelSearch);

        location = ManagerView.class.getResource(AppConstants.ERROR_ICON).getPath();
        JButton cancelButton = new JButton("Cancel ticket",
                (new ImageIcon(new ImageIcon(location).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH))));
        cancelButton.addActionListener(e -> {
            try {
                Object ticketId = table.getValueAt(table.getSelectedRow(), 0);
                clientController.cancelTicket((Integer) ticketId);
                JOptionPane.showMessageDialog(new JFrame(), "Ticket canceled!");
                showResult(table, null);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new JFrame(), "Please select ticket");
            }
        });

        location = ManagerView.class.getResource(AppConstants.INFO_ICON).getPath();
        JButton showInfoButton = new JButton("Show info",
                (new ImageIcon(new ImageIcon(location).getImage().getScaledInstance(23, 23, Image.SCALE_SMOOTH))));
        showInfoButton.addActionListener(e -> {
            try {
                Object ticketId = table.getValueAt(table.getSelectedRow(), 0);
                PostTicket postTicket = clientController.showTicketById((String) ticketId);
                TicketInfoView ticketInfoView = new TicketInfoView(postTicket);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new JFrame(), "Please select ticket");
            }
        });

        location = ManagerView.class.getResource(AppConstants.INFO_ICON).getPath();
        JButton addTicketButton = new JButton("Add Ticket",
                (new ImageIcon(new ImageIcon(location).getImage().getScaledInstance(23, 23, Image.SCALE_SMOOTH))));
        showInfoButton.addActionListener(e -> {
            ClientMakeOrder clientMakeOrder = new ClientMakeOrder(appDataContainer, this.client);
        });

        JPanel buttonsPanelOnSearchTab = new JPanel(new GridLayout(1, 2));
        buttonsPanelOnSearchTab.add(showInfoButton, BorderLayout.SOUTH);
        buttonsPanelOnSearchTab.add(cancelButton, BorderLayout.SOUTH);
        buttonsPanelOnSearchTab.add(addTicketButton, BorderLayout.SOUTH);
        panelSearch.add(buttonsPanelOnSearchTab, BorderLayout.SOUTH);


        return panelSearch;
    }


    private static void showResult(JTable table, List<PostTicket> postTicketList) {

        String tableRow[] = new String[4];
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);

        if (postTicketList == null) {
            tableRow[0] = "";
            tableRow[1] = "";
            tableRow[2] = "";
            tableRow[3] = "";
            defaultTableModel.addRow(tableRow);
        } else {
            for (int i = 0; i < postTicketList.size(); i++) {
                if (postTicketList.get(i).getStatus() != TicketStatus.CANCELED) {
                    tableRow[0] = postTicketList.get(i).getId();
                    tableRow[1] = postTicketList.get(i).getClient().getPassport().getFullname();
                    tableRow[2] = postTicketList.get(i).getClient().getPhone();
                    tableRow[3] = postTicketList.get(i).getClient().getMail();
                    defaultTableModel.addRow(tableRow);
                }
            }
        }

        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.setModel(defaultTableModel);
        defaultTableModel.fireTableDataChanged();
    }


}
