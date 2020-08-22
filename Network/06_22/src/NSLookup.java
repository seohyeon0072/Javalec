import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class NSLookup {

	public static void main(String[] args) {
		String domain = JOptionPane.showInputDialog("도메인을 입력하시오");
		InetAddress inetaddr[] =null; 
		try {
			inetaddr = InetAddress.getAllByName(domain);
	} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		for(int i=0;i<inetaddr.length;i++){
			System.out.println(inetaddr[i].getHostName());
			System.out.println(inetaddr[i].getHostAddress());
			System.out.println(inetaddr[i].toString());
			System.out.println("-------------------------");
		}
	}

}
