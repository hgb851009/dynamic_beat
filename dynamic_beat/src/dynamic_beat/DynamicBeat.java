package dynamic_beat;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Closeable;
import java.rmi.Remote;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.OptionPaneUI;

public class DynamicBeat extends JFrame {

	protected static final int JButton = 0;
	protected static final int OK_CANCEL_OPTION = 0;
	public static int TotalScore;
	// 전체 화면에 지속적인 버퍼를 위한 인스턴스
	private Image screenImage;
	private Graphics screenGraphic;
	// 버튼이미지
	private ImageIcon startenterbtnimg = new ImageIcon(Main.class.getResource("../img/startenterbtn.png"));
	private static ImageIcon startbtnimg = new ImageIcon(Main.class.getResource("../img/startbtn.png"));
	private ImageIcon rankenterbtnimg = new ImageIcon(Main.class.getResource("../img/rankenterbtn.png"));
	private static ImageIcon rankbtnimg = new ImageIcon(Main.class.getResource("../img/rankbtn.png"));
	private ImageIcon helpenterbtnimg = new ImageIcon(Main.class.getResource("../img/helpenterbtn.png"));
	private static ImageIcon helpbtnimg = new ImageIcon(Main.class.getResource("../img/helpbtn.png"));
	private ImageIcon quitenterbtnimg = new ImageIcon(Main.class.getResource("../img/quitenterbtn.png"));
	private static ImageIcon quitbtnimg = new ImageIcon(Main.class.getResource("../img/quitbtn.png"));
	private ImageIcon leftenterbtnimg = new ImageIcon(Main.class.getResource("../img/leftenterbtn.png"));
	private static ImageIcon leftbtnimg = new ImageIcon(Main.class.getResource("../img/leftbtn.png"));
	private ImageIcon rightenterbtnimg = new ImageIcon(Main.class.getResource("../img/rightenterbtn.png"));
	private static ImageIcon rightbtnimg = new ImageIcon(Main.class.getResource("../img/rightbtn.png"));

	private ImageIcon easyenterbtnimg = new ImageIcon(Main.class.getResource("../img/easyenterbtn.png"));
	private static ImageIcon easybtnimg = new ImageIcon(Main.class.getResource("../img/easybtn.png"));
	private ImageIcon normalenterbtnimg = new ImageIcon(Main.class.getResource("../img/normalenterbtn.png"));
	private static ImageIcon normalbtnimg = new ImageIcon(Main.class.getResource("../img/normalbtn.png"));
	private ImageIcon hardenterbtnimg = new ImageIcon(Main.class.getResource("../img/hardenterbtn.png"));
	private static ImageIcon hardbtnimg = new ImageIcon(Main.class.getResource("../img/hardbtn.png"));

	private static ImageIcon backenterbtnimg = new ImageIcon(Main.class.getResource("../img/backenterbtn.png"));
	private static ImageIcon backbtnimg = new ImageIcon(Main.class.getResource("../img/backbtn.png"));
	// 랭크구분과 이미지 가져오기
	private static Image Rank;
	// 배경화면 이미지 - 판넬
	private static Image Background = new ImageIcon(Main.class.getResource("../img/MainScreen2.jpg")).getImage();
	// 메인화면 메뉴바
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../img/menubar.png")));
	// 메뉴바의 exit버튼
	private static JButton exitBtn = new JButton(new ImageIcon(Main.class.getResource("../img/exitbtn.png")));
	// 메인화면 버튼
	private static JButton startbtn = new JButton(startbtnimg);
	private static JButton rankbtn = new JButton(rankbtnimg);
	private static JButton helpbtn = new JButton(helpbtnimg);
	private static JButton quitbtn = new JButton(quitbtnimg);
	private static JButton leftbtn = new JButton(leftbtnimg);
	private static JButton rightbtn = new JButton(rightbtnimg);
	private static JButton easybtn = new JButton(easybtnimg);
	private static JButton normalbtn = new JButton(normalbtnimg);
	private static JButton hardbtn = new JButton(hardbtnimg);
	private static JButton backbtn = new JButton(backbtnimg);

	// 메뉴바 드레그할때 창 움직이도록 하기
	private int mouseX, mouseY; // 마우스 포인터의 좌표변수명

	// 화면전환 변수 메인화면이 아니기때문에 false 로 선언함 변환할때 true로 바뀜
	private static boolean isMainScreen = false;
	// 게임화면으로 넘어왔는지에 대한 변수
	private static boolean isGameScreen = false;
	// 앤딩화면
	private static boolean isEndScreen = false;

	// Track 클래스를 이용하기 위해 ArrayList로 선언
	static ArrayList<Track> tracklist = new ArrayList<Track>();

	// 코딩 줄이며 메소드 만들기
	private static Image titleImage;
	private static Image selectedImage;
	private static Music selectedMusic;
	// 메인화면 인트로뮤직 삽입 / on off기능 추가할것
	private static Music introMusic = new Music("introMusic.mp3", true);
	private int nowSelected = 0; // 현재 선택된 곡 = 트렉번호

	// Game class 변수 초기화
	public static Game game;

	// ----------------------------------------------------------------
	// 생성자
	public DynamicBeat() {
		// 프로그램의 로딩을 고려하여 트렉리스트의 add가 이뤄지지 않았는데 버튼이벤트가 발생하여
		// 혜당 곡을 실행하도록 만든다면 오류가 발생됨

		// 한곡 보여질때 출력되는 이미지와 노래 경로 넣어주기 / 생성자 불러오기
		// 메인 테스팅곡 track 0
		tracklist.add(new Track("titleimg_track1.png", "9 game image.jpg", "7 Game image.jpg",
				"Jokim Karude - Mighty Love.mp3", "Jokim Karude - Mighty Love.mp3", "Jokim Karude - Mighty Love"));
		// Take A Bow track 1
		tracklist.add(new Track("titleimg_track2.png", "Null Start image.png", "5 Game image.jpg",
				"Rihanna - Take A Bow.mp3", "Rihanna - Take A Bow.mp3", "Rihanna - Take A Bow"));
		// 왕벌 track 2
		tracklist.add(new Track("null tltle image.png", "Null Start image1.png", "9 Game image.jpg",
				"The Flight Of The Bumble Bee.mp3", "The Flight Of The Bumble Bee.mp3",
				"The Flight Of The Bumble Bee"));
		// Stronger track 3
		tracklist.add(new Track("null tltle image.png", "8 game image.png", "8 game image.jpg",
				"Kanye West - Stronger.mp3", "Kanye West - Stronger.mp3", "Kanye West - Stronger"));
		// Stronger track 4
		
		// Take A Bow track 1
		tracklist.add(new Track("null tltle imageCanon.png", "Null Start imageCanon.png", "5 Game image.jpg",
				"canon.mp3", "canon.mp3", "canon"));

		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HERIGHT);
		// 만들어진 창 크기조정 불가
		setResizable(false);
		// 중앙에 창 고정시키기
		setLocationRelativeTo(null);
		// 종료시 프로그램 모두 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
		setBackground(new Color(0, 0, 0, 0)); // paintComponents 했을때 배경이 흰색으로 보이게하기
		setLayout(null); // 버튼과 라벨을 같은 위치에 세팅
		// 키입력받을때의 이벤트
		addKeyListener(new KeyListener());

		// 메인화면 인트로뮤직 삽입 / on off기능 추가할것
		Music introMusic = new Music("introMusic.mp3", true);
		introMusic.start();

		// exit 버튼 세팅
		exitBtn.setBounds(1245, 0, 30, 30);
		exitBtn.setBorderPainted(false);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setFocusPainted(false);
		exitBtn.addMouseListener(new MouseAdapter() {
			// 종료 버튼 눌렀을때의 이벤트
			@Override
			public void mousePressed(MouseEvent e) {

				// 종료 메세지 띄우기
				int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "E X I T", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
					System.exit(0);
				else if (result == JOptionPane.CANCEL_OPTION)
					;
			}
		});
		add(exitBtn);

		// 시작버튼 이벤트
		startbtn.setBounds(40, 220, 310, 90);
		startbtn.setBorderPainted(false);
		startbtn.setContentAreaFilled(false);
		startbtn.setFocusPainted(false);
		startbtn.addMouseListener(new MouseAdapter() {
			// 마우스 버튼에 닿을때 이벤트
			@Override
			public void mouseEntered(MouseEvent e) {
				startbtn.setIcon(startenterbtnimg);
				startbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 마우스가 버튼에 없을때 베이스유지
			@Override
			public void mouseExited(MouseEvent e) {
				startbtn.setIcon(startbtnimg);
				startbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 시작 버튼 눌렀을때의 이벤트
			@Override
			public void mousePressed(MouseEvent e) {
				// 게임시작 이벤트-> enterMain 메소드 호출
				enterMain();
				introMusic.close();

			}
		});
		add(startbtn);

		// 랭킹버튼 이벤트
		rankbtn.setBounds(40, 330, 310, 90);
		rankbtn.setBorderPainted(false);
		rankbtn.setContentAreaFilled(false);
		rankbtn.setFocusPainted(false);
		rankbtn.addMouseListener(new MouseAdapter() {
			// 마우스 버튼에 닿을때 이벤트
			@Override
			public void mouseEntered(MouseEvent e) {
				rankbtn.setIcon(rankenterbtnimg);
				rankbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 마우스가 버튼에 없을때 베이스유지
			@Override
			public void mouseExited(MouseEvent e) {
				rankbtn.setIcon(rankbtnimg);
				rankbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 랭킹 버튼 눌렀을때의 이벤트
			@Override
			public void mousePressed(MouseEvent e) {
				// 새창 띄우고 랭킹 보여주기
				JOptionPane.showMessageDialog(null, "서비스 준비중 입니다.", "R A N K", JOptionPane.OK_OPTION);
			}
		});
		add(rankbtn);

		// 도움말버튼 이벤트
		helpbtn.setBounds(40, 440, 310, 90);
		helpbtn.setBorderPainted(false);
		helpbtn.setContentAreaFilled(false);
		helpbtn.setFocusPainted(false);
		helpbtn.addMouseListener(new MouseAdapter() {
			// 마우스 버튼에 닿을때 이벤트
			@Override
			public void mouseEntered(MouseEvent e) {
				helpbtn.setIcon(helpenterbtnimg);
				helpbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 마우스가 버튼에 없을때 베이스유지
			@Override
			public void mouseExited(MouseEvent e) {
				helpbtn.setIcon(helpbtnimg);
				helpbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 도움말 버튼 눌렀을때의 이벤트
			@Override
			public void mousePressed(MouseEvent e) {
				// 새창 띄우고 게임도움말,제작자 프로필 ,업데이트,버전정보 등....
				JOptionPane.showMessageDialog(null, "서비스 준비중 입니다.", "H E L P", JOptionPane.OK_OPTION);
				
			}
		});
		add(helpbtn);

		// 종료버튼 이벤트
		quitbtn.setBounds(40, 550, 310, 90);
		quitbtn.setBorderPainted(false);
		quitbtn.setContentAreaFilled(false);
		quitbtn.setFocusPainted(false);
		quitbtn.addMouseListener(new MouseAdapter() {
			// 마우스 버튼에 닿을때 이벤트
			@Override
			public void mouseEntered(MouseEvent e) {
				quitbtn.setIcon(quitenterbtnimg);
				quitbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 마우스가 버튼에 없을때 베이스유지
			@Override
			public void mouseExited(MouseEvent e) {
				quitbtn.setIcon(quitbtnimg);
				quitbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 종료 버튼 눌렀을때의 이벤트
			@Override
			public void mousePressed(MouseEvent e) {
				// 종료 메세지 띄우기
				int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "E X I T", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
					System.exit(0);
				else if (result == JOptionPane.CANCEL_OPTION)
					;
			}
		});
		add(quitbtn);

		// left 버튼 이벤트
		leftbtn.setVisible(false);
		leftbtn.setBounds(140, 300, 80, 80);
		leftbtn.setBorderPainted(false);
		leftbtn.setContentAreaFilled(false);
		leftbtn.setFocusPainted(false);
		leftbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftbtn.setIcon(leftenterbtnimg);
				leftbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftbtn.setIcon(leftbtnimg);
				leftbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// left 버튼 눌렀을때의 이벤트
				selectLeft();
			}
		});
		add(leftbtn);

		// right 버튼 이벤트
		rightbtn.setVisible(false);
		rightbtn.setBounds(1080, 300, 80, 80);
		rightbtn.setBorderPainted(false);
		rightbtn.setContentAreaFilled(false);
		rightbtn.setFocusPainted(false);
		rightbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightbtn.setIcon(rightenterbtnimg);
				rightbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightbtn.setIcon(rightbtnimg);
				rightbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// right 버튼 눌렀을때의 이벤트
				selectRight();
			}
		});
		add(rightbtn);

		// easy 버튼 이벤트
		easybtn.setVisible(false);
		easybtn.setBounds(250, 600, 200, 70);
		easybtn.setBorderPainted(false);
		easybtn.setContentAreaFilled(false);
		easybtn.setFocusPainted(false);
		easybtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easybtn.setIcon(easyenterbtnimg);
				easybtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easybtn.setIcon(easybtnimg);
				easybtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// easy 버튼 눌렀을때의 이벤트 - 난이도 쉬움
				gameStart(nowSelected, "Easy");
			}
		});
		add(easybtn);
		
		// normal 버튼 이벤트
		normalbtn.setVisible(false);
		normalbtn.setBounds(530, 600, 200, 70);
		normalbtn.setBorderPainted(false);
		normalbtn.setContentAreaFilled(false);
		normalbtn.setFocusPainted(false);
		normalbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				normalbtn.setIcon(normalenterbtnimg);
				normalbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				normalbtn.setIcon(normalbtnimg);
				normalbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// normalbtn 버튼 눌렀을때의 이벤트 - 난이도 중간
				gameStart(nowSelected, "Normal");
			}
		});
		add(normalbtn);

		// hard 버튼 이벤트
		hardbtn.setVisible(false);
		hardbtn.setBounds(780, 600, 200, 70);
		hardbtn.setBorderPainted(false);
		hardbtn.setContentAreaFilled(false);
		hardbtn.setFocusPainted(false);
		hardbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardbtn.setIcon(hardenterbtnimg);
				hardbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardbtn.setIcon(hardbtnimg);
				hardbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// hard 버튼 눌렀을때의 이벤트 - 난이도 짱짱 어렵게
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardbtn);

		// back 버튼 이벤트
		backbtn.setVisible(false);
		backbtn.setBounds(20, 50, 62, 62);
		backbtn.setBorderPainted(false);
		backbtn.setContentAreaFilled(false);
		backbtn.setFocusPainted(false);
		backbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backbtn.setIcon(backenterbtnimg);
				backbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backbtn.setIcon(backbtnimg);
				backbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// back 버튼 눌렀을때의 이벤트
				// 메인화면으로 돌아가는 이벤트
				backMain();
				// 종료되면서 음악
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		});
		add(backbtn);

		// 메뉴바의 크기와 좌표
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			// 마우스를 눌렀을때의 이벤트
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseX = e.getX(); // x좌표 얻어오기
				mouseY = e.getY(); // y좌표 얻어오기
			}
		});

		// 누르고 드레그할때의 이벤트
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {

			/*
			 * @Override public void mouseMoved(MouseEvent e) { }
			 */

			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

	}

	// 창안에 초기화면 뿌리기
	public void paint(Graphics g) {

		// 그래픽 객체 얻어오기 (창안에 화면 가져오기위함)
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HERIGHT);
		// 스크린 이미지를 이용하여 그래픽 객체를 얻어옴
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null); // 이미지 좌표

	}

	// 판정별 점수체크
	public static void TotalScore() {
		int perfect = 100;
		int great = 90;
		int good = 70;
		int bad = 50;
		if (Judge.perfect != 0) {
			perfect = Judge.perfect * 100;
		} else if (Judge.great != 0) {
			great = Judge.great * 90;
		} else if (Judge.good != 0) {
			good = Judge.good * 70;
		} else if (Judge.bad != 0) {
			bad = Judge.bad * 50;
		} else {
			TotalScore = perfect + great + good + bad;
		}
	}

	// ---------------------------------------------------------------

	// 초기화면 이후에 이미지를 지속적으로 뿌리게 해줌(?)
	public void screenDraw(Graphics2D g) {
		// 이미지를 일정시간&동적으로 그려지게 할수있는 함수
		g.drawImage(Background, 0, 0, null);
		// isMainScreen == true 일때 selectedImage(곡마다 정해진 타이틀 이미지) 불러오기
		if (isMainScreen) {
			// 곡에대한 이미지
			g.drawImage(selectedImage, 340, 100, null);
			// 곡 제목과 아티스트
			g.drawImage(titleImage, 340, 30, null);
			// 제목과 아티스트를 텍스트필드로 변하게 하려면 어떻게????????
		}
		// 게임정보=gameinfo 화면 출력설정
		else if (isGameScreen) {
			game.screendraw(g);
		}
		// 앤딩화면 출력설정
		else if (isEndScreen) {
			// game 클래스의 endgame 메소드 호출
			g.drawImage(Rank, 0, 100, null);
			g.setColor(Color.white);
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.setFont(new Font("Arial", Font.BOLD, 50));
			// judge 클래스의 문자열을 반환하여 가져옴 스코어출력의 위치
			g.drawString(" : " + String.valueOf(Judge.perfect * 100), 660, 190);
			g.drawString(" : " + String.valueOf(Judge.great * 90), 660, 270);
			g.drawString(" : " + String.valueOf(Judge.good * 70), 660, 340);
			g.drawString(" : " + String.valueOf(Judge.bad * 50), 660, 420);
			g.drawString(" : " + String.valueOf(Judge.miss * 0), 660, 490);
			// Total???????????
			g.drawString(" : " + String.valueOf(
					(Judge.perfect*100)+(Judge.great*90)+(Judge.good*70)+(Judge.bad*50)+(Judge.miss*0)), 660, 640);
		}

		paintComponents(g); // 메뉴바 이미지를 정적으로 보여지기 위 한 함수 = 고정 (add)
		/*
		 * try { Thread.sleep(5); } catch (Exception e) { e.printStackTrace(); }
		 */
		this.repaint(); // JFrame 상속받은 gui 에서 첫번쩨로 보여주는 함수

	}

	// 현재 선택된 곡에대한 넘버링과 이벤트처리
	public static void selectTrack(int nowSelected) {
		// 곡이 실행될때 닫게함
		if (selectedMusic != null)
			selectedMusic.close();
		// 이미지
		titleImage = new ImageIcon(Main.class.getResource("../img//" + tracklist.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../img//" + tracklist.get(nowSelected).getStartImage()))
				.getImage();
		// 음악
		selectedMusic = new Music(tracklist.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	// 왼쪽 오른쪽 버튼 눌렀을때의 이벤트처리
	public void selectLeft() {
		// 0번쪠 곡일때 nowselect 를 왼족으로 이동시키기
		if (nowSelected == 0)
			nowSelected = tracklist.size() - 1;
		// 왼쪽의 곡이 아닐때 정상작동시키기
		else
			nowSelected--;
		selectTrack(nowSelected);
	}

	public void selectRight() {
		// nowselect가 가장 오른쪽 곡이라면 첫번쩨 곡으로 이동하도록 바꾸기
		if (nowSelected == tracklist.size() - 1)
			nowSelected = 0;
		// 그렇지않을경우 트렉의 위치를 오른쪽으로 이동
		else
			nowSelected++;
		selectTrack(nowSelected);
	}

	// 게임스타트 메소드 -> 난이도에 대한 정보를 difficul 에 담기
	public void gameStart(int nowSelected, String difficul) {
		// 현재 재생중인 음악을 닫기
		if (selectedMusic != null)
			selectedMusic.close();
		// 메인화면 닫기
		isMainScreen = false;
		// 각각의 버튼들 없애기
		leftbtn.setVisible(false);
		rightbtn.setVisible(false);
		easybtn.setVisible(false);
		normalbtn.setVisible(false);
		hardbtn.setVisible(false);
		Background = new ImageIcon(Main.class.getResource("../img/" + tracklist.get(nowSelected).getGameImage()))
				.getImage();
		// back 버튼이 보이게
		backbtn.setVisible(true);
		isGameScreen = true;
		//
		game = new Game(tracklist.get(nowSelected).getTitleName(), difficul, tracklist.get(nowSelected).getGameMusic());
		// 혜당 게임 인스턴의 run 함수를 실행해주도록 함
		game.start();
		// 키보드 포커스 맞추기위한 메소드
		setFocusable(true);
	}

	// back 버튼 메소드
	public void backMain() {
		// 화면 변환
		isMainScreen = true;
		// 버튼 보이기
		leftbtn.setVisible(true);
		rightbtn.setVisible(true);
		easybtn.setVisible(true);
		normalbtn.setVisible(true);
		hardbtn.setVisible(true);
		// 화면출력
		Background = new ImageIcon(Main.class.getResource("../img/MainScreen3.jpg")).getImage();
		// 뒤로가기 버튼 유지
		backbtn.setVisible(true);
		// 현재 트렉을 보여주고 하이라이트 부분을 재생
		selectTrack(nowSelected);
		isGameScreen = false;
		// 음악과 게임을 모두 종료하여 다른곡을 선택활수 있도록 함
		endMain();
	}

	// 곡 선택화면
	public static void enterMain() {
		// 버튼눌렀을때 화면전환
		Background = new ImageIcon(Main.class.getResource("../img/MainScreen3.jpg")).getImage();
		isMainScreen = true;
		// 첫번쩨 곡을 뿌리기
		startbtn.setVisible(false);
		// 다른버튼 안보이게하기
		rankbtn.setVisible(false);
		helpbtn.setVisible(false);
		quitbtn.setVisible(false);
		// 좌우이동
		leftbtn.setVisible(true);
		rightbtn.setVisible(true);
		// 난이도 설정
		easybtn.setVisible(true);
		normalbtn.setVisible(true);
		hardbtn.setVisible(true);
		// 음소거
		introMusic.close();

		// 화면변환
		selectTrack(0);
	}

	// // 스코어출력 화면에서 back 버튼 눌렀을때 이벤트
	// public void getenterMain(MouseEvent e)
	// {
	// public void mousePressed(MouseEvent e) {
	// System.exit(0);
	// game.endGame();
	// enterMain();
	// }
	// );
	// 게임이 끝났을때의 이벤트출력
	public static void endMain() {
		// 버튼눌렀을때 화면전환
		isMainScreen = false;

		// 좌우이동
		leftbtn.setVisible(false);
		rightbtn.setVisible(false);
		// 난이도 설정
		easybtn.setVisible(false);
		normalbtn.setVisible(false);
		hardbtn.setVisible(false);
		isGameScreen = false;
		isEndScreen = true;
		backbtn.setVisible(true);
		backbtn.setBounds(20, 40, 62, 62);
		Background = new ImageIcon(Main.class.getResource("../img/endScreen.png")).getImage();
		selectedMusic.close();
		Game.close();
		System.out.println("Total Note  :" + Judge.noteCount);
		System.out.println("Perfect  :" + Judge.perfect);
		System.out.println("Great  :" + Judge.great);
		System.out.println("Good  :" + Judge.good);
		System.out.println("Bad  :" + Judge.bad);
		System.out.println("Miss  :" + Judge.miss);
		/*if (game.result == "S") {
			Rank = new ImageIcon(Main.class.getResource("../img/rankS.png")).getImage();
		} else if (game.result == "A") {
			Rank = new ImageIcon(Main.class.getResource("../img/rankA.png")).getImage();
		} else if (game.result == "B") {
			Rank = new ImageIcon(Main.class.getResource("../img/rankB.png")).getImage();
		} else if (game.result == "C") {
			Rank = new ImageIcon(Main.class.getResource("../img/rankC.png")).getImage();
		} else if (game.result == "D") {
			Rank = new ImageIcon(Main.class.getResource("../img/rankD.png")).getImage();
		}*/
		backbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				enterMain();
				Thread.interrupted();
			}
		});
	}
}