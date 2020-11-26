package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api(tags="测试")
@RequestMapping("/")
public class test {
	@RequestMapping(value="test")
	public String Eest() {
		return "hello world";
	}
	@GetMapping("/get")
	public String getGet() {
		return "get";
	}
}
