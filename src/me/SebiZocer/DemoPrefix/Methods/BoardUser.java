package me.SebiZocer.DemoPrefix.Methods;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class BoardUser{
	
	//Class Management
	
	Player p;
	
	public BoardUser(Player p){
		this.p = p;
	}
	
	private static ArrayList<BoardUser> users = new ArrayList<>();
	
	public static BoardUser getBoardUser(Player p){
		for(BoardUser u : users){
			if(u.getPlayer().equals(p)){
				return u;
			}
		}
		BoardUser u = new BoardUser(p);
		users.add(u);
		return u;
	}
	
	//Class Management End
	
	public Player getPlayer(){
		return p;
	}
	
	public void sendBoard(Scoreboard sb){
		boolean b1 = true;
		if(sb != null){
			List<String> teams = new ArrayList<>();
			for(Team t : sb.getTeams()){
				teams.add(t.getName());
			}
			if(p.getScoreboard() != null){
				if(p.getScoreboard().getTeams().isEmpty()){
					b1 = false;
				}
				for(Team t : p.getScoreboard().getTeams()){
					if(teams.contains(t.getName())){
						teams.remove(t.getName());
					} else {
						b1 = false;
					}
				}
			}
		} else {
			b1 = true;
		}
		
		//----------------------------------------------------------------
		
		if(b1 == true){
			sb = p.getScoreboard();
		}
		
		//----------------------------------------------------------------
		
		sb = Prefix.setTeams(sb, p);
		
		//----------------------------------------------------------------
		
		if(b1 == false){
			p.setScoreboard(sb);
		}
	}
}