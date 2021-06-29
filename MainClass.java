import java.util.Scanner;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class MainClass {
	SerialPort activePort;
	SerialPort[] ports = SerialPort.getCommPorts();

	public void showAllPort() {
		int i = 0;
		for(SerialPort port : ports) {
			System.out.print(i + ". " + port.getDescriptivePortName() + " ");
			i++;
			}	
		}
	
	public void setPort() {
		activePort = SerialPort.getCommPort("COM6");
		if (activePort.openPort())
			System.out.println("\n"+activePort.getSystemPortName() + " opened.");
		activePort.addDataListener(new SerialPortDataListener() {

			@Override
			public void serialEvent(SerialPortEvent event) {
				int size = event.getSerialPort().bytesAvailable();
				byte[] buffer = new byte[size];
				event.getSerialPort().readBytes(buffer, size);
				for(byte b:buffer)
					System.out.print((char)b);
			}

			@Override
			public int getListeningEvents() { 
				return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;  
			}
			});
		}
	
	public void start() {
		showAllPort();
		setPort();
		}
	
	public static void main(String[] args) {
		MainClass mainClass = new MainClass();
		mainClass.start();
		}
	
	}
	//The following commands are to be executed in directory where MainClass.java and jSerialComm-1.3.11.jar are located
	//"C:\Program Files\Java\jdk1.8.0_221\bin\javac.exe" -cp ".;jSerialComm-1.3.11.jar" MainClass.java
	//"C:\Program Files\Java\jdk1.8.0_221\bin\java.exe" -cp ".;jSerialComm-1.3.11.jar" MainClass
	
