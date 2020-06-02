package practise_java.day01;

import org.apache.hadoop.io.Text;

public class bufferTest {
    public static void main(String[] args) {
        Text a = new Text();
        a.set("P0001");
        a.set("P0002");
        System.out.println(a.toString());

    }
}
