package me.SebiZocer.DemoPrefix.Listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import me.SebiZocer.SkinLoader.Methods.Classes.User;

@SuppressWarnings("deprecation")
public class LTRchat implements Listener {
	
	@EventHandler
	public void onChat(PlayerChatEvent e){
		e.setCancelled(true);
		User p = User.getUser(e.getPlayer());
		
		String msg = "";
		
	    if (p.hasPermission("rank.owner")) {
	    	msg = "§4Owner §8┃ §r§7" + p.getName() + " §8» §7" + e.getMessage();
	    } else if (p.hasPermission("rank.administrator")) {
	    	msg = "§4Administrator §8┃ §r§7" + p.getName() + " §8» §7" + e.getMessage();
	    } else {
	    	msg = "§7" + p.getName() + " §8» §7" + e.getMessage();
	    }
	    
	    for(Player all : Bukkit.getOnlinePlayers()){
    		if(all.equals(p.getPlayer())){
    			p.sendMessage(msg.replace(p.getName(), p.getRealname()));
    		} else {
    			if(p.isNicked()){
        			all.sendMessage("§7" + p.getName() + " §8» §7" + e.getMessage().replace("%", "Prozent"));
    			} else {
    				all.sendMessage(msg);
    			}
    		}
	    }
	}
}