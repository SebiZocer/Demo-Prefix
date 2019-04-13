package me.SebiZocer.DemoPrefix.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import me.SebiZocer.DemoPrefix.Main.Mainclass;
import me.SebiZocer.SkinLoader.Methods.Classes.User;

public class Prefix {
	
	private static Scoreboard sb = null;
	
	@SuppressWarnings("deprecation")
	public static void startAutoUpdater(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Mainclass.plugin, new BukkitRunnable(){
			@Override
			public void run() {
				if(sb == null){
					setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
				}
				for(Player all : Bukkit.getOnlinePlayers()){
					sendPrefix(all);
				}
			}
		}, 20, 20);
	}
	
	public static void setScoreboard(Scoreboard sb){
		if(sb.getTeam("01owner") == null){
			sb.registerNewTeam("01owner").setPrefix("§4Owner §8┃ §7");
		}
		if(sb.getTeam("02admin") == null){
			sb.registerNewTeam("02admin").setPrefix("§4Admin §8┃ §7");
		}
		if(sb.getTeam("03spieler") == null){
			sb.registerNewTeam("03spieler").setPrefix("§7");
		}
		Prefix.sb = sb;
	}
	
	public static Scoreboard getScoreboard(){
		return cloneScoreboard(sb);
	}
	
	private static Scoreboard cloneScoreboard(Scoreboard SB){
		if(SB == null){
			return null;
		}
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		if(!SB.getTeams().isEmpty()){
			for(Team t : SB.getTeams()){
				Team x = sb.registerNewTeam(t.getName());
				if(t.getPrefix() != null){
					if(t.getPrefix().length() > 16){
						x.setPrefix("§4§lERROR");
					} else {
						x.setPrefix(t.getPrefix());
					}
				}
				if(t.getSuffix() != null){
					if(t.getSuffix().length() > 16){
						x.setSuffix("§4§lERROR");
					} else {
						x.setSuffix(t.getSuffix());
					}
				}
				for(String s : t.getEntries()){
					x.addEntry(s);
				}
				if(t.getNameTagVisibility() != null){
					x.setNameTagVisibility(t.getNameTagVisibility());
				}
			}
		}
		
		Objective obx = null;
		
		for(Objective o : SB.getObjectives()){
			Objective ob = sb.registerNewObjective(o.getName(), o.getCriteria());
			if(o.getDisplayName() != null){
				ob.setDisplayName(o.getDisplayName());
			}
			if(o.getDisplaySlot() != null){
				ob.setDisplaySlot(o.getDisplaySlot());
				if(o.getDisplaySlot().equals(DisplaySlot.SIDEBAR)){
					obx = ob;
				}
			}
		}
		if(obx != null){
			for(String s : SB.getEntries()){
				if(SB.getScores(s) != null){
					for(Score sc : SB.getScores(s)){
						obx.getScore(sc.getEntry()).setScore(sc.getScore());
					}
				}
			}
		}
		return sb;
	}
	
	public static void sendPrefix(Player p){
		BoardUser bu = BoardUser.getBoardUser(p);
		bu.sendBoard(sb);
	}
	
	@SuppressWarnings("deprecation")
	public static Scoreboard setTeams(Scoreboard sb, Player p){
		for(Player all : Bukkit.getOnlinePlayers()){
			
			String team = "";
			try {
				for(Team t : sb.getTeams()){
					for(String s : t.getEntries()){
						if(Bukkit.getPlayer(s) != null){
							if(Bukkit.getPlayer(s).equals(all)){
								team = Bukkit.getPlayer(s).getName();
							}
						}
					}
				}
			} catch(Exception e){
			}
			
			//----------------------------------------------------------------
			
			String teamp = "03spieler";
			if (all.hasPermission("rank.owner")){
				teamp = "01owner";
			}else if (all.hasPermission("rank.admin")){
				teamp = "02admin";
			}
			
			User x = User.getUser(all);
			if(x.isNicked() && !all.equals(p)){
				team = "03spieler";
			}
			
			if(team.equals("")){
				
				sb.getTeam(teamp).addPlayer(all);
				all.setDisplayName(sb.getTeam(teamp).getPrefix() + " " + all.getName());
				
			} else {
				
				//----------------------------------------------------------------
				
				boolean change = false;
				
				for(Team t : sb.getTeams()){
					for(String s : t.getEntries()){
						if(Bukkit.getPlayer(s) != null){
							if(Bukkit.getPlayer(s).equals(all)){
								if(!t.getName().equals(teamp)){
									t.removePlayer(all);
									change = true;
								}
							}
						}
					}
				}
				
				if(change == true){
					sb.getTeam(teamp).addPlayer(all);
					all.setDisplayName(sb.getTeam(teamp).getPrefix() + " " + all.getName());
				}
			}
		}
		return sb;
	}
}