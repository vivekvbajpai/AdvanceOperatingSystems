package peer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Timer;

public class PeerServer {

	public static void main(String[] args) {
		try {
			FileManagerImpl fileman = new FileManagerImpl();
			Naming.rebind("filemanager",fileman);
			
			
			//schedule a periodic task which runs in every 10 seconds to scan
			//the shared directory and register/unregister files with index server
			Timer time = new Timer(); // Instantiate Timer Object
			DirectoryScanner st = new DirectoryScanner(); // Instantiate SheduledTask class
			time.schedule(st, 0, 10000); // Create Repetitively task for every 1 secs
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
