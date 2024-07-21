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
 * This class implements audio recognition capabilities for an input aufdio file.
 * 
 * @author aarushpathak
 */
public class MusicRecognizer {
	
	/** The private access token used to authenticate the API request */
	private static final String API_TOKEN = "b0f392b9d4bfa9323ac50ef264cf954d";
	/** URL of the song (where it can be accessed by the API) */ 
	private String audioUrl;

	/**
	 * Recognizes the song that the inputted file plays.
	 * 
	 * @param audioUrl URL of the audio file
	 * @return The name of the song being played
	 */
	public String recognizeSong(String audioUrl){
		this.audioUrl = audioUrl;
		return null;
	}
	
	public static void main(String[] args) {
		
		try {
			Song song = new Song();
			song.setUrl("https://github.com/AarushPathak1/Retriever/blob/main/fireball_trimmed.mp3?raw=true");
			
			// create the form body string with all necessary information
			String formParams = "url=" + URLEncoder.encode(song.getUrl(), StandardCharsets.UTF_8)
			+ "&return=" + URLEncoder.encode("spotify", StandardCharsets.UTF_8)
			+ "&api_token=" + URLEncoder.encode(API_TOKEN, StandardCharsets.UTF_8);
			
			// create the Http request
			HttpRequest postRequest = HttpRequest.newBuilder()
					.uri(new URI("https://api.audd.io/"))
					.header("Content-Type", "application/x-www-form-urlencoded")
					.POST(BodyPublishers.ofString(formParams))
					.build();
			// send the Http request and record the response in the postResponse object
			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> postResponse = client.send(postRequest, BodyHandlers.ofString());
			
			// Convert the json string to a Song object using Gson
			Gson gson = new Gson();
			song = gson.fromJson(postResponse.body(), Song.class);
			
			System.out.println(postResponse.body());
			// print out results for tesing purposes
			Song.Result result = song.getResult();
            if (result != null) {
                System.out.println("Title: " + result.getTitle());
                System.out.println("Artist: " + result.getArtist());
                System.out.println("Album: " + result.getAlbum());
                if (result.getSpotify() != null && result.getSpotify().getTrack() != null) {
                    System.out.println("Spotify ID: " + result.getSpotify().getTrack().getId());
                }
            } else {
                System.out.println("No result found.");
            }

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
