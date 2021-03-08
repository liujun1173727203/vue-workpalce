package com.xiaoer.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoer.demo.entity.Questions;
import com.xiaoer.demo.entity.utils;
import com.xiaoer.demo.mapper.QuestionMapper;

@RestController
public class QuestionsController {
	@Autowired
	private QuestionMapper questMapper;
	
	@RequestMapping(value="/getquestion")
	public List<Questions> getQuestions(String chapter,String level){
		List<Questions> lists =new ArrayList<Questions>();
		List<Questions> elists =new ArrayList<Questions>();
		if (!"".equals(chapter)&&!"".equals(level)){
			lists =questMapper.getQuestion3(chapter,level);
		}else if ("".equals(chapter)&&!"".equals(level)){
			lists =questMapper.getQuestion2(level);
		}else if (!"".equals(chapter)&&"".equals(level)){
			lists =questMapper.getQuestion1(chapter);
		}else if ("".equals(chapter)&&"".equals(level)){
			lists =questMapper.getQuestion();
		};
		for(Questions question : lists) {
			String[] c =question.getChoice().split("\\|");
			List<String> b =new ArrayList<String>();
			for(String i: c) {
				b.add(i);
			}
			question.setChoices(b);
		};
		if (lists.size()>20) {
			int a =lists.size();
			Random rand =new Random((int)System.currentTimeMillis());
			for (int i=0;i<20;i++) {
				int b =rand.nextInt(a-1);
				elists.add(lists.get(b));
			};
		}else {
			elists =lists;
		}
		return elists;
	}
	@GetMapping("/getutil")
	public utils getUtil() {
		return questMapper.getUtil();
	}
}
