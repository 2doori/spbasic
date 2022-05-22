package fileIO;

import java.io.*;

public class fileIO_test {

    static void printFile(String path) {

        File directory = new File(path);
        File[] fList = directory.listFiles();

        for(File file : fList) {
            if(file.isDirectory()) {
                printFile(file.getPath());
            } else {
                try {
                    System.out.println(file.getPath() + file.length() + "bytes");

                    if(file.length() > 3 * 1024) {
                        String outputFile = makeFilePath(file.getPath());
                        copyFile(file.getPath(), outputFile);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    static String makeFilePath(String filePath) {
        String newPath = filePath.replace("INPUT", "OUTPUT");

        File outputDirectory = new File(newPath.substring(0, newPath.lastIndexOf("\\")));
        System.out.println("outputDirectory => " + outputDirectory);
        if(!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        return newPath;
    }

    static void copyFile(String inputFile, String outputFile) {
        final int BUFFER_SIZE = 512;
        int readLen;

        try {

            InputStream inputStream = new FileInputStream(inputFile);
            OutputStream outputStream = new FileOutputStream(outputFile);

            byte[] buffer = new byte[BUFFER_SIZE];

            while((readLen = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, readLen);
            }

            inputStream.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        printFile("./fileIO/INPUT");
    }
}
