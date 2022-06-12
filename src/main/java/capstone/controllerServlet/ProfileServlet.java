package capstone.controllerServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import capstone.dao.PostDao;
import capstone.model.Post;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String destinationPage;
		HttpSession session = request.getSession();
		
		//checks session to see if user is already logged in
		if(session.getAttribute("username") != null) {
			//System.out.println(session.getAttribute("id"));
			destinationPage = "/views/profilepage.jsp";
			processRequest(request, response);
		}
		else {
			destinationPage = "/views/login.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(destinationPage);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		//read the fields from the form and collect data
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession();
		//fill it into login bean
		Post post = new Post(title, content);
		post.setAuthorID((int) session.getAttribute("id"));
		
		//call dao layer and saving post into db
		PostDao postdao = new PostDao();
		
		postdao.createPost(post);

	}

}
