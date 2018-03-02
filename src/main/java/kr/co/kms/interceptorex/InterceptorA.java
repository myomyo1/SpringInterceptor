package kr.co.kms.interceptorex;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//HandlerInterceptorAdapter 클래스 상속하기
public class InterceptorA extends HandlerInterceptorAdapter {

	//클라이언트가 실제 요청한 URL(컨트롤러)로 접근 전에 실행 됨
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("doA 인터셉터 작동함. /doA에 접근 전 입니다.");
		
		String param = request.getParameter("msg");
		if(param == null || param.equals("")) {
			System.out.println("파라미터가 올바르지 않아 doB로 이동합니다.");
			response.sendRedirect("/doB"); //다른곳으로 보내고
			return false; // 원래가려던 곳이 아니므로 false 준다
		}else {
			System.out.println("/doA로 이동 할 때 전달된 파라미터 : " + param);
		}
		//원래 클라이언트가 요청한 곳으로 보내려면 True 리턴 , 로직에 따라 다른곳으로 보내려면 False 리턴
		return true;
	}
	
	//서버가 클라이언트에 응답하여 클라이언트에게 도달 전에 실행됨
	//ModelAndView --> 서버가 클라이언트에게 전달하는 Attribute 모델과 이동하고자 하는 view의 정보를 가지고 있음
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("doA 인터셉터 작동함. /doA가 응답되었습니다.");
		
		//model을 꺼내기 위해서는 modelAndView 객체를 사용함
		ModelMap modelmap = modelAndView.getModelMap();
		String infoValue = (String)modelmap.get("info"); /*테스트컨트롤러에서 hello 값을 info에 넣어뒀음*/
		
		if(infoValue.equals("hello")) {
			System.out.println("/doA 응답 중 info값이 hello라서 doB로 이동합니다.");
			response.sendRedirect("/doB");
		}
		/*postHandle에서 포워딩 또는 리다이렉트 할 경우 하단에 어떠한 작업도 올 수 없음*/
		/*super.postHandle(request, response, handler, modelAndView);*/
	}
}
