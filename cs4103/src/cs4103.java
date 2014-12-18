//NAME:        (GUPTA,AYUSH)
//PROJECT:     (PA-1a)
//FILE:        (cs4103)
//INSTRUCTOR:  (FENG CHEN)
//CLASS:       (cs4103-sp14)
//LOGONID:     (cs4103xx)



public class cs4103 {
    
   
    public static final int NUM_THREADS = 10; //WE USE 9 ONLY
    
    public static void main(String args[])
    {
        int row;
        int col;
        int A[][] = {{1,4},{2,5},{3,6}};
        int B[][] = {{8,7,6},{5,4,3}};
        int C[][] = new int[3][3];
        int i = 0;   
        Thread[] workers = new Thread[NUM_THREADS];
               
                try
                {
                   for(row = 0 ; row < 3; row++)
                   {
                        for (col = 0 ; col < 3; col++ )
                        {                                
                             workers[i] = new Thread(new WorkerThread(row, col, A, B, C));
                             workers[i].start(); 
                             workers[i].join(); 
                             i++;
                        }
                   }
                  
                }
                catch (InterruptedException ie){}
                              
                              
                
                System.out.println("THE ANSWER: ");
                for(row = 0 ; row < 3; row++)
                {
                        for (col = 0 ; col < 3; col++ )
                        {
                            System.out.print("  "+C[row][col]);
                        }
                        System.out.println();
                 }
            
    }
    
}



class WorkerThread implements Runnable
{
    private int row;
    private int col;
    private int A[][];
    private int B[][];
    private int C[][];
    
    
    public WorkerThread(int row, int col, int [][]A, int [][]B, int [][]C )
    {
        this.row = row;
        this.col = col;
        this.A = A;
        this.B = B;
        this.C = C;
    }
    
    @Override
    public void run()
    {
       
       
            for(int k = 0; k < B.length; k++)
            {
             C[row][col] = C[row][col] + (A[row][k] * B[k][col]);
            }
                     
    }
   
}