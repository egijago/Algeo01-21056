public class splinvers {
    //Bukan Augmented
    static float[][] inversspl (float[][]A,float[][]b)
    {
        
        int sqr = A.length;
        float[][] invers = new float[sqr][sqr];
        float[][] hasil= new float[sqr][1];
        invers = inverse.invrs(A);
        for (int k = 0;k<sqr;k++)
        {
            hasil[k][0]=0;
        }
        for (int i=0;i<sqr;i++)
        {
            for(int j=0;j<sqr;j++)
            {
                
                hasil[i][0]=hasil[i][0]+invers[i][j]*b[j][0];
            }
        }
        return hasil;
    }
    //Bentuk Augmented
    static float[][] inverssplaug (float[][]M)
    {
        int sqr = M[0].length-1;
        float[][] A= new float[sqr][sqr];
        float[][] b= new float[sqr][1];
        float[][] hasil= new float[sqr][1];
        for (int i = 0;i<sqr;i++)
        {
            for(int j=0;j<sqr;j++)
            {
                A[i][j] = M[i][j];
            }
        }
        for(int k=0;k<sqr;k++)
        {
            b[k][0]=M[k][sqr];
        }
        hasil = inversspl(A, b);
        return hasil;

    }
//--------------------------------------------------
//------------Buat yang bentuk Augmented------------
//--------------------------------------------------
//tinggal kopas codenya 
    public static void main(String[] args){
        float[][] m = {
            {1,5,3,16},
            {1,-2,9,8},
            {2,1,-1,7}
        };
        
        int col = m[0].length-1;
        int row = m.length;
        float [][] solusi = new float [row][1];
        if(row==col)
        {
            if(inverse.isInvrs(m))
            {
                solusi = inverssplaug(m);
                for (int i=0;i<row;i++)
                {
                    System.out.printf("x%d=%.2f\n",i+1,solusi[i][0]);
                }
            }
            else
            {
                System.out.println("Matriks tidak punya balikan");
                System.out.println("SPL tidak ada solusi");
            }
        }
        else
        {
            System.out.println("Matriks tidak berbentuk square");
            System.out.println("Tidak dapat menyelesaikan SPL dengan Matriks balikan");
        }
        
    
//--------------------------------------------------------
//----Ini kalo Matriks yang udah kepisah jadi A sama b----
//--------------------------------------------------------
   
    
        float[][] A = {
            {1,2,-1},
            {1,2,1},
            {2,1,1}
        };
        float[][] b = {
            {3},
            {7},
            {4}
        };
        row = A.length;
        float [][] solusi1 = new float [row][1];
        if(inverse.isSquare(A))
        {
            if(inverse.isInvrs(A))
            {
                for (int i=0;i<row;i++)
                {
                    solusi1 = inversspl(A, b);
                    System.out.printf("x%d=%.2f\n",i+1,solusi1[i][0]);
                }
            }
            else
            {
                System.out.println("Matriks tidak punya balikan");
                System.out.println("Matriks tidak ada solusi");
            }

        }
        else
        {
            System.out.println("Matriks tidak square");
            System.out.println("Tidak dapat menyelesaikan SPL dengan Matriks balikan");
        }
    }
    
}
