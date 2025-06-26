import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class SandSimulation extends JPanel implements MouseMotionListener {
    private final int width, height;
    private final int[][] sand; 
    private final int pixelSize = 2;
    private final Timer timer;
    private final Random rand = new Random();

    private final Color[] rainbow = {
        new Color(255, 200, 100), 
        new Color(255, 220, 120), 
        new Color(255, 180, 80),  
        new Color(255, 160, 60)   
    };

    public SandSimulation(int width, int height) {
        this.width = width / pixelSize;
        this.height = height / pixelSize;
        this.sand = new int[this.width][this.height];
        this.setPreferredSize(new Dimension(width, height));
        this.addMouseMotionListener(this);

        timer = new Timer(16, e -> {
            updateSand();
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int colorIdx = sand[x][y];
                if (colorIdx > 0) {
                    g.setColor(rainbow[(colorIdx - 1) % rainbow.length]);
                    g.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
                }
            }
        }
    }

    public void dropSandPile(int mouseX, int mouseY) {
        int pileSize = 3 + rand.nextInt(3); 
        int gridX = mouseX / pixelSize;
        int gridY = mouseY / pixelSize;
        int colorIdx = 1 + rand.nextInt(rainbow.length);
        for (int dx = 0; dx < pileSize; dx++) {
            for (int dy = 0; dy < pileSize; dy++) {
                int x = gridX + dx - pileSize / 2;
                int y = gridY + dy - pileSize / 2;
                if (x >= 0 && y >= 0 && x < width && y < height) {
                    sand[x][y] = colorIdx;
                }
            }
        }
    }

    private void updateSand() {
        for (int y = height - 2; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                if (sand[x][y] > 0) {
                    if (sand[x][y + 1] == 0) {
                        sand[x][y + 1] = sand[x][y];
                        sand[x][y] = 0;
                    } else {
                        boolean moved = false;
                        int dir = rand.nextBoolean() ? -1 : 1;
                        for (int d = 0; d < 2; d++, dir = -dir) {
                            int nx = x + dir;
                            if (nx >= 0 && nx < width && sand[nx][y + 1] == 0) {
                                sand[nx][y + 1] = sand[x][y];
                                sand[x][y] = 0;
                                moved = true;
                                break;
                            }
                        }
                        
                    }
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dropSandPile(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sand Simulation");
        SandSimulation panel = new SandSimulation(800, 600);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
