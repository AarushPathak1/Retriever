/**
 * This class is used to implement the functionality of a client attempting to establish
 * a connection with the Spotify web API
 */
public class SpotifyClient {
	
	private static final String CLIENT_ID = "235e58e7ae5d4a23becd2e8b58bb86e5";
	private static final String CLIENT_SECRET = "bc48bf31527044caa6c144ad3c8ba2d7";
	private static String accessToken;
	
	/**
	 * @return the clientId
	 */
	public static String getClientId() {
		return CLIENT_ID;
	}

	/**
	 * @return the clientSecret
	 */
	public static String getClientSecret() {
		return CLIENT_SECRET;
	}

	/**
	 * @return the accessToken
	 */
	public static String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public static void setAccessToken(String accessToken) {
		SpotifyClient.accessToken = accessToken;
	}
}
