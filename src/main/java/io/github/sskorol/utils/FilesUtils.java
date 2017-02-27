package io.github.sskorol.utils;

import javaslang.control.Try;
import lombok.val;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

import static io.github.sskorol.utils.Configuration.CoreConstants.POLLING_INTERVAL;
import static io.github.sskorol.utils.Configuration.CoreConstants.WAIT_TIMEOUT;
import static org.apache.commons.io.FileUtils.isFileNewer;
import static org.awaitility.Awaitility.await;

public final class FilesUtils {

	public static boolean waitFor(final String path, final Date date) {
		return isFileNewer(new File(path), date);
	}

	public static void waitFor(final String path, final BiFunction<File, Date, Boolean> condition) {
		val date = new Date();

		await().atMost(WAIT_TIMEOUT, TimeUnit.SECONDS)
			   .pollInterval(POLLING_INTERVAL, TimeUnit.SECONDS)
			   .ignoreExceptions()
			   .until(() -> condition.apply(new File(path), date));
	}

	public static void main(String[] args) {
		waitFor("pathToFile", FileUtils::isFileNewer);
		waitFor("pathToFile", FileUtils::isFileOlder);

		val isFileNewer = Try.run(() -> waitFor("pathToFile", FileUtils::isFileNewer)).isSuccess();
	}

	private FilesUtils() {
		throw new UnsupportedOperationException("Illegal access to private constructor!");
	}
}
