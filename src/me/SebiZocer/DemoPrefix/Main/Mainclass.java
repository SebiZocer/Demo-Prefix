package me.SebiZocer.DemoPrefix.Main;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.SebiZocer.DemoPrefix.Listener.LTRcallscoreboard;
import me.SebiZocer.DemoPrefix.Listener.LTRchat;
import me.SebiZocer.DemoPrefix.Methods.Prefix;

public class Mainclass extends JavaPlugin implements Listener {
	
	public static Mainclass plugin;
	
    @Override
    public void onEnable() {
    	plugin = this;
    	registerEvents();
    	setInfos();
        System.out.println("[" + getDescription().getName() + "] enabled. Plugin by Sebi_Zocer");
    }
    
	public void onDisable(){
        super.onDisable();
        System.out.println("[" + getDescription().getName() + "] disabled");
    }
	
	public void registerEvents(){
		getServer().getPluginManager().registerEvents(new LTRcallscoreboard(), this);
		getServer().getPluginManager().registerEvents(new LTRchat(), this);
	}
	
	public void setInfos(){
		Prefix.startAutoUpdater();
	}
}