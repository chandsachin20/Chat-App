import java.io.*;
import java.net.*;
public class Sdk_server1 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Sdk_server1 server=new Sdk_server1();
		server.run();
	}
//-------------------------------------------------
	public void run() throws Exception
	{
		ServerSocket svrskt =new ServerSocket(444);
		Socket sock=svrskt.accept();
		InputStreamReader isr=new InputStreamReader(sock.getInputStream());
		BufferedReader br=new BufferedReader(isr);
		
		String message=br.readLine();
		System.out.println(message);
		
		if(message != null)
		{
			PrintStream ps=new PrintStream(sock.getOutputStream());
			ps.println("Message received");
		}
	}
//-------------------------------------------------
}
