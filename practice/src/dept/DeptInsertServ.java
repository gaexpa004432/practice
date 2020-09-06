package dept;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import emp.*;
import job.*;

/**
 * Servlet implementation class DeptInsertServ
 */
@WebServlet("/dept/deptInsert")
public class DeptInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeptInsertServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 지역정보
		System.out.println("locations 전체 조회 실행");
		ArrayList<HashMap<String, String>> locationList = LocationDAO.getInstance().selectAll(null);
		request.setAttribute("locationList", locationList);
		
		// 사원(매니저)정보
		System.out.println("employees 전체 조회 실행");
		List<EmpVO> empList = EmpDAO.getInstance().selectAll();
		request.setAttribute("empList", empList);
		
		
		request.getRequestDispatcher("deptInsertForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeptVO deptVO = new DeptVO();
		
		request.setCharacterEncoding("UTF-8");
		
//		deptVO.setDepartment_id(Integer.parseInt(request.getParameter("department_id"))); // getParameter는 String 값이므로 parseInt 해줌
//		deptVO.setDepartment_name(request.getParameter("department_name"));
//		deptVO.setLocation_id(Integer.parseInt(request.getParameter("location_id")));
//		deptVO.setManager_id(Integer.parseInt(request.getParameter("manager_id")));
		try {
			BeanUtils.copyProperties(deptVO, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2. 등록 처리
		DeptDAO dao = new DeptDAO();
		dao.insert(deptVO);

		// 3. 결과 처리(생략)

		// 4. 전체 조회 서블릿 페이지로 이동
		response.sendRedirect("deptSelectAll");
	}

}
