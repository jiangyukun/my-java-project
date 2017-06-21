import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by jiangyukun on 2017/6/16.
 */
public class StatisticsJavaLines {

    static String file = "D:/2017/company/shell-brunch/branch";
    static String file1 = "D:/2017/company/trunk/src";


    public static void main(String[] args) throws IOException {
        int total = new StatisticsJavaLines().traversalDirection(new File(file1));
        System.out.println(total);
    }

    public int traversalDirection(File file) throws IOException {
        int totalLines = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    if (f.getName().equals(".idea") || f.getName().equals(".settings") || f.getName().equals("target") || f.getName().equals("webapp")) {
                        continue;
                    }
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
        if (fileName.contains(".java")
                || fileName.contains(".xml")
                || fileName.contains(".properties")
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
