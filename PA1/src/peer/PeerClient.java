package peer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PeerClient {

	public static void main(String[] args) {
		String url = "rmi://localhost/filemanager";
		try {
			FileManagerInterface fmi = (FileManagerInterface) Naming.lookup(url);
			byte[] filedata = fmi.downloadFile("/Users/vivekbajpai/personal/workspace/peer/src/test.txt");
			File file = new File("testrec.txt");
	         BufferedOutputStream output = new
	           BufferedOutputStream(new FileOutputStream(file.getName()));
	         output.write(filedata,0,filedata.length);
	         output.flush();
	         output.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
