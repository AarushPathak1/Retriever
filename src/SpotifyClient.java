import com.google.gson.annotations.SerializedName;

public class SpotifyClient {
	@SerializedName("access_token")
	private String accessToken;

	/**
	 * @return the access_token
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param access_token the access_token to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}
