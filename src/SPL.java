import java.text.Format;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
public class SPL {

    public static Matrix sortZero(Matrix matrix, int col,int r1){
        for (int i=r1;i<matrix.getNumRow()-1;i++){
            if (matrix.getELMT(i, col)==0 && matrix.getELMT(i+1, col)!=0){
                matrix = Matrix.swapRow(matrix,i,i+1);
            }
        }
        return matrix;
    } 

    public static String gauss(Matrix matrix){
        //Mengembalikan solusi spl dari matrix augmented mat
    
        //Melakukan OBE hingga terbentuk eselon reduksi
        matrix = ref(matrix);

        // back-elimination (or substitution)
        // back-elimination akan dilakukan dalam matrix augmented, dimulai dari baris paling bawah, lalu mengeliminasi x lain diatasnya
        for (int i=n-1;i>0;i--){
            // mencari kolom dari kolom tempat X leading one
            int col_lead = 0;
            while (matrix.getELMT(i, col_lead)==0 && col_lead<(matrix.getNumCol() - 2)){
                col_lead ++;
            }

            // koefisien dari X leading one nol
            if (matrix.getELMT(i, col_lead) == 0){
                continue;
            }

            // Mengeliminasi baris atas yang juga mempunya Xi, Xi adalah X yang menjadi leading one
            for (int row =0;row<i;row++){
                // rasio eliminasi
                double ratio = matrix.getELMT(row, col_lead)/matrix.getELMT(i, col_lead);

                // melakukan eliminasi
                for (int col = 0;col<matrix.getNumCol();col++){
                    matrix.setELMT(row, col, matrix.getELMT(row, col)-(ratio*matrix.getELMT(i, col)));
                }
            }
        }
        
        return (solve(matrix));
    }

    public static String gaussJordan(Matrix matrix){
        // Mengembalikan solusi SPL dengan metode gauss jordan

        // Melakukan OBE hingga terbentuk eselon baris
        matrix = rref(matrix);

        return (solve(matrix));
    }
    public static Matrix ref(Matrix matrix){
        // Melakukan OBE pada matriks hingga terbentuk formasi eseleon baris dan mengembalikannya. (untuk metode Gauss)

        int n = matrix.getNumRow();

        // OBE hingga terbentuk eselon baris
        int col_lead = 0;
        for (int i=0;i<n;i++){
            // mengurutkan agar yg berelemen 0 ada di paling bawah
            matrix = sortZero(matrix,col_lead,i);

            // mengidentifikasi kolom yang menjadi leading one
            while (matrix.getELMT(i, col_lead) == 0.0 && col_lead < matrix.getNumCol()-2){
                col_lead+=1;
                matrix = sortZero(matrix,col_lead,i);
            }
            // tidak ada leading one (koef x nol semua)
            if(matrix.getELMT(i, col_lead)==0.0){
                break;
            } else {
                // menjadikan elemen pada col_lead sebagai leading one
                double factor = matrix.getELMT(i, col_lead);
                for (int j=0;j<matrix.getNumCol();j++){
                    matrix.setELMT(i, j, matrix.getELMT(i, j)*1/factor);;
                }
            }

            // mengeliminasi baris-baris dibawahnya sehingga pada elemen di baris lain pada col_lead 0
            for (int row=i+1;row<n;row++){
                double ratio = matrix.getELMT(row, col_lead)/matrix.getELMT(i, col_lead);
                for (int col=0;col<matrix.getNumCol();col++){
                    matrix.setELMT(row, col, matrix.getELMT(row, col)-ratio*matrix.getELMT(i, col));
                }
            }
            col_lead +=1;

        }
        return matrix;
    }

    public static Matrix rref(Matrix matrix){
        // Melakukan OBE pada Matrix hingga terbentuk formasi eselon terekduksi dan mengembalikannya (untuk metode Gauss Jordan)
        int n = matrix.getNumRow();

        // Diawali dengan melakukan OBE hingga terbentuk formasi eselon
        matrix = ref(matrix);

        // Melakukan OBE hingga terbentuk eselon tereduksi (dari belakang)
        for (int i=n-1;i>0;i--){
            // identifikasi leading one
            int col_lead = 0;
            while (matrix.getELMT(i, col_lead)==0 && col_lead<(matrix.getNumCol() - 2)){
                col_lead ++;
            }
            // tidak ada leading one
            if (matrix.getELMT(i, col_lead) == 0){
                continue;
            }
            // melakuan OBE agar kolom diatas leading one nol
            for (int row =0;row<i;row++){
                double ratio = matrix.getELMT(row, col_lead)/matrix.getELMT(i, col_lead);
                for (int col = 0;col<matrix.getNumCol();col++){
                    matrix.setELMT(row, col, matrix.getELMT(row, col)-(ratio*matrix.getELMT(i, col)));
                }
            }
        }
        return matrix;
    }

    public static int searchInCol(String[][] matrix, int col, int val) {
        // Mengembalikan index pertama ditemukannya val pada matrix, mengembalikan -1 jika nihil
        for (int i = 0;i<matrix.length;i++){
            if (Integer.valueOf(matrix[i][col])== val){
                return i;
                }
        }
        return -1;
    }

    static Matrix append(Matrix list1, Matrix list2){
        // Mengembalikkan gabungan  list2 dibelakang list1
        Matrix temp = new Matrix(1, list1.getNumCol()+list2.getNumCol());
        for (int i = 0;i<list1.getNumCol();i++){
            temp.setELMT(0, i, list1.getELMT(0, i));;
        }
        for (int i = list1.getNumCol(); i<list1.getNumCol()+list2.getNumCol();i++){
            temp.setELMT(0, i, list2.getELMT(0, i-list1.getNumCol()));;
        }
        return temp;
    }

    static String[][] append(String[][] list1,String [][]list2){
         // Mengembalikkan gabungan  list2 dibelakang list1
        String[][] temp = new String[list1.length+list2.length][];
        for (int i = 0;i<list1.length;i++){
            temp[i] = list1[i];
        }
        for (int i = list1.length; i<list1.length+list2.length;i++){
            temp[i] = list2[i-list1.length];
        }
        return temp;
    }


    static String solve(Matrix matrix){
        //  Mengembalikan solusi SPL dari matrix yang telah dalam formasi eselon tereduksi
        // Mengembalikan "SPL tidak memiliki solusi." jika SPL tidak ada solusi.
        for (int row = 0 ;row<matrix.getNumRow();row++){
            int col_lead = 0;
            while (matrix.getELMT(row, col_lead)==0){
                if (col_lead >= matrix.getNumCol()-2){
                    break;
                }
                col_lead +=1;
            }
            // Ditemukan eq : 0 = c , c =/= 0
            if (matrix.getELMT(row, col_lead) == 0 && matrix.getELMT(row, col_lead+1) != 0){
                return ("SPL tidak memiliki solusi.");
            }
        
        String spl ="";

        // List of parameter
        String var[] ={"s","t","u","v","w","x","y","z","a","b","c","d","e","e","f","g","h","i","j","k","l","m","n","o","p","q","r"};

        // Assigning x yang bebas sebagai parameter untuk x leading one.
        int idx = 0;
        String parameter[][] = new String[0][];
        for (int row = 0 ;row<matrix.getNumRow();row++){
            int col_lead = 0;
            while (matrix.getELMT(row, col_lead)==0){
                if (col_lead >= matrix.getNumCol()-2){
                    break;
                }
                col_lead +=1;
            }

            // eq : 0 = 0 (trivial)
            if (matrix.getELMT(row, col_lead)==0 && matrix.getELMT(row, col_lead+1) == 0){
                // eq 0 = 0
                continue;
            }

            // Building string eq
            String eq = "";
            // Assign parameter ke variable selain leading one
            for (int col = col_lead+1;col<matrix.getNumCol()-1;col++){
                if (matrix.getELMT(row, col)==0){
                    continue;
                }
                else if (searchInCol(parameter,0,col+1) == -1){
                    // Variable belum di assign ke parameter
                    spl += String.format("X%d = %s\n",col+1,var[idx]) +'\n';
                    String[][] lst = {{String.format("%d",col+1),var[idx]}};
                    parameter = append(parameter,lst);
                    idx +=1;
                }
                // Variable sudah di assing ke parameter
                if (eq==""){
                    double coef = -1 * matrix.getELMT(row, col);
                    eq += String.format("(%.2f)%s",coef,parameter[searchInCol(parameter,0,col+1)][1]);
                }
                else {
                    double coef = -1 * matrix.getELMT(row, col);
                    eq += String.format("+ (%.2f)%s",coef,parameter[searchInCol(parameter,0,col+1)][1]);
                }
            }
            if (eq==""){
                if (matrix.getELMT(row, col_lead)==0){
                    eq = String.format("0 = ",col_lead+1) + eq+ String.format("(%.2f)",matrix.getELMT(row, matrix.getNumCol()-1));
                }
                else{
                    eq = String.format("X%d = ",col_lead+1) + eq + String.format("(%.2f)",matrix.getELMT(row, matrix.getNumCol()-1));
                }
                }
            else {
                if (matrix.getELMT(row, matrix.getNumCol()-1) == 0){
                    eq = String.format("X%d = ",col_lead+1) + eq;
                }
                else {
                    eq = String.format("X%d = ",col_lead+1) + eq + String.format("+ (%.2f)",matrix.getELMT(row, matrix.getNumCol()-1));
                }
            }
            spl += (eq) + '\n';   
            }
        }
        return spl;
    }
//------------------------------------------------------
//--------------------Cramer----------------------------
//------------------------------------------------------
    public static Matrix replaceCol(Matrix matrix, Matrix rep, int col){
        int kolom = matrix.getNumRow();
        Matrix copy = Matrix.copyMatrix(matrix);
        for(int i=0; i<kolom; i++){
            copy.setELMT(i, col, rep.getELMT(0, i));
        }
        return copy;
    }
    public static Matrix cramer(Matrix matrix, boolean print){
        int row = matrix.getNumRow();
        int col = matrix.getNumCol();

        Matrix square = new Matrix(row,col-1);
        Matrix hasil = new Matrix(1,row);
        
        Matrix.splitAugmentedMatrix(matrix, square, hasil);
        int n = square.getNumRow();
        Matrix result = new Matrix(1,n);
        Matrix copy = Matrix.copyMatrix(square);

        if(square.isSquare()){
            double det = Determinan.detRowRed(square);

            if(det != 0){
                for(int i=0; i<n; i++){
                    Matrix mat = replaceCol(copy,hasil,i);
                    result.setELMT(0, i, Determinan.detRowRed(mat)/det); 
                }

                if(print){
                    System.out.println("Hasil SPL:");
                    for(int i=0; i<n; i++){
                        System.out.printf("x%d = %.3f\n",i+1,result.getELMT(0, i));
                }
                }
            }else{
                if(print){
                    System.out.println("Matrix tidak memiliki solusi tunggal, silakan coba metode lain untuk penyelesaian.");
                }
            }
        }else{
            if(print){
                System.out.println("Matrix tidak berbentuk persegi, persoalan tidak dapat diselesaikan dengan cramer.");
            }
        }
        return result;
    }
    
    static Matrix inversspl (Matrix augmented) 
    {   
        int row = augmented.getNumRow();
        int col = augmented.getNumCol();
        Matrix A = new Matrix(row,col-1);
        Matrix b = new Matrix(1,row);

        Matrix.splitAugmentedMatrix(augmented, A, b);
        Matrix invers = new Matrix(row, row);
        Matrix hasil= new Matrix(1, col-1);
        if(A.isSquare())
        {
            if(Determinan.detRowRed(A)!=0)
            {
                
                invers = Inverse.inverseGJ(A);
                for (int k = 0;k<row;k++)
                {
                    hasil.setELMT(0, k, 0);
                }
                for (int i=0;i<row;i++)
                {
                    for(int j=0;j<col-1;j++)
                    {
                        hasil.setELMT(0, i, hasil.getELMT(0, i)+invers.getELMT(i, j)*b.getELMT(0,j));
                    }
                }
                for (int i =0;i<row;i++)
                {
                    System.out.printf("x%d= %f\n",i+1,hasil.getELMT(0, i));
                }
            }
            else
            {
                System.out.println("Matriks tidak mempunyai balikan.");
                System.out.println("SPL tidak dapat dicari dengan metode balikan. silakan coba metode lain.");
            }
        }
        else
        {
            System.out.println("Matriks tidak berbentuk square.");
            System.out.println("Tidak dapat menyelesaikan SPL dengan matriks balikan.");
        }
        
        return hasil;
    }


//-----------------------------------------------------------------------
//---------------------Output File---------------------------------------
//-----------------------------------------------------------------------
    public static void fileInversspl(Matrix matrix, String path){
        String str = "Hasil SPL:\n";
        for(int col = 0; col < matrix.getNumCol(); col++){
            str += String.format("x%d= %f\n", col+1, matrix.getELMT(0, col));
        }
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(str);
            writer.close();
            System.out.println("Berhasil menulis matrix ke dalam file.");
        } catch (IOException e){
            System.out.println("Gagal menulis matrix ke file.");
            e.printStackTrace();
        }
    }

    public static void fileCramer(Matrix matrix, String path){
        String str = "Hasil SPL:\n";
        for(int col = 0; col < matrix.getNumCol(); col++){
            str += String.format("x%d= %f\n", col+1, matrix.getELMT(0, col));
        }
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(str);
            writer.close();
            System.out.println("Berhasil menulis matrix ke dalam file.");
        } catch (IOException e){
            System.out.println("Gagal menulis matrix ke file.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        Matrix testing = new Matrix();
        testing = IO.FileToMatrix("../test/test4.txt");
        Matrix hasil = new Matrix();
        hasil = inversspl(testing);
        fileInversspl(hasil,"../test/cramsku.txt");
        // solve(hasil);
    }
}