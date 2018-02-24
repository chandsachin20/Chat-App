
import java.io.*;
import java.net.*;
public class Soc1_client {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Soc1_client server=new Soc1_client();
		server.run();
	}
//-------------------------------------------------
	public void run() throws Exception
	{
		Socket soc=new Socket("localhost",444);
		PrintStream ps=new PrintStream(soc.getOutputStream());
		ps.println("hello to server from client");
		
		InputStreamReader isr=new InputStreamReader(soc.getInputStream());
		BufferedReader br=new BufferedReader(isr);
		
		String message=br.readLine();
		System.out.println(message);
		
	}

}
