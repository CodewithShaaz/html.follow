public class DH {
 
    private static long power(long a,long b,long P)
    {
        if(b==1)
         return a;
        else
         return(((long)Math.pow(a,b) % P));
    }
    public static void main(String []args)
    {
        long a,b,x,y,G,P,ka,kb;
        P=23;
        System.out.println("The value of prime number:"+P);
        G=9;
        System.out.println("The value of primitive root:"+G);
        a=4;
        System.out.println("The private key for A:"+a);
        x=power(G,a,P);
        b=3;
        System.out.println("The private key for B:"+b);
        y=power(G,b,P);
        ka=power(y,a,P);
        kb=power(x,b,P);
        System.out.println("The secret key value of A is:"+ka);
        System.out.println("The secret key value of B is"+kb);
    }

}
