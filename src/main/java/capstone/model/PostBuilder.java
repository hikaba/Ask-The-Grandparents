package capstone.model;

import java.util.Date;
import java.util.UUID;

public class PostBuilder {

    String title;
    String content;
    Date createTimestamp;
    UUID id;
    
	public PostBuilder(String title, String content, Date date, UUID uuid) {
		this.title = title;
		this.content = content;
		this.createTimestamp = date;
		this.id = uuid;
	}
	
	public PostBuilder() {
	}

	public PostBuilder setTitle(String title) {
		this.title = title;
		return this;
	}
	public PostBuilder setContent(String content) {
		this.content = content;
		return this;
	}
	public PostBuilder setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
		return this;
	}
	public PostBuilder setId(UUID uuid) {
		this.id = uuid;
		return this;
	}
	
	public Post createPost() {
		return new Post(title, content);
	}
    
}
