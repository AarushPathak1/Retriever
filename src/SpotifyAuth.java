/**
 * This class is used to set up the connection with the Spotify web API so that
 * further requests can be made.
 */
public class SpotifyAuth {

	private static final String CLIENT_ID = "235e58e7ae5d4a23becd2e8b58bb86e5";
	private static final String CLIENT_SECRET = "bc48bf31527044caa6c144ad3c8ba2d7";
	private static String accessToken;

	/**
	 * Initializes the connection to the API by getting an access token and storing it
	 */
	public SpotifyAuth() {
		accessToken = getAccessToken();
	}
	
	private static String getAccessToken() {
		return "";
	}
	
	public static void main(String[] args) {
		
	}

}
