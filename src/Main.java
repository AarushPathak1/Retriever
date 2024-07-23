import static spark.Spark.*;

import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Main {

	public static void main(String[] args) {
		// TODO: convert the video file into an audio file
		
		// using the audio file as input, recognize the song playing
		String audioUrl = "https://github.com/AarushPathak1/Retriever/blob/main/fireball_trimmed.mp3?raw=true";
		Song song = MusicRecognizer.recognizeSong(audioUrl);
		Scanner scanner = null;
		
		if(song != null && song.getSongUri() != null) {
			// run the server
			SpotifyPlaylistManager manager = new SpotifyPlaylistManager();

	        port(8888);
	        // Start the auth process by printing the auth URL
	        System.out.println("Go to the following URL to authorize:");
	        System.out.println(manager.getAuthorizationUrl());

	        // Endpoint to handle the OAuth2 callback
	        get("/callback", (req, res) -> {
	            String code = req.queryParams("code");
	            manager.getAccessToken(code);
	            return "Authorization successful! You can close this window.";
	        });

	        
	        // once the authorization is complete, we can get all the user's playlists
	        JsonArray playlists = manager.getUserPlaylists();
	        if(playlists != null) {
	        	// output all the playlists to the screen
	        	System.out.println("Your Playlists:");
	            for (int i = 0; i < playlists.size(); i++) {
	                JsonObject playlist = playlists.get(i).getAsJsonObject();
	                System.out.println((i + 1) + ": " + playlist.get("name").getAsString() + " (ID: " + playlist.get("id").getAsString() + ")");
	            }
	            // get the input from user
	            scanner = new Scanner(System.in);
	            System.out.print("Select a playlist by number: ");
	            int playlistIndex = scanner.nextInt();
	            String playlistId = playlists.get(playlistIndex - 1).getAsJsonObject().get("id").getAsString();
	            // add the song to the chosen playlist
	        	manager.addSongToPlaylist(playlistId, song.getSongUri());
	        } else {
				System.out.println("Failed to retrieve playlists");
			}
		} else {
            System.out.println("Song recognition failed or no URI found.");
        }
		scanner.close();
	}
}
