import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Matrix extends JFrame {
    public static void main(String[] args) {

    }
    JCheckBox [][] cbMat;
    JButton btnok;
    JPanel pancenter;
    JPanel pansouth;

    boolean [][] adjmat;
    int n;
    GraphPanel mp;

    public Matrix(boolean [][] mat , int m , GraphPanel p ){
        adjmat = mat;
        n=m;
        mp=p;

        cbMat = new JCheckBox[n][n];

        for(int i=0 ; i<n ; i++) {
            for (int j = 0; j < n; j++) {
                cbMat[i][j] = new JCheckBox();
            }
        }
            pancenter = new JPanel();
            pancenter.setLayout(new GridLayout(n+1,n+1));

            pancenter.add(new JLabel());
            for(int j=0;j<n;j++){
                pancenter.add(new JLabel("V"+j));
            }
            for(int i=0 ; i<n;i++){
                pancenter.add(new JLabel("V"+i));
                for(int j=0;j<n;j++){
                    pancenter.add(cbMat[i][j]);
                }
            }
            btnok = new JButton("Ok");
            pansouth = new JPanel();
            pansouth.setLayout(new FlowLayout());
            pansouth.add(btnok);

            setTitle("Accept Adjacency Matrix");
            setSize(700,630);
            setLocation(500,200);
            add(pancenter,"Center");
            add(pansouth,"South");
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            btnok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0 ; i<n ; i++){
                        for(int j=0;j<n;j++){
                            adjmat[i][j] = cbMat[i][j].isSelected();
                        }
                    }
                    mp.repaint();
                    dispose();
                }
            });
        }
    }


