
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class Engine implements java.awt.event.MouseListener{
    
    SquareOfTheKnight main;
    SoundBasis sounds;
    private int lastx=1;
    private int lasty=1;
    private final int tamanyo=50;
    
    public Engine(SquareOfTheKnight instance)
    {
        main=instance;
        sounds=new SoundBasis();
    }
    
    private void lightSquares()
    {
        boolean light=false;
        for(int x=1;x<11;++x)
            for(int y=1;y<11;++y)
            {
                if(legalMove(x,y) && available(x,y))
                {
                    light=true;
                    main.getGraphics().fillOval(x*tamanyo+5, y*tamanyo+tamanyo/2+5, tamanyo-5, tamanyo-5);
                }
            }
        if(!light)main.endGame();
    }
    
    private void stopLight()
    {
        for(int x=1;x<11;++x)
            for(int y=1;y<11;++y)
            {
                if(available(x,y) && legalMove(x,y))
                {
                    main.getGraphics().clearRect(x*tamanyo+5+2, y*tamanyo+tamanyo/2+5, tamanyo-5, tamanyo-5);
                }
            }
    }
    
    private boolean able(int x, int y)
    {
        return available(x,y) && legalMove(x,y);
    }
    
    private boolean available(int x, int y)
    {
        return main.getMatrix()[1+x][1+y]==0;
    }
    
    private boolean legalMove(int x, int y)
    {
        if(x==lastx)
        {
            return false;
        }
        else if(x+1==lastx || x-1==lastx)
        {
            if(y-2==lasty || y+2==lasty) return true;
            else return false;
        }
        else if(x+2==lastx || x-2==lastx)
        {
            if(y-1==lasty || y+1==lasty) return true;
            else return false;
        }
        return false;
    }
    
    

    @Override
    public void mouseClicked(MouseEvent evt) {}

    @Override
    public void mousePressed(MouseEvent evt) {
        int x = evt.getPoint().x;
        int y = evt.getPoint().y;
        x=(x/tamanyo);
        y=(y/tamanyo);
        
        if(x>0 && x<11)
            if(able(x,y))
            {
                stopLight();
                lastx=x;
                lasty=y;
                main.shoot(x,y);
                lightSquares();
            }
            else sounds.reproduce("error");
        Basics.delay(50);
    }
    
    public void restart()
    {
        sounds.reproduce("startGame");
        JOptionPane.showMessageDialog(main.getContentPane(), "A new game is starting! Good luck, you know how it works: there are 100 squares. Go after them like a "
                + "real Knight would do!", "Square Of The Knight", 1);
        lastx=1;lasty=1;
        lightSquares();
        main.shoot(1,1);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    /*
    public void showLasts()
    {
        System.out.println("Y los lasts son: X: "+lastx+", Y: "+lasty);
    }
    */
   
}
