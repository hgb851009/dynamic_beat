package dynamic_beat;

import java.awt.Container;

public class Judge {
	// 각각 판정의 변수
	static int perfect ;
	static int great ;
	static int good ;
	static int bad ;
	static int miss ;
	// 판정누락변수
	static int noteCount;

	// 랭크를 배열로 담음
	final String[] Rank = new String[] { "S", "A", "B", "C", "F" };

	// 카운트를 초기화
	public Judge(int noteCount) {
		perfect = 0;
		great = 0;
		good = 0;
		bad = 0;
		miss = 0;
		this.noteCount = noteCount;
	}

	/*
	 * public static int totalscore() {
	 * 
	 * return bad; }
	 */
	// 랭크 초기화
	public String[] getRank() {
		return Rank;
	}

	// 점수누락함수
	public int sum() {
		
		return 0;
	}

	// 스코어 저장메소드
	public void setScore(String score) {
		String sc = score.toLowerCase();
		int sumResult=0;
		if (sc.equals(Game.judge("perfect"))) {
			++perfect;
			System.out.println(Judge.perfect*100);
		} else if (sc.equals(Game.judge("great"))) {
			++great;
			System.out.println(Judge.great*90);
		} else if (sc.equals(Game.judge("good"))) {
			++good;
			System.out.println(Judge.good*70);
		} else if (sc.equals(Game.judge("bad"))) {
			++bad;
			System.out.println(Judge.bad*50);
		} else if (sc.equals(Game.judge("miss"))) {
			miss++;
		}
		//	1. sumResult 에 각각의 누락을 담은 후에 for문으로 증가시킴(?)
		//	2. 이대로 label 에 setScore 메소드 안에 각각의 조건문을 어떻게 담아야 하는지...?
		
	}

	// 카운트 통계로 랭크 구하기
	public String getScore() {
		int max = (int) ((perfect * 100 + great * 90 + good * 70 + bad * 50 + miss * 0) / (noteCount));

		System.out.println(" ToTal Note: :" + noteCount);
		System.out.println(" Perfect :" + (perfect * 100));
		System.out.println(" Great :" + (great * 90));
		System.out.println(" Good :" + (good * 70));
		System.out.println(" Bad :" + (bad * 50));
		System.out.println(" Miss : " + (miss * 0));
		/* +"Miss"+miss */
		if (max == 100) {
			return Rank[0];
		} else if (max >= 90) {
			return Rank[1];
		} else if (max >= 80) {
			return Rank[2];
		} else if (max >= 70) {
			return Rank[3];
		} else {
			return Rank[4];
		}
	}

}