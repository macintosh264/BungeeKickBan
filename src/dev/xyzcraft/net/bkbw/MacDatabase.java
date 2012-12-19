package dev.xyzcraft.net.bkbw;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.*;
public abstract class MacDatabase {
	final static String pluginname = "bkwb";
	private final static String pluginFolder = "./plugins/";
	public String currentData = null;
	private JSONObject db = null;
	public MacDatabase() {
		db = defaultDb();
		reload();
	}
	abstract JSONObject defaultDb();
	abstract String name();
	public void reload() {
		File file = new File( pluginFolder+pluginname+"/"+name());
	    boolean existed = file.exists();
		if (!existed) {
			(new File(pluginFolder+pluginname)).mkdirs();
	    	  try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  currentData = db.toString();
	    	  this.save();
	      }
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentData = "";
		Scanner scanner = new Scanner(fr);

		while(scanner.hasNext()) {
		    currentData = currentData + scanner.next() + " ";
		}
		scanner.close();
		try {
			this.db = new JSONObject(currentData);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void save() {
		File file = new File(pluginFolder+pluginname+"/"+name());
	     FileWriter writer = null;
		try {
			writer = new FileWriter(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		currentData = this.db.toString();
	      // Writes the content to the file
	      try {
			writer.write(currentData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	      try {
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	JSONObject databaseVar() {
		return db;
	}
}
