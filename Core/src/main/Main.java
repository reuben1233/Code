/*    */ package main;
import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class Main extends JavaPlugin
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onPlayerJoin(PlayerJoinEvent e) {
/* 32 */     final Player p = e.getPlayer(); 
             Bukkit.getServer().dispatchCommand(p, "/rapidfw 6");
           } 
/*    */ }
