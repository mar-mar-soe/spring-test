package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import javassist.expr.NewArray;


@Controller
public class TestingController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/")
	public String showUser()
	{
		return "user-list";
	}
	
	@GetMapping("/add")
	public String showAddUserForm(Model model)
	{
		model.addAttribute("user",new User());
		return "add-user";	
	}
	@PostMapping("addUser")
	public String saveUser(@Valid User user,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			return "add-user";
		}
		System.out.println("User name " + user.getName());
		System.out.println("Email " + user.getEmail());
		userRepository.save(user);
		model.addAttribute("user",userRepository.findAll());
		
		return "user-list";
	}
	


}
