package dynamic_beat;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.w3c.dom.css.Counter;

import dynamic_beat.DynamicBeat;


public class Game extends Thread {

	// 노트 라인 이미지
	private Image noteRouteLineImageLine = new ImageIcon(Main.class.getResource("../img/noteLine.png")).getImage();
	// 판정라인 이미지
	private Image LineImage = new ImageIcon(Main.class.getResource("../img/line.png")).getImage();
	// 게임인포 이미지
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../img/gameinfo.png")).getImage();
	// 배경화면 이미지 - 판넬
	private Image Background = new ImageIcon(Main.class.getResource("../img/MainScreen2.jpg")).getImage();
	// 노트 경로 ( 키위치별)
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
	// 이펙트 효과 이미지
	// private Image flareImage = new
	// ImageIcon(Main.class.getResource("../img/Flare1.png")).getImage();
	private static Image judgeImage;
	// 현재 실행할 곡의 이름을 담은 변수;
	private String titleName;
	// 난이도 설정 변수
	private String difficulty;
	private String musicTltle;
	private static Music gameMusic;
	//엔딩화면에 랭크를 담기위한 변수
	public static String result;
	//judge 클래스 초기화 
	private static Judge judge;
	
	private static int notesize=0;
	
	
	// 노트에 대한 배열들을 생성하여 관리해주도록 하기위해 선언
	static ArrayList<Note> noteList = new ArrayList<Note>();
	
	// 정보창 info 안에 점수를 담기위한 변수
	private static int RealScore = 0;
	private static Game selectedMusic;
	
	// 생성자
	public Game(String titleName, String difficulty, String musicTltle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTltle = musicTltle;
		// 한번만 실행되고 말도록
		gameMusic = new Music(this.musicTltle, false);

	}

	public void screendraw(Graphics2D g) {
		// 이미지 투명
		float alpha = (float) (7.8 * 0.1f);
		AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		g.setComposite(alcom);
		// 판정라인
		g.drawImage(LineImage, 0, 580, null);
		// 판정 맨트 (Perfect / Great / Good / Bad)
		g.drawImage(judgeImage, 460, 400, null);
		// 이펙트 효과이미지
		// g.drawImage(flareImage,320,370,null);

		// 노트떨어지는 경로
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		// 노트라인과 구분선
		g.drawImage(noteRouteLineImageLine, 224, 30, null);
		g.drawImage(noteRouteLineImageLine, 328, 30, null);
		g.drawImage(noteRouteLineImageLine, 432, 30, null);
		g.drawImage(noteRouteLineImageLine, 536, 30, null);
		g.drawImage(noteRouteLineImageLine, 740, 30, null);
		g.drawImage(noteRouteLineImageLine, 844, 30, null);
		g.drawImage(noteRouteLineImageLine, 948, 30, null);
		g.drawImage(noteRouteLineImageLine, 1052, 30, null);
		// 현재 리스트에 추가된 노트들을 하나씩 돌면서 그려주도록 반복문
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			//현재의 y좌표가 620보다 크다면
			//미스 이미지로 바꿔주도록 한다
			if(note.getY() > 620) {
				judgeImage = new ImageIcon(Main.class.getResource("../img/Miss.png")).getImage();
				judge.setScore("Miss");
			}
			// 현재 노트가 작동하고 있는 상태가 아니라면
			if (!note.isProceeded()) {
				// 노트를 삭제함
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}

		}
		// 글자 선명하게 보이게하기
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		;
		g.setFont(new Font("Arial", Font.BOLD, 30));

		// 상황판 //상황판은 투명에 영향을 받지않도록 함
		g.drawImage(gameInfoImage, 0, 660, null);
		// 곡제목 보이게
		g.setColor(Color.white);

		// 곡에대한 정보 / 난이도 String 값 띄우기 , 좌표설정
		g.drawString(titleName, 20, 702); // default 702 오차 12
		g.drawString(difficulty, 1160, 702);
		// 키패드 버튼 출력
		g.setColor(Color.WHITE);
		g.drawString("S", 270, 609); // default 609 오차 11
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		/*JLabel realScore = new JLabel("I    D");
		realScore.setHorizontalAlignment(SwingConstants.CENTER);
		realScore.setBackground(Color.BLACK);
		realScore.setFont(new Font("굴림", Font.PLAIN, 18));
		realScore.setBounds(62, 51, 111, 36);
		realScore.add(realScore);*/
		// 점수출력
		g.setColor(Color.WHITE);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		//g.drawString("1000000", 565, 702); // default 609 오차 11
		
		
	}

	// 버튼 눌렀을때의 메소드 색변화
	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../img/noteRouteP.png")).getImage();
		// 한번 눌렀을때 반복재생을 막기위해 false
		new Music("drumsmol1.mp3", false).start();
	}

	// 버튼을 누르다가 놨을때 복귀 = noteRoute.png
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
	}

	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../img/noteRouteP.png")).getImage();
		new Music("drumsmol2.mp3", false).start();
	}

	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../img/noteRouteP.png")).getImage();
		new Music("drumsmol3.mp3", false).start();
	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
	}

	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../img/noteRouteP.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../img/noteRouteP.png")).getImage();
		new Music("drumbig1.mp3", false).start();
	}

	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../img/noteRouteP.png")).getImage();
		new Music("drumsmol3.mp3", false).start();
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
	}

	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../img/noteRouteP.png")).getImage();
		new Music("drumsmol2.mp3", false).start();
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
	}

	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../img/noteRouteP.png")).getImage();
		new Music("drumsmol1.mp3", false).start();
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../img/noteRoute.png")).getImage();
	}

	@Override
	public void run() {
		dropNote(this.titleName);
	}
	//게임 종료 메소드
	public static void endGame() {
		Timer timer=new Timer();
		TimerTask tt= new TimerTask() {
			
			@Override
			public void run() {
				//클로즈 메소드를 불러옴
				close();
			}
		};
		timer.schedule(tt, 500);
	}
	// 클로즈 메소드 생성
	public static void close() {
		//게임노래 끔
		gameMusic.close();
		// result 초기화 변수에 judge 클래스의 rank판정 함수 가져오기
			result=judge.getScore();
		//랭크 결과 출력
		System.out.println("Rank  :"+result);
		//DynamicBeat 클래스의 endmain 메소드를 호출 (종료화면)
		// endMain 함수 가져오기
		//DynamicBeat.endMain();
		// 화면변환
		//this.interrupt(); // 스레드를 종료시켜주기 위해서는 인터럽트를 붙여야한다고 함...
		
	}

	/*
	 * bpm = 1분에 나오는 비트의 수 4/4 의 곡에서 bpm 이 60이라면 1박자는 1초가 됨 
	 * 첫번쩨 박자가 시작되는 시간대를
	 * 구하는것이 중요 그래야 싱크오차범위도 줄어들게 되고 비트를 구현하기 편해짐
	 */

	// 각각의 노트들이 떨어질수 있도록 하는 메소드
	public void dropNote(String titleName) {
		Beat[] beats = null; // null로 설정
		// 해당 트렉과 비교했을때 타이틀 네임이 같다면
		if (titleName.equals("Jokim Karude - Mighty Love") && difficulty.equals("Normal")) {
			// 노트의 도달시간에 영향없이 항상 S가 하나 떨어지도록
			int startTime = 4460 - Main.REACH_TIME * 1000;
			// 최소 박자의 간격 1초 =1000 / 125 약 1/8
			int gap = 125;
			beats = new Beat[] { new Beat(startTime + gap * 1, "S"), new Beat(startTime + gap * 3, "D"),
					new Beat(startTime + gap * 5, "S"), new Beat(startTime + gap * 7, "D"),
					new Beat(startTime + gap * 9, "S"), new Beat(startTime + gap * 11, "D"),
					new Beat(startTime + gap * 13, "S"), new Beat(startTime + gap * 15, "D"),
					new Beat(startTime + gap * 18, "J"), new Beat(startTime + gap * 20, "K"),
					new Beat(startTime + gap * 22, "J"), new Beat(startTime + gap * 24, "K"),
					new Beat(startTime + gap * 26, "J"), new Beat(startTime + gap * 28, "K"),
					new Beat(startTime + gap * 30, "J"), new Beat(startTime + gap * 32, "K"),
					new Beat(startTime + gap * 35, "S"), new Beat(startTime + gap * 37, "D"),
					new Beat(startTime + gap * 39, "S"), new Beat(startTime + gap * 41, "D"),
					new Beat(startTime + gap * 43, "S"), new Beat(startTime + gap * 45, "D"),
					new Beat(startTime + gap * 48, "J"), new Beat(startTime + gap * 49, "K"),
					new Beat(startTime + gap * 50, "L"), new Beat(startTime + gap * 52, "F"),
					new Beat(startTime + gap * 52, "Space"), new Beat(startTime + gap * 52, "J"),
					new Beat(startTime + gap * 54, "S"), new Beat(startTime + gap * 56, "D"),
					new Beat(startTime + gap * 59, "F"), new Beat(startTime + gap * 59, "Space"),
					new Beat(startTime + gap * 59, "J"), new Beat(startTime + gap * 61, "L"),
					new Beat(startTime + gap * 63, "K"), new Beat(startTime + gap * 65, "F"),
					new Beat(startTime + gap * 65, "Space"), new Beat(startTime + gap * 65, "J"),
					new Beat(startTime + gap * 70, "S"), new Beat(startTime + gap * 72, "S"),
					new Beat(startTime + gap * 74, "F"), new Beat(startTime + gap * 78, "J"),
					new Beat(startTime + gap * 79, "K"), new Beat(startTime + gap * 80, "L"),
					new Beat(startTime + gap * 83, "Space"), new Beat(startTime + gap * 85, "S"),
					new Beat(startTime + gap * 85, "F"), new Beat(startTime + gap * 87, "D"),
					new Beat(startTime + gap * 89, "S"), new Beat(startTime + gap * 91, "D"),
					new Beat(startTime + gap * 93, "F"), new Beat(startTime + gap * 96, "Space"),
					new Beat(startTime + gap * 98, "L"), new Beat(startTime + gap * 100, "Space"),
					new Beat(startTime + gap * 102, "S"), new Beat(startTime + gap * 103, "D"),
					new Beat(startTime + gap * 106, "Space"), new Beat(startTime + gap * 109, "Space"),
					new Beat(startTime + gap * 111, "Space"), new Beat(startTime + gap * 116, "Space"),
					new Beat(startTime + gap * 118, "S"), new Beat(startTime + gap * 119, "S"),
					new Beat(startTime + gap * 120, "F"), new Beat(startTime + gap * 123, "S"),
					new Beat(startTime + gap * 124, "D"), new Beat(startTime + gap * 125, "F"),
					new Beat(startTime + gap * 126, "J"), new Beat(startTime + gap * 127, "K"),
					new Beat(startTime + gap * 130, "J"), new Beat(startTime + gap * 133, "K"),
					new Beat(startTime + gap * 136, "L"), new Beat(startTime + gap * 138, "S"),
					new Beat(startTime + gap * 140, "Space"), new Beat(startTime + gap * 142, "S"),
					new Beat(startTime + gap * 144, "Space"), new Beat(startTime + gap * 146, "Space"),
					new Beat(startTime + gap * 150, "Space"), new Beat(startTime + gap * 152, "Space"),
					new Beat(startTime + gap * 157, "J"), new Beat(startTime + gap * 161, "K"),
					new Beat(startTime + gap * 165, "L"), new Beat(startTime + gap * 167, "S"),
					new Beat(startTime + gap * 171, "F"), new Beat(startTime + gap * 174, "S"),
					new Beat(startTime + gap * 176, "D"), new Beat(startTime + gap * 178, "F"),
					new Beat(startTime + gap * 180, "Space"), new Beat(startTime + gap * 181, "L"),
					new Beat(startTime + gap * 184, "Space"), new Beat(startTime + gap * 187, "L"),
					new Beat(startTime + gap * 188, "K"), new Beat(startTime + gap * 189, "J"),
					new Beat(startTime + gap * 192, "S"), new Beat(startTime + gap * 196, "D"),
					new Beat(startTime + gap * 196, "Space"), new Beat(startTime + gap * 200, "S"),
					new Beat(startTime + gap * 200, "Space"), new Beat(startTime + gap * 207, "J"),
					new Beat(startTime + gap * 207, "Space"), new Beat(startTime + gap * 211, "K"),
					new Beat(startTime + gap * 216, "L"), new Beat(startTime + gap * 216, "Space"),
					new Beat(startTime + gap * 218, "Space"), new Beat(startTime + gap * 221, "J"),
					new Beat(startTime + gap * 223, "K"), new Beat(startTime + gap * 225, "L"),
					new Beat(startTime + gap * 227, "S"), new Beat(startTime + gap * 227, "Space"),
					new Beat(startTime + gap * 231, "D"), new Beat(startTime + gap * 231, "Space"),
					new Beat(startTime + gap * 235, "S"), new Beat(startTime + gap * 235, "Space"),
					new Beat(startTime + gap * 242, "S"), new Beat(startTime + gap * 242, "Space"),
					new Beat(startTime + gap * 242, "L"), new Beat(startTime + gap * 246, "D"),
					new Beat(startTime + gap * 246, "Space"), new Beat(startTime + gap * 246, "K"),
					new Beat(startTime + gap * 250, "F"), new Beat(startTime + gap * 250, "Space"),
					new Beat(startTime + gap * 250, "J"), new Beat(startTime + gap * 255, "J"),
					new Beat(startTime + gap * 257, "K"), new Beat(startTime + gap * 259, "K"),
					new Beat(startTime + gap * 262, "S"), new Beat(startTime + gap * 262, "Space"),
					new Beat(startTime + gap * 266, "D"), new Beat(startTime + gap * 266, "Space"),
					new Beat(startTime + gap * 270, "S"), new Beat(startTime + gap * 270, "Space"),
					new Beat(startTime + gap * 275, "J"), new Beat(startTime + gap * 277, "K"),
					new Beat(startTime + gap * 279, "L"), new Beat(startTime + gap * 282, "J"),
					new Beat(startTime + gap * 284, "K"), new Beat(startTime + gap * 286, "L"),
					new Beat(startTime + gap * 289, "J"), new Beat(startTime + gap * 291, "K"),
					new Beat(startTime + gap * 293, "L"), new Beat(startTime + gap * 295, "J"),
					new Beat(startTime + gap * 297, "F"), new Beat(startTime + gap * 299, "D"),
					new Beat(startTime + gap * 301, "S"), new Beat(startTime + gap * 304, "F"),
					new Beat(startTime + gap * 306, "D"), new Beat(startTime + gap * 308, "S"),
					new Beat(startTime + gap * 310, "F"), new Beat(startTime + gap * 312, "D"),
					new Beat(startTime + gap * 314, "S"), new Beat(startTime + gap * 317, "F"),
					new Beat(startTime + gap * 319, "D"), new Beat(startTime + gap * 321, "S"),
					new Beat(startTime + gap * 323, "F"), new Beat(startTime + gap * 325, "D"),
					new Beat(startTime + gap * 327, "S"), new Beat(startTime + gap * 330, "F"),
					new Beat(startTime + gap * 332, "S"), new Beat(startTime + gap * 332, "Space"),
					new Beat(startTime + gap * 336, "D"), new Beat(startTime + gap * 336, "Space"),
					new Beat(startTime + gap * 340, "S"), new Beat(startTime + gap * 340, "Space"),
					new Beat(startTime + gap * 348, "Space"),
					new Beat(startTime + gap * 356, "Space"),
					new Beat(startTime + gap * 364, "Space"),
					new Beat(startTime + gap * 372, "Space"),
					new Beat(startTime + gap * 380, "Space"),
					new Beat(startTime + gap * 460, "Space"),
					new Beat(startTime + gap * 484, "Space"),
					new Beat(startTime + gap * 492, "Space"),
					new Beat(startTime + gap * 500, "Space"),
					new Beat(startTime + gap * 508, "Space"),
					new Beat(startTime + gap * 516, "Space"),
					new Beat(startTime + gap * 524, "Space"),
					new Beat(startTime + gap * 532, "Space"),
					new Beat(startTime + gap * 540, "Space"),
					new Beat(startTime + gap * 548, "Space"),
					new Beat(startTime + gap * 552, "Space"),
					new Beat(startTime + gap * 556, "Space"),
					new Beat(startTime + gap * 560, "S"),
					new Beat(startTime + gap * 564, "D"),
					new Beat(startTime + gap * 568, "F"),
					new Beat(startTime + gap * 572, "J"),
					new Beat(startTime + gap * 576, "K"),
					new Beat(startTime + gap * 580, "L"),
					new Beat(startTime + gap * 588, "Space"),
					new Beat(startTime + gap * 590, "F"),
					new Beat(startTime + gap * 592, "S"),
					new Beat(startTime + gap * 594, "F"),
					new Beat(startTime + gap * 596, "S"),
					new Beat(startTime + gap * 598, "F"),
					new Beat(startTime + gap * 600, "S"),
					new Beat(startTime + gap * 602, "F"),
					new Beat(startTime + gap * 604, "S"),
					new Beat(startTime + gap * 606, "J"),
					new Beat(startTime + gap * 608, "L"),
					new Beat(startTime + gap * 610, "J"),
					new Beat(startTime + gap * 612, "L"),
					new Beat(startTime + gap * 614, "J"),
					new Beat(startTime + gap * 616, "L"),
					new Beat(startTime + gap * 618, "J"),
					new Beat(startTime + gap * 620, "L"),
					new Beat(startTime + gap * 622, "D"),
					new Beat(startTime + gap * 624, "K"),
					new Beat(startTime + gap * 626, "D"),
					new Beat(startTime + gap * 628, "K"),
					new Beat(startTime + gap * 630, "D"),
					new Beat(startTime + gap * 632, "K"),
					new Beat(startTime + gap * 634, "D"),
					new Beat(startTime + gap * 636, "K"),
					new Beat(startTime + gap * 638, "K"),
					new Beat(startTime + gap * 640, "D"),
					new Beat(startTime + gap * 642, "K"),
					new Beat(startTime + gap * 644, "D"),
					new Beat(startTime + gap * 646, "K"),
					new Beat(startTime + gap * 648, "D"),
					new Beat(startTime + gap * 650, "K"),
					new Beat(startTime + gap * 652, "D"),
					new Beat(startTime + gap * 652, "Space"),
					new Beat(startTime + gap * 652, "K"),
					new Beat(startTime + gap * 654, "L"),
					new Beat(startTime + gap * 655, "S"),
					new Beat(startTime + gap * 657, "D"),
					new Beat(startTime + gap * 659, "F"),
					new Beat(startTime + gap * 661, "J"),
					new Beat(startTime + gap * 663, "K"),
					new Beat(startTime + gap * 655, "L"),
					new Beat(startTime + gap * 655, "Space"),
					new Beat(startTime + gap * 655, "S"),
					new Beat(startTime + gap * 658, "Space"),
					new Beat(startTime + gap * 661, "F"),
					new Beat(startTime + gap * 664, "J"),
					new Beat(startTime + gap * 667, "D"),
					new Beat(startTime + gap * 670, "K"),
					new Beat(startTime + gap * 673, "S"),
					new Beat(startTime + gap * 676, "L"),
					new Beat(startTime + gap * 679, "J"),
					new Beat(startTime + gap * 682, "F"),
					new Beat(startTime + gap * 684, "K"),
					new Beat(startTime + gap * 686, "L"),
					new Beat(startTime + gap * 688, "S"),
					new Beat(startTime + gap * 690, "L"),
					new Beat(startTime + gap * 692, "K"),
					new Beat(startTime + gap * 694, "J"),
					new Beat(startTime + gap * 696, "S"),
					new Beat(startTime + gap * 698, "D"),
					new Beat(startTime + gap * 700, "F"),
					new Beat(startTime + gap * 702, "F"),
					new Beat(startTime + gap * 704, "J"),
					new Beat(startTime + gap * 706, "D"),
					new Beat(startTime + gap * 708, "K"),
					new Beat(startTime + gap * 710, "S"),
					new Beat(startTime + gap * 712, "L"),
					new Beat(startTime + gap * 716, "Space"),
					new Beat(startTime + gap * 720, "F"),
					new Beat(startTime + gap * 724, "J"),
					new Beat(startTime + gap * 728, "L"),
					new Beat(startTime + gap * 732, "S"),
					new Beat(startTime + gap * 736, "F"),
					new Beat(startTime + gap * 740, "S"),
					new Beat(startTime + gap * 744, "F"),
					new Beat(startTime + gap * 748, "S"),
					new Beat(startTime + gap * 752, "S"),
					new Beat(startTime + gap * 755, "F"),
					new Beat(startTime + gap * 758, "L"),
					new Beat(startTime + gap * 761, "J"),
					new Beat(startTime + gap * 764, "L"),
					new Beat(startTime + gap * 767, "J"),
					new Beat(startTime + gap * 770, "L"),
					new Beat(startTime + gap * 774, "L"),
					new Beat(startTime + gap * 777, "J")
					
					// 노트 자동생성 알고리즘 참고할것
			};
			notesize=beats.length;
		} else if (titleName.equals("Rihanna - Take A Bow") && difficulty.equals("Normal")) {
			int startTime = 1000;
			beats = new Beat[] { new Beat(startTime, "Space") };
			notesize=beats.length;
		}
		else if (titleName.equals("canon")) {
				int startTime = 1000 - Main.REACH_TIME * 1000;
				int gap = 125;
				beats = new Beat[] { new Beat(startTime + gap * 10, "K"), 
						new Beat(startTime + gap * 22, "F"),
						new Beat(startTime + gap * 33, "J"), 
						new Beat(startTime + gap * 44, "D"),
						new Beat(startTime + gap * 55, "J"),
						new Beat(startTime + gap * 66, "K"),
						new Beat(startTime + gap * 77, "D"),
						new Beat(startTime + gap * 88, "K"),
						new Beat(startTime + gap * 99, "F"),
						new Beat(startTime + gap * 99, "J"),
						
				};
				notesize=beats.length;
		}
		int i = 0;
		judge = new Judge(beats.length);
		// 배열이 초기화 되는데서 오는 시간격차를 줄이기 위한 코드위치
		// 초기화가 끝나고 바로 음악이 실행 되면서 배열에 담긴 비트가 떨어지게 됨
		gameMusic.start();
		// 현재 i가 bteats.leng 보다 작고 인터럽트가 이루어진 상태가 아닐때(?)
		while (i < beats.length && !interrupted()) {
			boolean dropped = false;
			// beats 배열에 타임을 얻게 된 값=비트가떨어지는 시간대
			// 게임뮤직의 gettime 보다 작다면
			if (beats[i].getTime() <= gameMusic.getTime()) {
				// 한개의 노트를 선언하여 현재의 비트에서 노트네임(혜당하는 키)을 얻어올수 있도록 함
				Note note = new Note(beats[i].getNoteName());
				// 얻은 노트를 떨어트림
				note.start();
				noteList.add(note);
				// 각각의 키패드에 혜당되는 노트들을 +1 씩 시킴
				i++;

				// 요약 : 현재 곡이 재생되는 시점을 실시간으로 파악하여
				// 각각의 키폐드에 위치한 노트들을 접근을 하여 떨어트리도록 함

				dropped = true;
			}
			if (!dropped) {
				try {
					// dropped 가 false 값일때 0.005 초 이후에 나오도록 한다 / 무한정 반복을 방지
					Thread.sleep(5);// 0.005초를 쉬게함
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		// 테스팅 로컬변수
		/*
		 * Note note = new Note(228, "short"); note.start(); noteList.add(note);
		 */

		/*
		 * noteList.add(new Note(228 , 120, "short")); //S noteList.add(new Note(332 ,
		 * 500, "short")); //D noteList.add(new Note(436 , 500, "short")); //F
		 * noteList.add(new Note(540 , 340, "long")); //Space noteList.add(new Note(744
		 * , 325, "short")); //J noteList.add(new Note(848 , 305, "short")); //K
		 * noteList.add(new Note(952 , 305, "short")); //L
		 */ }
	
	// 판정함수
	public static String judge(String input) {
		int sum_perfect=100;
		int sum_great=90;
		int sum_good=70;
		int sum_bad=50;
		int sum;
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			// 입력한 노트가 떨어지는 노트타입과 일치할때
			if (input.equals(note.getnoteType())) {
				String empty=" ";
				judgeEvent(empty=note.judge()); // 판정라인에 닿았을때 점수를 체크하며 노트를 사라지게하는 메소드
				//  판정 부분에 따른 이미지 호출
				judge.setScore(empty.toString());
				break;
			}
		}
		return input;
	}
	//판정에 대한 이벤트 메소드
	public static void judgeEvent(String judge) {
		//각 판정에 대한 이미지를 불러옴
		if(judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../img/None.png")).getImage();
		}
		else if(judge.equals("Bad")) {
			judgeImage = new ImageIcon(Main.class.getResource("../img/Bad.png")).getImage();
		}
		else if(judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../img/Good.png")).getImage();
		}
		else if(judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../img/Great.png")).getImage();
		}
		else if(judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../img/Perfect.png")).getImage();
		}
		else if(judge.equals("None")) {
		}
		
	}
}
