package io.github.tanghuibo.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 文件工具类
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-31 01:01
 **/
public class FileUtil {
    /**
     * 工程目录
     */
    private static String projectPath;

    public static String getProjectPath() {
        if(projectPath == null) {
            synchronized (FileUtil.class) {
                if (projectPath == null) {
                    synchronized (FileUtil.class) {
                        projectPath = getSrcPath();
                    }
                }
            }

        }
        return projectPath;

    }

    private static String getSrcPath() {
        String userDir = System.getProperty("user.dir");
        try {
            String mainJava = System.getProperty("sun.java.command");
            if (mainJava != null && !"".equals(mainJava)) {
                int index = mainJava.lastIndexOf(".");
                if (index >= 0) {
                    mainJava = mainJava.substring(index + 1) + ".java";
                    mainJava = mainJava.toUpperCase();
                }

            }
            File file = new File(userDir);
            File searchFile = null;
            if (file.isDirectory()) {
                File[] fileList = file.listFiles();
                while (fileList.length > 0) {
                    List<File> newFileList = new ArrayList<File>();
                    for (File fl : fileList) {

                        if (fl.isDirectory()) {
                            File[] files = fl.listFiles();
                            if (files.length > 0) {
                                newFileList.addAll(Arrays.asList(files));
                            }

                        }
                        if (fl.isFile()) {
                            if (fl.getName().toUpperCase().equals(mainJava)) {
                                searchFile = fl;
                                break;
                            }
                        }
                    }
                    fileList = newFileList.toArray(new File[newFileList.size()]);
                }
            }
            if (searchFile != null) {
                String path = searchFile.getPath();

                return path.substring(0, path.length() - System.getProperty("sun.java.command").length() - 6);
            }


        } finally {

        }

        return userDir + "\\src\\main\\java";

    }
}
