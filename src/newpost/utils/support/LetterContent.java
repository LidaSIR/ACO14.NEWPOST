package newpost.utils.support;

import newpost.utils.email.model_letter.Letter;
import newpost.utils.email.smtp.SMTP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Vladislav on 20.08.2016.
 */
public class LetterContent extends JFrame {

    private Letter letter;
    JTextArea mailContent = new JTextArea();

    public LetterContent(Letter letter){
        setTitle("Mail content");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.letter = letter;
        init(letter);
        setVisible(true);
    }

    private void init(Letter letter){

        mailContent.setText(letter.getMessage());
        JButton reply = new JButton("Reply");
        reply.addActionListener(new ReplyActionListener());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(mailContent);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(reply, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }


    private class ReplyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                SMTP.sendMail(letter.getFromName(), mailContent.getText());
                dispose();
            } catch (IOException ee) {
                ee.printStackTrace();
            }
        }
    }
}
