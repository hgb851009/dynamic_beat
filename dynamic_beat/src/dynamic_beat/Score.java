package dynamic_beat;
// 점수관리 클래스
public class Score extends Thread{
	//콤보 변수
	private boolean Combo= true;
	//이벤트당 판정되어 체점하는 점수변수
	private int Score;
	//점수 누락 변수 / 담을 변수
	private int Total , result;
	
	public void reSetcombo() {
		Combo=true;
	}
	//콤보 변환
	public boolean getCombo() {
		return Combo;
	}
	//콤보가 false(리셋) 일때 리셋 시킴
	public void reSet() {
		Combo= false;
	}
	
	
	//점수 합산
	//각 판정별 변수를 전역변수처럼 정해서 
	
	//0으로 초기화 하고 judgeEvent에서 변수가 증가하게 설정해서 한 다음

	//screendraw에서 위치 지정해놓으면 나타남
	
	
		
	public Score(boolean combo) {
		super();
		Combo = combo;
	}
	//get set 생성자
	public final int getScore() {
		return Score;
	}
	public final void setScore(int score) {
		Score = score;
	}
	public final int getTotal() {
		return Total;
	}
	public final void setTotal(int total) {
		Total = total;
	}
	public final int getResult() {
		return result;
	}
	public final void setResult(int result) {
		this.result = result;
	}
	public Score(int score, int total, int result) {
		super();
		Score = score;
		Total = total;
		this.result = result;
	}
	
}
