import java.io.*;
import java.nio.file.*;

public class LogGenerator
{
    public static void main(String[] args) throws IOException
    {
        String file = "test.log";
        
        String line = "INFO 2026-02-19 User action\n";
        
        String error = "SEVERE 2026-02-20 CRITICAL_ERROR\n";
        
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file)))
        {
            for (int i = 0; i < 1000000; i++)
                writer.write(i % 50000 == 0 ? error : line);
        }
        System.out.println("Log Created.");
    }
}
