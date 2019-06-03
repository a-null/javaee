import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

import DB.DB;


public class namerepeatServlet extends HttpServlet {

		public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");//�����������
			response.setContentType("UTF-8");//������Ӧ����
			String username=request.getParameter("username");//���jsp������û���
			String password=request.getParameter("password");//���jsp���������
			DB db=new DB();///��������ж϶���
			User user=new User();
		    user.setUsername(username);
		    user.setPassword(password);
		    try{
		    	if(db.confirmrigister(username))
		    		response.sendRedirect("Rigster.jsp?error=yes");
		    	else{
		    	    HttpSession session=request.getSession();//����������Ϣ����
                    session.setAttribute("user", user);
                    response.sendRedirect("registerconfirm.jsp");
		    	}
		    }catch(SQLException e){
		    	e.printStackTrace();
		    }
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
