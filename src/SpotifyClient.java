/**
 * This class is used to implement the functionality of a client attempting to establish
 * a connection with the Spotify web API.
 * 
 * @author aarushpathak
 */
public class SpotifyClient {
	
	private final String CLIENT_ID = "235e58e7ae5d4a23becd2e8b58bb86e5";
	private final String CLIENT_SECRET = "bc48bf31527044caa6c144ad3c8ba2d7";
	private String access_token;
	
	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return CLIENT_ID;
	}

	/**
	 * @return the clientSecret
	 */
	public String getClientSecret() {
		return CLIENT_SECRET;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return access_token;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.access_token = accessToken;
	}
}
