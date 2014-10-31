import java.net.*;
import java.io.*;

//On connection, should start new thread? - probably yes, as what we do in the onConnect method may take a while to return
//and mean while connections attempts from other clients may be building up
public class ServerSocketConnection
{
	private boolean active;
	private int port;
	private ServerSocket serversocket;
	private SocketEventListener listener;
	
	//Thread to handle connection requests
	private Thread thread = new Thread()
	{
		public void run ()
		{
			while (active)
				try
				{
					onConnect(serversocket.accept());
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
		}
	};
	//might not want to activate connection immeditely
	ServerSocketConnection(int port) throws IOException
	{
		this.port = port;
		//this(new ServerSocket(port));
	}

	public void setEventListener(SocketEventListener listener)
	{
		this.listener = listener;
	}
	
	private void onConnect(final Socket socket)
	{
		// maybe is should be up to the user's listener's onConnect event as the
		// the listener which implements the onDisconnect and onRecievedData methods
		// maybe different than the on tha handles onConnect events for this server
		Thread thread = new Thread()
		{
			public void run ()
			{
				SocketConnection conn = new SocketConnection(socket, listener);
				//SocketEvent e = new SocketEvent(conn);
				//listener.onConnect(e);
			}
		};
		thread.start();
	}
	
	public void open()
	{
		try
		{
			active = true;
			serversocket = new ServerSocket(1234);
			thread.start();
			System.out.println("Opened");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void close()
	{
		try
		{
			active = false;
			thread.interrupt();
			serversocket.close();
			System.out.println("Closed");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
