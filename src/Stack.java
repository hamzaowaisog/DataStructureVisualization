import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Stack extends JFrame implements ActionListener {
    public static void main(String[] args) {
        Stack s1 = new Stack();

    }
    JFrame jf = new JFrame();
    Font f = new Font("Times New Roman", Font.BOLD, 35);
    Font f1 = new Font("Times New Roman", Font.BOLD, 20);
    JLabel l1;
    JButton btnpush, btnpop, exit, back,btnpeek;
    StackPanel panleft;
    StackImpl mystk;
    JPanel panright;

    public Stack(){
        jf.setTitle("STACK DEMO");
        jf.setSize(700, 630);
        jf.setVisible(true);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().setBackground(new Color(0x939393));

        l1 = new JLabel();
        l1.setText("STACK DEMO");
        l1.setFont(f);
        l1.setHorizontalTextPosition(JLabel.CENTER);
        l1.setVerticalTextPosition(JLabel.BOTTOM);
        l1.setBounds(100, 20, 500, 300);
        l1.setIconTextGap(30);
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setVerticalAlignment(JLabel.TOP);
        l1.setForeground(Color.BLACK);
        jf.add(l1);


        btnpush = new JButton("PUSH");
        btnpop = new JButton("POP");
        btnpeek = new JButton("PEEK");
        back = new JButton("Back", new ImageIcon("back.png"));
        exit = new JButton("Exit", new ImageIcon("cancel.png"));

        mystk = new StackImpl();
        panleft = new StackPanel(mystk);

        panleft.setBounds(100, 100, 500, 300);
        jf.add(panleft);

        btnpeek.setFont(f1);
        btnpeek.setBounds(100, 430, 110, 50);
        btnpeek.addActionListener(this);
        jf.add(btnpeek);

        btnpush.setFont(f1);
        btnpush.setBounds(300, 430, 110, 50);
        btnpush.addActionListener(this);
        jf.add(btnpush);

        btnpop.setFont(f1);
        btnpop.setBounds(500,430,110,50);
        btnpop.addActionListener(this);
        jf.add(btnpop);

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
        if(e.getSource()==btnpush){
            String val = JOptionPane.showInputDialog("Enter the value: ");
            mystk.push(val);
        }
        if(e.getSource() == btnpop){
            String val = mystk.pop();
            if(val != null)
                JOptionPane.showMessageDialog(null,"Popped element: "+val);
        }
        if(e.getSource() == btnpeek){
            String val = mystk.peek();
            if(val != null)
                JOptionPane.showMessageDialog(null, "Top Element: "+val);
        }
        panleft.repaint();
        if(e.getSource() == back){
            try {
                new Welcome();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            jf.setVisible(false);
        }
        else if(e.getSource() == exit){
            System.exit(0);
            jf.setVisible(false);
        }
    }
}
class StackImpl{
    String [] arr;
    int top ;
    int max = 5;

    public StackImpl(){
        arr = new String[max];
        top = -1;
    }
    public boolean isEmpty(){
        return top ==-1;
    }
    public boolean isFull(){
        return top==max-1;
    }
    public void push(String val){
        if(isFull()){
            JOptionPane.showMessageDialog(null, "Stack Overflow");
            return;
        }
        arr[++top] = val;
    }
    public String pop (){
        if(isEmpty()){
            JOptionPane.showMessageDialog(null, "Stack Empty");
            return null;
        }
        return arr[top--];
    }
    public String peek(){
        if(isEmpty()){
            JOptionPane.showMessageDialog(null, "Stack Underflow");
            return null;
        }
        return arr[top];

    }
    public String[] getelements(){
        return arr;
    }

    public int getTop(){
        return top;
    }
}

class StackPanel extends JPanel{
    StackImpl s;

    public StackPanel(StackImpl s){
        this.s = s;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();

        int cellwidth = w/2;
        int cellheight = h/5 -5;


        for(int i =0; i<s.max;i++){
            g.drawRect(125, i*cellheight+15, cellwidth, cellheight);

        }
        String elements[] = s.getelements();

        int j = s.max;


        g.setFont(new Font("TimesRoman", Font.BOLD, 25));
        for(int i=0 ; i<=s.getTop();i++){
            g.drawString(elements[i],235,j*cellheight-cellheight/2 +25);
            j--;
        }
        g.drawString("top", 135+cellwidth, (s.max-s.getTop())*cellheight-cellheight/2 +25);
    }
}
