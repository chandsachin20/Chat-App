import  javax.swing.*;
import java.io.PrintWriter;
import java.net.*;

public class Chat_client_Gui  {

	private static Chat_client chat_client;
	public static String username = "Anonymous";
	public static TCPDataClient ob;
	
	public  static JFrame Mainwindow = new JFrame();
	private static JButton about = new JButton();
	private static JButton connect = new JButton();
	private static JButton disconnect = new JButton();
	private static JButton file_send = new JButton();
	private static JButton send = new JButton();
	private static JLabel message = new JLabel("Message: ");
	public  static JTextField tf_message = new JTextField(20);
	private static JLabel conversation = new JLabel();
	public  static JTextArea ta_conversation = new JTextArea();
	private static JScrollPane sp_conversation = new JScrollPane();
	private static JLabel online = new JLabel();
	public  static JList jl_online = new JList();
	private static JScrollPane sp_online = new JScrollPane();
	private static JLabel loggedInAs = new JLabel();
	private static JLabel loggedInAsBox = new JLabel();
	
	public static JFrame loginWindow = new JFrame();
	public static JTextField UserNameBox = new JTextField(20);
	private static JButton Enter = new JButton("Enter");
	private static JLabel EnterUserName = new JLabel("Enter Username: ");
	private static JPanel login = new JPanel();
	//-----------------
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			BuildMainWindow();
			Initialise();
	}

	
	public static void connect()
	{
		try
		{	
			final int port = 5000;
			final String host = "127.0.0.1";//can also be ip adddress
			Socket sock = new Socket(host,port);
			System.out.println("You connected to : "+ host);
			
			chat_client =  new Chat_client(sock);
			
			PrintWriter out=new PrintWriter(sock.getOutputStream());
			out.println(username);
			out.flush();
			
			Thread x = new Thread(chat_client);
			x.start();
		}
		catch(Exception x)
		{
			System.out.print(x);
			JOptionPane.showMessageDialog(null,"server not responding");
			System.exit(0);
		}
	}
	//------------------------------------
	public static  void Initialise()
	{
		send.setEnabled(false);
		disconnect.setEnabled(false);
		connect.setEnabled(true);
		file_send.setEnabled(false);
	}
	//-----------------------------------
	public static void BuildLogInWindow()
	{
		loginWindow.setTitle("What's your name?");
		loginWindow.setSize(400,100);
		loginWindow.setLocation(250,250);
		loginWindow.setResizable(false);
		login= new JPanel();
		login.add(EnterUserName);
		login.add(UserNameBox);
		login.add(Enter);
		loginWindow.add(login);
		
		Login_action();
		loginWindow.setVisible(true);
	}
	
	//--------------------------------
		public static void  BuildMainWindow()
		{
			Mainwindow.setTitle(username + " 's chat Box: ");
			Mainwindow.setSize(450,500);
			Mainwindow.setLocation(220,180);
			Mainwindow.setResizable(false);
			ConfigureMainWindow();
			Mainwindow_action();
			Mainwindow.setVisible(true);
		}
	//----------------------------------	
		public static void 	ConfigureMainWindow()
		{	
			Mainwindow.setBackground(new java.awt.Color(255,255,255));
			Mainwindow.setSize(500,320);
			Mainwindow.getContentPane().setLayout(null);
			
			send.setBackground(new java.awt.Color(0,0,255));
			send.setForeground(new java.awt.Color(255,255,255));
			send.setText("SEND");
			Mainwindow.getContentPane().add(send);
			send.setBounds(250 , 40 , 81 , 25 );
			
			disconnect.setBackground(new java.awt.Color(255,0,0));
			disconnect.setForeground(new java.awt.Color(255,255,255));
			disconnect.setText("DISCONNECT");
			Mainwindow.getContentPane().add(disconnect);
			disconnect.setBounds(10,40,110,25);
			
			connect.setBackground(new java.awt.Color(0,252,0));
			connect.setForeground(new java.awt.Color(255,255,255));
			connect.setText("CONNECT");
			connect.setToolTipText("");
			Mainwindow.getContentPane().add(connect);
			connect.setBounds(130,40,110,25);
			
			
			file_send.setBackground(new java.awt.Color(0,0,255));
			file_send.setForeground(new java.awt.Color(255,255,255));
			file_send.setText("FILES");
			Mainwindow.getContentPane().add(file_send);
			file_send.setBounds(340,40,75,25);
			
			about.setBackground(new java.awt.Color(0,0,255));
			about.setForeground(new java.awt.Color(255,255,255));
			about.setText("ABOUT");
			Mainwindow.getContentPane().add(about);
			about.setBounds(420,40,70,25);//
			
			message.setText("MESSAGE: ");
			Mainwindow.getContentPane().add(message);
			message.setBounds(10,10,60,20);
			
			tf_message.setForeground(new java.awt.Color(0,0,255));
			tf_message.requestFocus();
			Mainwindow.getContentPane().add(tf_message);
			tf_message.setBounds(70 , 4 , 260 ,30);
			
			conversation.setHorizontalAlignment(SwingConstants.CENTER);
			conversation.setText("Conversation");
			Mainwindow.getContentPane().add(conversation);
			conversation.setBounds(100 , 70 , 140 ,16);
			
			ta_conversation.setColumns(20);
			ta_conversation.setFont(new java.awt.Font("Tahoma", 0, 12));
			ta_conversation.setForeground(new java.awt.Color(0,0,255));
			ta_conversation.setLineWrap(true);
			ta_conversation.setRows(5);
			ta_conversation.setEditable(false);
			
			sp_conversation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			sp_conversation.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			sp_conversation.setViewportView(ta_conversation);
			Mainwindow.getContentPane().add(sp_conversation);
			sp_conversation.setBounds(10,90,330,180);
			
			online.setHorizontalAlignment(SwingConstants.CENTER);
			online.setText("Currently Online");
			online.setToolTipText("");
			Mainwindow.getContentPane().add(online);
			online.setBounds(350,70,130,16);
			
			String []TestNames= {"",""," "," "};
			jl_online.setForeground(new java.awt.Color(0,0,153));
			jl_online.setListData(TestNames);
			
			
			sp_online.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			sp_online.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			sp_online.setViewportView(jl_online);
			Mainwindow.getContentPane().add(sp_online);
			sp_online.setBounds(350,90,130,180 );
			
			loggedInAs.setFont(new java.awt.Font("Tahoma",0,12));
			loggedInAs.setText("Currently logged In AS");
			Mainwindow.getContentPane().add(loggedInAs);
			loggedInAs.setBounds(348,0,140,15);
			
			loggedInAsBox.setHorizontalAlignment(SwingConstants.CENTER);
			loggedInAsBox.setFont(new java.awt.Font("Tahoma",0,12));
			loggedInAsBox.setForeground(new java.awt.Color(255,0,0));
			loggedInAsBox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
			Mainwindow.getContentPane().add(loggedInAsBox);
			loggedInAsBox.setBounds(340,17,150,20);
			
		}
		
		//---------------------------------------
		public static void Login_action()
		{
			Enter.addActionListener(
					new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent evt)
						{	action_enter();
						}
					}
					);
		}
				
		//--------------------------
		public static void action_enter()
		{
			if(!UserNameBox.getText().equals(""))
			{
				username=UserNameBox.getText().trim();
				loggedInAsBox.setText(username);
				Chat_server.Currentusers.add(username);
				Mainwindow.setTitle(username+"'s chat box");
				loginWindow.setVisible(false);
				send.setEnabled(true);
				file_send.setEnabled(true);
				disconnect.setEnabled(true);
				connect.setEnabled(false);
				connect();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please enter a name");
				
			}
		}
		//---------------------------
		public static void Mainwindow_action()
		{
			send.addActionListener(
					new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent evt)
						{	Action_send();
						}
						}
					);
			
			disconnect.addActionListener(
					new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent evt)
						{	Action_disconnect();
						}
					});
			
			connect.addActionListener(
					new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent evt)
						{	 BuildLogInWindow();
						}
					});
			
			file_send.addActionListener(
					new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent evt)
						{	// Action_file_send2();
							 ob = new TCPDataClient();
						}
					});
			
			about.addActionListener(
					new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent evt)
						{	 Action_descp();
						}
					});
			
		}
		//------------------------------------
		
		public static void Action_send()
		{
			if(!tf_message.getText().equals(""))
			{
				chat_client.send(tf_message.getText());
				tf_message.requestFocus();
			}
		}
		//----------------------------------------
		public static void Action_disconnect()
		{
			try
			{
				chat_client.disconnect();
			}
			catch(Exception y)
			{
				y.printStackTrace();
			}
		}
		
		/*public static void Action_file_send2()
		{
			
			//TCPDataServer ob=new TCPDataServer();
			 ob = new TCPDataClient();
		//	ob.setVisible(true);
		}*/
		//---------------------------------------------
		public static void Action_descp()
		{
			 JOptionPane.showMessageDialog(null, "Mutil-client Chat Program\n Sachin chand ");
		}
		
		//---------------------------------------------
}