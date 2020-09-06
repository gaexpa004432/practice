package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.ConnectionManager;

public class MemberDAO {
	// 전역변수. 모든 메서드에 공통으로 사용되는 변수
	Connection conn;
	PreparedStatement pstmt; // PreparedStatement는 Statement와 같은 기능을 수행하지만 가독성이 좋고 더 빠르다. ?기호 사용가능
	ResultSet rs = null; // ResultSet은 결과의 집합이라 select할때 사용하기. 초기값 필요하다
	
	// 싱글톤
	static MemberDAO instance;
	public static MemberDAO getInstance() {
		if(instance == null)
			instance = new MemberDAO();
			return instance;
	}
	

	// 단건 조회
	public MemberVO selectOne(MemberVO memberVO) {
		MemberVO member = null; // select할때는 리턴값이 필요해서 리턴값을 저장할 변수 선언

		try {
			conn = ConnectionManager.getConnnect();
			String sql = "select * from member"
					+ " where id=?"; // 컨+쉬+x 대문자, 컨+쉬+y 소문자 변환가능. 쿼리 엔터해서 쓸거면 앞에 공백 붙이기
			pstmt = conn.prepareStatement(sql); // 미리 sql 구문이 준비가 되어야한다
			pstmt.setString(1, memberVO.getId()); // ?의 첫번째 자리에 올 값 지정
			rs = pstmt.executeQuery(); // select 시에는 executeQuery() 쓰기

			if (rs.next()) { // 단건조회라서 next()로 한건 한건마다 true 인지 false인지 확인하고 이동함
				member = new MemberVO();
				member.setId(rs.getString(1)); // 컬럼이 첫번째 자리라서 1을 입력한거임
				member.setPw(rs.getString("pw"));
				member.setGender(rs.getString("gender")); // 컬럼명에다가 별칭있으면 별칭을 넣어줘야함
				member.setJob(rs.getString("job")); // 대소문자 구별 없음
			} else {
				System.out.println("no data");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		return member; // 값을 리턴해줌
	}

	// 전체 조회
	public List<MemberVO> selectAll() { // 조회가 여러건이면 DeptVO를 list에 담음
		List<MemberVO> list = new ArrayList<MemberVO>(); // 결과값을 저장할 list 변수 객체 선언

		try {
			conn = ConnectionManager.getConnnect();
			String sql = "select * from member"
					+ " ORDER BY id"; // 컨+쉬+x 대문자, 컨+쉬+y 소문자 변환가능. 쿼리 엔터해서 쓸거면 앞에 공백 붙이기
			pstmt = conn.prepareStatement(sql); // 미리 sql 구문이 준비가 되어야한다
			rs = pstmt.executeQuery(); // select 시에는 executeQuery() 쓰기

			while (rs.next()) { // 여러건 조회라서 while를 사용. next()로 한건 한건마다 true 인지 false인지 확인하고 이동함
				MemberVO member = new MemberVO(); // 레코드 한건을 resultVO에 담음
				member.setId(rs.getString(1)); // 컬럼이 첫번째 자리라서 1을 입력한거임
				member.setPw(rs.getString("pw"));
				member.setJob(rs.getString("job")); // 컬럼명에다가 별칭있으면 별칭을 넣어줘야함
				member.setReason(rs.getString("reason")); // 대소문자 구별 없음
				member.setGender(rs.getString("gender"));
				member.setMailyn(rs.getString("mailyn"));
				member.setHobby(rs.getString("hobby"));
				member.setRegdate(rs.getString("regdate"));
				list.add(member); // resultVo를 list에 담음
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		return list; // 값을 리턴해줌
	}
	
	
	// 메일 수신 회원수
	public int getMailynCnt() {
		int cnt = 0;
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "select count(id) from member where mailyn='Y'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			cnt = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		return cnt;
	}
	
	// 성별 인원수
	public List<HashMap<String,Object>> getGenderCnt() {
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "select gender,count(id) cnt from member group by gender";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()) {
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("gender", rs.getString("gender"));
				map.put("cnt", rs.getInt("cnt"));
				list.add(map);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		return list;
	}
	

	// update
	public void update(MemberVO memberVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "update member set pw =? where id=?"; // 값 들어갈 자리에 ? 로 지정
			pstmt = conn.prepareStatement(sql); // 미리 sql 구문이 준비가 되어야한다
			pstmt.setString(1, memberVO.getPw()); // ?의 첫번째 자리에 올 값 지정
			pstmt.setString(2, memberVO.getId()); // ?의 두번째 자리에 올 값 지정
			int r = pstmt.executeUpdate(); // 실행
			System.out.println(r + " 건이 수정됨"); // 결과 처리

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(null, pstmt, conn); // 연결 해제
		}
	}

	
	// delete
	public void delete(MemberVO memberVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "delete from member where id=?"; // 값 들어갈 자리에 ? 로 지정
			pstmt = conn.prepareStatement(sql); // 미리 sql 구문이 준비가 되어야한다
			pstmt.setString(1, memberVO.getId()); // ?의 첫번째 자리에 올 값 지정
			int r = pstmt.executeUpdate(); // 실행
			System.out.println(r + " 건이 삭제됨"); // 결과 처리

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(null, pstmt, conn); // 연결 해제
		}
	}

	// insert
	public void insert(MemberVO memberVO) {
		int r=0;
		try {
			// 1. DB 연결
			Connection conn = ConnectionManager.getConnnect(); // ConnectionManager클래스의 getConnnect실행

			// 2. sql 구문 실행
			String sql = "insert into member(id,pw,job,reason,gender,mailyn,hobby,regdate) values(?,?,?,?,?,?,?,sysdate)";
					 
			PreparedStatement psmt = conn.prepareStatement(sql);
			System.out.println(memberVO.getPw()+memberVO.getGender());
			psmt.setString(1, memberVO.getId());
			psmt.setString(2, memberVO.getPw());
			psmt.setString(3, memberVO.getJob());
			psmt.setString(4, memberVO.getReason());
			psmt.setString(5, memberVO.getGender());
			psmt.setString(6, memberVO.getMailyn());
			psmt.setString(7, memberVO.getHobby());
			psmt.executeUpdate();
			
			// 3. 결과 처리
			System.out.println(r + " 건이 처리됨");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// 4. 연결 해제
			ConnectionManager.close(conn);
		}

	}
}