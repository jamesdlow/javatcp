import java.util.*;

public interface SocketEventListener extends EventListener
{
	void onConnect(SocketEvent e);
	void onDisconnect(SocketEvent e);
	void onReceiveData(SocketEvent e);
}
