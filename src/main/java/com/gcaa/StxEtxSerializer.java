package com.gcaa;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

/*import org.springframework.integration.ip.tcp.serializer.AbstractByteArraySerializer;
import org.springframework.integration.ip.tcp.serializer.SoftEndOfStreamException;
import org.springframework.integration.mapping.MessageMappingException;*/

public class StxEtxSerializer/* extends AbstractByteArraySerializer */{

	public static final int STX = 0x02;

	public static final int ETX = 0x03;

	protected int maxMessageSize = 2048;

	private static final Logger logger = Logger.getLogger(StxEtxSerializer.class);
	
	/**
	 * Reads the data in the inputStream to a byte[]. Data must be prefixed
	 * with an ASCII STX character, and terminated with an ASCII ETX character.
	 * Throws a {@link SoftEndOfStreamException} if the stream
	 * is closed immediately before the STX (i.e. no data is in the process of
	 * being read).
	 *
	 */
	/*@Override*/
	public byte[] deserialize(InputStream inputStream) throws IOException {
		int bite = inputStream.read();
		if (bite < 0) {
			throw new /*SoftEndOfStream*/IOException("Stream closed between payloads");
		}
		byte[] buffer = null;
		int n = 0;
		try {
			if (bite != STX) {
				throw new /*MessageMapping*/IOException("Expected STX to begin message");
			}
			buffer = new byte[this.maxMessageSize];
			while ((bite = inputStream.read()) != ETX) {
				checkClosure(bite);
				buffer[n++] = (byte) bite;
				if (n >= this.maxMessageSize) {
					throw new IOException("ETX not found before max message length: "
							+ this.maxMessageSize);
				}
			}
			byte[] assembledData = new byte[n];
			System.arraycopy(buffer, 0, assembledData, 0, n);
			return assembledData;
		}
		catch (IOException e) {
//			publishEvent(e, buffer, n);
			throw e;
		}
		catch (RuntimeException e) {
//			publishEvent(e, buffer, n);
			throw e;
		}
	}

	/**
	 * Writes the byte[] to the stream, prefixed by an ASCII STX character and
	 * terminated with an ASCII ETX character.
	 */
	/*@Override*/
	public void serialize(byte[] bytes, OutputStream outputStream) throws IOException {
		outputStream.write(STX);
		outputStream.write(bytes);
		outputStream.write(ETX);
	}

	protected void checkClosure(int bite) throws IOException {
		if (bite < 0) {
			logger.debug("Socket closed during message assembly");
			throw new IOException("Socket closed during message assembly");
		}
	}

	
}
