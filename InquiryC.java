package adventour.mypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InquiryC
 */
@WebServlet("/InquiryC")
public class InquiryC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
//		문의하기 등록 처리
		
		request.getParameterNames();
//		request.getParameterValues("같은 name에 input이 여러개인 값들 가져올때 여기에 name");
		request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
	
		Connection conn = null; 
		Statement stmt = null;
		int rowNum = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver"); /*데이테베이스에 연결*/
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/adventour?characterEncoding=utf8","root","dkssud2!!");
			if(conn== null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			stmt = conn.createStatement();
			String command = "INSERT INTO INQUIRY (seq, title, content, write_date, status) values "
					+"( (select max(seq)+1 from INQUIRY), "
					+title+", "
					+content+", "
					+"SYSDATE(),  "
					+"'' )"
					;
//			String command = String.format("insert into member(m_id, m_pw, m_lastname, m_firstname, m_nickname, m_postcode, m_addr1,m_addr2, m_pnum1, m_pnum2, m_email,m_birth_y, m_birth_m, m_birth_d, m_gender, m_agree)values('"
//											+m_id+"', '"+m_pw+"', '"+m_lastname+"','"+m_firstname+"', '"+m_nickname+"', '"+m_postcode+"', '"+m_addr1+m_addr2+"', '"+m_addr3+m_addr4+"', '"+m_pnum1+m_pnum1_1+m_pnum1_2+ "','"
//											+m_pnum2+m_pnum2_1+m_pnum2_2+ "','"+m_email+m_emaild+"', '"+m_birth_y+"', '"+m_birth_m+"','"+m_birth_d+"','"+m_gender+"','"+m_agree+"');" );
							
			
			rowNum = stmt.executeUpdate(command);
			
//			if(rowNum < 1)
//				throw new Exception("데이터를 DB에 입력할 수 없습니다.");
			
			System.out.println("sucess");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("catch fail");
		} finally {
//		 	out.println("파이널");
			try {
				stmt.close();
			} catch (Exception ignored) {
				
			} try {
				conn.close();
			} catch (Exception ignored){
				
			} 
		}
		String jsonResponse = "{\"message\": "+rowNum+"}";
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
		
	}

}
