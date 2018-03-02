package kr.co.kms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
	
	@RequestMapping(value="/doA")
	String doA(Model model, @RequestParam("msg") String msg) {
		System.out.println("doA 실행중~");
		model.addAttribute("info", msg); //doA로 이동하면서 ?mgs= 에 hello들어오면 b로 아니면 a로 이동하게 됨
		
		return "doA";
	}

	@RequestMapping(value="/doB")
	String doB() {
		System.out.println("doB 실행중~");
		return "doB";
	}

}
