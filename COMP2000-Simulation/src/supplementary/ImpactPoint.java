package supplementary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.time.Instant;
import javax.swing.JPanel;
import placed_objects.Ground;

public class ImpactPoint extends JPanel {
    Point position;
    long startTime;
    public static int lifespan = 5000;
    Ground ground;

    ImpactPoint(Point position, Ground ground) {
        this.position = position;
        startTime = Instant.now().toEpochMilli();
        this.ground = ground;
        ground.addImpact(this);
        this.setBounds(position.x-50, position.y-50, 100, 100);
    }

    public void tick() {
        repaint();
        revalidate();
    }

    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //System.out.println(Instant.now().toEpochMilli() > startTime + lifespan);
        if(Instant.now().toEpochMilli() > startTime + lifespan) {
            ground.remove(this);
        } 
        g.setColor(new Color(255, 0 , 0));
        g.fillOval(0, 0, 100, 100);
    }
}
