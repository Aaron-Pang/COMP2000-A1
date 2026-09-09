package supplementary;
public class Direction {
    public int dx;
    public int dy;

    public Direction(int dx, int dy) {
        Math.clamp(dx, -20, 20);
        Math.clamp(dy, -20, 20);

        this.dx = dx;
        this.dy = dy;
    }


}
