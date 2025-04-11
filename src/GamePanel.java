import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private PlayerCar playerCar;
    private ArrayList<EnemyCar> enemyCars;
    private Road road;
    private Timer timer;
    private Random random;
    private int score;
    private boolean gameOver;
    private int enemySpawnRate = 100; // lower is faster

    public GamePanel() {
        setPreferredSize(new Dimension(400, 600));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        playerCar = new PlayerCar(175, 450);
        enemyCars = new ArrayList<>();
        road = new Road();
        random = new Random();
        score = 0;
        gameOver = false;

        timer = new Timer(16, this); // ~60 FPS
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        road.draw(g);
        playerCar.draw(g);

        for (EnemyCar enemy : enemyCars) {
            enemy.draw(g);
        }

        // Draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 30);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 80, 300);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Final Score: " + score, 140, 350);
            g.drawString("Press R to restart", 120, 400);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            road.move();
            playerCar.move();

            // Spawn new enemy cars randomly
            if (random.nextInt(enemySpawnRate) == 0) {
                int x = random.nextInt(3) * 100 + 50; // 50, 150, or 250
                enemyCars.add(new EnemyCar(x, -100));
            }

            // Move enemy cars and check for collisions
            for (int i = 0; i < enemyCars.size(); i++) {
                EnemyCar enemy = enemyCars.get(i);
                enemy.move();

                // Check collision with player
                if (playerCar.getBounds().intersects(enemy.getBounds())) {
                    gameOver = true;
                }

                // Remove cars that are off screen
                if (enemy.getY() > 600) {
                    enemyCars.remove(i);
                    i--;
                    score++;

                    // Increase difficulty
                    if (score % 5 == 0 && enemySpawnRate > 30) {
                        enemySpawnRate -= 5;
                    }
                }
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (!gameOver) {
            if (key == KeyEvent.VK_LEFT) {
                playerCar.setLeft(true);
            }
            if (key == KeyEvent.VK_RIGHT) {
                playerCar.setRight(true);
            }
        } else {
            if (key == KeyEvent.VK_R) {
                // Restart game
                playerCar = new PlayerCar(175, 450);
                enemyCars.clear();
                score = 0;
                gameOver = false;
                enemySpawnRate = 100;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            playerCar.setLeft(false);
        }
        if (key == KeyEvent.VK_RIGHT) {
            playerCar.setRight(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}