package io.ffreedom.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public final class FileUtil {

	public static void deleteAllChildFile(File parentFile, FileFilter fileFilter) {
		List<File> loadAllChildFile = loadAllChildFile(parentFile, fileFilter);
		for (File file : loadAllChildFile)
			file.delete();
	}

	public static List<File> loadAllChildFile(File parentFile, FileFilter fileFilter) {
		List<File> list = new ArrayList<>();
		loadAllChildFile(list, parentFile, fileFilter);
		return list;
	}

	private static void loadAllChildFile(List<File> loadList, File parentFile, FileFilter fileFilter) {
		if (parentFile == null || fileFilter == null)
			return;
		File[] listFiles = parentFile.listFiles();
		if (listFiles != null && listFiles.length != 0) {
			for (File file : listFiles) {
				if (file.isDirectory())
					loadAllChildFile(loadList, file, fileFilter);
				else if (fileFilter.filter(file))
					loadList.add(file);
				else
					continue;
			}
		} else {
			return;
		}
	}

	@FunctionalInterface
	public interface FileFilter extends Predicate<File> {

		boolean filter(File file);

		@Override
		default boolean test(File file) {
			return filter(file);
		}

	}

}
