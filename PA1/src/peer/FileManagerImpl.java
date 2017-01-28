package peer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FileManagerImpl extends UnicastRemoteObject implements FileManagerInterface{

	protected FileManagerImpl() throws RemoteException {
		super();
	}

	@Override
	public byte[] downloadFile(String fileName) throws RemoteException {
		try {
	         File file = new File(fileName);
	         byte buffer[] = new byte[(int)file.length()];
	         BufferedInputStream input = new
	      BufferedInputStream(new FileInputStream(fileName));
	         input.read(buffer,0,buffer.length);
	         input.close();
	         return(buffer);
	      } catch(Exception e){
	         e.printStackTrace();
	         return(null);
	      }
	}


}
