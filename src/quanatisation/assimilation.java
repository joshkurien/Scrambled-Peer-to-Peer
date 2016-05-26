package quanatisation;

public class assimilation {
	public static String assemble(String key,char scrambled[][],int len,int no_channel)
	{
		char assembled[];
		String result;
		int i,msg_size;
		
		msg_size = len*no_channel;
		assembled = new char[msg_size];
		
		int temp[]={0,0},map[] = random_permutation.kbrp(key,msg_size,no_channel);
		
		for(i=0;temp[0]<no_channel;i++)
		{			
			assembled[map[i]-1] = scrambled[temp[0]][temp[1]];
			temp[1]++;
			if(temp[1]==len)
			{
				temp[1]=0;
				temp[0]++;
			}
		}
		
		i=assembled[msg_size-1];
		i+=no_channel*2;
		assembled[msg_size-i] = '\0';
		
		result = new String(assembled);
		
		return(result.substring(0, msg_size-i));
	}
	
/*	public static void main(String Args[])
	{
		int chno=4,len;
		String key="k3cew",message="This is a very very very long message that I'm trying to make for debugging purposes only";
		
		char scrambled[][] = division.scramble(key, message, chno);
		len = scrambled[0].length;
		
		System.out.println(assemble(key,scrambled,len,chno));
	}*/
}
