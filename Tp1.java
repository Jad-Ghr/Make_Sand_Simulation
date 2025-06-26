public class Tp1 {
    public  int x ,y;

    public Tp1(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void fall (){
        y++;
    }

    public String toString(){
        return "Particle at (" + x + ", " + y + ")";
    }

    
}

