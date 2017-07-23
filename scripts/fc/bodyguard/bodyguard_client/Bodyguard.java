package scripts.fc.bodyguard.bodyguard_client;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.tribot.api.General;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.types.RSArea;

import scripts.fc.bodyguard.requester_client.BodyguardRequest;

public class Bodyguard
{
	public final String USERNAME;
	public final Queue<BodyguardRequest> REQUESTS;
	public final Positionable HOME_TILE;
	public final RSArea HOME_AREA;
	public final int RADIUS;
	
	private transient BodyguardClientThread updateThread;
	
	public Bodyguard(String username, Positionable homeTile, int radius)
	{
		USERNAME = username;
		HOME_TILE = homeTile;
		REQUESTS = new ConcurrentLinkedQueue<>();
		RADIUS = radius;
		HOME_AREA = new RSArea(HOME_TILE, RADIUS);
	}
	
	public void connect()
	{
		General.println("Initializing bodyguard: " + this);
		updateThread = new BodyguardClientThread(this);
		updateThread.start();
	}
	
	public String toString()
	{
		return USERNAME + ", home: " + HOME_TILE + ", radius: " + RADIUS;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((USERNAME == null) ? 0 : USERNAME.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bodyguard other = (Bodyguard) obj;
		if (USERNAME == null)
		{
			if (other.USERNAME != null)
				return false;
		} 
		else if (!USERNAME.equals(other.USERNAME))
			return false;
		return true;
	}	
}
