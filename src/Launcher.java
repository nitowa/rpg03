import java.net.URL;

public class Launcher {

    public static void main(String[] args) {

        for(String s : System.getProperty("java.class.path").split(";")){
            System.out.println(s);
        }
        System.out.println("");

        URL url = Launcher.class.getResource("cat.jpg");
        System.out.println(url);
    }
}
