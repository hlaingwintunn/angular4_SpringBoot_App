package com.hlaing.ng4boot.monitoring;

import java.io.File;
import java.nio.file.Files;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 
 * @author hlaingwintun
 *
 */
@Component
public class FileMonitor {
	private static final Logger logger = LoggerFactory.getLogger(FileMonitor.class);

	private FileAlterationMonitor monitor;

	@Value("${data.directory}")
	private String dataDirectory;

	@EventListener(ContextRefreshedEvent.class)
	private void contextRefreshedEvent() {
		start();
	}

	@EventListener(ContextStoppedEvent.class)
	private void contextStoppedEvent() {
		stop();
	}

	// Get the user home directory to be monitored
	private static final String FOLDER = System.getProperty("data.directory");

	// The monitor will perform polling on the folder every 30 seconds
	private static final long pollingInterval = 5 * 1000;

	public void start() {
		initDataDirectory();

		// Change this to match the environment you want to watch.
		//final File directory = new File(FOLDER);

		// Create a new FileAlterationObserver on the given directory
		FileAlterationObserver fob = new FileAlterationObserver(dataDirectory);

		// Create a new FileAlterationListenerImpl and pass it the previously created
		// FileAlterationObserver
		fob.addListener(new FileAlterationListenerImpl());

		// Create a new FileAlterationMonitor with the given pollingInterval period
		 monitor = new FileAlterationMonitor(pollingInterval);

		// Add the previously created FileAlterationObserver to FileAlterationMonitor
		monitor.addObserver(fob);

		// Start the FileAlterationMonitor
		try {
			logger.info("Starting monitoring {}", dataDirectory);
			monitor.start();
		} catch (Exception e) {
			logger.error("failed to start monitoring {}", dataDirectory);
		}
	}

	public void stop() {
		try {
			monitor.stop(100);
		} catch (Exception ex) {
			logger.warn("Failed to stop monitoring data directory.");
		}
	}

	private void initDataDirectory() {
		logger.debug("Checking data directory {} exists..", dataDirectory);

		File dataDir = new File(dataDirectory);

		if (dataDir.exists()) {
			logger.info("Data Directory {} exists", dataDirectory);

			if (Files.isWritable(dataDir.toPath())) {
				logger.info("Data directory is writable");
			} else {
				logger.warn("Data Directory is not writable. Attempting to change permission.");
				boolean canRead = dataDir.setReadable(true);
				boolean canWrite = dataDir.setWritable(true);

				if (canRead && canWrite) {
					logger.info("Data directory is Ready");
				} else {
					setTempDirectory();
				}
			}
		} else {
			logger.warn("Data directory {} does not exists. Trying to create the directory now ..", dataDirectory);

			boolean isCreated = dataDir.mkdir();

			if (isCreated) {
				logger.info("{} directory is created and ready.", dataDirectory);
			} else {
				setTempDirectory();
			}
		}
	}
	
	private void setTempDirectory() {
		final String tempDir = System.getProperty("java.io.tmpdir");
		
		logger.debug("Unable to use {}. Using temp directory {} instead.", dataDirectory, tempDir);
		dataDirectory = tempDir;
	}
}
