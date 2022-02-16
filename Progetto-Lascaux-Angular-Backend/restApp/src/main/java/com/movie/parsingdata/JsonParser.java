package com.movie.parsingdata;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.model.Movie;

public class JsonParser {

	private static Logger logger = Logger.getLogger(JsonParser.class);

	private static Layout patternLayout = new PatternLayout("%d %n %C %M %m");

	//// To not istantiate
	private JsonParser() {
	}

	/**
	 * @param path of JSON file
	 * 
	 * @return arrayOfMovie
	 * @throws IOException
	 */

	public static Movie[] parse(String path) {
		Appender appenderFile = null;
		try {
			appenderFile = new FileAppender(patternLayout, "log4j");
		} catch (IOException e1) {
			logger.error(e1);
		}
		logger.addAppender(appenderFile);
		ObjectMapper objectMapper = new ObjectMapper();
		File jsonFile = new File(path);

		try {
			return objectMapper.readValue(jsonFile, Movie[].class);
		} catch (JsonParseException | JsonMappingException e) {

			logger.error(e);
		} catch (IOException e) {
			logger.error(e);

		}
		return null;
	}

}
