public class Tp1_5 {
    public static void main(String[] args){
        Tp1 t = new Tp1(4,0);
        for (int i = 0; i < 10; i++) {
            t.fall();
            System.out.println(t);
        }

    }
}