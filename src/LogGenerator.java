import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.Scanner;

public class LogGenerator
{
    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Size in GB: "); 
        long gb = sc.nextLong();
        long target = gb * 1024 * 1024 * 1024;
        
        
        if (target > new File(".").getUsableSpace())
        {
            System.out.println("No Space!"); 
            return; 
        }

        try(FileChannel out = FileChannel.open(Paths.get("test.log"), StandardOpenOption.CREATE, StandardOpenOption.WRITE))
        {
            ByteBuffer buf = ByteBuffer.allocate(8 * 1024 * 1024);
            byte[] line = "INFO 2026-02-19 CRITICAL_ERROR: Failure\n".getBytes();
            long current = 0;
            while (current < target)
            {
                if (buf.remaining() < line.length)
                { 
                    buf.flip(); 
                    out.write(buf); 
                    buf.clear(); 
                }
                buf.put(line); 
                current += line.length;
            }
            buf.flip(); 
            out.write(buf);
        }
        System.out.println("Dynamic Log Created.");
    }
}
