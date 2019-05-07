package io.ffreedom.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public final class FileUtil {

	public static void deleteAllChildFile(File parentFile, Predicate<File> fileFilter) {
		List<File> allChildFile = loadAllChildFile(parentFile, fileFilter);
		for (File file : allChildFile) {
			if (file.exists())
				file.delete();
		}
	}

	public static List<File> loadAllChildFile(File parentFile, Predicate<File> fileFilter) {
		List<File> allFiles = new ArrayList<>();
		loadAllChildFile(allFiles, parentFile, fileFilter);
		return allFiles;
	}

	private static void loadAllChildFile(List<File> loadList, File parentFile, Predicate<File> fileFilter) {
		if (parentFile == null || fileFilter == null)
			return;
		File[] listFiles = parentFile.listFiles();
		if (listFiles != null && listFiles.length != 0) {
			for (File file : listFiles) {
				if (file.isDirectory())
					loadAllChildFile(loadList, file, fileFilter);
				else if (fileFilter.test(file))
					loadList.add(file);
				else
					continue;
			}
		} else
			return;
	}

}
