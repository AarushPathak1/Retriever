import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

/**
 * This class is used to set up the connection with the Spotify web API so that
 * further requests can be made.
 */
public class SpotifyPlaylistManager {

	private static String accessToken;

	/**
	 * Initializes the connection to the API by getting an access token and storing it in acessToken
	 */
	public SpotifyPlaylistManager() {
		accessToken = getAccessToken();
	}
	
	/**
	 * This method establishes a connection with the Spotify API and retrieves the Client's access token
	 * 
	 * @return The access token
	 */
	private static String getAccessToken() {
		HttpRequest postRequest;
		HttpResponse<String> postResponse = null;
		SpotifyClient client = new SpotifyClient();
		try { // create an HTTP request object with the necessary information to get the access_token as specified in Spotify's web API documentation
			postRequest = HttpRequest.newBuilder()
					.uri(new URI("https://accounts.spotify.com/api/token"))
					.header("Content-Type", "application/x-www-form-urlencoded")
					.POST(BodyPublishers.ofString(
							"grant_type=client_credentials&client_id=" + client.getClientId() + "&client_secret=" + client.getClientSecret()))
					.build();
			// send the HTTP request
			HttpClient httpClient = HttpClient.newHttpClient();
			postResponse = httpClient.send(postRequest, BodyHandlers.ofString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// convert the received Json String into a SpotifyClient object to retrieve the access_token
		Gson gson = new Gson();
		client = gson.fromJson(postResponse.body(), SpotifyClient.class);
		return client.getAccessToken(); // return the access_token of the client
		
		
	}
	
	
	
	public static void main(String[] args) {
	}

}
