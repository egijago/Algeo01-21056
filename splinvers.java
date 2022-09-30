public class splinvers {
    //Bukan Augmented
    static float[][] inversspl (float[][]A,float[][]b)
    {
        inverse.invrs(A);
        int sqr = A.length;
        float[][] hasil= new float[sqr][1];
        for (int k = 0;k<sqr;k++)
        {
            hasil[k][0]=0;
        }
        for (int i=0;i<sqr;i++)
        {
            for(int j=0;j<sqr;j++)
            {
                
                hasil[i][0]=hasil[i][0]+A[i][j]*b[j][0];
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
            {1,-1,2,-1},
            {2,1,-2,-2},
            {-1,2,-4,1},
            {3,0,0,-3},
        };
        int col = m[0].length-1;
        int row = m.length;
        if(row==col)
        {
            if(inverse.isInvrs(m))
            {
                reff.displayMatrix(inverssplaug(m));
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
            {1,-1,2,-1,4},
            {2,1,-2,-2,3},
            {-1,2,-4,1,2},
            {3,0,0,-3,1},
        };
        float[][] b = {
            {2},
            {4},
            {6},
            {8}
        };
        if(inverse.isSquare(A))
        {
            if(inverse.isInvrs(A))
            {
                inversspl(A, b);
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
