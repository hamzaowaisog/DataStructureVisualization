import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Welcome extends JFrame implements ActionListener {
    public static void main(String[] args) throws IOException {
        Welcome w1 = new Welcome();
    }
    private JButton btnSort,btnGraph,btnQueue,btnStack,btnList , exit;
    private JLabel l1;
    JFrame jf;
    public Welcome() throws IOException {
        jf = new JFrame();
        Font f = new Font("Times New Roman",Font.BOLD,35);
        Font f1 = new Font("Times New Roman",Font.BOLD,20);
        btnSort = new JButton("Sorting");
        btnQueue = new JButton("Queue");
        btnStack = new JButton("Stack");
        btnList = new JButton("Linked List");
        btnGraph = new JButton("Graph Traversal");
        exit = new JButton("Exit", new ImageIcon( "cancel.png"));

        jf.setTitle("Data Structure Representation");
        jf.setSize(700,630);
        jf.setVisible(true);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().setBackground(new Color(0x939393));


        l1 = new JLabel();
        l1.setText("Data Structure Representation");
        l1.setFont(f);
        l1.setHorizontalTextPosition(JLabel.CENTER);
        l1.setVerticalTextPosition(JLabel.BOTTOM);
        l1.setBounds(100,20,500,300);
        l1.setIconTextGap(30);
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setVerticalAlignment(JLabel.TOP);
        l1.setForeground(Color.BLACK);
        jf.add(l1);


        btnSort.setBounds(200,100,300,50);
        btnSort.setFont(f1);
        btnSort.addActionListener(this);
        jf.add(btnSort);

        btnStack.setBounds(200,180,300,50);
        btnStack.setFont(f1);
        btnStack.addActionListener(this);
        jf.add(btnStack);

        btnQueue.setBounds(200,260,300,50);
        btnQueue.setFont(f1);
        btnQueue.addActionListener(this);
        jf.add(btnQueue);

        btnList.setBounds(200,340,300,50);
        btnList.setFont(f1);
        btnList.addActionListener(this);
        jf.add(btnList);

        btnGraph.setBounds(200,420,300,50);
        btnGraph.setFont(f1);
        btnGraph.addActionListener(this);
        jf.add(btnGraph);

        exit.setBounds(200,500,300,50);
        exit.setFont(f1);
        exit.addActionListener(this);
        jf.add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSort){
            try {
                new sorting();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            jf.setVisible(false);
        }
        else if(e.getSource() == btnQueue){
            new Queue();
            jf.setVisible(false);
        }
        else if(e.getSource() == btnStack){
            new Stack();
            jf.setVisible(false);
        }
        else if(e.getSource() == btnList){
            new Linklist();
            jf.setVisible(false);
        }
        else if(e.getSource() == btnGraph){
            new Graph();
            jf.setVisible(false);
        }
        else if(e.getSource() == exit){
            System.exit(0);
        }
    }
}
