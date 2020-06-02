package practise_java.day22;

import org.junit.Test;

import java.io.*;

public class DataStream {
    private File file = new File("E:/data/a.txt");

    @Test
    public void run() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        Boolean b = dis.readBoolean();
        long l = dis.readLong();
        Float f = dis.readFloat();
        System.out.println(b);
        System.out.println(l);
        System.out.println(f);
        dis.close();
    }

    @Test
    public void write() throws IOException {
        File outFile = new File("E:/data/b.txt");
        if (!outFile.exists()) {
            System.out.println(outFile.createNewFile());
        }
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(outFile));
//        dos.writeBoolean(true);
//        dos.writeLong(12345L);
//        dos.writeInt(1);
        dos.writeUTF("asn网");
        dos.close();
    }
}








