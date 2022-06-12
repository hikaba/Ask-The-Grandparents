package capstone.controllerServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import capstone.dao.UserDao;
import capstone.model.User;
import capstone.model.UserBuilder;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//read the fields from the form and collect data
    	String fName = request.getParameter("first_name");
    	String lName = request.getParameter("last_name");
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String email = request.getParameter("email");
    	String birthdate = request.getParameter("birthdate");
    	
    	//create user using userBuilder
    	User user = new UserBuilder().setfName(fName).setlName(lName).setUsername(username).setPassword(password).setEmail(email).setBirthdate(birthdate).createUser();
    	
    	String destinationPage;
    	
    	//call dao layer and save user to db
    	UserDao userdao = new UserDao();
    	//checks if email is already in use
    	boolean checkEmail = userdao.doesEmailExist(user);
    	boolean checkUsername = userdao.doesUsernameExist(user);
    	System.out.println(checkUsername);

    	
    	
    	//if email is in use, it will redirect back to register page, indicating and error message
       	if(checkEmail == true) {
       		request.setAttribute("errorMessage", "An account with this email already exists");
   			destinationPage = "/views/register.jsp";
       	}
       	
      //if username is in use, it will redirect back to register page, indicating and error message
       	if(checkUsername == true) {
       		request.setAttribute("errorMessage2", "An account with this username already exists");
   			destinationPage = "/views/register.jsp";
       	}
       	
       	else {        		
       		try {
        		userdao.registerUser(user);
        	}
       		catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
        		
        	//starts a session
    		HttpSession session = request.getSession();
    		session.setAttribute("username", user.getUsername());
    		session.setAttribute("fName", user.getfName());
    		session.setAttribute("lName", user.getlName());
    		session.setAttribute("birthdate", user.getBirthdate());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("id", user.getID());
    		
    			
    		destinationPage = "/views/profilepage.jsp";	
        	}
    		
           
         
    	//redirects to destination page
    	RequestDispatcher dispatcher = request.getRequestDispatcher(destinationPage);
		dispatcher.forward(request, response);

    }
}



