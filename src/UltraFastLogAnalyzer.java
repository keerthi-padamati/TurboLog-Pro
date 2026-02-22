public class UltraFastLogAnalyzer
{
    public static void main(String[] args) throws Exception
  {
        try (FileChannel in = FileChannel.open(Paths.get("test.log"), StandardOpenOption.READ))
        {
            ForkJoinPool pool = new ForkJoinPool();
            try
            {
                List<Long> results = pool.invoke(new SearchTask(in, 0, in.size(), "CRITICAL_ERROR".getBytes()));
                System.out.println("Matches: " + results.size());
            }
            finally
            {
                pool.shutdown();
            }
        }
    }



    static class SearchTask extends RecursiveTask<List<Long>>
    {
            FileChannel ch; long s, e; byte[] p;
            SearchTask(FileChannel ch, long s, long e, byte[] p)
            {
                this.ch=ch; 
                this.s=s; 
                this.e=e; 
                this.p=p; 
            }

        
    }
}
