package com.mvc.dao;

import com.mvc.model.Team;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDAOImpl implements TeamDAO {

	@Autowired
	SessionFactory factory;
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public void addTeam(Team team) {
		getSession().save(team);
	}

	@Override
	public void updateTeam(Team team) {
		getSession().saveOrUpdate(team);
	}

	@Override
	public Team getTeam(int id) {		
		return (Team) getSession().get(Team.class, id);
	}

	@Override
	public void deleteTeam(int id) {
		Team team =  getTeam(id);
		if(team != null)
			getSession().delete(team);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Team> teamList() {
		
		return getSession().createQuery("from Team").list();
	}

}
