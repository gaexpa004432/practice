package dept;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Paging;

/**
 * Servlet implementation class DeptSelectAllServ
 */
@WebServlet("/dept/deptSelectAll")
public class DeptSelectAllServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeptSelectAllServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기
		DeptDAO dao = new DeptDAO();
		
		String p = request.getParameter("p");
		String department_name = request.getParameter("department_name");
		DeptVO vo = new DeptVO();
		vo.setDepartment_name(department_name);
		
		Paging paging = new Paging();
		
		int page = 1;
		if(p != null) {
			page = Integer.parseInt(p);
		}
		paging.setPageUnit(5);
		paging.setPageSize(3);
		paging.setPage(page);
		paging.setTotalRecord(dao.count(vo));
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		
		ArrayList<DeptVO> list =dao.selectAll(vo);
		System.out.println("select exe");
		request.setAttribute("paging", paging);
		request.setAttribute("list",list);
		request.getRequestDispatcher("deptSelectAll.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
