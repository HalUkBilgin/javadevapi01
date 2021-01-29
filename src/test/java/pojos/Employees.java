package pojos;

public class Employees {

	private String status;
	private Data data;
	private String message;

	public Employees() {
	}

	public Employees(String status, Data data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Employees [status=" + status + ", data=" + data + ", message=" + message + "]";
	}

}