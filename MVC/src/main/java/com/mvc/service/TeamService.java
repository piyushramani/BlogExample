package com.mvc.service;

import com.mvc.model.Team;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface TeamService {

	public void addTeam(Team team);
	public void updateTeam(Team team);
	public Team getTeam(int id);
	public void deleteTeam(int id);
	public List<Team> teamList();
}
