import java.util.*;

public class Tp0 {

    public static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
            
            System.out.println("================================");
            
            for(int i=0;i<3;i++){
                String s1=sc.next();
                int x=sc.nextInt();
                
                while(s1.length()!=15){
                    s1+=" ";
                }
                System.out.print(s1);
                String ch = x+"";
                while(ch.length() != 3){
                    ch="0"+ch;
                }
                System.out.println(ch);
                
            }
            System.out.println("================================");

    }
}
