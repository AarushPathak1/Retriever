import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

/**
 * This class is used to extract the audio from an inputted video.
 * 
 * @author aarushpathak
 */
public class VideoToAudioConverter {
	
	/**
	 * Extracts the audio from a given video src and stores it in the given output location.
	 * 
	 * @param videoFilePath The path of the video from which audio is to be extracted
	 * @param outputAudioFilePath The location where the extracted audio is to be saved
	 */
	public static void extractAudio(String videoFilePath, String outputAudioFilePath) {
		// Path to the FFmpeg executable in the project directory
        String ffmpegExecutable = "./ffmpeg";
        
        // Build the FFmpeg command
        CommandLine command = new CommandLine(ffmpegExecutable);
        command.addArgument("-i");
        command.addArgument(videoFilePath);
        command.addArgument(outputAudioFilePath);
                
        try { // Execute the command
        	DefaultExecutor executor = new DefaultExecutor();
        	executor.execute(command);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String videoFilePath = "./sample.mp4"; 
        String outputAudioFilePath = "./sample.mp3";

        try {
            extractAudio(videoFilePath, outputAudioFilePath);
            System.out.println("Audio extraction completed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
