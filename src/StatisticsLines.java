import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by jiangyukun on 2017/6/16.
 */
public class StatisticsLines {

    static String file = "C:/Users/jiangyukun/WebstormProjects/smell-shell-console/src/containers";
    static String file1 = "C:/Users/jiangyukun/WebstormProjects/smell-shell-console/css/app";
    static String file2 = "D:/2017/projects/simo-recruit-console/src";
    static String file3 = "D:/2017/web/app-core/src";

    public static void main(String[] args) throws IOException {
        int total = new StatisticsLines().traversalDirection(new File(file));
        System.out.println(total);
    }

    public int traversalDirection(File file) throws IOException {
        int totalLines = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    totalLines += this.traversalDirection(f);
                } else {
                    totalLines += statisticsFileLines(f);
                }
            }
        } else {
            totalLines += statisticsFileLines(file);
        }
        return totalLines;
    }

    public int statisticsFileLines(File file) throws IOException {
        int count = 0;
        String fileName = file.getName();
        if (fileName.contains(".js")
                || fileName.contains(".jsx")
                || fileName.contains(".ts")
                || fileName.contains(".tsx")
                || fileName.contains(".scss")
                || fileName.contains(".less")
                || fileName.contains(".css")
                ) {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            while (bufferedReader.readLine() != null) {
                count++;
            }
        }

        return count;
    }

}
