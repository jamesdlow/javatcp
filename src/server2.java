import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class server2 extends JFrame
{
	ServerListener sl = new ServerListener();
	
	server2()
	{
		super("server");
		JPanel panel = new JPanel();
	  	
		JButton button1 = new JButton("Start");	
		button1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				start();
			}
		});
		
		JButton button2 = new JButton("Stop");	
		button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				stop();
			}
		});
		
		panel.add(button1);
		panel.add(button2);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		setSize(640, 480);
	}
	
	public void start()
	{
		sl.start();
	}
	
	public void stop()
	{
		sl.stop();
	}
	
	public static void main (String[] Args)
	{
		JFrame frame = new server2();
		frame.setVisible(true);
	}
}
