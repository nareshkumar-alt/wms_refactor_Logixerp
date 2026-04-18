package utilities;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * VideoRecorder utility class for recording test execution videos
 * Thread-safe implementation for parallel test execution
 * Supports recording all tests or only failed tests
 */
public class VideoRecorder {
	
	private static final Logger logger = LogManager.getLogger(VideoRecorder.class);
	
	// Thread-safe storage for ScreenRecorder instances (one per thread)
	// Using Object to avoid compilation errors when Monte Media is not available
	private static final ConcurrentHashMap<Long, Object> recorderMap = new ConcurrentHashMap<>();
	
	// Flag to check if Monte Media library is available
	private static Boolean monteMediaAvailable = null;
	
	/**
	 * Check if Monte Media library is available at runtime
	 * @return true if available, false otherwise
	 */
	private static boolean isMonteMediaAvailable() {
		if (monteMediaAvailable == null) {
			try {
				Class.forName("org.monte.screenrecorder.ScreenRecorder");
				monteMediaAvailable = true;
				logger.info("Monte Media Library is available");
			} catch (ClassNotFoundException e) {
				monteMediaAvailable = false;
				logger.warn("Monte Media Library is not available. Video recording will be disabled.");
				logger.warn("To enable video recording, install Monte Media Library. See INSTALL_MONTE_MEDIA.md");
			}
		}
		return monteMediaAvailable;
	}
	
	// Thread-safe storage for video file paths
	private static final ConcurrentHashMap<Long, String> videoPathMap = new ConcurrentHashMap<>();
	
	// Thread-safe storage for recording status
	private static final ConcurrentHashMap<Long, Boolean> recordingStatusMap = new ConcurrentHashMap<>();
	
	// Video directory path
	private static final String VIDEO_DIR = "./test-output/videos/";
	
	// Note: Format constants removed as they're not directly used in the simplified implementation
	
	/**
	 * Initialize video directory if it doesn't exist
	 */
	private static void initializeVideoDirectory() {
		File videoDir = new File(VIDEO_DIR);
		if (!videoDir.exists()) {
			boolean created = videoDir.mkdirs();
			if (created) {
				logger.info("Created video directory: " + VIDEO_DIR);
			} else {
				logger.warn("Failed to create video directory: " + VIDEO_DIR);
			}
		}
	}
	
	/**
	 * Start video recording for the current thread
	 * @param testMethodName Name of the test method
	 * @param recordOnlyOnFailure If true, recording will only be saved if test fails
	 * @return true if recording started successfully, false otherwise
	 */
	public static boolean startRecording(String testMethodName, boolean recordOnlyOnFailure) {
		// Check if Monte Media is available
		if (!isMonteMediaAvailable()) {
			logger.warn("Video recording skipped - Monte Media Library not available");
			return false;
		}
		
		long threadId = Thread.currentThread().getId();
		
		try {
			// Initialize video directory
			initializeVideoDirectory();
			
			// Check if already recording for this thread
			if (recordingStatusMap.getOrDefault(threadId, false)) {
				logger.warn("Recording already in progress for thread: " + threadId);
				return false;
			}
			
			// Generate video file name with timestamp
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String videoFileName = testMethodName + "_" + timestamp + ".avi";
			String videoFilePath = VIDEO_DIR + videoFileName;
			
			// Get graphics configuration for screen recording
			GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
					.getDefaultScreenDevice().getDefaultConfiguration();
			
			// Use reflection to create and start ScreenRecorder
			// This avoids compile-time dependency on Monte Media
			Class<?> screenRecorderClass = Class.forName("org.monte.screenrecorder.ScreenRecorder");
			Class<?> formatClass = Class.forName("org.monte.media.Format");
			Class<?> formatKeysClass = Class.forName("org.monte.media.FormatKeys");
			
			// Get enum values using reflection
			Object mediaTypeKey = formatKeysClass.getField("MediaTypeKey").get(null);
			Object mimeTypeKey = formatKeysClass.getField("MimeTypeKey").get(null);
			Class<?> mediaTypeEnum = Class.forName("org.monte.media.FormatKeys$MediaType");
			Object fileMediaType = Enum.valueOf((Class<Enum>) mediaTypeEnum, "FILE");
			
			// Create Format for file output
			Object fileFormat = formatClass.getConstructor(
				Object.class, Object.class, Object.class, String.class
			).newInstance(mediaTypeKey, fileMediaType, mimeTypeKey, "video/avi");
			
			// Create ScreenRecorder instance
			Object screenRecorder = screenRecorderClass.getConstructor(
				GraphicsConfiguration.class,
				java.awt.Rectangle.class,
				formatClass,
				formatClass,
				formatClass,
				File.class,
				String.class
			).newInstance(
				gc,
				gc.getBounds(),
				fileFormat,
				null,
				null,
				new File(VIDEO_DIR),
				videoFileName
			);
			
			// Start recording
			screenRecorderClass.getMethod("start").invoke(screenRecorder);
			
			// Store recorder and path in thread-safe maps
			recorderMap.put(threadId, screenRecorder);
			videoPathMap.put(threadId, videoFilePath);
			recordingStatusMap.put(threadId, true);
			
			logger.info("Video recording started for test: " + testMethodName + " (Thread: " + threadId + ")");
			logger.info("Video will be saved to: " + videoFilePath);
			
			return true;
			
		} catch (Exception e) {
			logger.error("Failed to start video recording for test: " + testMethodName, e);
			// Clean up on failure
			recorderMap.remove(threadId);
			videoPathMap.remove(threadId);
			recordingStatusMap.remove(threadId);
			return false;
		}
	}
	
	/**
	 * Stop video recording for the current thread
	 * @param testMethodName Name of the test method
	 * @param testPassed true if test passed, false if test failed
	 * @param recordOnlyOnFailure If true, video will be deleted if test passed
	 * @return Path to the saved video file, or null if recording failed or was deleted
	 */
	public static String stopRecording(String testMethodName, boolean testPassed, boolean recordOnlyOnFailure) {
		// Check if Monte Media is available
		if (!isMonteMediaAvailable()) {
			return null;
		}
		
		long threadId = Thread.currentThread().getId();
		
		try {
			// Check if recording is in progress for this thread
			if (!recordingStatusMap.getOrDefault(threadId, false)) {
				logger.warn("No active recording found for thread: " + threadId);
				return null;
			}
			
			// Get the recorder for this thread
			Object screenRecorder = recorderMap.get(threadId);
			if (screenRecorder == null) {
				logger.warn("ScreenRecorder not found for thread: " + threadId);
				recordingStatusMap.remove(threadId);
				return null;
			}
			
			// Stop recording using reflection
			Class<?> screenRecorderClass = Class.forName("org.monte.screenrecorder.ScreenRecorder");
			screenRecorderClass.getMethod("stop").invoke(screenRecorder);
			
			// Get video file path
			String videoFilePath = videoPathMap.get(threadId);
			
			// Handle record only on failure option
			if (recordOnlyOnFailure && testPassed) {
				// Delete video file if test passed and we only record failures
				File videoFile = new File(videoFilePath);
				if (videoFile.exists()) {
					boolean deleted = videoFile.delete();
					if (deleted) {
						logger.info("Video deleted (test passed, recordOnlyOnFailure=true): " + videoFilePath);
					} else {
						logger.warn("Failed to delete video file: " + videoFilePath);
					}
				}
				videoFilePath = null; // Return null to indicate video was deleted
			} else {
				logger.info("Video recording stopped and saved for test: " + testMethodName + " (Thread: " + threadId + ")");
				logger.info("Video saved to: " + videoFilePath);
			}
			
			// Clean up thread-specific data
			recorderMap.remove(threadId);
			videoPathMap.remove(threadId);
			recordingStatusMap.remove(threadId);
			
			return videoFilePath;
			
		} catch (Exception e) {
			logger.error("Failed to stop video recording for test: " + testMethodName, e);
			
			// Clean up on failure
			recorderMap.remove(threadId);
			videoPathMap.remove(threadId);
			recordingStatusMap.remove(threadId);
			
			return null;
		}
	}
	
	/**
	 * Check if recording is in progress for the current thread
	 * @return true if recording is active, false otherwise
	 */
	public static boolean isRecording() {
		long threadId = Thread.currentThread().getId();
		return recordingStatusMap.getOrDefault(threadId, false);
	}
	
	/**
	 * Force stop recording for the current thread (cleanup method)
	 * Use this in case of unexpected failures
	 */
	public static void forceStopRecording() {
		if (!isMonteMediaAvailable()) {
			return;
		}
		
		long threadId = Thread.currentThread().getId();
		
		try {
			Object screenRecorder = recorderMap.get(threadId);
			if (screenRecorder != null) {
				Class<?> screenRecorderClass = Class.forName("org.monte.screenrecorder.ScreenRecorder");
				screenRecorderClass.getMethod("stop").invoke(screenRecorder);
				logger.info("Force stopped video recording for thread: " + threadId);
			}
		} catch (Exception e) {
			logger.error("Error force stopping recording for thread: " + threadId, e);
		} finally {
			// Clean up regardless of success/failure
			recorderMap.remove(threadId);
			videoPathMap.remove(threadId);
			recordingStatusMap.remove(threadId);
		}
	}
	
	/**
	 * Get video directory path
	 * @return Video directory path
	 */
	public static String getVideoDirectory() {
		return VIDEO_DIR;
	}
	
	/**
	 * Clean up old video files (optional utility method)
	 * @param daysOld Delete videos older than this many days
	 * @return Number of files deleted
	 */
	public static int cleanupOldVideos(int daysOld) {
		File videoDir = new File(VIDEO_DIR);
		if (!videoDir.exists() || !videoDir.isDirectory()) {
			return 0;
		}
		
		long cutoffTime = System.currentTimeMillis() - (daysOld * 24L * 60L * 60L * 1000L);
		int deletedCount = 0;
		
		File[] files = videoDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".avi"));
		if (files != null) {
			for (File file : files) {
				if (file.lastModified() < cutoffTime) {
					if (file.delete()) {
						deletedCount++;
						logger.info("Deleted old video file: " + file.getName());
					}
				}
			}
		}
		
		logger.info("Cleaned up " + deletedCount + " old video files (older than " + daysOld + " days)");
		return deletedCount;
	}

}

