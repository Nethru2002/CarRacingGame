import java.awt.*;

public class Road {
    private int y1, y2;
    private int speed;

    public Road() {
        y1 = 0;
        y2 = -600;
        speed = 5;
    }

    public void draw(Graphics g) {
        // Draw road background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 400, 600);

        // Draw road markings
        g.setColor(Color.YELLOW);
        for (int i = y1; i < 600; i += 120) {
            g.fillRect(195, i, 10, 60);
        }
        for (int i = y2; i < 600; i += 120) {
            g.fillRect(195, i, 10, 60);
        }

        // Draw road borders
        g.setColor(Color.WHITE);
        g.fillRect(50, 0, 10, 600);
        g.fillRect(340, 0, 10, 600);
    }

    public void move() {
        y1 += speed;
        y2 += speed;

        if (y1 > 600) {
            y1 = y2 - 600;
        }
        if (y2 > 600) {
            y2 = y1 - 600;
        }
    }
}