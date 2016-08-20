package newpost.utils.support;

import newpost.utils.email.exceptions.NoNewEmailException;
import newpost.utils.email.mail_controller.MailController;
import newpost.utils.email.model_letter.Letter;

import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.*;
import java.util.List;

import newpost.utils.email.model_letter.Letter;

/**
 * Created by Vladislav on 19.08.2016.
 */
public class SupportForm extends JFrame{

    String host = "pop.gmail.com";// change accordingly
    String mailStoreType = "pop3";
    String username = "lightpostua@gmail.com";// change accordingly
    String password = "lightpostuaaco14";// change accordingly
    String location = "mail_storage/lightpostua.txt";
    private DefaultListModel letterListModel = new DefaultListModel();
    private JList mailList = new JList(letterListModel);

    public SupportForm() {
        setTitle("Support");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }

    void init(){

        initEmailList();

        JLabel mailsLabel = new JLabel("e-mail list");

        JScrollPane scrollPane = new JScrollPane();

       mailList.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent evt) {
               JList list = (JList)evt.getSource();
               if (evt.getClickCount() == 2) {
                   new LetterContent((Letter) mailList.getSelectedValue());
               }
           }
       });

        scrollPane.setViewportView(mailList);
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initEmailList();
            }
        });
        JPanel mailListPanel = new JPanel(new BorderLayout());
        mailListPanel.add(scrollPane, BorderLayout.CENTER);
        mailListPanel.add(refresh, BorderLayout.SOUTH);

        JPanel main = new JPanel(new BorderLayout());
        main.add(mailsLabel, BorderLayout.NORTH);
        main.add(mailListPanel, BorderLayout.CENTER);

        getContentPane().add(main);
    }

    public List<Letter> getNewEmails(){

        MailController mailController = new MailController();
        List<Letter> letterList = new ArrayList<>();

        try {
            letterList = mailController.getNewEmails(mailController.connectToEmail(host,mailStoreType,username,password),location);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoNewEmailException e) {
            //e.printStackTrace();
        }

        return letterList;
    }

    public void initEmailList(){
        List<Letter> emailList = getNewEmails();
        for (Letter letter : emailList) {
            letterListModel.addElement(letter);
        }
    }
}
