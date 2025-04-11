import java.awt.*;

public class EnemyCar {
    private int x, y;
    private int width = 50, height = 100;
    private int speed;

    public EnemyCar(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = (int)(Math.random() * 5) + 3; // Random speed between 3-7
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);

        // Add some details to make it look like a car
        g.setColor(Color.PINK);
        g.fillRect(x + 5, y + 10, width - 10, height / 3);
        g.fillRect(x + 5, y + height / 2, width - 10, height / 3);
    }

    public void move() {
        y += speed;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getY() {
        return y;
    }
}