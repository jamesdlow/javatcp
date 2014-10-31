import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends JFrame implements SocketEventListener
{
	private ServerSocketConnection s;
	
	Server()
	{
		super("server");
		JPanel panel = new JPanel();
		
		try
		{
			s = new ServerSocketConnection(1234);
			s.setEventListener(this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	  	
		JButton button1 = new JButton("Open");	
		button1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				s.open();
			}
		});
		
		JButton button2 = new JButton("Close");	
		button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				s.close();
			}
		});
		
		panel.add(button1);
		panel.add(button2);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		setSize(640, 480);
	}
	
	public void onConnect(SocketEvent e)
	{
		System.out.println("Someone Connected");
	}
	public void onDisconnect(SocketEvent e)
	{
		System.out.println("Someone Disconnected");
	}
	public void onReceiveData(SocketEvent e)
	{
		
	}
	
	public static void main (String[] Args)
	{
		JFrame frame = new Server();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
