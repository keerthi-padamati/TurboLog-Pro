import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class UltraFastLogAnalyzer
{
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Search Word: ");
        String word = sc.nextLine();
        byte[] pattern = word.getBytes();

        try (FileChannel in = FileChannel.open(Paths.get("test.log"), StandardOpenOption.READ))
        {
            ForkJoinPool pool = new ForkJoinPool();
            long start = System.currentTimeMillis();

            List<Long> res = pool.invoke(
                new SearchTask(in, 0, in.size(), pattern)
            );

            
            System.out.println("Found " + res.size() + " in + (System.currentTimeMillis() - start) + "ms");
            
        }
    }

    static class SearchTask extends RecursiveTask<List<Long>>
    {
        FileChannel ch;
        long s, e;
        byte[] p;

        SearchTask(FileChannel ch, long s, long e, byte[] p)
        {
            this.ch = ch;
            this.s = s;
            this.e = e;
            this.p = p;
        }

        protected List<Long> compute()
        {
            return new ArrayList<>();
        }
    }
}
