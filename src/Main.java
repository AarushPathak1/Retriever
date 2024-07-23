import static spark.Spark.get;
import static spark.Spark.port;

public class Main {

	public static void main(String[] args) {
		// convert the video file into an audio file
		
		// using the audio file as input, recognize the song playing
		String audioUrl = "https://github.com/AarushPathak1/Retriever/blob/main/fireball_trimmed.mp3?raw=true";
		Song song = MusicRecognizer.recognizeSong(audioUrl);
		
		
		if(song != null && song.getSongUri() != null) {
			String playlistId = "";
			// run the server
			SpotifyPlaylistManager manager = new SpotifyPlaylistManager();

	        port(8888);

	        // Endpoint to handle the OAuth2 callback
	        get("/callback", (req, res) -> {
	            String code = req.queryParams("code");
	            manager.getAccessToken(code);
	            return "Authorization successful! You can close this window.";
	        });

	        // Start the auth process by printing the auth URL
	        System.out.println("Go to the following URL to authorize:");
	        System.out.println(manager.getAuthorizationUrl());
	        
	        // once the authorization is complete, we can add the song to the playlist
	        manager.addSongToPlaylist(playlistId, song.getSongUri());
		} else {
            System.out.println("Song recognition failed or no URI found.");
        }
	}
}
