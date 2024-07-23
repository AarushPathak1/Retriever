import static spark.Spark.*;

public class SpotifyAuthServer {
	
	public static void runServer() {
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
	}
}
