package supplementary;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import placed_objects.Ground;

public class LightningEvent implements MouseListener{
    Ground ground;
    public LightningEvent(Ground ground) {
        this.ground = ground;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override 
    public void mouseExited(MouseEvent e) {

    }

    @Override 
    public void mouseReleased(MouseEvent e) {

    }

    @Override 
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked");
        Lightning.categoriseGrowables(ground, e.getPoint());
    }

    @Override 
    public void mouseEntered(MouseEvent e) {

    }
}
