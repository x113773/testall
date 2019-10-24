package com.ansel.testall.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FileContentReplaceHelper {
	/**
	 * 替换文件内单行内容
	 * 
	 * @param path
	 *            操作目录。从该目录开始。该文件目录下及其所有子目录的文件都将被替换。
	 * @param target
	 *            需要被替换 、改写的内容。
	 * @param newContent
	 *            需要新写入的内容。
	 */
	public static void Modify(String path, String target, String newContent) {
		Map<String, String> targetAndNew = new HashMap<String, String>();
		targetAndNew.put(target, newContent);
		Modify(path, targetAndNew);
	}

	/**
	 * 替换文件内多行内容
	 * 
	 * @param path
	 *            操作目录。从该目录开始。该文件目录下及其所有子目录的文件都将被替换。
	 * @param targetAndNew
	 *            key:需要被替换、改写的内容;value:需要新写入的内容。
	 */
	public static void Modify(String path, Map<String, String> targetAndNew) {
		File file = new File(path);
		try {
			InputStream is = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String filename = file.getName();
			// tmpfile为缓存文件，代码运行完毕后此文件将重命名为源文件名字。
			File tmpfile = new File(file.getParentFile().getAbsolutePath() + "\\" + filename + ".tmp");

			BufferedWriter writer = new BufferedWriter(new FileWriter(tmpfile));

			boolean flag = false;
			String str = null;
			String newContent = null;
			while (true) {
				str = reader.readLine();

				if (str == null)
					break;
				
				newContent = containString(str, targetAndNew);
				if (newContent != null) {
					writer.write(newContent + "\n");
					flag = true;
				} else
					writer.write(str + "\n");
			}

			is.close();

			writer.flush();
			writer.close();

			if (flag) {
				file.delete();
				tmpfile.renameTo(new File(file.getAbsolutePath()));
			} else
				tmpfile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String containString(String str, Map<String, String> targetAndNew) {
		String newContent = null;
		for (String target : targetAndNew.keySet()) {
			if (str.contains(target)) {
				newContent = targetAndNew.get(target);
			}
		}
		return newContent;
	}

}
