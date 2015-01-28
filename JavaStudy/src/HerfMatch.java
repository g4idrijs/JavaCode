import java.io.*;
import java.net.*;
import java.util.regex.*;

public class HerfMatch {
    public static void main(String[] args) {
        System.out.println("\n**********");
        try {
            String urlString;
            if (args.length > 0) {
                urlString = args[0];
            } else {
                urlString = "http://www.sun.com";
            }

            InputStreamReader in = new InputStreamReader(new URL(urlString).openStream());
            StringBuilder input = new StringBuilder();
            int ch;
            while ((ch = in.read()) != -1) {
                input.append((char) ch);
            }
//            System.out.println(input);//获得页面文件源代码
            String pattenString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
            Pattern pattern = Pattern.compile(pattenString, Pattern.CASE_INSENSITIVE);
//            Pattern pattern = Pattern.compile(pattenString, Pattern.LITERAL);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                String match = input.substring(start, end);
                System.out.println(match);
            }
        } catch (IOException | PatternSyntaxException e) {
            e.printStackTrace();
        }

    }

}
