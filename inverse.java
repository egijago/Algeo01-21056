
public class inverse 
{
    static float[][] invrs (float[][] M)
    {

        int sqr = M.length;
        float[][] invers= new float[sqr][sqr*2];
        float[][] hasil= new float[sqr][sqr];
        
        for(int i=0;i<sqr;i++)
        {
            for(int j=0;j<sqr;j++)
            {
                invers[i][j]=M[i][j];
            }
            
        }
        for (int p=0;p<sqr;p++)
        {
            for(int q=0;q<sqr;q++)
            {
                if(p==q)
                {
                    invers[p][q+sqr]=1;
                }
                else if(p!=q)
                {
                    invers[p][q+sqr]=0;
                }
            }
        }
        gaussJordan(invers);
        for(int i = 0; i<sqr;i++)
        {
            for (int j = sqr;j<2*sqr;j++)
            {
            
                hasil[i][j-sqr]=hasil[i][j-sqr]+invers[i][j];
                
            }
        }
        return hasil;
        
    }
    static boolean isInvrs(float[][]M)
    //Apakah Matriks bisa di invers atau tidak
    {
        float sqr = M.length;
        gauss(M);
        float det = 1;
        for(int i=0;i<sqr;i++)
        {
            det = det*M[i][i];
        }
        if(det==0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    

}
