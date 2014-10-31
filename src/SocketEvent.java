import java.util.*;
import java.net.*;

public class SocketEvent extends EventObject
{
	SocketEvent(SocketConnection conn)
	{
		super(conn);
	}
	
	public Socket getSocket()
	{
		return ((SocketConnection) super.getSource()).getSocket();
	}
	
	public SocketConnection getConn()
	{
		return (SocketConnection) super.getSource();
	}
	
	public String toString()
	{
		return "Socket Event from " + source;
	}
}
