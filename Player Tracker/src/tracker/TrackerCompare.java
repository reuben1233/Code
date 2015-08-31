package tracker;

import java.util.Comparator;
import java.util.UUID;

import org.bukkit.Bukkit;

public class TrackerCompare implements Comparator<UUID>{

	private UUID player;

	public TrackerCompare (UUID p) {
		this.player = p;
	}
	
	@Override
	public int compare (UUID target1, UUID target2) {
		return compare (Bukkit.getPlayer(target2).getLocation().distance(Bukkit.getPlayer(player).getLocation()),
	    Bukkit.getPlayer(target1).getLocation().distance(Bukkit.getPlayer(player).getLocation()));
	}
	
	private int compare(double a, double b) {
		return a > b ? -1 : a > b ? 1 : 0;
	}
}
