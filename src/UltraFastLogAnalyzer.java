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
        
        System.out.print("Search Word: "); String word = sc.nextLine();
        
        byte[] pattern =word.getBytes();

        try (FileChannel in =FileChannel.open(Paths.get("test.log"), StandardOpenOption.READ))
        {
            ForkJoinPool pool =new ForkJoinPool();
            
            long start = System.currentTimeMillis();
            
            List<Long> res =pool.invoke(new SearchTask(in, 0, in.size(), pattern));
            
            System.out.println("found "+res.size()+" in "+(System.currentTimeMillis()-start)+" ms ");
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
        
        protected List<Long> compute()
        {
            if (e - s < 1024 * 1024)
                return search(); 
        
            
            long m = s + (e - s) / 2;
            SearchTask t1 = new SearchTask(ch, s, m, p);
            SearchTask t2 = new SearchTask(ch, m, e, p);
            t1.fork();
            List<Long> r = t2.compute(); r.addAll(t1.join());
            return r;
            
        }
        private List<Long> search()
        {
            List<Long> found = new ArrayList<>();
            try
            {
                MappedByteBuffer mb = ch.map(FileChannel.MapMode.READ_ONLY, s, e - s);
                for (int i=0; i<=mb.limit()-p.length;i++)
                {
                    boolean m = true;
                    for (int j=0;j<p.length;j++)
                        if(mb.get(i+j)!=p[j])
                        { 
                            m=false; 
                            break; 
                        }
                    
                    if (m) 
                        found.add(s + i);
                    
                }
                
            }
            catch (Exception ex)
            {
            }
            return found;
        }
    }
}
