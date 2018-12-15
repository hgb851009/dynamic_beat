package dynamic_beat;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	private Image noteBasicImg = new ImageIcon(Main.class.getResource("../img/note.png")).getImage();
	// 현재 노트가 어디에 있는지 보여주기위한 좌표변수
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	// 노트가 생성되고 1초뒤에 도달할수 있도록 하는 공식(?)
	private String noteType;
	// 현재 노트의 진행여부를 체크하기위한 변수
	private boolean proceeded = true;

	// 반환 함수
	public String getnoteType() {
		return noteType;
	}

	// get 함수
	public boolean isProceeded() {
		return proceeded;
	}

	// 현재 노트가 성공적으로 입력했을때 움직이지 않도록 해주는 함수
	public void close() {
		proceeded = false;

	}

	// 생성자
	public Note(String noteType) {
		// 노트의 타입을 S로 받았을때
		// 노트타입이 어떤것인가에 따라 노트가 x좌표의 다른그림(프레임을 뿌려주게 함)
		if (noteType.equals("S")) {
			// 키페드 좌표에 맞게 노트의 좌표를 잡아준다
			x = 228;
		} else if (noteType.equals("D")) {
			x = 332;
		} else if (noteType.equals("F")) {
			x = 436;
		} else if (noteType.equals("Space")) {
			x = 540;
		} else if (noteType.equals("Space")) {
			x = 640;
		} else if (noteType.equals("J")) {
			x = 744;
		} else if (noteType.equals("K")) {
			x = 848;
		} else if (noteType.equals("L")) {
			x = 952;
		}
		// 초기화
		this.noteType = noteType;
	}

	public void screenDraw(Graphics2D g) {
		// 노트타입이 짧다면
		if (noteType.equals("Space")) { // 스페이스가 아닌경우의 출력
			g.drawImage(noteBasicImg, x, y, null);
			// 스페이스바 용 공간을 차지할수 있도록 생성 ( 기본노트 + 100 px / 스페이스바 = 100 )
			g.drawImage(noteBasicImg, x + 100, y, null);
		}
		// 노트 타입이 길다면 //스페이스일 경우
		else {
			g.drawImage(noteBasicImg, x, y, null);
		}

	}

	// 노트 떨어지는 메소드
	public void drop() {
		y += Main.NOTE_SPEED; // 노트 떨어지는 속도(7)만큼 y좌표로 떨어짐
		// 판정선보다 밑으로 내려갈 때 미스에대한 판정
		if (y > 620) { // 680 / 620 미정
			System.out.println("Miss");
			close();
		}
	}

	@Override
	public void run() {
		try {
			// 무한반복
			while (true) {
				drop();
				// 노트의 움직임이 진행중이라면
				if (proceeded) {
					// 반복적으로 쉬면서 내려보네개 하고
					Thread.sleep(Main.SLEEP_TIME);
				} else {
					// 키패드에 입력한 노트의 thread 가 정지되도록 함
					interrupt();
					break;
				}
			}
		} catch (Exception e) {
			// 오류알림 메세지
			System.out.println(e.getMessage());
		}
	}

	// 노트판정 좌표와 메세지 띄우기
	public String judge() {
			if (y >= 613) {
				System.out.println("Bad"+"Combo");
				close();
				return "Bad";
			} else if (y >= 600) {
				System.out.println("Good"+"Combo");
				close();
				return "Good";
			} else if (y >= 587) {
				System.out.println("Great"+"Combo");
				close();
				return "Great";
			} else if (y >= 573) {
				System.out.println("Perfect"+"Combo");
				close();
				return "Perfect";
			} else if (y >= 565) {
				System.out.println("Great"+"Combo");
				close();
				return "Great";
			} else if (y >= 550) {
				System.out.println("Good"+"Combo");
				close();
				return "Good";
			} else if (y >= 535) {
				System.out.println("Bad"+"Combo");
				close();
				return "Bad";
			}
		return "None";
		};

	// 현재의 y좌표를 반환해주는 함수
	public int getY() {
		return y;
	}
}
