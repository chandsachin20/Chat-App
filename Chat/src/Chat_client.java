import java.net.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Chat_client implements Runnable{

	Socket sock;
	Scanner input;
	Scanner c_send=new Scanner(System.in);
	PrintWriter out;
	//--------------------------
	public Chat_client(Socket y)
	{
		this.sock=y;
	}
	//---------------------
	public void run()
	{
		try
		{
			try
			{
				input=new Scanner(sock.getInputStream());
				out=new PrintWriter(sock.getOutputStream());
				out.flush();
				CheckStream();
			}
			finally
			{
				sock.close();
			}
		}
		catch(Exception x)
		{
			System.out.print(x);
		}
	}
	//----------------------------------
	
	public void disconnect()throws IOException
	{
		out.println(Chat_client_Gui.username + "has disconnected");
		out.flush();
		sock.close();
		JOptionPane.showMessageDialog(null, "You disconnected");
		System.exit(0);
	}
	//---------------------------------
	public void CheckStream()
	{
		while(true)
		{
			receive();
		}
	}
	//------------------------
	public void receive()
	{
		if(input.hasNext())
		{
			String message=input.nextLine();
			
			if(message.contains("#?!"))
			{
				String temp1=message.substring(3);
				temp1=temp1.replace("[","");
				temp1=temp1.replace("]","");
				
				String[] currrentuserss = temp1.split(", ");
				
				 Chat_client_Gui.jl_online.setListData(currrentuserss);
			}
			else
			{
				Chat_client_Gui.ta_conversation.append(message +"\n");
			}
		}
	}
	//----------------------
	public void send(String x)
	{
		out.println(Chat_client_Gui.username+": " + x);
		out.flush();
		Chat_client_Gui.tf_message.setText("");
	}
	//----------------------------
	

}
