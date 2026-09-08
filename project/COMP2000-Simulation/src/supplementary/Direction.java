package supplementary;
public class Direction {
    int dx;
    int dy;

    Direction(int dx, int dy) {
        Math.clamp(dx, -20, 20);
        Math.clamp(dy, -20, 20);

        this.dx = dx;
        this.dy = dy;
    }


}
