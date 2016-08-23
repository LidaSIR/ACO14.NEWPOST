package newpost.utils.support;

import newpost.utils.email.model_letter.Letter;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Vladislav on 20.08.2016.
 */
public class LetterContent extends JFrame {
    private Letter letter;
    private ISupportController controller;
    JTextPane textPane = new JTextPane();

    public LetterContent(Letter letter, ISupportController controller){
        this.controller = controller;
        setTitle("Mail content");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.letter = letter;
        init(letter);
        setVisible(true);
    }

    private void init(Letter letter){
        StyledDocument doc = textPane.getStyledDocument();

        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setForeground(attributeSet, Color.black);
        StyleConstants.setBackground(attributeSet, Color.cyan);
        StyleConstants.setFontSize(attributeSet, 12);
        StyleConstants.setItalic(attributeSet, true);

        try {
            doc.insertString(0, letter.getMessage(), attributeSet);
            doc.insertString(0, "\n\n\n\n\n", null);
            textPane.setCaretPosition(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JButton reply = new JButton("Reply");
        reply.addActionListener(new ReplyActionListener());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textPane);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(reply, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    private class ReplyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.sendEmail(letter.getFromName(), textPane.getText());
            dispose();
        }
    }
}
