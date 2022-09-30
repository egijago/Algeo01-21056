public class invrs12{
    
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
        rref.gaussJordan(invers);
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
        rref.gauss(M);
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
    static boolean isSquare(float[][]M)
    //Apakah matriks square atau tidak
    {
        int col = M[0].length;
        int row = M.length;

        if(row==col)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static void main(String[]args)
    {
        float[][] m = {
            {1,1,-1,-3},
            {1,2,1,7},
            {2,1,1,4}
        };
        rref.displayMatrix(splinvers.inverssplaug(m));
        float[][] A = {
            {1,5,3},
            {1,-2,9},
            {2,1,-1}
        };
        float[][] b = {
            {16},
            {8},
            {7}
        };
        rref.displayMatrix(splinvers.inversspl(A, b));
    }
}
