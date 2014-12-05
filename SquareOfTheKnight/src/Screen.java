


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Screen extends JPanel{
    private static Screen instance;
    private final int offset=50;
    private final int tamanyo=50;
    private final int lineWidth=5;
    
    private Screen()
    {
        setVisible(true);
        setFocusable(true);
    }
    
    public static Screen getInstance()
    {
        if(instance==null) instance=new Screen();
        return instance;
    }
    
    @Override
    public void update(Graphics g)
    {
        paintComponent(g);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(offset, offset, lineWidth, tamanyo*10);
        g.fillRect(offset, offset, tamanyo*10, lineWidth);
        g.setColor(Color.DARK_GRAY);
        for(int i=2;i<11;++i)
        {
            g.fillRect(tamanyo*i, tamanyo+5, lineWidth, tamanyo*10);
            g.fillRect(tamanyo+5, tamanyo*i, tamanyo*10, lineWidth);
        }
        g.setColor(Color.black);
        g.fillRect(tamanyo*11, tamanyo, lineWidth, tamanyo*10);
        g.fillRect(tamanyo, tamanyo*11, tamanyo*10, lineWidth);
        g.fillRect(tamanyo*11, tamanyo*11, lineWidth, lineWidth);
    }
}
