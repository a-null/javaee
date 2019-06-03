import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.DB;


import model.User;


public class AddServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//�����������
		response.setContentType("UTF-8");//������Ӧ����
		String username=request.getParameter("username");//���jsp������û���
		String password=request.getParameter("password");//���jsp���������
		//User user=(User) request.getSession().getAttribute("user");//��session��ȡ����ǰ�û�����
		HttpSession session = request.getSession();//���session��������������Ϣ
		//////////////��װ����
		User us=new User();
		us.setUsername(username);
		us.setPassword(password);
		try {
			if(new DB().addUser(us)){
				response.sendRedirect("registersuccess.jsp");
			}
			else
				response.sendRedirect("Regster.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("user", us);///�Ѷ���us���浽session��
	}

		public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request,response);
		
	}

	

}
