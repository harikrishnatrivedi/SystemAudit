package org.systemaudit.runmain;

import java.net.InetAddress;

public class TestSystemObject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("System.getProperties()"+System.getProperties());
		System.out.println("System.getProperty(user.name)"+System.getProperty("user.name"));
		System.out.println("System.getProperty(os.name)"+System.getProperty("os.name"));
		System.out.println("System.getProperty(sun.cpu.isalist)"+System.getProperty("sun.cpu.isalist"));
		
		try {
			System.out.println("InetAddress.getLocalHost()"+InetAddress.getLocalHost().toString());
			System.out.println("InetAddress.getLocalHost()"+InetAddress.getLocalHost().getHostName());
		}catch(Exception e){
			e.printStackTrace();
		}
			
	
	}

}
