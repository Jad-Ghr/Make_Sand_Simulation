public class Tp2{
    private int width = 10;
    private int height = 10;
    private Tp1[][] grid;

    public void Grid(){
        grid = new Tp1[width][height];
    }
    public void addParticle(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            grid[x][y] = new Tp1(x, y);
    }

    public void printGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(grid[x][y] == null ? "." : "o");
            }
            System.out.println();
        }
    }
}