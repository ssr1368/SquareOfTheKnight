


import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SquareOfTheKnight extends JFrame{
    private int[][] Matrix = new int[14][14];
    private SquareOfTheKnight instance;
    private int next;
    private final Engine engine;
    
    private SquareOfTheKnight()
    {
        engine=new Engine(this);
        Screen.getInstance().addMouseListener(engine);
        setVisible(true);
        setFocusable(false);
        setResizable(false);
        this.setBounds(600, 150, 610, 610);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(new Color(255,255,229)); //That's the colour recommended for dyslexic people
        start();
        //debugButton();
    }
    
    private void start()
    {
        next=1;
        for(int x=2;x<12;++x)
            for(int y=2;y<12;++y)
            {
                Matrix[x][y]=0;
            }
        
        for(int x=0;x<2;++x)
            for(int y=0;y<14;++y)
                {
                    Matrix[x][y]=1;
                    Matrix[y][x]=1;
                    Matrix[13-x][y]=1;
                    Matrix[y][13-x]=1;
                }
        this.setContentPane(Screen.getInstance());
        newGame();
    }
    
    private void newGame()
    {
        engine.restart();
    }
    
    public SquareOfTheKnight getInstance(){instance=this; return instance;}
    public int[][] getMatrix(){return Matrix;}
    
    public void changeColor(Color c){getContentPane().getGraphics().setColor(c);}
    
    public void shoot(int x, int y){
        Matrix[1+x][1+y]=1;
        draw(x,y,next);
        ++next;
    }
    
    private void draw(int x, int y, int n)
    {
        x*=50;
        y*=50;
        if(next<10)
        {
            getGraphics().drawImage(Basics.img(n+".png"),x+5+10,y+50-5-10,null);
        }
        else if(next<100)
        {
            int p=n/10;
            getGraphics().drawImage(Basics.img(n%(p*10)+".png"),x+5+10+5+5+3,y+50-5-10,null);
            getGraphics().drawImage(Basics.img(p+".png"),x+3+5,y+50-5-10,null);
        }
        else
        {
            getGraphics().drawImage(Basics.img("1.png"),x+3,y+50-5-10,null);
            getGraphics().drawImage(Basics.img("0.png"),x+3+15,y+50-5-10,null);
            getGraphics().drawImage(Basics.img("0.png"),x+3+15+20,y+50-5-10,null);
        }
    }
    
    public void endGame()
    {
        //JOptionPane.showMessageDialog(rootPane, "Game over! You achieved "+(next-1)+" points. Not bad!", "Game Over", 0);
        int n;
        if(next<100)
        n = JOptionPane.showConfirmDialog(rootPane, "Game over! You achieved "+(next-1)+" points. Not bad! Do you wanna play again?", "Game Over", 0);
        else n = JOptionPane.showConfirmDialog(rootPane, "WOOOOOOW, YOU DID IT!!!! 100 POINTS, YOU ARE A REAL GRANDMASTER. Minstrels will sing songs about you. Would you like to show that it was not luck-related repeating it?"
                , "Perfect game", 0);
        if(n==1)this.dispose();
        else start();
    }
    
    /*
    private void debugButton()
    {
        javax.swing.JButton a = new javax.swing.JButton();
        a.setText("Check");
        a.setFont(new java.awt.Font("Sylfaen", 0, 18));
        a.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                engine.showLasts();
            }
        });
        add(a);
        a.setBounds(250, 0, 160, 55);
        a.setFocusable(false);
    }
    */
    
    
    public static void main(String[] args) {
        SquareOfTheKnight game = new SquareOfTheKnight();
        game.setFocusable(true);
        game.setAlwaysOnTop(true);
    }
}
