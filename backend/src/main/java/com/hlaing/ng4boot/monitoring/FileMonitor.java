package com.hlaing.ng4boot.monitoring;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author hlaingwintun
 *
 */
public class FileMonitor {
	private static final Logger logger = LoggerFactory.getLogger(FileMonitor.class);
 
	// Get the user home directory to be monitored
	private static final String FOLDER = System.getProperty("user.home");
	
	// The monitor will perform polling on the folder every 30 seconds
	private static final long pollingInterval = 30 * 1000;
	
	public static void main(String[] args) throws Exception {
		
		// Change this to match the environment you want to watch.
		final File directory = new File(FOLDER);
		
		// Create a new FileAlterationObserver on the given directory
		FileAlterationObserver fob = new FileAlterationObserver(directory);
		
		// Create a new FileAlterationListenerImpl and pass it the previously created FileAlterationObserver
		fob.addListener(new FileAlterationListenerImpl());
		
		// Create a new FileAlterationMonitor with the given pollingInterval period
		final FileAlterationMonitor monitor = new FileAlterationMonitor(pollingInterval);
		
		// Add the previously created FileAlterationObserver to FileAlterationMonitor
		monitor.addObserver(fob);
		
		// Start the FileAlterationMonitor
		monitor.start();
		
		logger.info("Starting monitor ( {} ). \"Press CTRL+C to stop\"", FOLDER);
	} 
}
