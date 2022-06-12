package capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import capstone.model.Post;

public class PostDao {

    public Map<UUID, Post> readPosts() {
        Post post = null;
        Map<UUID, Post> posts = new LinkedHashMap<UUID, Post>();

        try {
            // get connection to database
            Connection connection = DBConnProxy.getConnectionToDatabase();

            // write select query to get all the post
            String sql = "select * from posts;";
            PreparedStatement statement = connection.prepareStatement(sql);

            // execute query, get resultset and return User info
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                post = new Post();
                post.setId(UUID.fromString(set.getString("uuid")));
                post.setTitle(set.getString("title"));
                post.setContent(set.getString("content"));
                // post.setCreateTimestamp(Date.parse(set.getDate("createTimestamp")));
                posts.put(post.getId(), post);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return posts;
    }

    public Post readPost(String id) {
        Post post = null;
        try {
            // get connection to database
            Connection connection = DBConnProxy.getConnectionToDatabase();

            // write select query to get the post
            String sql = "SELECT * FROM posts where uuid=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);

            // execute query, get resultset and return Post info
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                post = new Post();
                post.setId(UUID.fromString(set.getString("uuid")));
                post.setTitle(set.getString("title"));
                post.setContent(set.getString("content"));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return post;
    }

    public int createPost(Post post) {
    	int rowsAffected = 0;
        try {
            // get connection to database
            Connection connection = DBConnProxy.getConnectionToDatabase();

            // write select query to get the post
            String sql = "INSERT INTO posts"
            		+ "(post_id, title, content, user_id)"
            		+ "VALUES (?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, post.getId().toString());
            statement.setString(2, post.getTitle());
            statement.setString(3, post.getContent());
            //statement.setInt(4, post.getLikes());
            statement.setInt(4, post.getAuthorID());


            // execute query, update resultset
            rowsAffected = statement.executeUpdate();
            
          //close connection affect completed
			connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return rowsAffected;
    }

    public int updatePost(Post post) {
    	int rowsAffected = 0;
        try {
            // get connection to database
            Connection connection = DBConnProxy.getConnectionToDatabase();

            // write select query to get the post
            String updateSQL = "UPDATE posts"
            		+ "SET title=?, content=? WHERE post_id=?;";
            PreparedStatement statement = connection.prepareStatement(updateSQL);
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setString(3, post.getId().toString());

            //execute query, update resultset
            rowsAffected = statement.executeUpdate();
            //close connection affect completed
			connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return rowsAffected;
    }

    public int deletePost(String id) {
    	int rowsAffected = 0;
        try {
            // get connection to database
            Connection connection = DBConnProxy.getConnectionToDatabase();

            // write select query to get the post
            String deleteSQL = "DELETE FROM posts WHERE post_id=?;";
            PreparedStatement statement = connection.prepareStatement(deleteSQL);
            statement.setString(1, UUID.fromString(id).toString());

            // execute query, update resultset
            rowsAffected = statement.executeUpdate();
            //close connection affect completed
			connection.close();


        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return rowsAffected;
    }

    public void createOrUpdatePost(Post post) {
        Post localpost = readPost(post.getId().toString());
        if (localpost == null) {
            createPost(post);
        } else {
            updatePost(post);
        }
    }

}

