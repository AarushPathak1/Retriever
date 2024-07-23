import com.google.gson.annotations.SerializedName;
/**
 * Represents a song object. When the conversion from a String to a json object
 * is run in the MusicRecognizer class, appropriate parameters' values are stored
 * in an instance of this class.
 * 
 * @author aarushpathak
 */
public class Song {
	
	/** URL of the song (where it can be accessed by the API) */
	private String url;
	/** the status of the request */
	private String status;
	/** The "result" sub section of the returned json object */
    @SerializedName("result")
    private Result result;

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return result the result object
	 */
	public Result getResult() {
        return result;
    }
	
	/**
	 * @param result
	 */
    public void setResult(Result result) {
        this.result = result;
    }
    
    /**
     * @return the song uri from the Spotify sub-class
     */
    public String getSongUri() {
		return this.getResult().getSpotify().getUri();
	}
	
	
	/** The "result" sub section of the returned json object */
	public static class Result {
		/** artist of the song */
        @SerializedName("artist")
        private String artist;
        /** title of the song */
        @SerializedName("title")
        private String title;
        /** name of the album the song is from */
        @SerializedName("album")
        private String album;
        /** The "spotify" sub section of the returned json object */
        @SerializedName("spotify")
        private Spotify spotify;
        
    	/**
    	 * @return the name of the artist
    	 */
        public String getArtist() {
            return artist;
        }

    	/**
    	 * @param artist the name of the artist
    	 */
        public void setArtist(String artist) {
            this.artist = artist;
        }

    	/**
    	 * @return the title of the song
    	 */
        public String getTitle() {
            return title;
        }

    	/**
    	 * @param title the title of the song
    	 */
        public void setTitle(String title) {
            this.title = title;
        }

    	/**
    	 * @return the album name
    	 */
        public String getAlbum() {
            return album;
        }

    	/**
    	 * @param album the name of the album
    	 */
        public void setAlbum(String album) {
            this.album = album;
        }

    	/**
    	 * @return the spotify object
    	 */
        public Spotify getSpotify() {
            return spotify;
        }

    	/**
    	 * @param spotify reference to the Spotify class
    	 */
        public void setSpotify(Spotify spotify) {
            this.spotify = spotify;
        }
    }

	/**
	 * The "Spotify" sub section of the returned json object
	 */
    public static class Spotify {
    	@SerializedName("id")
    	private String id;
    	@SerializedName("uri")
    	private String uri;
    	
    	
    	public String getId() {
    		return id;
    	}
    	
    	public void setId(String id) {
			this.id = id;
		}
    	
    	public String getUri() {
			return uri;
		}
    	
    	public void setUri(String uri) {
			this.uri = uri;
		}
    	
    }
    
}
