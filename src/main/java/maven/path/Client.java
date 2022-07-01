package maven.path;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Client extends Methods {

    static Methods exec = new Methods();

    public static void main(String[] args) throws UnknownHostException, IOException {

        Socket sock = new Socket("68.183.239.26", 80);
        System.out.println("Connected");

        // Output then Input
        OutputStream os = sock.getOutputStream();
        //DataOutputStream dos = new DataOutputStream(os);
        ObjectOutputStream oos = new ObjectOutputStream(os);

        InputStream is = sock.getInputStream();
        //DataInputStream dis = new DataInputStream(is);
        ObjectInputStream ois = new ObjectInputStream(is);

        String response = ois.readUTF();
        // assigning the responses to array of Strings
        //response 0 = id the rest =numbers to be averaged
        String[] resp = response.split(",");
        String id = resp[0];
        String fg1 = resp[1];
        int figure1 = Integer.parseInt(resp[1]);
        String fg2 = resp[2];
        int figure2 = Integer.parseInt(resp[2]);
        String fg3 = resp[3];
        int figure3 = Integer.parseInt(resp[3]);
        String fg4 = resp[4];
        int figure4 = Integer.parseInt(resp[4]);
        String fg5 = resp[5];
        int figure5 = Integer.parseInt(resp[5]);
        //to hold the figures
        List<Integer> total = new ArrayList<Integer>();
        //creating a list of int to average
        total.add(figure1);
        total.add(figure2);
        total.add(figure3);
        total.add(figure4);
        total.add(figure5);

        float calculations = exec.avg(total);

        // creating the output
        oos.writeUTF(id);
        oos.writeUTF("Nawaz Kareem Sudhakaran");
        oos.writeUTF("nawaz.shss@gmail.com");
        oos.writeFloat(calculations);

        //
        while (ois.available() > 0) {
            String input = ois.readUTF();
            boolean accuracy = ois.readBoolean();
            if (accuracy = true) {
                System.out.println("SUCCESSFUL");
                sock.close();
            } else if (accuracy = false) {
                System.out.println("FAILURE");
                ois.readUTF();
                sock.close();
            }

        }
    }

}
