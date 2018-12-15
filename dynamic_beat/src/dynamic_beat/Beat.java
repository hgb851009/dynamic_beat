package dynamic_beat;

//비트와 노트관리 클래스
public class Beat {
	private int time;
	// 노트의 종류
	private String noteName;

	// get set
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	// 생성자
	public Beat(int time, String noteName) {
		super();
		this.time = time;
		this.noteName = noteName;
	}

}
