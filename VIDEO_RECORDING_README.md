# Video Recording Feature Documentation

## Overview
This framework includes automatic video recording functionality for test execution. Videos are recorded for each test case and saved automatically.

## Features
- ✅ Automatic video recording for each test case
- ✅ Thread-safe implementation (supports parallel execution)
- ✅ Option to record only failed tests (saves disk space)
- ✅ Videos saved with test method name and timestamp
- ✅ Cross-platform support (Windows, Linux, Mac)
- ✅ Automatic cleanup of old videos (optional)

## Configuration

### Enable/Disable Video Recording
Edit `src/test/resources/config.properties`:

```properties
# Enable video recording (true/false)
video_recording_enabled=true

# Record only failed tests (true/false)
# If true, videos of passed tests will be deleted automatically
video_record_only_on_failure=false
```

### Configuration Options

| Property | Default | Description |
|----------|---------|-------------|
| `video_recording_enabled` | `false` | Set to `true` to enable video recording |
| `video_record_only_on_failure` | `false` | Set to `true` to save videos only for failed tests |

## Video Storage

- **Location**: `./test-output/videos/`
- **Naming Format**: `{TestMethodName}_{Timestamp}.avi`
- **Example**: `verify_signin_20250113_143022.avi`

## How It Works

1. **Before Test Execution** (`@BeforeMethod`):
   - Video recording starts automatically if enabled
   - Recording is thread-safe (each thread has its own recorder)

2. **During Test Execution**:
   - Screen is recorded continuously
   - Recording captures the entire screen

3. **After Test Execution** (`@AfterMethod`):
   - Video recording stops
   - Video is saved with test method name and timestamp
   - If `record_only_on_failure=true` and test passed, video is deleted

## Thread Safety

The implementation uses `ConcurrentHashMap` to ensure thread-safe operation:
- Each thread has its own `ScreenRecorder` instance
- Videos are saved with unique names per thread
- No conflicts when running tests in parallel

## Usage Examples

### Example 1: Record All Tests
```properties
video_recording_enabled=true
video_record_only_on_failure=false
```
Result: All test videos are saved

### Example 2: Record Only Failed Tests
```properties
video_recording_enabled=true
video_record_only_on_failure=true
```
Result: Only failed test videos are saved (saves disk space)

### Example 3: Disable Video Recording
```properties
video_recording_enabled=false
```
Result: No videos are recorded

## Video File Management

### Clean Up Old Videos
The `VideoRecorder` class includes a utility method to clean up old videos:

```java
// Delete videos older than 7 days
VideoRecorder.cleanupOldVideos(7);
```

### Manual Cleanup
Videos are stored in `./test-output/videos/`. You can manually delete old videos as needed.

## Troubleshooting

### Issue: Videos not being recorded
**Solution**: 
1. Check `video_recording_enabled=true` in config.properties
2. Ensure the videos directory exists: `./test-output/videos/`
3. Check logs for any recording errors

### Issue: Videos are too large
**Solution**: 
1. Enable `video_record_only_on_failure=true` to save only failed test videos
2. Regularly clean up old videos using `cleanupOldVideos()` method

### Issue: Recording fails on Windows
**Solution**: 
1. Ensure you have proper screen recording permissions
2. Check if antivirus is blocking screen recording
3. Run tests with administrator privileges if needed

## Technical Details

### Dependencies
- `org.monte:media:4.3.2` - Monte Media Library for screen recording
- `com.github.stephenc.monte:monte-media:1.0.1` - Alternative Monte Media implementation

### Video Format
- **Format**: AVI
- **Codec**: TechSmith Screen Capture Codec (TSCC)
- **Frame Rate**: 15 fps (configurable)
- **Quality**: High quality (1.0f)

### Performance Impact
- Video recording adds minimal overhead (~5-10% CPU usage)
- Disk space: Approximately 1-5 MB per minute of recording
- Memory usage: Minimal (uses native screen capture)

## Best Practices

1. **For CI/CD Pipelines**: Set `video_record_only_on_failure=true` to save storage space
2. **For Local Development**: Set `video_recording_enabled=false` if not needed
3. **For Debugging**: Enable recording for all tests to capture all scenarios
4. **Storage Management**: Regularly clean up old videos to prevent disk space issues

## Integration with ExtentReports

To attach videos to ExtentReports, you can extend the `ExtentReportManager` class:

```java
// In ExtentReportManager.onTestFailure()
String videoPath = VideoRecorder.getVideoPath();
if (videoPath != null) {
    test.addScreenCaptureFromPath(videoPath);
}
```

## Support

For issues or questions, check the logs in `./logs/automation.log` for detailed error messages.

