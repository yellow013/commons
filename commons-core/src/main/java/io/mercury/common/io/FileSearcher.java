package io.mercury.common.io;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public final class FileSearcher {

	private FileSearcher() {
	}

	public static Set<File> findWith(File parentFile, Predicate<File> fileFilter) {
		Set<File> files = new HashSet<>();
		findWith0(files, parentFile, fileFilter);
		return files;
	}

	private static void findWith0(Set<File> loadFiles, File parentFile, Predicate<File> fileFilter) {
		if (parentFile == null || fileFilter == null)
			return;
		File[] listFiles = parentFile.listFiles();
		if (listFiles != null && listFiles.length != 0)
			for (File file : listFiles)
				if (file.isDirectory())
					findWith0(loadFiles, file, fileFilter);
				else if (fileFilter.test(file))
					loadFiles.add(file);
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
