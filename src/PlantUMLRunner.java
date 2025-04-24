import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PlantUMLRunner {
    private static String jarPath;
    private static File file;
    public static void filePathSetter(String path){
        jarPath = path;
    }
    public static void generate(String data, String filePathOut, String fileNameOut){
        file = new File(filePathOut);
        file.mkdirs();
        String fullPath = filePathOut+"/"+fileNameOut+".txt";
        try {
            FileWriter writing = new FileWriter(fullPath);
            writing.write(data);
            writing.close();
            ProcessBuilder process = new ProcessBuilder("Java", "-jar", jarPath, fullPath);
            Process p = process.start();
            System.out.println(p.info());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
