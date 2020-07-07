package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class Log {
    public static void writeLogHeader(File myfile, String password, String user_name) {
        try {
            FileWriter fw = new FileWriter(myfile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            out.println("Username: " + user_name);
            out.println("CREATED_AT: " + timestamp);
            out.println("PASSWORD: " + password);
            out.close();
        } catch (Exception e) {
            return;
        }
    }
}
