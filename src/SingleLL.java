import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SingleLL implements ActionListener {
    public static void main(String[] args) {
        new SingleLL();
    }
    JFrame jf = new JFrame();
    Font f = new Font("Times New Roman", Font.BOLD, 35);
    Font f1 = new Font("Times New Roman", Font.BOLD, 20);
    JLabel l1;
    JButton exit, back,addfirst , addlast , addposition , removefirst , removelast , removeposition , removeval;
    node1 head ;
    int count;
    SListpanel Spanel;
    public SingleLL(){
        jf.setTitle("Sll Demo");
        jf.setSize(700, 630);
        jf.setVisible(true);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().setBackground(new Color(0x939393));

        l1 = new JLabel();
        l1.setText("SLL DEMO");
        l1.setFont(f);
        l1.setHorizontalTextPosition(JLabel.CENTER);
        l1.setVerticalTextPosition(JLabel.BOTTOM);
        l1.setBounds(100, 20, 500, 300);
        l1.setIconTextGap(30);
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setVerticalAlignment(JLabel.TOP);
        l1.setForeground(Color.BLACK);
        jf.add(l1);

        Spanel = new SListpanel();
        Spanel.setBounds(0, 150, 700, 120);
        jf.add(Spanel);


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
        if(e.getSource()==exit){
            System.exit(0);
            jf.setVisible(false);
        }
        if(e.getSource()== back){
            new Linklist();
            jf.setVisible(false);
        }
        if(e.getSource() ==  addfirst){
            int val = Integer.parseInt(JOptionPane.showInputDialog("Enter value to be inserted:"));

            node1 n = new node1(val);
            if(head == null){
                head = n;
            }
            else{
                n.setNext(head);
                head = n;
            }
            count++;
            Spanel.setSll(head);
            Spanel.repaint();
        }
        if(e.getSource() == addlast){
            int val = Integer.parseInt(JOptionPane.showInputDialog("Enter value to be inserted:"));
            node1 n = new node1(val);
            node1 n1 =head;
            if(head == null){
                head  = n;
            }
            else{
                while(n1.next !=null){
                    n1 = n1.next;
                }
                n1.next = n;
            }
            count++;
            Spanel.setSll(head);
            Spanel.repaint();

        }
        if(e.getSource() == addposition){
            int val = Integer.parseInt(JOptionPane.showInputDialog("Enter value to be inserted:"));
            int pos = Integer.parseInt(JOptionPane.showInputDialog("Enter position:"));
            if (pos <= 0 || pos > count) {
                JOptionPane.showMessageDialog(null, "Invalid position:" + pos);
                return;
            }
            int i = 1;
            node1 n = new node1(val);
            node1 n1 =head;
            node1 n2 =n1;
            while(i<pos && n1 !=null){
                i++;
                n2 = n1;
                n1 = n1.getNext();
            }
            if(pos==1){
                n.setNext(head);
                head = n;
            }
            else{
                n2.setNext(n);
                n.setNext(n1);
            }
            count++;
            Spanel.setSll(head);
            Spanel.repaint();
        }
        if(e.getSource() == removefirst){
            if(count == 0){
                JOptionPane.showMessageDialog(null, "Linked List empty");
                return;
            }
            head = head.next;
            count -- ;
            Spanel.setSll(head);
            Spanel.repaint();
        }
        if(e.getSource()== removelast){
            if(count == 0){
                JOptionPane.showMessageDialog(null, "Linked List empty");
                return;
            }
            node1 n = head;
            node1 n1 = n;
            while(n.getNext() !=null){
                n1 = n ;
                n = n.getNext();
            }
            n1.setNext(n.getNext());
            count--;
            Spanel.setSll(head);
            Spanel.repaint();
        }
        if(e.getSource() == removeposition){
            if (count == 0) {
                JOptionPane.showMessageDialog(null, "Linked list empty.");
                return;
            }
            int pos = Integer.parseInt(JOptionPane.showInputDialog("Enter position of the element to be deleted:"));

            if (pos <= 0 || pos > count) {
                JOptionPane.showMessageDialog(null, "Invalid position:" + pos);
                return;
            }
            int i =1;
            node1 n = head;
            node1 n1 = n;
            while(n1 != null && i < pos){
                n1 = n;
                n = n.getNext();
                i++;
            }
            if(pos == 1){
                head = head.getNext();
            }
            else {
                n1.setNext(n.getNext());
            }
            count -- ;
            Spanel.setSll(head);
            Spanel.repaint();
        }
        if(e.getSource() == removeval){
            if(count==0){
                JOptionPane.showMessageDialog(null, "Linked List Empty");
                return;
            }
            int val = Integer.parseInt(JOptionPane.showInputDialog("Enter the value of the element to be deleted :"));
            node1 n = head;
            node1 n1 = n ;

            while(n != null && n.getData() !=val){
                n1 = n ;
                n = n.getNext();
            }
            if(n == null){
                JOptionPane.showMessageDialog(null,val+" not found");
            }
            if(n == head){
                head = head.next;
            }
            else {
                n1.setNext(n.getNext());
            }
            count-- ;
            Spanel.setSll(head);
            Spanel.repaint();

        }
    }
}
class node1{
    node1 next;
    int data;

    node1(int data){
        this.data = data;
        next =null;
    }

    public node1 getNext() {
        return next;
    }

    public void setNext(node1 next) {
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
class SListpanel extends JPanel{
    node1 sll;
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        node1 p ;
        p = sll;
        int x = 50 ;
        int y = 50;

        while (p!=null){

            g.drawRect(x-10, y-10, 50, 50);
            g.drawRect(x+20, y-10, 20, 50);

            if(p.getNext()!=null){
                g.drawLine(x+25, y+15, x+80, y+15);

                g.drawString(">",x+81,y+20);

            }
            g.drawString(Integer.toString(p.getData()), x, y+17);
            p = p.getNext();
            x+=100;
        }
    }

    public void setSll(node1 sll) {
        this.sll = sll;
    }
}
