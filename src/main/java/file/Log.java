package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;


public class Log {
	public static void writeLog(File myfile , String event , String description) {
		try{
			FileWriter fw = new FileWriter(myfile , true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			out.println(event + " " + timestamp + " " + description);
			out.close();
		} catch (Exception e) {
			return;
		}
	}
	public static void writeLogHeader(File myfile , String password , String user_name) {
		try{
			FileWriter fw = new FileWriter(myfile , true);
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
	public static void writeLogDeletion(File myfile) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		final int lineNumWhereToInsert = 3;
		final String stringToInsert = "Deleted_AT:" + timestamp;

		try {
			ArrayList<String> lines = new ArrayList<String>();
			Scanner myReader = new Scanner(myfile);
			while(myReader.hasNext())
				lines.add(myReader.nextLine());
			lines.add(lineNumWhereToInsert , stringToInsert);
			myReader.close();
			
			FileWriter writer = new FileWriter(myfile);
			for(String line : lines) {
				writer.write(line);
				writer.write(System.getProperty( "line.separator" ));
			}
			writer.close();
		} 
		catch (Exception e) {
		    System.out.println("Cant write delete_at in file");
		}
	}

	public static File getLogsFile() {
		String current_dir = System.getProperty("user.dir");
		File myfile = new File(FileAssistance.getResources() + "/logs");
		return myfile;
	}
}
