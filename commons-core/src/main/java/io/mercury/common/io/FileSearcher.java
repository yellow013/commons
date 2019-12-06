package io.mercury.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public final class FileUtil {

	public static void deleteFileWith(File parentFile, Predicate<File> fileFilter) {
		List<File> allChildFile = findFileWith(parentFile, fileFilter);
		for (File file : allChildFile) {
			if (file.exists())
				file.delete();
		}
	}

	public static List<File> findFileWith(File parentFile, Predicate<File> fileFilter) {
		List<File> allFiles = new ArrayList<>();
		findFileWith0(allFiles, parentFile, fileFilter);
		return allFiles;
	}

	private static void findFileWith0(List<File> loadList, File parentFile, Predicate<File> fileFilter) {
		if (parentFile == null || fileFilter == null)
			return;
		File[] listFiles = parentFile.listFiles();
		if (listFiles != null && listFiles.length != 0)
			for (File file : listFiles)
				if (file.isDirectory())
					findFileWith0(loadList, file, fileFilter);
				else if (fileFilter.test(file))
					loadList.add(file);
				else
					continue;
		else
			return;
	}

	public static void main(String[] args) {

		for (int i = 0; i < 20; i++)
			if (i == 0)
				System.out.println("00" + 0);
			else if (i % 2 == 0)
				System.out.println(i);
			else
				System.out.println("PPPP");

	}

}
