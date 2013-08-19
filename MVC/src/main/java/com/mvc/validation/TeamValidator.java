package com.mvc.validation;

import com.mvc.model.Team;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TeamValidator implements Validator{

	private static final String TEAM_NAME = "name";
	private static final String TEAM_RATING = "rating";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Team.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Team team = (Team) target;
		Integer rating = team.getRating();
		
		ValidationUtils.rejectIfEmpty(errors, TEAM_NAME, "team.name.empty","Name Empty Not allwed");
		ValidationUtils.rejectIfEmpty(errors, TEAM_RATING, "team.rating.empty","Rating Empty Not allwed");
		
		if(rating != null && rating < 1)
			errors.rejectValue(TEAM_RATING, "team.rating.less.than.zero","Rating Not less than Zero");
	}

}
