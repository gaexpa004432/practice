package emp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import job.*;
import dept.*;
/**
 * Servlet implementation class EmpSelectOne
 */
@WebServlet("/emp/empInsert")
public class EmpInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpInsertServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<JobVO> jobList = JobDAO.getInstance().selectAll();
		List<EmpVO> manager = EmpDAO.getInstance().selectAll();
		ArrayList<DeptVO> depart = new ArrayList<DeptVO>();
		DeptDAO dao = new DeptDAO();
		depart = dao.selectAll();
		
		request.setAttribute("depart", depart);
		request.setAttribute("managerList", manager);
		request.setAttribute("joblist", jobList);
		request.getRequestDispatcher("empInsert.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		// 파라미터 VO에 담기
		EmpVO emp = new EmpVO();
		
		try {  // 위의 파라미터 한꺼번에 담아주는거
			BeanUtils.copyProperties(emp, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EmpDAO empdao = new EmpDAO();
		empdao.insert(emp);
		System.out.println(emp.toString());
		response.sendRedirect("empSelectAll");	
		
	}

}