import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Queue extends JFrame implements ActionListener {
    public static void main(String[] args) {

        new Queue();
    }
    JFrame jf = new JFrame();
    Font f = new Font("Times New Roman", Font.BOLD, 35);
    Font f1 = new Font("Times New Roman", Font.BOLD, 20);
    JLabel l1;
    JButton btnpush, btnpop, exit, back,btnpeek,rear;
    Queuepanel panup;
    QueueImpl imp;

    public Queue(){
        jf.setTitle("Queue Demo");
        jf.setSize(700, 630);
        jf.setVisible(true);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().setBackground(new Color(0x939393));

        l1 = new JLabel();
        l1.setText("QUEUE DEMO");
        l1.setFont(f);
        l1.setHorizontalTextPosition(JLabel.CENTER);
        l1.setVerticalTextPosition(JLabel.BOTTOM);
        l1.setBounds(100, 20, 500, 300);
        l1.setIconTextGap(30);
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setVerticalAlignment(JLabel.TOP);
        l1.setForeground(Color.BLACK);
        jf.add(l1);

        imp = new QueueImpl();
        panup = new Queuepanel(imp);

        panup.setBounds(100, 100, 500, 300);
        jf.add(panup);



        btnpush = new JButton("ENQUE");
        btnpop = new JButton("DEQUEUE");
        btnpeek = new JButton("FRONT");
        rear = new JButton("REAR");
        back = new JButton("Back", new ImageIcon("back.png"));
        exit = new JButton("Exit", new ImageIcon("cancel.png"));

        btnpeek.setFont(f1);
        btnpeek.setBounds(40, 430, 110, 50);
        btnpeek.addActionListener(this);
        jf.add(btnpeek);

        btnpush.setFont(f1);
        btnpush.setBounds(210, 430, 110, 50);
        btnpush.addActionListener(this);
        jf.add(btnpush);

        btnpop.setFont(f1);
        btnpop.setBounds(375,430,135,50);
        btnpop.addActionListener(this);
        jf.add(btnpop);

        rear.setFont(f1);
        rear.setBounds(550, 430, 110, 50);
        rear.addActionListener(this);
        jf.add(rear);

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
        if(e.getSource() == btnpush){
            String val = JOptionPane.showInputDialog("Enter the value");
                imp.add(val);


        }
        else if(e.getSource() == btnpop){
            String val = imp.remove();

            if(val !=null){
                JOptionPane.showMessageDialog(null, "Deleted Element: "+val);
            }
        }
        else if(e.getSource() == btnpeek){
            String val = imp.peek();
            if(val!=null){
                JOptionPane.showMessageDialog(null, "Peek Element: "+val);

            }
        }
        else if (e.getSource() == back){
            try {
                new Welcome();
                jf.setVisible(false);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if(e.getSource() == exit){
            System.exit(0);
            jf.setVisible(false);
        }
        else if (e.getSource() == rear){
            String val =imp.arr[imp.getRear()];
            if (val != null) {
                JOptionPane.showMessageDialog(null, "The rear value is "+val);

            }

        }

        panup.repaint();
    }
}
class QueueImpl{
    String [] arr;
    int front , rear;
    int max =5;

    public QueueImpl(){
        arr = new String[max];
        front = rear =-1;
    }
    public boolean isEmpty(){
        return front == -1 && rear ==-1;
    }
    public boolean isFull(){
        return (rear+1)%max == front;
    }
    public void add(String q) {
        if (isFull()) {
            JOptionPane.showMessageDialog(null, "Queue Overflowed");
            return;
        }
        if (q != null) {
            if (front == -1) {
                front = 0;
            }
            rear = (rear + 1) % max;
            arr[rear] = q;
        }
    }
    public String remove(){
        if(isEmpty()){
            JOptionPane.showMessageDialog(null, "Queue Underflowd");
            return null;
        }
        String result = arr[front];
        if(rear==front){
            front = rear = -1;
        }
        else{
            front = (front+1)%max;
        }
        return result;
    }
    public String peek(){
        if(isEmpty()){
            JOptionPane.showMessageDialog(null, "Queue Underflowed");
            return null;
        }
        return arr[front];
    }
    public String rear(){
        if(isEmpty()){
            JOptionPane.showMessageDialog(null, "Queue Underflowed");
            return null;
        }
        return arr[rear];
    }

    public String[] getArr() {
        return arr;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }
}
class Queuepanel extends JPanel{
    QueueImpl queue;
    public Queuepanel(QueueImpl que){
        queue = que;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        int w = getWidth();
        int cw = w/(queue.max);

        for(int i =0 ; i< queue.max;i++){
            g.drawRect(i*cw, 105, cw, cw);

        }
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("Front",queue.getFront()*cw+30,250);
        g.drawString("Rear",queue.getRear()*cw+30,280);

        String [] elements = queue.getArr();


        if(!queue.isEmpty()){
         if(queue.getFront() == -1){
             int i = (queue.getFront())+1;
             g.drawString(elements[i],i*cw+30,175);
         }
         else {
             if(queue.getRear()>= queue.getFront()){
                 for(int i= queue.getFront();i<= queue.getRear();i++){
                     g.drawString(elements[i],i*cw+30,175);
                 }
             }
             else{
                 for(int i= queue.getFront();i< queue.max;i++){
                     g.drawString(elements[i],i*cw+30,175);
                 }
                 for(int i=0 ; i<=queue.getRear();i++){
                     g.drawString(elements[i],i*cw+30,175);
                 }
             }
         }
        }

    }
}
