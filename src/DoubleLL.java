import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DoubleLL extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new DoubleLL();
    }
    JFrame jf = new JFrame();
    Font f = new Font("Times New Roman", Font.BOLD, 35);
    Font f1 = new Font("Times New Roman", Font.BOLD, 20);
    JLabel l1;
    JButton exit, back,addfirst , addlast , addposition , removefirst , removelast , removeposition , removeval;
    node head , last;
    int count;
    Listpanel panel;

    public DoubleLL(){

        jf.setTitle("DLL Demo");
        jf.setSize(700, 630);
        jf.setVisible(true);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().setBackground(new Color(0x939393));

        l1 = new JLabel();
        l1.setText("DLL DEMO");
        l1.setFont(f);
        l1.setHorizontalTextPosition(JLabel.CENTER);
        l1.setVerticalTextPosition(JLabel.BOTTOM);
        l1.setBounds(100, 20, 500, 300);
        l1.setIconTextGap(30);
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setVerticalAlignment(JLabel.TOP);
        l1.setForeground(Color.BLACK);
        jf.add(l1);

        panel = new Listpanel();
        panel.setBounds(0, 150, 700, 120);
        jf.add(panel);


        back = new JButton("Back", new ImageIcon("back.png"));
        exit = new JButton("Exit", new ImageIcon("cancel.png"));
        addfirst = new JButton("Add First");
        addlast = new JButton("Add Last");
        addposition = new JButton("Add Position");
        removefirst = new JButton("Remove First");
        removelast = new JButton("Remove Last");
        removeposition = new JButton("Remove by Postion");
        removeval = new JButton("Remove by value");


        addfirst.setBounds(20, 350, 120, 50);
        addfirst.addActionListener(this);
        jf.add(addfirst);

        addlast.setBounds(150, 350, 120, 50);
        addlast.addActionListener(this);
        jf.add(addlast);

        addposition.setBounds(280, 350, 120, 50);
        addposition.addActionListener(this);
        jf.add(addposition);

        removefirst.setBounds(410, 350, 120, 50);
        removefirst.addActionListener(this);
        jf.add(removefirst);

        removelast.setBounds(540, 350, 120, 50);
        removelast.addActionListener(this);
        jf.add(removelast);

        removeposition.setBounds(180, 420, 150, 50);
        removeposition.addActionListener(this);
        jf.add(removeposition);

        removeval.setBounds(380, 420, 140, 50);
        removeval.addActionListener(this);
        jf.add(removeval);

        back.setFont(f1);
        back.setBounds(170, 520, 150, 50);
        back.addActionListener(this);
        jf.add(back);

        exit.setFont(f1);
        exit.setBounds(400, 520, 150, 50);
        exit.addActionListener(this);
        jf.add(exit);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
            jf.setVisible(false);
        }
        if (e.getSource() == back) {
            new Linklist();
            jf.setVisible(false);
        }
        if (e.getSource() == addfirst) {
            int val = Integer.parseInt(JOptionPane.showInputDialog("Enter value to be inserted:"));

            node n = new node(val);
            if (head == null) {
                head = last = n;
            } else {
                n.setNext(head);
                head.setPrev(n);
                head = n;
            }
            count++;
            panel.setDll(head);
            panel.repaint();
        }
        if (e.getSource() == addlast) {
            int val = Integer.parseInt(JOptionPane.showInputDialog("Enter value to be inserted:"));
            node n = new node(val);

            if (head == null) {
                head = last = n;
            } else {
                last.setNext(n);
                n.setPrev(last);
                last = n;
            }
            count++;
            panel.setDll(head);
            panel.repaint();

        }
        if (e.getSource() == addposition) {
            int val = Integer.parseInt(JOptionPane.showInputDialog("Enter value to be inserted:"));
            int pos = Integer.parseInt(JOptionPane.showInputDialog("Enter position:"));
            if (pos <= 0 || pos > count) {
                JOptionPane.showMessageDialog(null, "Invalid position:" + pos);
                return;
            }
            int i = 1;
            node n = new node(val);

            node n1;
            n1 = head;
            while (i < pos && n1 != null) {
                i++;
                n1 = n1.getNext();
            }
            if (pos == 1) {
                n.setNext(head);
                head.setPrev(n);
                head = n;
            } else {
                n1.getPrev().setNext(n);
                n.setPrev(n1.getPrev());
                n.setNext(n1);
                n1.setPrev(n);
            }
            count++;
            panel.setDll(head);
            panel.repaint();
        }
        if (e.getSource() == removefirst) {
            if (count == 0) {
                JOptionPane.showMessageDialog(null, "Linked List empty.");
                return;
            }
            if (head == last) {
                head = last = null;
            } else {
                head = head.getNext();
                head.setPrev(null);
            }
            count--;
            panel.setDll(head);
            panel.repaint();
        }
        if (e.getSource() == removelast) {
            if (count == 0) {
                JOptionPane.showMessageDialog(null, "Linked list empty.");
                return;
            }
            if (head == last) {
                head = last = null;
            } else {
                last = last.getPrev();
                last.setNext(null);
            }
            count--;
            panel.setDll(head);
            panel.repaint();

        }
        if (e.getSource() == removeposition) {
            if (count == 0) {
            JOptionPane.showMessageDialog(null, "Linked list empty.");
            return;
        }
        int pos = Integer.parseInt(JOptionPane.showInputDialog("Enter position of the element to be deleted:"));

        if (pos <= 0 || pos > count) {
            JOptionPane.showMessageDialog(null, "Invalid position:" + pos);
            return;
        }
        int i = 1;
        node n = head;
        while (n != null && i < pos) {
            i++;
            n = n.getNext();
        }
        if (head == last) {
            head = last = null;
        } else if (pos == 1) {
            head = head.getNext();
            head.setPrev(null);
        } else if (pos == count) {
            last = last.getPrev();
            last.setNext(null);
        } else {
            n.getPrev().setNext(n.getNext());
            n.getNext().setPrev(n.getPrev());
        }
        count--;
        panel.setDll(head);
        panel.repaint();
    }
        if(e.getSource()==removeval){
            if(count==0){
                JOptionPane.showMessageDialog(null, "Linked List Empty");
                return;
            }
            int val = Integer.parseInt(JOptionPane.showInputDialog("Enter the value of the element to be deleted :"));
            node n = head;

            while(n!=null && n.getData() !=val){
                n = n.getNext();
            }
            if(n==null){
                JOptionPane.showMessageDialog(null,val+" not found.");
                return;
            }
            if(head == last){
                head = last = null;
            }
            else if(head == n){
                head = head.getNext();
                head.setPrev(null);
            }
            else{
                n.getPrev().setNext(n.getNext());
                n.getNext().setPrev(n.getPrev());
            }
            count -- ;
            panel.setDll(head);
            panel.repaint();

        }

    }


    }

class node {
    node prev , next;
    int data;

    node(){
        prev = next = null;
    }
    node(int data){
        this.data = data;
        prev = next = null;
    }

    public node getPrev() {
        return prev;
    }

    public node getNext() {
        return next;
    }

    public void setPrev(node prev) {
        this.prev = prev;
    }

    public void setNext(node next) {
        this.next = next;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }
}
class Listpanel extends JPanel{
    node dll;
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        node p ;
        p = dll;
        int x = 50 ;
        int y = 50;

        while (p!=null){
            g.drawRect(x-30, y-10, 20, 50);
            g.drawRect(x-10, y-10, 50, 50);
            g.drawRect(x+20, y-10, 20, 50);

            if(p.getNext()!=null){
                g.drawLine(x+25, y+5, x+80, y+5);
                g.drawLine(x+85, y+20, x+30, y+20);
                g.drawString(">",x+81,y+10);
                g.drawString("<",x+24,y+25);
            }
            g.drawString(Integer.toString(p.getData()), x, y+17);
            p = p.getNext();
            x+=100;
        }
    }

    public void setDll(node dll) {
        this.dll = dll;
    }
}