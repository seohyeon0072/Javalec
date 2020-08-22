package Network_Lec3;



import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CrollingEx {
	public static void claseAll(Closeable... c){
		for(Closeable temp : c){
			try{
				temp.close();
			}catch(Exception e){}
		}
	}
	
	public static void main(String[] args){
		URL url = null;
		HttpURLConnection con = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		FileWriter fw = null;
		PrintWriter pw = null;
		try{
			// URI : �ڿ��� �����ϴ� ������
			// URL : ��ġ������� �ڿ��� �����ϴ� �ĺ���
			url = new URL("https://www.daum.net/");
			// HttpURLConnection ����
			con = (HttpURLConnection)url.openConnection();
			int code = con.getResponseCode();
			if(code == HttpURLConnection.HTTP_OK){
				is = con.getInputStream();
				// "UTF-8" ���ڵ� ����
				isr = new InputStreamReader(is, "UTF-8");
				br = new BufferedReader(isr);
				
				fw = new FileWriter("result.html");
				pw = new PrintWriter(fw);
				String line = null;
				
				while((line = br.readLine()) != null){
					pw.println(line); 
				}
				pw.flush();
			}
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		} finally{
			claseAll(br, isr, is, pw, fw);
			try{
				con.disconnect();
			}catch(Exception e){}
		}
	}
}