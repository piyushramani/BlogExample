package com.mvc.dao;

import com.mvc.model.Team;

import java.util.List;

public interface TeamDAO {
	
	public void addTeam(Team team);
	public void updateTeam(Team team);
	public Team getTeam(int id);
	public void deleteTeam(int id);
	public List<Team> teamList();
}
