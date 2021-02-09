import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**@author Rahul Myneni
 * UTA ID:1001678634*/
/**This program implements a system that (a)Can determine the posterior probability 
 *of different hypotheses, given priors for these hypotheses, and given a sequence
 *of observations.(b)Can determine the probability that the next observation will be
 *of a specific type, priors for different hypotheses, and given a sequence of 
 *observations.
 **/

//rounding values, writing output to a file
public class compute_a_posteriori {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		double prior[]= {10, 20, 40, 20, 10}; //initialization of an array in percentages(%)
		int cherry[]= {100, 75, 50, 25, 0};
		int lime[]= {0, 25, 50, 75, 100};
		String Q = args[0];
		String outputFile = "result.txt";
		BufferedWriter output = new BufferedWriter(
				new FileWriter( outputFile ));
		System.out.println("Observation sequence Q: " +Q);
		output.write("Observation sequence Q: " +Q+"\n");
		System.out.println("Length of Q: " +Q.length());
		output.write("Length of Q: " +Q.length()+"\n");

		for(int m=0;m<Q.length();m++) 
		{
			System.out.println("After Observation "+(m+1)+" = "+Q.charAt(m));
			output.write("After Observation "+(m+1)+" = "+Q.charAt(m)+"\n");
			double temp = 0.0, templ=0.0;	
			for(int i=0;i<5;i++) // to calculate Pt+1(Qt)
			{
				temp+= cherry[i]*prior[i]/10000.0;
				templ+= lime[i]*prior[i]/10000.0;
			}
			if (Q.charAt(m)=='C')// 1st element of the the observation 
			{
				for (int j=0;j<5;j++)
				{

					prior[j]= cherry[j]*prior[j]/(temp*100); // to calculate Pt(h(i))
					System.out.println("P(h"+(j+1)+"| Q) = "+String.format("%.15f",(prior[j]/100.00 )));//round value to 5 decimal point
					output.write("P(h"+(j+1)+"| Q) = "+String.format("%.15f", (prior[j])/100.00) +"\n");

				}
			}
			else 
			{
				for (int j=0;j<5;j++) 
				{

					prior[j]= lime[j]*prior[j]/(templ*100);
					System.out.println("P(h"+(j+1)+"| Q) = "+String.format("%.15f",(prior[j]/100.0)));
					output.write("P(h"+(j+1)+"| Q) = "+String.format("%.15f", (prior[j])/100.00)+"\n");

				}
			}
			//	if(m==0) {
			temp = 0.0; templ=0.0;
			for(int i=0;i<5;i++) // to calculate Pt+1(Qt)
			{
				temp+= cherry[i]*prior[i]/10000.0;
				templ+= lime[i]*prior[i]/10000.0;
			}
			//}
			
			System.out.println("Probability that the next candy we pick will be C, given Q: "+String.format("%.15f", (temp))+"\n");
			output.write("Probability that the next candy we pick will be C, given Q: "+String.format("%.15f", (temp))+"\n");
			System.out.println("Probability that the next candy we pick will be L, given Q: "+String.format("%.15f", (templ))+"\n");
			output.write("Probability that the next candy we pick will be C, given Q: "+String.format("%.15f", (templ))+"\n");
			System.out.println();
			output.write("\n");
		}
		output.close();
	}

}
