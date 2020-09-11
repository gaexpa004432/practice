package dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionManager;

public class DeptDAO {
	PreparedStatement stmt;
	ArrayList<DeptVO> list = new ArrayList<DeptVO>();
	Connection conn;
	ResultSet rs = null;
	public ArrayList<DeptVO> selectAll(DeptVO deptvo) {
		DeptVO resultVO = null;
		try {
			String where = "where 1=1";
			if(deptvo.getDepartment_name() != null) {
				where += " and department_name like '%' || ? || '%' ";
			}
			String sql = "select a.* " + 
					"from("+ 
					"  select rownum rn,b.*" + 
					"  from (select department_id , department_name,manager_id,location_id from departments "+where+" order by department_id ) b" + 
					") a where rn between ? and ? ";
			conn = ConnectionManager.getConnnect();
			stmt = conn.prepareStatement(sql);
			int pos = 1;
			if(deptvo.getDepartment_name() != null) {
				stmt.setString(pos++,deptvo.getDepartment_name());
			}
			stmt.setInt(pos++, deptvo.getFirst());
			stmt.setInt(pos++, deptvo.getLast());
			rs = stmt.executeQuery();
			while(rs.next()) {
				resultVO = new DeptVO(rs.getInt("department_id"),rs.getString("department_name"),rs.getInt("manager_id"),rs.getInt("location_id"));
				list.add(resultVO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionManager.close(rs, stmt, conn);
		}
		return list;
	}
	public DeptVO selectOne(DeptVO deptVO) {
		DeptVO resultVO = null;
		try {
			String sql = "select * from departments where department_id = ?";
			conn = ConnectionManager.getConnnect();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, deptVO.getDepartment_id());
			rs = stmt.executeQuery();
			if(rs.next()) {
				resultVO = new DeptVO(rs.getInt("department_id"),rs.getString("department_name"),rs.getInt("manager_id"),rs.getInt("location_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionManager.close(rs, stmt, conn);
		}
		return resultVO;
	}
	public void insert(DeptVO deptVO) {
		
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "insert into hr.departments (department_id, department_name, location_id, manager_id)" 
					+ "values(?,?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);	// conn이라는 연결되는 길을 만듬(createStatement는 트럭과 같은 역할)
		//System.out.println(deptVO.toString());
		pstmt.setInt(1, deptVO.getDepartment_id());
		pstmt.setString(2, deptVO.getDepartment_name());
		pstmt.setInt(3, deptVO.getLocation_id());
		pstmt.setInt(4, deptVO.getManager_id());
	//	System.out.println("이전");
			int r = pstmt.executeUpdate();
	//		System.out.println("이후");	
			if(r == 1) {
				System.out.println(r+" case Processed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close(conn);
		
		}
	public void update(DeptVO deptVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "update departments set department_name = ? where department_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, deptVO.getDepartment_name());
			stmt.setInt(2, deptVO.getDepartment_id());
			int r = stmt.executeUpdate();
			if(r == 1) {
				System.out.println(r+" case Processed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close(conn);
	}
	public void delete(DeptVO deptVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "delete from departments where department_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, deptVO.getDepartment_name());
			int r = stmt.executeUpdate();
			if(r == 1) {
				System.out.println(r+" case Processed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close(conn);
	}
	public int count(DeptVO deptvo) {
		int cnt = 0;
		try {
			conn = ConnectionManager.getConnnect();
			String where = "where 1=1";
			if(deptvo.getDepartment_name() != null) {
				where += " and department_name like '%' || ? || '%' ";
			}
			String sql = "select count(*) from departments "+ where;
			stmt = conn.prepareStatement(sql);
			int pos =1;
			if( deptvo.getDepartment_name() !=null) {
				stmt.setString(pos++, deptvo.getDepartment_name());
			}
			rs = stmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(conn);
		}
		return cnt;
	}
}
