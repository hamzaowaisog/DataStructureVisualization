import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Linklist implements ActionListener {
    public static void main(String[] args) {
        Linklist l1 = new Linklist();
    }
    JButton Singly , Doubly , back , exit;
    JFrame j1;
    JLabel l1;
    public Linklist(){
        j1 = new JFrame();
        Singly = new JButton("Singly Link List");
        Doubly = new JButton("Doubly Link List");
        back = new JButton("Back", new ImageIcon("back.png"));
        exit = new JButton("Exit",new ImageIcon("cancel.png"));
        Font f = new Font("Times New Roman",Font.BOLD,35);
        Font f1 = new Font("Times New Roman",Font.BOLD,20);

        j1.setTitle("Linked List");
        j1.setVisible(true);
        j1.setLayout(null);
        j1.setLocation(500,200);
        j1.setSize(700,630);
        j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j1.getContentPane().setBackground(new Color(0x939393));

        l1 = new JLabel();
        l1.setText("Linked List Representation");
        l1.setFont(f);
        l1.setHorizontalTextPosition(JLabel.CENTER);
        l1.setVerticalTextPosition(JLabel.BOTTOM);
        l1.setBounds(100,50,500,300);
        l1.setIconTextGap(30);
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setVerticalAlignment(JLabel.TOP);
        l1.setForeground(Color.BLACK);
        j1.add(l1);

        Singly.setBounds(200,200,300,50);
        Singly.setFont(f1);
        Singly.addActionListener(this);
        j1.add(Singly);

        Doubly.setBounds(200,300,300,50);
        Doubly.setFont(f1);
        Doubly.addActionListener(this);
        j1.add(Doubly);

        exit.setBounds(420,470,150,50);
        exit.setFont(f1);
        exit.addActionListener(this);
        j1.add(exit);

        back.setBounds(150,470,150,50);
        back.setFont(f1);
        back.addActionListener(this);
        j1.add(back);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Singly){
            new SingleLL();
            j1.setVisible(false);

        }
        if(e.getSource() == Doubly) {
            new DoubleLL();
            j1.setVisible(false);
        }
        if(e.getSource() == exit){
            System.exit(0);
            j1.setVisible(false);
        }
        if(e.getSource() == back){
            try {
                new Welcome();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            j1.setVisible(false);
        }
    }
}
