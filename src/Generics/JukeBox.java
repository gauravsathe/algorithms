package Generics;

import java.util.*;

public class JukeBox {
	private ArrayList<Song> allSongs;
	
	public JukeBox() {
		this.allSongs = new ArrayList<Song>();
	}
	
	public void addSong(Song s) {
		this.allSongs.add(s);
	}
}
