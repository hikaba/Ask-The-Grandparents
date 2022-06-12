package capstone.controllerServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import capstone.dao.PostDao;
import capstone.dao.PostsInMemory;
import capstone.dao.PostsInMemoryProxy;
import capstone.model.Post;

/**
 * Servlet implementation class PostsServlet
 */
@WebServlet("/posts")
public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    PostsInMemory posts;    
    
    public PostServlet() {
        super();
        this.posts = new PostsInMemory();
//        this.logs = new ApplicationDao();
    }
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            delete(request);
        }

        String title = "";
        String content = "";
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            // Initialize id and continue with the rendering.
            id = "";
        	
        } else {
            // Read the record from memory.
            Post post = posts.readPost(id);
            if (post == null) {
                // Post not found, initialize id and continue with the rendering.
                id = "";
            } else {
                // Post found, initialize title and content.
                title = post.getTitle();
                content = post.getContent();
            }
        }


        // For MVC
        Map<UUID, Post> userPosts = this.posts.readPosts();
        request.setAttribute("userPosts", userPosts);
        request.getRequestDispatcher("/views/profilepage.jsp").forward(request, response);
    
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Post post = null;
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        if (id == null || "".equals(id)) {
            // Create the post.
            post = new Post(title, content);
        } else {
            // Read the post.
            post = posts.readPost(id);
            post.setTitle(title);
            post.setContent(content);
        }
        // Update the post.
        posts.createOrUpdatePost(post);

        closePosts(posts);

        // Process GET for rendering the page with updates.
        doGet(request, response); 
    }

    private void delete(HttpServletRequest request) throws ServletException, IOException {
    	PostsInMemory posts = openPosts();
        String id = request.getParameter("id");
        if (id != null && !id.equals("null")) {
            // Remove the post.
            posts.deletePost(id);
        }
    }

    private PostsInMemory openPosts() {

        ServletContext sc = getServletContext();
        posts = (PostsInMemory) sc.getAttribute("posts"); // getting attribute on context scope

        if (posts == null) {
            posts = new PostsInMemory();
        }
        return posts;
    }

    private void closePosts(PostsInMemory posts) {
        ServletContext sc = getServletContext();
        sc.setAttribute("posts", posts); // setting attribute on context scope
    }

}