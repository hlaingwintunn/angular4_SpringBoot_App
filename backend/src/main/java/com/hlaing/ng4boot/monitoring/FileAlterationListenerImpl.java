package com.hlaing.ng4boot.monitoring;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author hlaingwintun
 *
 */

public class FileAlterationListenerImpl implements FileAlterationListener {
	private static final Logger logger = LoggerFactory.getLogger(FileAlterationListenerImpl.class);

	@Override
	public void onDirectoryChange(final File directory) {
       logger.info("{} was modified", directory.getAbsolutePath());		
	}

	@Override
	public void onDirectoryCreate(final File directory) {
		logger.info("{} was created", directory.getAbsolutePath());		
	}

	@Override
	public void onDirectoryDelete(final File directory) {
		logger.info("{} was deleted", directory.getAbsolutePath());		
	}

	@Override
	public void onFileChange(final File file) {
		logger.info("{} was modified", file.getAbsoluteFile());	
		logger.info("1. lenght : {}", file.length());
		logger.info("2. last Modified : {}", new Date(file.lastModified()));
		logger.info("3. Readable : {}", file.canRead());
		logger.info("4. Writable : {}", file.canWrite());
		logger.info("5. Executable : {}", file.canExecute());
	}

	@Override
	public void onFileCreate(final File file) {
		logger.info("{} was created", file.getAbsoluteFile());		
		logger.info("1. lenght : {}", file.length());
		logger.info("2. last Modified : {}", new Date(file.lastModified()));
		logger.info("3. Readable : {}", file.canRead());
		logger.info("4. Writable : {}", file.canWrite());
		logger.info("5. Executable : {}", file.canExecute());
	}

	@Override
	public void onFileDelete(final File file) {
		logger.info("{} was deleted", file.getAbsoluteFile());		
	}

	@Override
	public void onStart(final FileAlterationObserver observer) {
        logger.info("The file listener has started on {}", observer.getDirectory().getAbsolutePath());		
	}

	@Override
	public void onStop(final FileAlterationObserver observer) {
		logger.info("The file listener has stoped on {}", observer.getDirectory().getAbsolutePath());		
	}

}
