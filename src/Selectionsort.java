import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class Selectionsort extends JFrame implements ActionListener {
    public static void main(String[] args) {

    }
    JFrame jf ;
    JButton btnstart , back , exit;
    int [] arr;
    Font f = new Font("Times New Roman", Font.BOLD, 35);
    Font f1 = new Font("Times New Roman", Font.BOLD, 20);
    JLabel l1;
    Selectionpanel pancenter;
    Mythread t;

    public Selectionsort() throws IOException {
        jf = new JFrame();

        jf.setTitle("Selection Sort");
        jf.setSize(700, 630);
        jf.setVisible(true);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().setBackground(new Color(0x939393));

        l1 = new JLabel();
        l1.setText("Selection Sort");
        l1.setFont(f);
        l1.setHorizontalTextPosition(JLabel.CENTER);
        l1.setVerticalTextPosition(JLabel.BOTTOM);
        l1.setBounds(100, 20, 500, 300);
        l1.setIconTextGap(30);
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setVerticalAlignment(JLabel.TOP);
        l1.setForeground(Color.BLACK);
        jf.add(l1);

        btnstart = new JButton("Start");
        back = new JButton("Back", new ImageIcon("back.png"));
        exit = new JButton("Exit", new ImageIcon("cancel.png"));

        arr = new int[10];
        pancenter = new Selectionpanel(arr);
        pancenter.setBounds(0, 150, 700, 120);
        jf.add(pancenter);

        btnstart.setBounds(250, 300, 200, 50);
        btnstart.setFont(f1);
        btnstart.addActionListener(this);
        jf.add(btnstart);

        back.setBounds(250, 400, 200, 50);
        back.setFont(f1);
        back.addActionListener(this);
        jf.add(back);

        exit.setBounds(250, 500, 200, 50);
        exit.setFont(f1);
        exit.addActionListener(this);
        jf.add(exit);



    }
    public void generatearr(){
        for(int i=0;i<10;i++){
            Random rand = new Random();
            int a = rand.nextInt(500-10)+10;
            arr[i] = a;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnstart){
            generatearr();
            pancenter.setFlag(true);
            t = new Mythread();
            t.start();

        }
        else if (e.getSource() == back){
            try {
                new sorting();
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

    class Mythread extends Thread{
        public void run(){
            int n = 10;
            for(int i=0;i<n;i++){
                pancenter.setPass(i+1);
                int smallest = i;
                for(int j = i+1 ;j<10;j++){
                    if(arr[j]<arr[smallest]){
                        smallest = j;
                    }
                }
                pancenter.setPos(i,smallest);
                int temp = arr[smallest];
                arr[smallest] = arr[i];
                arr[i] = temp;
                pancenter.repaint();

                try{
                    Thread.sleep(2000);
                }
                catch (Exception e){}
            }
            JOptionPane.showMessageDialog(null,"Sorting Complete");
            pancenter.setPos(-1,-1);
            pancenter.repaint();
        }
    }
}
class Selectionpanel extends JPanel{
    int [] arr ;
    int pos1 ,pos2,pass;
    boolean flag;

    public Selectionpanel(int []arr){
        this.arr = arr;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if(flag){
            int w = getWidth();
            int cw = w/10;
            g.setColor(Color.PINK);
            g.fillRect(pos1*cw,15,cw,cw);
            g.setColor(Color.CYAN);
            g.fillRect(pos2*cw,15,cw,cw);
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.BOLD, 15));
            for(int i=0;i<10;i++){
                g.drawRect(i*cw,15,cw,cw);
                g.drawString(Integer.toString(arr[i]),
                        i*cw+10,20+cw/2);
            }
            g.drawString("Pass:"+pass,4,10);
        }

    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public void setPos(int pos1 , int pos2){
        this.pos1 = pos1;
        this.pos2 = pos2;
    }
}
