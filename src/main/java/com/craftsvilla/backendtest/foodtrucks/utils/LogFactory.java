package com.craftsvilla.backendtest.foodtrucks.utils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.stereotype.Service;

@Service("LogFactory")
public class LogFactory {

	private ConfigUtils configUtils = new ConfigUtils();

	private static Logger logger = LogManager.getRootLogger();

	public LogFactory() throws URISyntaxException {
		logger.info("log factory initialized");
		String log4jConfigFile = "/ENV-"
				+ configUtils.properties.getProperty("environment")
				+ File.separator + "log.xml";
		System.setProperty("log4j.configurationFile", log4jConfigFile);

		LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager
				.getContext(false);

		// InputStream is = LogFactory.class.getClassLoader()
		// .getResourceAsStream(log4jConfigFile);
		// File file = new File(is);

		// this will force a reconfiguration
		context.setConfigLocation(new URI(log4jConfigFile));
		logger.info(log4jConfigFile);
		logger.info("Logging Enabled");
	}

	public static Logger getLogger(Class<?> clazz) {

		return logger = LogManager.getLogger(clazz);
	}

	public Logger getLOGGER(Class<?> clazz) {
		return logger = LogManager.getLogger(clazz);
	}

}
