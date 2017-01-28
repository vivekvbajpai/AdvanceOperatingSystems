package peer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TimerTask;

public class DirectoryScanner extends TimerTask {
	private Set<String> cachedFileList;
	private String peerId;
	
	public DirectoryScanner() {
		this.cachedFileList = new HashSet<>();
		
		//load the peer id from properties file
		Properties prop = new Properties();
		try {
			InputStream input = new FileInputStream("config.properties");
			// load a properties file
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.peerId = prop.getProperty("peer_id");
	}
	
	@Override
	public void run() {
		File sharedDir = new File("./shared_dir");
		Set<String> currentFiles = new HashSet<>();
		for(File filename: sharedDir.listFiles()){
			currentFiles.add(filename.getName());
		}
		
		//check the files which has been newly added to shared directory
		List<String> newAddedFiles = new ArrayList<>(currentFiles);
		newAddedFiles.removeAll(cachedFileList);
		
		//check the files which has been deleted from the shared directory
		List<String> deletedFiles = new ArrayList<>(cachedFileList);
		deletedFiles.removeAll(currentFiles);
		
		//register the newly added files
		cachedFileList.addAll(newAddedFiles);
		
		
		//Unregister the files deleted from the shared directory
		cachedFileList.removeAll(deletedFiles);
		
	}

}
