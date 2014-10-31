import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class client2 extends JFrame
{
	client2()
	{
		super("server");
		JPanel panel = new JPanel();
	  	
		JButton button = new JButton("Connect");
		
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				start();
			}
		});
		
		panel.add(button);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		setSize(640, 480);
	}
	
	public void start()
	{
		try
		{
			Socket skt = new Socket("localhost", 1234);
			System.out.println("Local Port: " + skt.getLocalPort());	//binds to any avaliable port, cool!
			System.out.println("Keep Alive: " + skt.getKeepAlive());
			System.out.println("No Delay: " + skt.getTcpNoDelay());
			
			BufferedReader in = new BufferedReader(new
			InputStreamReader(skt.getInputStream()));
			System.out.println("Received string: '");
			
			
			
			while (!in.ready()) {}
			System.out.println(in.readLine()); // Read one line and output it
			
			System.out.print("'\n");
			in.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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

		
		JFrame frame = new client2();
		frame.setVisible(true);
	}
}
