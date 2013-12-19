package cn.com.sinosoft.util.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataHandler;

public class DataHandlerUtils {

	private static final int INITIAL_SIZE = 1024 * 1024;
	private static final int BUFFER_SIZE = 1024;

	public static byte[] toBytes(DataHandler dataHandler) throws IOException {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream(INITIAL_SIZE);
	    InputStream in = dataHandler.getInputStream();
	    byte[] buffer = new byte[BUFFER_SIZE];
	    int bytesRead;
	    while ( (bytesRead = in.read(buffer)) >= 0 ) {
	        bos.write(buffer, 0, bytesRead);
	    }
	    return bos.toByteArray();
	}
}
