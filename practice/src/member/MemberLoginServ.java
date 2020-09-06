package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberLoginServ
 */
@WebServlet({"/member/memberLogin","/member/memberLogout"})
public class MemberLoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().invalidate(); //세션 객체 삭제 
		
		response.sendRedirect("../index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(request.getParameter("id"));
		memberVO.setPw(request.getParameter("pw"));
		
		// 2. 서비스 처리(DB)
		MemberVO resultVO = MemberDAO.getInstance().selectOne(memberVO);  // memberVO 집어넣고 결과를 MemberVO로 받기
		
		
		// 3. 결과 저장
		String page = "";		// 이동할 페이지 이름 변수 선언
		if(resultVO == null) {  // id가 없는 경우
			request.setAttribute("errormsg", "해당 ID가 없습니다.");
			page = "login.jsp";
			
		} else {
			if(memberVO.getPw().equals(resultVO.getPw())) {  // memberVO에 있는 pw와 resultVO의 pw를 비교해서 같으면 로그인성공
				request.getSession().setAttribute("login", resultVO);		// 세션으로 result객체를 보내는 것
				request.getSession().setAttribute("id", resultVO.getId());	// 세션으로 result객체를 보내는 것
				page = "../index.jsp";
			} else {	// 패스워드 불일치
				request.setAttribute("errormsg", "패스워드 불일치");
				page = "login.jsp";
			}
		}
		
		// 4. 뷰페이지 이동(redirect, forward) 또는 뷰페이지 출력
		request.getRequestDispatcher(page).forward(request, response);
		
	}
	

}
