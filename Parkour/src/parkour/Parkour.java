package parkour;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_8_R1.EntityVillager;
import npc.NMSUtils;

public class Parkour extends JavaPlugin implements Listener{
	
	List<Player> Parkour = new ArrayList<>();

	public void onEnable(){
		
    Bukkit.getPluginManager().registerEvents(this, this);
		
    new NMSUtils().registerEntity("Villager", 120, EntityVillager.class, npc.Villager.class);
    
	for(Entity e : Bukkit.getWorld("Lobby").getEntities()){
		if(e instanceof Villager || e instanceof ArmorStand){
			e.remove();
		}
	}
		
    ArmorStand am = (ArmorStand) Bukkit.getWorld("Lobby").spawn(Bukkit.getWorld("Lobby").getSpawnLocation(), ArmorStand.class);
    am.setVisible(false);
    am.setCustomName("§3§lLava Parkour");
    am.setCustomNameVisible(true);
    am.setGravity(false);
    am.setSmall(true);
    
	final org.bukkit.entity.Villager z = npc.Villager.spawn(Bukkit.getWorld("Lobby").getSpawnLocation());
	z.setCustomName("");
	z.setCustomNameVisible(false);
	z.setPassenger(am);
	
	}

    @EventHandler
    public void onVillagerClick(PlayerInteractEntityEvent e){
    	if (e.getRightClicked() instanceof Villager){
        	e.setCancelled(true);
	    if(e.getRightClicked().getPassenger().getCustomName().contains("Lava Parkour")){
	    	if(!Parkour.contains(e.getPlayer())){
	    	e.getPlayer().sendMessage("§b[Parkour] §7You have entered §eParkour Mode§7.");
	    	Parkour.add(e.getPlayer());
	    	} else if(Parkour.contains(e.getPlayer())){
	    		e.getPlayer().sendMessage("§b[Parkour] §7You have exited §eParkour Mode§7.");
	    		Parkour.remove(e.getPlayer());
	    	}
	    }
    }
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
    	if(Parkour.contains(e.getPlayer())){
    		if(e.getPlayer().getLocation().getBlock().getType() == Material.STATIONARY_LAVA || e.getPlayer().getLocation().getBlock().getType() == Material.LAVA){
    			e.getPlayer().teleport(Bukkit.getWorld("Lobby").getSpawnLocation());
    		}
    	}
    }
}
