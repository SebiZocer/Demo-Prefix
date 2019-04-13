package me.SebiZocer.DemoPrefix.Listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import me.SebiZocer.DemoPrefix.Main.Mainclass;
import me.SebiZocer.DemoPrefix.Methods.Prefix;
import me.SebiZocer.SkinLoader.Events.CallScoreboardEvent;

public class LTRcallscoreboard implements Listener {
	
	@EventHandler
	public void onCall(CallScoreboardEvent e){
		new BukkitRunnable(){
			@Override
			public void run() {
				for(Player all : Bukkit.getOnlinePlayers()){
					Prefix.sendPrefix(all);
				}
			}
		}.runTaskLater(Mainclass.plugin, 1);
	}
}