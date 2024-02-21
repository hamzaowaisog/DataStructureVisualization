import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph extends JFrame implements ActionListener {
    public static void main(String[] args) {

        new Graph();

    }
    JFrame jf = new JFrame();
    Font f = new Font("Times New Roman", Font.BOLD, 35);
    Font f1 = new Font("Times New Roman", Font.BOLD, 20);
    JLabel l1;
    JButton btnbfs, btndfs, exit, back;
    GraphPanel panCenter;
    point [] points;
    boolean [][] adjmat;
    int n,i;
    boolean [] visited;
    public Graph(){
        String s = JOptionPane.showInputDialog("Enter the no.of vertices :");
        if(s==null){
            dispose();
            return;
        }
        n = Integer.parseInt(s);

        points = new point[n];
        adjmat = new boolean[n][n];
        visited = new boolean[n];



        jf.setTitle("Graph traversal");
        jf.setSize(700, 630);
        jf.setVisible(true);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().setBackground(new Color(0x939393));

        l1 = new JLabel();
        l1.setText("Graph Traversal");
        l1.setFont(f);
        l1.setHorizontalTextPosition(JLabel.CENTER);
        l1.setVerticalTextPosition(JLabel.BOTTOM);
        l1.setBounds(100, 20, 500, 300);
        l1.setIconTextGap(30);
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setVerticalAlignment(JLabel.TOP);
        l1.setForeground(Color.BLACK);
        jf.add(l1);


        panCenter = new GraphPanel(points, adjmat, visited);
        panCenter.setBounds(0, 100, 700, 300);
        jf.add(panCenter);
        panCenter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (i < n) {
                   points[i] = new point(me.getX(), me.getY());
                   i++;
                   panCenter.SetI(i);
                   panCenter.repaint();
                }
                else{
                    new Matrix(adjmat, n, panCenter);
                }
            }
        });




        btnbfs = new JButton("BFS");
        btndfs = new JButton("DFS");
        back = new JButton("Back", new ImageIcon("back.png"));
        exit = new JButton("Exit", new ImageIcon("cancel.png"));

        btnbfs.setFont(f1);
        btnbfs.setBounds(170, 430, 150, 50);
        btnbfs.addActionListener(this);
        jf.add(btnbfs);

        btndfs.setBounds(400, 430, 150, 50);
        btndfs.setFont(f1);
        btndfs.addActionListener(this);
        jf.add(btndfs);

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
        if(e.getSource() == exit){
            System.exit(0);
        }
        if(e.getSource()== back){
            try {
                new Welcome();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            jf.setVisible(false);
        }
        if(e.getSource()== btnbfs){
            for(int i=0;i<n;i++){
                visited[i] = false;
            }
            new BFSThread().start();
        }
        if(e.getSource() == btndfs){
            for(int i=0;i<n;i++){
                visited[i]=false;
            }
            new DFSthread().start();
        }

    }
    class DFSthread extends Thread{
        public void run(){
            int v = Integer.parseInt(JOptionPane.showInputDialog("Enter Start Vertex: "));
            int u,w;

            IntStack s = new IntStack();

            s.push(v);
            visited[v]= true;
            panCenter.repaint();
            try{
                Thread.sleep(1000);
            }
            catch (Exception e){

            }
            while(!s.isEmpty()){
                u = s.peek();
                for( w=0 ; w<n ; w++) {
                    if (adjmat[u][w] && !visited[w]) {
                        break;
                    }
                }
                    if(w==n){
                        s.pop();
                    }
                    else{
                        visited[w]=true;
                        panCenter.repaint();
                        try{
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        s.push(w);
                    }
                }
            panCenter.repaint();
            }

        }

    class BFSThread extends Thread {
        public void run(){

            int v = Integer.parseInt(JOptionPane.showInputDialog("Enter start vertex: "));
            int u,w;

            IntQueue q = new IntQueue();
            q.addq(v);
            visited[v]=true;
            panCenter.repaint();

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(!q.isEmpty()){
                u=q.delq();
                for(w=0;w<n;w++){
                    if(adjmat[u][w] && !visited[w]){
                        visited[w] = true;
                        q.addq(w);
                        panCenter.repaint();
                        try{
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
            panCenter.repaint();
        }

    }
}
class IntStack{
    int max = 20;
    int [] stk;
    int top;

    IntStack(){
        stk = new int[max];
        top = -1;
    }
    public void push(int val){
        stk[++top] = val;
    }
    public int pop (){
        return stk[top--];
    }
    public int peek(){
        return stk[top];
    }
    public boolean isEmpty(){
        return top == -1;
    }
}
class IntQueue{
    int max = 20;
    int [] qu ;
    int front , rear;

    IntQueue(){
        qu = new int[max];
        front = rear = -1;
    }
    public boolean isEmpty(){
        return front == rear;
    }
    public void addq(int val){
        qu[++rear] = val;
    }
    public int delq(){
        return qu[++front];
    }
}
class point {
    int x,y;

    public point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
class GraphPanel extends JPanel{
    point [] pts;
    boolean flag;
    boolean [][] adjMat;
    int n;
    boolean [] visited;
    ArrayList<Integer> arr ;
    ArrayList<Integer> arr1;

    public GraphPanel(point[] pts , boolean [][] adjMat , boolean []v){
        this.pts = pts;
        this.adjMat = adjMat;
        n = 0;
        this.visited = v;
        arr = new ArrayList<>();
        arr1 = new ArrayList<>();

    }

    public void SetI(int i){
        n = i;
    }
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        for (int j = 0; j < n; j++) {
            g.drawString("V" + j, pts[j].getX(), pts[j].getY() + 40);
            g.drawOval(pts[j].getX() - 10, pts[j].getY() - 10, 30, 30);
        }
        for (int k = 0; k < n; k++) {
            for (int j = 0; j < n; j++) {
                if (adjMat[k][j]) {
                    g.drawLine(pts[k].getX(), pts[k].getY(), pts[j].getX(), pts[j].getY());
                }
            }
        }


        int x = 0;
        for (int j = 0; j < n; j++) {
            if (visited[j]) {
                g.setColor(Color.YELLOW);
                g.fillOval(pts[j].getX() - 10, pts[j].getY() - 10, 30, 30);
                g.setFont(new Font("Arial", Font.BOLD, 14));
                g.setColor(Color.RED);
                g.drawString("V"+j,10+x,getHeight()-50);
                g.setColor(Color.blue);
                g.fillOval(pts[j].getX() - 10, pts[j].getY() - 10, 30, 30);
                x += 20;

            } else {
                g.setColor(Color.red);
                g.fillOval(pts[j].getX() - 10, pts[j].getY() - 10, 30, 30);
            }
        }

    }

}
