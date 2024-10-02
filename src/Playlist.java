import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Playlist {
	
	private ArrayList<Song> songs;
	
	public Playlist() {
		// TODO: Initialize the songs field.
		songs = new ArrayList<Song>();
	}
	
	public Playlist(String filename) throws IOException {
		this();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			
			while((line = br.readLine()) != null) {
				Song song = new Song(line);
				songs.add(song);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int getNumSongs() {
		return songs.size();
	}
	
	public Song getSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		return songs.get(index);
	}
	
	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}
	
	public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}
	
	public boolean addSong(int index, Song song) {
		// TODO: Update the Lab 3 method.
		if(song == null || index < 0 || index > getNumSongs()) {
			return false;
		}
		
		songs.add(index, song);
		return true;
		
	}
	
	public int addSongs(Playlist playlist) {
		// TODO: Update the Lab 3 method.
		
		if(playlist == null) {
			return 0;
		}
		
		int added = 0;
		Song[] temp = playlist.getSongs();
		for(Song i : temp) {
			addSong(i);
			added++;
		}
		return added;
		
	}
	
	public int addSongs(String filename) throws IOException {
		
		int added = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String info;
			while(!((info = br.readLine()) == null)){
				Song nSong = new Song(info);
				songs.add(nSong);
				added++;
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return added;
	}
	
	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}
	
	public Song removeSong(int index) {
		// TODO: Update the Lab 3 method.
		if(index < 0 || index > getNumSongs()-1) {
			return null;
		}
		Song removed = songs.remove(index);
		return removed;
		
	}
	
	public void saveSongs(String filename) {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			for(Song i : songs) {
				bw.write(i.toString() + "\n");				
			}
			
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		
		String string = "";
		
		for(Song song: songs) {
			string += song.toString() + System.lineSeparator();
		}
		return string.trim();
	}
	
	public int[] getTotalTime() {
		
		int totalSec = 0;
		int totalMin = 0;
		int totalHr = 0;
		
		for(Song s: this.songs) {
			
			int[] time = s.getTime();
			
			if(time.length == 3) {
				totalSec += time[0];
				totalMin += time[1];
				totalHr += time[2];
			}
			
			if(time.length == 2) {
				totalSec += time[0];
				totalMin += time[1];
			}
			
			if(time.length == 1) {
				totalSec += time[0];
			}
		}
		
		totalMin += totalSec/60;
		totalSec = totalSec%60;
		totalHr += totalMin/60;
		totalMin = totalMin%60;
		
		if(totalMin == 0 && totalHr == 0) {
			return new int[] {totalSec};
		}
		
		else if(totalHr == 0) {
			return new int[] {totalSec,totalMin};
		}
		
		else {
			return new int[] {totalSec,totalMin,totalHr};
		}
	}
}
