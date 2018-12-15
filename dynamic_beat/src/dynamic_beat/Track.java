package dynamic_beat;

//곡에 대한 정보를 담는 클래스
public class Track {
	// 엘범 표지
	// 제목
	// 아티스트
	private String titleImage; // 제목부분 곡명 아티스트
	private String startImage; // 게임시작시 곡 이미지
	private String gameImage; // 혜당 곡을 실행했을때 인게임화면
	private String startMusic; // 게임 선택시 음악 하이라이트
	private String gameMusic; // 혜당 곡을 실행했을때 인게임 뮤직플레이
	public String titleName; // 곡 제목

	// get set
	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public String getStartImage() {
		return startImage;
	}

	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}

	public String getGameImage() {
		return gameImage;
	}

	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}

	public String getStartMusic() {
		return startMusic;
	}

	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}

	public String getGameMusic() {
		return gameMusic;
	}

	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	// 생성자
	public Track(String titleImage, String startImage, String gameImage, String startMusic, String gameMusic,
			String titleName) {
		super();
		this.titleImage = titleImage;
		this.startImage = startImage;
		this.gameImage = gameImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
		this.titleName = titleName;
	}

}
