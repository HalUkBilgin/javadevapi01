package pojos;

public class Todos {

	private Integer userId;
	private String title;
	private Boolean completed;

	public Todos() {
	}

	public Todos(Integer userId, String title, Boolean completed) {
		this.userId = userId;
		this.title = title;
		this.completed = completed;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "Todos [userId=" + userId + ", title=" + title + ", completed=" + completed + "]";
	}

}
