package ph.edu.uplb.cs137.client;
import java.net.InetAddress;

/**
 * This class encapsulates a network players
 * @author Joseph Anthony C. Hermocilla
 *
 */

public class NetPlayer {
	private InetAddress address;
	private int port;
	private String name;
	private String msg;
    private int health=170;

	public NetPlayer(String name,InetAddress address, int port){
		this.address = address;
		this.port = port;
		this.name = name;
	}

	/**
	 * Returns the address
	 * @return
	 */
	public InetAddress getAddress(){
		return address;
	}

	/**
	 * Returns the port number
	 * @return
	 */
	public int getPort(){
		return port;
	}

	/**
	 * Returns the name of the player
	 * @return
	 */
	public String getName(){
		return name;
	}

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }
	/**
	 * String representation. used for transfer over the network
	 */
	public String toString(){
		String retval="";
		retval+="PLAYER ";
		retval+=name+" ";
		retval+=msg;
		return retval;
	}

    public void setHealth(int health){
        this.health=health;
    }

    public int getHealth(){
        return this.health;
    }
}
