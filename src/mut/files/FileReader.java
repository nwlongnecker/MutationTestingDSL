package mut.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import mut.util.Msg;

public class FileReader {
	
	/**
	 * Reads the specified file and returns the contents as a string
	 * @param path Path to the file
	 * @param msg The messager to report errors to
	 * @return The file contents
	 */
	public static String readFile(String path, Msg msg)	{
		String ret = null;
		try {
			ret = new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			msg.err("Error reading from file " + e.getMessage());
		}
		return ret;
	}
	
	public static Collection<String> matchDirRegex(String currentDir, String regex) {
		Collection<String> fileList = new HashSet<String>();
		int slashIndex = regex.indexOf('/');
		int backslashIndex = regex.indexOf("\\");
		String nextDirRegex;
		String remainingRegex;
		if (slashIndex >= 0 || backslashIndex >= 0) {
			if ((slashIndex < backslashIndex || backslashIndex < 0) && slashIndex >= 0) {
				nextDirRegex = regex.substring(0, slashIndex);
				remainingRegex = regex.substring(slashIndex + 1);
			} else {
				nextDirRegex = regex.substring(0, backslashIndex);
				remainingRegex = regex.substring(backslashIndex + 1);
			}
		} else {
			// Support finding all files in subdirectories too
			if (regex.contains("*")) {
				Collection<String> matchedFiles = findMatchingFilesInDirTree(currentDir, regex);
				for (String match : matchedFiles) {
					if (!fileList.contains(match)) {
						fileList.add(match);
					}
				}
			}
			fileList.addAll(matchRegexInDir(currentDir, regex));
			return fileList;
		}
		if (nextDirRegex.equals("..")) {
			Collection<String> files = matchDirRegex(nextDirRegex, remainingRegex);
			for (String file : files) {
				fileList.add("../" + file);
			}
			return fileList;
		}
		Collection<String> matchedDirs = matchRegexInDir(currentDir, nextDirRegex);
		for(String dir : matchedDirs) {
			Collection<String> files = matchDirRegex(currentDir + "/" + dir, remainingRegex);
			for (String file : files) {
				fileList.add(dir + "/" + file);
			}
		}
		// Support finding all files in subdirectories too
		if (nextDirRegex.contains("*")) {
			Collection<String> matchedFiles = findMatchingFilesInDirTree(currentDir, regex);
			for (String match : matchedFiles) {
				if (!fileList.contains(match)) {
					fileList.add(match);
				}
			}
		}

		return fileList;
	}
	
	static Collection<String> matchRegexInDir(String dir, String regex) {
		Collection<String> fileList = new HashSet<String>();
		File f = new File(dir);
		Pattern p = Pattern.compile(regex.replace("*", ".*"));
		List<String> list = Arrays.asList(f.list());
		for(String file : list) {
			if (p.matcher(file).matches()) {
				fileList.add(file);
			}
		}
		return fileList;
	}
	
	static Collection<String> findMatchingFilesInDirTree(String baseDir, String regex) {
		Collection<String> fileList = new HashSet<String>();
		Pattern p = Pattern.compile(regex.replace("*", ".*"));
		Collection<String> list = getAllFilesInDir(baseDir, "");
		for(String file : list) {
			if (p.matcher(file.substring(1)).matches()) {
				fileList.add(file.substring(1));
			}
		}
		return fileList;
	}
	
	static Collection<String> getAllFilesInDir(String baseDir, String currentSubdir) {
		Collection<String> fileList = new HashSet<String>();
		File base = new File(baseDir + "/" + currentSubdir);
		List<String> list = Arrays.asList(base.list());
		for (String f : list) {
			File file = new File(baseDir + "/" + currentSubdir + "/" + f);
			if (file.isDirectory()) {
				Collection<String> innerFiles = getAllFilesInDir(baseDir + "/" + currentSubdir, f);
				for (String innerFile : innerFiles) {
					fileList.add(currentSubdir + "/" + innerFile);
				}
			} else {
				fileList.add(currentSubdir + "/" + f);
			}
		}
		return fileList;
	}
	
	public static Collection<String> explodeDirs(Collection<String> files) {
		Collection<String> filesToAdd = new HashSet<String>();
		for (String file : files) {
			File f = new File(file);
			if (f.isDirectory()) {
				List<String> list = Arrays.asList(f.list());
				for(int i = 0; i < list.size(); i++) {
					String dir = file;
					if (!file.endsWith("/")) {
						dir = file + "/";
					}
					list.set(i, dir + list.get(i));
				}
				filesToAdd.addAll(explodeDirs(list));
			} else {
				filesToAdd.add(file);
			}
		}
		return filesToAdd;
	}
}
