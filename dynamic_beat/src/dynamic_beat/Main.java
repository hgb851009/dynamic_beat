package dynamic_beat;

// 프로그램 실행 클래스
public class Main {

	// 상수 선언 혜상도
	// 스크린 넓이 상수값
	public static final int SCREEN_WIDTH = 1280;
	// 스크린 높이 상수값
	public static final int SCREEN_HERIGHT = 720;
	// 노트 떨어지는 속도 명시
	public static final int NOTE_SPEED = 3;
	// 노트가 떨어지는 주기를 설정
	public static final int SLEEP_TIME = 10;
	// 노트가 생성되고나서 판정바에 도달하기까지의 시간
	public static final int REACH_TIME = 2;

	public static void main(String[] args) {
		// 인스턴스 객체 불러오기
		new DynamicBeat();
	}
}
// github URL - https://github.com/hgb851009/dynamic_beat.git
// 추가 및 변경사항들
// 1. 혜상도 맞추기 (x.y 좌표설정에 영향있음)
// 2. 창 클릭하고 드레그할때 이벤트 복구
// 3. 트렉과 음악설정 변경
// 4. 시작화면 버튼 추가조정 (옵션항목에 도움말을 추가하도록하고 설정메뉴 기능추가)
// 5. 그래픽&이미지 삽입 수정
// 6. 게임 끝나고 스코어 기능 추가
// 7. 덜어지는 노트와 입력한 순간 판정라인에서 이팩트 일어난 후에 사라지게하기
// 8. 랭킹 시스템 기능 추가 (게임 끝나고 토탈점수와 이니셜 입력으로 값을 저장하여 비교한 후 등수 판정 및 출력(SQLServer Connection))

// 이펙트 효과 이미지 / perpact good bad 글자 이미지 수집
// 이미지와 효과 좌표 설정하고 점수판 좌표 이동시킬 것
// 이펙트 효과 이미지 링크
// https://www.shutterstock.com/ko/?kw=%2Bfree%20%2Bimages&gclid=CjwKCAjwxILdBRBqEiwAHL2R81dWhDX-IF6TcEs4zhlP4nUIzo_pChdoslSy5G5wejbA2d5GdcpmZRoC-5cQAvD_BwE&gclsrc=aw.ds&dclid=CN_WxqvmxN0CFQ9TvQodfvUDug