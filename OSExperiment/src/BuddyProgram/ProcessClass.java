package BuddyProgram;

public class ProcessClass {

	private String process_id;
	private long process_time;
	private int process_size;

	public int getProcess_size() {
		return process_size;
	}

	public void setProcess_size(int process_size) {
		this.process_size = process_size;
	}

	private int xMemlocate;
	private int yMemlocate;

	public String getProcess_id() {
		return process_id;
	}

	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}

	public long getProcess_time() {
		return process_time;
	}

	public void setProcess_time(long process_time) {
		this.process_time = process_time;
	}

	public int getxMemlocate() {
		return xMemlocate;
	}

	public void setxMemlocate(int xMemlocate) {
		this.xMemlocate = xMemlocate;
	}

	public int getyMemlocate() {
		return yMemlocate;
	}

	public void setyMemlocate(int yMemlocate) {
		this.yMemlocate = yMemlocate;
	}

}
