package com.mvc.controller;

import com.mvc.model.Team;
import com.mvc.service.TeamService;
import com.mvc.validation.TeamValidator;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/team")
public class TeamController {

	@Autowired
	private TeamValidator teamValidator;
	
	@Autowired
	private TeamService teamService;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(teamValidator);
	}
	
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public ModelAndView addTeamPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("add-team-form");
		modelAndView.addObject("team", new Team());
		return modelAndView;
	}
	
	
	@RequestMapping(value="/add/process")
	public ModelAndView addingTeam(@Valid @ModelAttribute Team team,BindingResult result){
		
		if(result.hasErrors())
			return new ModelAndView("add-team-form");
		
		ModelAndView modelAndView = new ModelAndView("home");
		teamService.addTeam(team);
		
		String message = "Team was successfully saved";
		modelAndView.addObject("message", message);
		return modelAndView;
		
	}
	
	@RequestMapping(value="/list")
	public ModelAndView listTeam(){
		ModelAndView modelAndView = new ModelAndView("list-team-form");
		List<Team> teams=  teamService.teamList();
		String message = "List of Team Member";
		modelAndView.addObject("message",message);
		modelAndView.addObject("teams",teams);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
	public ModelAndView editPage(@PathVariable Integer id){
		ModelAndView modelAndView = new ModelAndView("edit-team-form");
		Team team = teamService.getTeam(id);
		modelAndView.addObject("editTeam", team);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.POST)
	public ModelAndView updateTeam(@Valid @ModelAttribute Team team,BindingResult result, @PathVariable Integer id){
		if(result.hasErrors())
			return new ModelAndView("home");
		
		ModelAndView modelAndView = new ModelAndView("home");
		teamService.updateTeam(team);
		modelAndView.addObject("message","Update the Team Successfully");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public ModelAndView deleteTeam(@PathVariable Integer id){
		ModelAndView modelAndView = new ModelAndView("home");
		teamService.deleteTeam(id);
		modelAndView.addObject("message", "Delete Team Successfully");
		return modelAndView;
	}
	
}
