package com.zhkj.ui.tool;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

public class TestJTextPane extends JFrame {
    private JTextPane textPane = new JTextPane();

    public TestJTextPane () {
        this.add(new JScrollPane(textPane), BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        Style def = textPane.getStyledDocument().addStyle(null, null);
        StyleConstants.setFontFamily(def, "verdana");
        StyleConstants.setFontSize(def, 12);
        Style normal = textPane.addStyle("normal", def);
        Style s = textPane.addStyle("red", normal);
        StyleConstants.setForeground(s, Color.RED);
        textPane.setParagraphAttributes(normal, true);

        for(int i=0; i<1000; i++){
            try {
                textPane.getDocument().insertString(textPane.getDocument().getLength(),
                        "hello, ", textPane.getStyle(i%2 == 0 ? "red" : "normal"));
            } catch (BadLocationException e1) {
            }
        }
    }

    public static void main(String[] args) {
        new TestJTextPane().setVisible(true);
    }

}
