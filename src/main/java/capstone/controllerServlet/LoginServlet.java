package capstone.controllerServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import capstone.dao.LoginDao;
import capstone.model.Login;
import capstone.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean check;
		//read fields from the login form and collect data
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//fill it into login bean
		Login loginuser = new Login();
		loginuser.setUsername(username);
		loginuser.setPassword(password);
		
		//call dao layer and validate user (checking if in db)
		//LoginDao logindao = new LoginDao();
		String destinationPage;
		
		check = LoginDao.validateUser(loginuser);
		
		//if user credentials are correct
		if(check == true) {
			//create a user object from login information
			
			User user = LoginDao.getUserFromLogin(loginuser);
			//starts a session
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUsername());	
			session.setAttribute("password", user.getPassword());
			session.setAttribute("fName", user.getfName());
			session.setAttribute("lName", user.getlName());
			session.setAttribute("birthdate", user.getBirthdate());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("id", user.getID());
			
	    	
			

			//destinationPage = "/WEB-INF/views/profilepage.jsp";		
			destinationPage = "/newsfeed";		

			
			}
		else {
			//redirects back to login and indicates error message
			request.setAttribute("errorMessage", "Incorrect username or password!");
			destinationPage = "/views/login.jsp";
			
			}
		RequestDispatcher dispatcher = request.getRequestDispatcher(destinationPage);
		dispatcher.forward(request, response);
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		
	
	}


}
