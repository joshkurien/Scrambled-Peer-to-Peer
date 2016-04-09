package quanatisation;

import java.util.Random;

public class random_permutation {
	
	static int calc_perm_size(int array_len, int channel_no)
	{
		int difference = channel_no - array_len%channel_no;
		
		if(difference==0)
			return array_len+(2*channel_no);		
		
		return array_len+difference+(2*channel_no);		
	}
	static char m_array[],A[];
	static int P[]	;
	
	private static void init(int perm_size,String message,String key)
	{
		Random r = new Random();
		int i,j,s = key.length();
		
		for(i = message.length();i<perm_size;i++)
			message+= (char)(r.nextInt(26) + 'a');
		m_array = message.toCharArray(); 
		
		A = new char[perm_size];
		P = new int[perm_size];
		
		for(i=0;i<s;i++)
		{
			A[i] = key.charAt(i);
			P[i] = A[i];
		}
		for(i=0;i<s-1;i++)
			P[i]+=P[i+1];
			
		P[s-1] = A[0];		
		
		j=s;
		while(j<perm_size)
		{
			int k;
			
			for(i=0;i<s;i++)
			{
				for(k=i+1;k<s && j<perm_size;k++)
				{
					P[j] = P[i]+ P[k+i];
					j++;
				}
			}
		}
		
		for(i=0;i<perm_size;i++)
			P[i] = P[i]%perm_size;
	}

	private static void eliminate(int p_size)
	{
		int i,L=0,R=p_size-1;
		while(L<R)
		{
			for(i=L+1;i<=R;i++)
			{
				if(P[L]==P[i])
					P[i]=0;
			}
			for(i=R-1;i>L;i--)
			{
				if(P[R]==P[i])
					P[i]=0;
			}
			L++;
			R--;
		}
	}
	
	private static void fill(int p_size)
	{
		int i,j,count = 0,values[];
	
		//
		for(i = 0;i<p_size;i++)	
		{
			if(P[i]==0)
				count++;
		}
		values = new int[count];
		count=0;
		
		for(i = p_size;i>0;i--)	
		{
			for(j=0;j<p_size&&P[j]!=i;j++);
			if(j==p_size)
				values[count++] = i;
		}

		i=0;
		j=p_size-1;
		int k=0;
		while(i<count)
		{			
			while( P[j] != 0 && j > 0 )
				j--;
			if( j > 0 )
			{
				P[j] = values[i];
				i++;
			}
			while( P[k] != 0 && k<=p_size )
				++k;
			if(k<=p_size)
			{
				P[k] = values[i];
				i++;
			}
		}
	for(i = 0;i<p_size;i++)	
		System.out.print(P[i]);
	System.out.println(" ");
		for(i = 0;i<count;i++)	
			System.out.print(values[i]);
		
	}
	
	public static void kbrp(String key, String message,int no_channel)
	{
		int pSize=calc_perm_size(message.length(),no_channel);
		
		init(pSize,message,key);
		eliminate(pSize);
		fill(pSize);
		
	}
	
	public static void main(String Args[])
	{		
		String message="abcdefg",key="computer";
		int pSize=calc_perm_size(message.length(),2);
		init(pSize,message,key);
		eliminate(pSize);
		fill(pSize);
	}
}
