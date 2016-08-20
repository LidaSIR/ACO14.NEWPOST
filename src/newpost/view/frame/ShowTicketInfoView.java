package newpost.view.frame;

import newpost.model.common.*;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by sasha on 20.08.2016.
 */
public class ShowTicketInfoView extends JFrame {

    private PostTicket postTicket;
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 500;
    private static final String TITLE = "ticket info";

    public ShowTicketInfoView(PostTicket postTicket) {

        this.postTicket = postTicket;
        JFrame main = createMainFrame();
        main.add(createInfoPanel(postTicket));
        main.add(createClientInfoPanel(postTicket.getClient()));
        main.add(adressPanel(postTicket.getFrom(), postTicket.getTo()));
        main.add(datePanel(postTicket.getCreationDate(), postTicket.getEstimationArrivalDate()));
        main.add(productsPanel(postTicket.getProducts()));

        main.setVisible(true);
    }

    private JPanel datePanel (MyDate dateStart, MyDate dateEnd) {
        JPanel panelDate = new JPanel(null);

        panelDate.setLayout(new GridLayout(1,2));
        panelDate.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new TitledBorder("Date start / finish")));
        panelDate.setBounds(20, 160, 550, 70);
        panelDate.add(new JLabel("date start: " + dateStart.toString()));
        panelDate.add(new JLabel("date estimation: " + dateEnd.toString()));
        return panelDate;
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

    private static JPanel createInfoPanel(PostTicket postTicket) {
        JPanel panelTicketInfo = new JPanel();
        panelTicketInfo.setLayout(new BorderLayout());
        panelTicketInfo.add(new JLabel("ticket info"+postTicket.getId().toString(), SwingConstants.CENTER), BorderLayout.NORTH);

        return panelTicketInfo;
    }

    private JPanel productsPanel(Product[] products) {
        JPanel panelProducts = new JPanel(null);

        panelProducts.setLayout(new GridLayout(products.length, 3));
        panelProducts.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new TitledBorder("Client info")));
        panelProducts.setBounds(20, 170, 550, 500);

        for (int i = 0; i < products.length; i++) {
            panelProducts.add( new JLabel("product name: " + products[i].getName()));
            panelProducts.add( new JLabel("product price: " + String.valueOf(products[i].getPrice())));
            panelProducts.add( new JLabel("product size: " + products[i].getSize().toString()));
        }
        return panelProducts;
    }

    private  JPanel createClientInfoPanel(Client client) {
        JPanel panelClientInfo = new JPanel(null);

        panelClientInfo.setLayout(new GridLayout(2,2));
        panelClientInfo.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new TitledBorder("Client info")));
        panelClientInfo.setBounds(20, 15, 550, 70);
        panelClientInfo.add(new JLabel("full name: "+ client.getPassport().getFullname()));
        panelClientInfo.add(new JLabel("passport Id: "+ client.getPassport().getNumber()));
        panelClientInfo.add(new JLabel("phone number: "+ client.getPhone()));
        panelClientInfo.add(new JLabel("email: "+ client.getMail()));

        return panelClientInfo;
    }

    private JPanel adressPanel (Address addressFrom, Address addressTo) {
        JPanel panelAddress = new JPanel(null);

        panelAddress.setLayout(new GridLayout(3,2));
        panelAddress.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new TitledBorder("address from / to")));
        panelAddress.setBounds(20, 80, 550, 90);
        panelAddress.add(new JLabel("City from: "+ addressFrom.getCity()));
        panelAddress.add(new JLabel("Street from: "+ addressFrom.getStreet()));
        panelAddress.add(new JLabel("HouseNum from: "+ addressFrom.getHouseNum()));
        panelAddress.add(new JLabel("City to: "+ addressTo.getCity()));
        panelAddress.add(new JLabel("Street to: "+ addressTo.getStreet()));
        panelAddress.add(new JLabel("HouseNum to: "+ addressTo.getHouseNum()));

        return panelAddress;
    }


}
