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


public class MainServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");//�����������
		response.setContentType("UTF-8");//������Ӧ����
		String username=request.getParameter("username");//���jsp������û���
		String password=request.getParameter("password");//���jsp���������
		DB db=new DB();///��������ж϶���
	    HttpSession session=request.getSession();//����������Ϣ����
		User user=(User) session.getAttribute("user");
        if(user==null){//��һ�ν���
        	try {
				user=db.checkUser(username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}///����˻�������ȷ���ѷ��صĶ����׸�user������ȷ������Ϊ��
        }
        session.setAttribute("user", user);///�������
        if(user!=null){///�ж����û���������ȷ
        	response.sendRedirect("loginsuccess.jsp");//////////////////////
        }else{//����Ϊ��
        	/*request.setAttribute("msg", "��¼ʧ��");//�Ѵ�����Ϣ��ŵ�Ԥ������  
        	request.getRequestDispatcher("login.jsp").forward(request, response); why??*/
        	response.sendRedirect("login.jsp?error=yes");///���ݲ���error����ֵΪyes
        }
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
			}

}
