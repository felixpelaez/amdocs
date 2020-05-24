package exercise04;

import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadFile {
	private static final long MAP_SIZE = 5242880;
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String sendBBDD(String paramVoices) {
		String result="";
		try {
			System.out.println(paramVoices.toString());
			SendBBDD sendData = new SendBBDD();
			String[] voices= paramVoices.split("\n");
			for (String voice : voices) {
				if (voice.contains("}")) {
					sendData.addVoice(new Voice(voice));
				}else {
					result=voice;
				}
			}
			new Thread(sendData).start();
		}catch(Exception ee) {
			ee.printStackTrace();
		}
		return result;
	}
	
	public static String readFile(String paramPath) {		
		try {
			CharBuffer charBuffer = null;
			MappedByteBuffer mappedByteBuffer = null;
			String charEncoding = System.getProperty("file.encoding");
            Path pathRead =FileSystems.getDefault().getPath(paramPath);
            if (Files.exists(pathRead, new LinkOption[]{LinkOption.NOFOLLOW_LINKS})) {
            	try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(pathRead, EnumSet.of(StandardOpenOption.READ))) {
                    long position=0;
                    long maxposition=fileChannel.size();
                    long nextposition;
                    long remaining;
                    long bytestomap;
                    String rest="";
                    while (position < maxposition) {
                    	nextposition = position + MAP_SIZE;
                    	if (nextposition > maxposition) {
                    		nextposition=maxposition; 
                    	}
                    	remaining = maxposition - position;
                        bytestomap = (long) Math.min(MAP_SIZE, remaining);
                 		mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, position, bytestomap);
	                    if (mappedByteBuffer != null) {
	                        charBuffer = Charset.forName(charEncoding).decode(mappedByteBuffer);
	                        rest = sendBBDD(rest + charBuffer.toString());	                        	                       
	                    }
	                    position = nextposition;
	                    maxposition=fileChannel.size();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
		}catch(Exception ee) {
			ee.printStackTrace();
		}
		return "";
	}
	
	public static void main(String[] args) {
		readFile("C:\\tmp\\large_file.txt");
	}

}
