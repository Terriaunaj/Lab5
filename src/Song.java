import java.util.Arrays;

public class Song {
	
	private String title;
	private String artist;
	private int[] time;
	
	private static final String INFO_DELIMITER = "; ";
	private static final String TIME_DELIMITER = ":";
	private static final int IDX_TITLE = 0;
	private static final int IDX_ARTIST = 1;
	private static final int IDX_TIME = 2;
	
	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}
	
	public Song(String info) {
		
		String[] data = info.split(INFO_DELIMITER);
		title = data[IDX_TITLE];
		artist = data[IDX_ARTIST];
		
		String[] timeString = data[IDX_TIME].split(TIME_DELIMITER);

		time = new int[timeString.length];
		
		for(int i = 0; i < time.length; i++) {
			
			String s = timeString[time.length - i - 1];
			time[i] = Integer.parseInt(s);
			
		}
			
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int[] getTime() {
		return Arrays.copyOf(time, time.length);
	}
	
	public String toString() {

		String timeString = "";
        if(time.length == 3){
            timeString += time[2] + TIME_DELIMITER;
            if(time[1] < 10 ){
                timeString += "0";
            }
        }
        if(time.length >= 2){
        timeString += time[1] + TIME_DELIMITER;
        if(time[0] < 10){
            timeString += "0";
        }
    }
        timeString += time[0] + "";

		return title + INFO_DELIMITER + artist + INFO_DELIMITER + timeString;
	}
}
