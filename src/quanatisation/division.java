package quanatisation;

import java.util.Random;

public class division {
	
	static int calc_perm_size(int array_len, int channel_no)
	{
		int difference = channel_no - array_len%channel_no;
		
		if(difference==0)
			return array_len+(2*channel_no);		
		
		return array_len+difference+(2*channel_no);		
	}
	
	public static char[][] scramble(String key, String message,int no_channel)
	{
		Random r = new Random();
		int i,perm_size=calc_perm_size(message.length(),no_channel);
		char output[][];
		{
			int temp = message.length();
		for(i = temp;i<perm_size-1;i++)
			message+= (char)(r.nextInt(100));
			
		temp = (perm_size - 2*no_channel) - temp;
		message+=(char)(temp);
		}
		int temp[]={0,0},map[] = random_permutation.kbrp(key,perm_size,no_channel);
		
		output = new char[no_channel][perm_size/no_channel];
		
		for(i=0;i<perm_size;i++)
		{			
			output[temp[0]][temp[1]] = message.charAt(map[i]-1);
			temp[1]++;
			if(temp[1]==perm_size/no_channel)
			{
				temp[1]=0;
				temp[0]++;
			}
		}
		
		return output;		
	}
	
	public static void main(String Args[])
	{
		int ch=4;
		char output[][] = scramble("qwert","Amazing woohoo",ch);
		for(int i=0;i<ch;i++)
			System.out.println(output[i]);
	}
}
