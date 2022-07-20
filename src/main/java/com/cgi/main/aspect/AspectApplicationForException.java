package com.cgi.main.aspect;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cgi.main.entity.Employee;

@RestControllerAdvice
public class AspectApplicationForException {

	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	public Map<String, String> handleInvalidArguments(javax.validation.ConstraintViolationException ex)
	{
		Map<String,String> map=new  LinkedHashMap<>();
		Set<ConstraintViolation<?>> s = ex.getConstraintViolations();
		Iterator<ConstraintViolation<?>> it = s.iterator();
		while(it.hasNext()) {
			ConstraintViolation<?> c= it.next();
			System.out.println(c.getPropertyPath()+"<===");
			map.put(c.getPropertyPath()+"", c.getMessage());
		}
		//map.put("AGE", "In Valid Age");
		return map;
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler
	public Map<String,String> handleEntityNotFoundException(javax.persistence.EntityNotFoundException e)
	{
		System.out.println(e.getMessage());
		Map<String,String> map=new LinkedHashMap<>();
		map.put("errorMessage", e.getMessage());
		return map;
	}

	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler
	public Map<String,String> handleEmployeeNotFoundException(com.cgi.main.exception.EmployeeNotFoundException e){
		Map<String,String> map=new LinkedHashMap<>();
		map.put("errorMessage", e.getMessage());
		return map;
	}
}
