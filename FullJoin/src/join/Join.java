package join;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.plugin.java.JavaPlugin;

public class Join extends JavaPlugin implements Listener{
	
	boolean perms = getConfig().getBoolean("permissions");

	public void onEnable(){
		Bukkit.getPluginManager().registerEvents(this, this);
		this.saveDefaultConfig();
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e){
		if(e.getPlayer().hasPermission("join.full") || perms == false)
		if(e.getResult() == Result.KICK_FULL){
		e.allow();
		}
	}
	
}
