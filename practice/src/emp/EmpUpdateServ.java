package emp;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import dept.*;
import job.*;

/**
 * Servlet implementation class EmpSelectOne
 */
@WebServlet("/emp/empUpdate")
public class EmpUpdateServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpUpdateServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpVO empvo = new EmpVO();
		empvo.setEmployee_id(request.getParameter("employee_id"));
		EmpVO dao = EmpDAO.getInstance().selectOne(empvo);
		List<JobVO> jobList = JobDAO.getInstance().selectAll();
		List<EmpVO> manager = EmpDAO.getInstance().selectAll();
		ArrayList<DeptVO> depart = new ArrayList<DeptVO>();
		DeptDAO dao1 = new DeptDAO();
		depart = dao1.selectAll();
		request.setAttribute("depart", depart);
		request.setAttribute("managerList", manager);
		request.setAttribute("joblist", jobList);
		request.setAttribute("empone", dao);
		request.getRequestDispatcher("empUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		EmpVO empvo = new EmpVO();
		try {
			BeanUtils.copyProperties(empvo, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EmpDAO.getInstance().update(empvo);
		
		response.sendRedirect("empSelectAll");
	}

}
