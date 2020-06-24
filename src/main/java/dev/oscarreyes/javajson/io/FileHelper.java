package dev.oscarreyes.javajson.io;

import java.io.*;
import java.net.URL;
import java.util.logging.Logger;

public class FileHelper {
	private static final Logger LOGGER = Logger.getLogger(FileHelper.class.getName());

	public static String readResourceFile(String filename) {
		final FileHelper instance = new FileHelper();
		final URL resource = instance.getClass()
			.getClassLoader()
			.getResource(filename);

		if (resource == null) {
			LOGGER.warning(String.format("Unable to find resource file: %s", filename));

			return null;
		}

		final StringBuilder stringBuilder = new StringBuilder();
		final File resourceFile = new File(resource.getFile());

		try {
			FileReader fileReader = new FileReader(resourceFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;

			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line + System.lineSeparator());
			}
		} catch (FileNotFoundException e) {
			LOGGER.warning(String.format("Could not read file %s. Reason: %s", filename, e.getMessage()));
		} catch (IOException e) {
			LOGGER.warning(String.format("Cannot read content of file %s. Reason: %s", filename, e.getMessage()));
		}

		return stringBuilder.toString();
	}
}
