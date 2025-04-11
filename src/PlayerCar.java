import java.awt.*;

public class PlayerCar {
    private int x, y;
    private int width = 50, height = 100;
    private boolean left, right;
    private int speed = 5;

    public PlayerCar(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);

        // Add some details to make it look like a car
        g.setColor(Color.CYAN);
        g.fillRect(x + 5, y + 10, width - 10, height / 3);
        g.fillRect(x + 5, y + height / 2, width - 10, height / 3);
    }

    public void move() {
        if (left && x > 50) {
            x -= speed;
        }
        if (right && x < 300) {
            x += speed;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}