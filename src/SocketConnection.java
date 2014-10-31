import java.net.*;
import java.io.*;

//On recieving data should start new thread? - probably not, better to just have one thread reading socket for my applications
//Sort out connect / disconnect methods
//This can be created as a standalone connection (ie client), or from a ServerConnection when a client connects
//The server connection will have have a SocketEventListener assigned to it and will call the relevant constructor
//Maybe we don't want to start a new thread on receive data, leave that to the client application, because we might not want to start 60 concurrent threads, but have 4 threads "workers" handling a que of runnables.
public class SocketConnection
{
	private boolean active;
	private String host;
	private int port;
	private Socket socket;
	private SocketEventListener listener;
	
	//Thread to handle recieving data
	private Thread thread = new Thread()
	{
		public void run ()
		{
			try
			{
				//Check best way to do this
				//if (socket.getInputStream().avaliable() > 0)
				InputStreamReader in = new InputStreamReader(socket.getInputStream());
				while (active)
					if ( in.ready() )
						onReceiveData();
			}
			catch (IOException e)
			{
				//e.printStackTrace();
				onDisconnect();
			}
		}
	};
		
	
	SocketConnection(String host, int port)
	{
		this.host = host;
		this.port = port;
	}
	/*
	protected SocketConnection(Socket socket)
	{
		this.socket = socket;
		connect();
	}
	*/
	protected SocketConnection(Socket socket, SocketEventListener listener)
	{
		this.socket = socket;
		this.listener = listener;
		try
		{
			connect();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setEventListener(SocketEventListener listener)
	{
		this.listener = listener;
	}
	
	public SocketEventListener getEventListener()
	{
		return listener;
	}
	
	private void onReceiveData()
	{
		listener.onReceiveData(new SocketEvent(this));
	}
	
	private void onDisconnect()
	{
		active = false;
		listener.onDisconnect(new SocketEvent(this));
		System.out.println("Disconnected");
	}
	
	private void onConnect()
	{
		listener.onConnect(new SocketEvent(this));
		System.out.println("Connected");
	}
	
	public void connect() throws IOException
	{
		if (socket == null)
		{
			socket = new Socket();
			socket.connect(new InetSocketAddress(host,port));
		}
		active = true;
		onConnect();
		
		thread.start();
	}
	
	public void disconnect() throws IOException
	{
		active = false;
		socket.close();
	}
	
	public Socket getSocket()
	{
		return socket;
	}
	
}
