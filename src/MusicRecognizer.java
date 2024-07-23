import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;

/**
 * This class implements audio recognition capabilities for an input audio file.
 * 
 * @author aarushpathak
 */
public class MusicRecognizer {
	
	/** The private access token used to authenticate the API request */
	private static final String API_TOKEN = "b0f392b9d4bfa9323ac50ef264cf954d";

	/**
	 * Recognizes the song that the inputted file plays.
	 * 
	 * @param audioUrl URL of the audio file
	 * @return A song object that contains all details about the song
	 */
	public static Song recognizeSong(String audioUrl){
		Song song = new Song();
		HttpRequest postRequest;
		HttpResponse<String> postResponse = null;
		song.setUrl(audioUrl);
		
		try {		
			// create the form body string with all necessary information
			String formParams = "url=" + URLEncoder.encode(song.getUrl(), StandardCharsets.UTF_8)
			+ "&return=" + URLEncoder.encode("spotify", StandardCharsets.UTF_8)
			+ "&api_token=" + URLEncoder.encode(API_TOKEN, StandardCharsets.UTF_8);
			
			// create the Http request
			postRequest = HttpRequest.newBuilder()
					.uri(new URI("https://api.audd.io/"))
					.header("Content-Type", "application/x-www-form-urlencoded")
					.POST(BodyPublishers.ofString(formParams))
					.build();
			// send the Http request and record the response in the postResponse object
			HttpClient client = HttpClient.newHttpClient();
			postResponse = client.send(postRequest, BodyHandlers.ofString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		// Convert the json string to a Song object using Gson
		Gson gson = new Gson();
		song = gson.fromJson(postResponse.body(), Song.class);
		return song;
	}
	
	public static void main(String[] args) {
			recognizeSong("https://github.com/AarushPathak1/Retriever/blob/main/fireball_trimmed.mp3?raw=true");
	}
}
