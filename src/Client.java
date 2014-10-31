import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame implements SocketEventListener
{
	private SocketConnection s;
	
	Client()
	{
		super("server");
		JPanel panel = new JPanel();
		s = new SocketConnection("localhost",1234);
		s.setEventListener(this);
	  	
		JButton button1 = new JButton("Connect");
		button1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					s.connect();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		panel.add(button1);
		
		JButton button2 = new JButton("Disconnect");
		button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					s.disconnect();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		panel.add(button2);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		setSize(640, 480);
	}
	
	public void onConnect(SocketEvent e)
	{
		System.out.println("I Connected");
	}
	public void onDisconnect(SocketEvent e)
	{
		System.out.println("I Disconnected");
	}
	public void onReceiveData(SocketEvent e)
	{
		
	}
	
	public static void main (String[] Args)
	{
		String keys [] =
		{
			"java.vendor", "java.vendor.url",
			"java.version", "java.class.version",
			"os.name", "os.arch", "os.version",
			"file.separator", "path.separator",
			"line.separator", "browser",
			"browser.vendor", "browser.version"
		};

		for (int i = 0; i < keys.length; i++)
		{
			try
			{
				String key = keys[i];
				String value = System.getProperty (key);
				System.out.println("Key :" + key + ": = :" + value + ":\n");
			}
			catch (SecurityException see) { ; }
		}

		
		JFrame frame = new Client();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
