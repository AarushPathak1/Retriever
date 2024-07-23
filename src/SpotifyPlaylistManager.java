import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * This class is used to set up the connection with the Spotify web API so that
 * further requests can be made. 
 * 
 * @author aarushpathak
 */
public class SpotifyPlaylistManager {

	private final String CLIENT_ID = "235e58e7ae5d4a23becd2e8b58bb86e5";
	private final String CLIENT_SECRET = "bc48bf31527044caa6c144ad3c8ba2d7";
	private final String REDIRECT_URI = "http://localhost:8888/callback";
	private static String accessToken;

	/**
	 * returns the authorization URL needed to establish user connection with the spotify api
	 * @return the authorization String
	 */
	public String getAuthorizationUrl() {
	    String scope = "playlist-modify-public playlist-read-private"; // the scope of the authorization request
	    
	    return "https://accounts.spotify.com/authorize" +
	            "?response_type=code" +
	            "&client_id=" + CLIENT_ID +
	            "&scope=" + scope +
	            "&redirect_uri=" + REDIRECT_URI;
	}
	
	/**
	 * This method establishes a connection with the Spotify API and retrieves the Client's access token
	 * @param code The authorization code returned by spotify after user logs in
	 */
	public void getAccessToken(String code) {
		HttpRequest postRequest;
		HttpResponse<String> postResponse = null;
		try { // create an HTTP request object with the necessary information to get the access_token as specified in Spotify's web API documentation
			postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://accounts.spotify.com/api/token"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(BodyPublishers.ofString(
                            "grant_type=authorization_code&code=" + code +
                            "&redirect_uri=" + REDIRECT_URI +
                            "&client_id=" + CLIENT_ID +
                            "&client_secret=" + CLIENT_SECRET))
                    .build();
			// send the HTTP request
			HttpClient httpClient = HttpClient.newHttpClient();
			postResponse = httpClient.send(postRequest, BodyHandlers.ofString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// convert the received Json String into a SpotifyClient object to retrieve the access_token
		Gson gson = new Gson();
		SpotifyClient client = gson.fromJson(postResponse.body(), SpotifyClient.class);
		accessToken = client.getAccessToken();
	}
	
	/**
	 * Adds the song with the given URI to the user's playlist with the given playlist ID
	 * @param playlistId ID of the playlist to which the song is to be added
	 * @param trackUri The URI of the song
	 */
	public void addSongToPlaylist(String playlistId, String trackUri) {
		HttpRequest postRequest;
		HttpResponse<String> postResponse = null;
		try {
			// create the HTTP request
			postRequest = HttpRequest.newBuilder()
					.uri(new URI("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks"))
					.header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + accessToken)
					.POST(HttpRequest.BodyPublishers.ofString("{\"uris\": [\"" + trackUri + "\"]}"))
					.build();
			
			// send the request
			HttpClient client = HttpClient.newHttpClient();
			postResponse = client.send(postRequest, BodyHandlers.ofString());
			if (postResponse.statusCode() == 201) {
                System.out.println("Track added successfully.");
            } else {
                System.out.println("Failed to add track: " + postResponse.body());
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Finds and returns all of the user's playlists
	 * @return a JsonArray of the user's playlists
	 */
    public JsonArray getUserPlaylists() {
        HttpRequest getRequest;
        HttpResponse<String> getResponse = null;
        try {
            getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.spotify.com/v1/me/playlists"))
                    .header("Authorization", "Bearer " + accessToken)
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            getResponse = client.send(getRequest, BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(getResponse.body(), JsonObject.class);
        return jsonObject.getAsJsonArray("items");
    }

}
