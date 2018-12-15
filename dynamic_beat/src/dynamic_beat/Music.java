package dynamic_beat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	private Player player; // 라이브러리 import
	private boolean isLoop; // 재생 횟수설정
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			// 파일 경로 / input
			file = new File(Main.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			// 혜당 파일을 버퍼에 담아 읽게함
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			// 오류시 메세지 띄우기
			System.out.println(e.getMessage());
		}
	}

	// 현재 실행되는 음악의 재생 시간에 대한 메소드
	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	// 종료기능 메소드
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt(); // 인터럽트=혜당 스레드 중지시키기

	}

	// 스레드 -> 런 생성 @override
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop); // true 일때 do 에서 무한반복 하도록

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
