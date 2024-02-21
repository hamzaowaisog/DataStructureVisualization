import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class sorting extends JFrame implements ActionListener {
    public static void main(String[] args) throws IOException {
        sorting s1 = new sorting();
    }
    JButton bubblesort , insertionsort, selectionsort , back , exit;
    JFrame j1;
    JLabel l1;
    public sorting() throws IOException {
        j1 = new JFrame();
        bubblesort = new JButton("Bubble Sort");
        insertionsort = new JButton("Insertion Sort");
        selectionsort = new JButton("Selesction Sort");
        back = new JButton("Back", new ImageIcon("back.png"));
        exit = new JButton("Exit",new ImageIcon("cancel.png"));
        Font f = new Font("Times New Roman",Font.BOLD,35);
        Font f1 = new Font("Times New Roman",Font.BOLD,20);

        j1.setTitle("Sorting");
        j1.setVisible(true);
        j1.setLayout(null);
        j1.setLocation(500,200);
        j1.setSize(700,630);
        j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j1.getContentPane().setBackground(new Color(0x939393));

        l1 = new JLabel();
        l1.setText("SORTING REPRESENTATION");
        l1.setFont(f);
        l1.setHorizontalTextPosition(JLabel.CENTER);
        l1.setVerticalTextPosition(JLabel.BOTTOM);
        l1.setBounds(100,50,500,300);
        l1.setIconTextGap(30);
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setVerticalAlignment(JLabel.TOP);
        l1.setForeground(Color.BLACK);
        j1.add(l1);

        bubblesort.setBounds(200,150,300,50);
        bubblesort.setFont(f1);
        bubblesort.addActionListener(this);
        j1.add(bubblesort);

        insertionsort.setBounds(200,250,300,50);
        insertionsort.setFont(f1);
        insertionsort.addActionListener(this);
        j1.add(insertionsort);

        selectionsort.setBounds(200,350,300,50);
        selectionsort.setFont(f1);
        selectionsort.addActionListener(this);
        j1.add(selectionsort);

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
        if(e.getSource() == bubblesort){
            new Bubblesort();
            j1.setVisible(false);
        }
        else if(e.getSource() == selectionsort){
            try {
                new Selectionsort();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            j1.setVisible(false);
        }
        else if(e.getSource() == insertionsort){
            new Insertionsort();
            j1.setVisible(false);
        }
        else if(e.getSource() == back){
            try {
                new Welcome();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            j1.setVisible(false);
        }
        else if(e.getSource() == exit){
            System.exit(0);
        }

    }
}
