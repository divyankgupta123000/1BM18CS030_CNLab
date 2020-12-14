package CNLab;
import java.util.Scanner;

public class LeakyBucket 
{
    public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    int bucket=0,op_rate,i,n,bsize;
    
    System.out.println("Enter total number of Packets : ");
    n=sc.nextInt();
    
    int pkt[]=new int[n];
    
    System.out.println("Enter the output rate of the Bucket : ");
    op_rate=sc.nextInt();
    
    System.out.println("Enter the Bucket Size : ");
    bsize=sc.nextInt();
    
    System.out.println("Enter the size of arriving packets : ");
    for(i=0;i<n;i++)
        pkt[i]=sc.nextInt();
    
    System.out.println("\nsec\tpsize\tbucketsize\taccept/reject\tpkt_send");
    System.out.println("-------------------------------------------------------");
    for(i=0;i<n;i++)
    {
        System.out.print(i+1+"\t"+pkt[i]+"\t");
        if(bucket+pkt[i]<=bsize)
        {
            bucket+=pkt[i];
            System.out.println(bucket+"\t\taccept\t\t"+min(bucket,op_rate)+"\n");
            bucket=sub(bucket,op_rate);
        }
        else
        {
            System.out.println(bucket+"\t\treject\t"+(bucket+pkt[i]-bsize) +"\t"+min(bsize,op_rate)+"\n");
            bucket=bsize;
            bucket=sub(bucket,op_rate);
        }
    }
    while(bucket!=0)
    {
        System.out.print(++i +"\t 0 \t"+bucket+"\t\taccept\t\t"+min(bucket,op_rate)+"\n");
        bucket=sub(bucket,op_rate);
    }
    sc.close();
  }

  static int min(int a,int b)
  {
      return(a<b)?a:b;
  }

  static int sub(int a,int b)
  {
      return(a-b)>0?(a-b):0;
  }
  
}
