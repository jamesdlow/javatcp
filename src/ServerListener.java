import java.net.*;
import java.io.*;

//need to add interupt
public class ServerListener
{
	private ServerSocket server;
	private boolean active;
	private Thread thread = new Thread()
	{
		public void run ()
		{
			while (active)
				try
				{
					onConnect(server.accept());
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
		}
	};
	
	ServerListener()
	{
	}
	
	public void start()
	{
		try
		{
			active = true;
			server = new ServerSocket(1234);
			thread.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void stop()
	{
		try
		{
			active = false;
			server.close();
			thread.interrupt();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void onConnect(Socket socket)
	{
		try
		{
			String data = "Toobie ornaught toobie";
			
			System.out.print("Server has connected!\n");
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			System.out.print("Sending string: '" + data + "'\n");
			out.print(data);
			out.close();
			
			socket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
