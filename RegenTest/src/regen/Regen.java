package regen;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import utils.iBlock;

public class Regen extends JavaPlugin implements Listener{

	public List<iBlock> BlockData = new ArrayList<>();
	
	public void onEnable(){
		Bukkit.getPluginManager().registerEvents(this, this);
		regenWorld();
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		BlockData.add(new iBlock(e.getBlock()));
	}
	
    @SuppressWarnings("deprecation")
	public void regenWorld(){
    	Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(iBlock blockData : BlockData){
                    Block block = blockData.getLocation().getWorld().getBlockAt(blockData.getLocation());
                    block.setType(blockData.getMaterial());
                    block.setData(blockData.getData());
                    block.getState().update();
                }
            }
        }, 0L, 200L);
    }
	
}
