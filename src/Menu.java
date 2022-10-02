import java.io.InputStreamReader;

import java.io.*;

public class Menu {

    private static InputStreamReader streamReader = new InputStreamReader(System.in);
    private static BufferedReader bufferedReader = new BufferedReader(Menu.streamReader);
    private static Matrix m = new Matrix();
    private static String pathIn;
    private static String pathOut; 

    public static void printMenu(){
        System.out.println();
        System.out.println("###########MENU##############");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic");
        System.out.println("6. Regresi Linear Berganda");
        System.out.println("7. Keluar");
        System.out.println();
    }

    public static Matrix askInput() throws IOException{
        System.out.println("Pilih cara masukkan matrix: ");
        System.out.println("1. Input melalui terminal");
        System.out.println("2. Menggunakan file txt");
        System.out.println();

        System.out.print("Pilih metode input matrix: ");
        int actionInputMatrixInt = 0;
        try{
            actionInputMatrixInt = Integer.parseInt(bufferedReader.readLine());
        }catch(IOException e){
            e.printStackTrace();
        }

        while(actionInputMatrixInt < 1 || actionInputMatrixInt > 2){
            System.out.println("Tolong masukkan input yang benar!");
            System.out.print("Pilih Metode: ");
            try{
                actionInputMatrixInt = Integer.parseInt(bufferedReader.readLine());
            }catch(IOException e){
                e.printStackTrace();
            }
            System.out.println();
        }

        if(actionInputMatrixInt == 1){
            Menu.m = IO.inputTerminalToMatrix();
        }else{
            System.out.print("Masukkan path dari file yang berisi matrix: ");
            try{
                Menu.pathIn = bufferedReader.readLine();
            }catch (IOException e){
                e.printStackTrace();
            }
            Menu.m = IO.FileToMatrix(Menu.pathIn);
        }
        return Menu.m;
    }

    public static Matrix askInputSqrt() throws IOException{
        System.out.println("Matrix yang dimasukkan harus berbentuk persegi!");
        System.out.println("Pilih cara masukkan matrix persegi: ");
        System.out.println("1. Input melalui terminal");
        System.out.println("2. Menggunakan file txt");
        System.out.println();

        System.out.print("Pilih metode input matrix: ");
        int actionInputMatrixInt = Integer.parseInt(bufferedReader.readLine());

        while(actionInputMatrixInt < 1 || actionInputMatrixInt > 2){
            System.out.println("Tolong masukkan input yang benar!");
            System.out.print("Pilih Metode: ");
            actionInputMatrixInt = Integer.parseInt(bufferedReader.readLine());
            System.out.println();
        }

        if(actionInputMatrixInt == 1){
            Menu.m = IO.inputTerminalToMatrixSqrt();
        }else{
            System.out.print("Masukkan path dari file yang berisi matrix: ");
            try{
                Menu.pathIn = bufferedReader.readLine();
            }catch (IOException e){
                e.printStackTrace();
            }
            Menu.m = IO.FileToMatrix(Menu.pathIn);
        }
        return Menu.m;
    }

    public static Matrix askInputInterpolasi() throws IOException{
        System.out.println("Pilih cara masukkan titik untuk interpolasi :");
        System.out.println("1. Input melalui terminal (keyboard)");
        System.out.println("2. Menggunakan file txt");
        System.out.println();

        System.out.print("Pilih metode input matrix: ");
        int actionInputInterpolasi = Integer.parseInt(bufferedReader.readLine());

        while(actionInputInterpolasi < 1 || actionInputInterpolasi > 2){
            System.out.println("Tolong masukkan input yang benar!");
            System.out.print("Pilih Metode: ");
            actionInputInterpolasi = Integer.parseInt(bufferedReader.readLine());
            System.out.println();
        }

        if(actionInputInterpolasi == 1){
            int n = 0;
            System.out.print("Masukkan jumlah titik: ");
            try{
                n = Integer.parseInt(bufferedReader.readLine());
            }catch (IOException e){
                e.printStackTrace();
            }

            Matrix hasil = new Matrix(n+1,2,0);
            for(int i = 0; i < n; i++){
                for(int j = 0; j < 2; j++){
                    if(j == 0){
                        double elmtx = 0;
                        System.out.printf("Masukkan x%d: ", i+1);
                        try{
                            elmtx = Double.parseDouble(bufferedReader.readLine());
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        hasil.setELMT(i, j, elmtx);
                    }else if(j == 1){
                        double elmty = 0;
                        System.out.printf("Masukkan y%d: ", i+1);
                        try{
                            elmty = Double.parseDouble(bufferedReader.readLine());
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        hasil.setELMT(i, j, elmty);
                    }
                }
            }
            double x = 0;
            System.out.print("Masukkan nilai x yang ingin dicari f(x) nya: ");
            try{
                x = Double.parseDouble(bufferedReader.readLine());
            }catch (IOException e){
                e.printStackTrace();
            }
            hasil.setELMT(n, 0, x);

            Menu.m = Matrix.copyMatrix(hasil);
            
        }else{
            System.out.print("Masukkan path dari file yang berisi matrix: ");
            try{
                Menu.pathIn = bufferedReader.readLine();
            }catch (IOException e){
                e.printStackTrace();
            }
            Matrix hasil  = IO.FileToMatrix(Menu.pathIn);

            Menu.m = Matrix.copyMatrix(hasil);
        }
        return Menu.m;

    }

    public static void mainMenu(){
        System.out.println();
        System.out.println("Selamat datang di Program Matrix Calculator kelompok hoetan.");
        System.out.println("Silahkan pilih aksi pada menu di bawah.");
        printMenu();
        
        System.out.print("Masukkan pilihan: ");

        int action = 0;

        try{
            action = Integer.parseInt(bufferedReader.readLine());
        }catch (IOException e){
            e.printStackTrace();
        }

        while (action < 1 || action > 7){
            System.out.println("Masukkan perintah yang benar!");
            System.out.print("Masukkan perintah berdasarkan nomor: ");
            try{
                action = Integer.parseInt(bufferedReader.readLine());
            }catch (IOException e){
                e.printStackTrace();
            }
            System.out.println();
        }

        while(action != 7){
            if(action == 1){
                System.out.println("Pilihan Metode penyelesaian Sistem Persamaan Linear: ");
                System.out.println("1. Metode Eliminasi Gauss");
                System.out.println("2. Metode Eliminasi Gauss-Jordan");
                System.out.println("3. Metode Matriks Balikan");
                System.out.println("4. Kaidah Cramer");
                System.out.println();

                System.out.print("Pilih Metode yang ingin digunakan: ");
                int actionSPL = 0;
                try{
                    actionSPL = Integer.parseInt(bufferedReader.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }

                while(actionSPL < 1 || actionSPL > 4){
                    System.out.println("Tolong masukkan input yang benar!");
                    System.out.print("Pilih Metode: ");
                    try{
                        actionSPL = Integer.parseInt(bufferedReader.readLine());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    System.out.println();
                }

                try{
                    Menu.m = askInput();
                }catch (IOException e){
                    e.printStackTrace();
                }

                String hasil = "";

                System.out.println();
        
                if(actionSPL == 1){
                    System.out.println("Penyelesaian dengan Metode Eliminasi Gauss.");
                    hasil = SPL.gauss(Menu.m);
                }else if(actionSPL == 2){
                    System.out.println("Penyelesaian dengan Metode Eliminasi Gauss-Jordan");
                    hasil = SPL.gaussJordan(Menu.m);
                }else if(actionSPL == 3){
                    System.out.println("Penyelesaian dengan Metode Matriks Balikan");
                    hasil = SPL.SPLInverseToString(SPL.inversspl(Menu.m));
                }else if(actionSPL == 4){
                    System.out.println("Penyelesaian dengan Kaidah Cramer");
                    hasil = SPL.cramerToString(SPL.cramer(Menu.m, false));
                }

                System.out.println();
                System.out.println(hasil);
                System.out.println();

                System.out.print("Apakah anda ingin menyimpan output ke file? (0 : tidak | 1 : iya) : ");
                int jawab = 0;
                try{
                    jawab = Integer.parseInt(bufferedReader.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }

                if(jawab == 1){
                    System.out.print("Masukkan path dari file tempat anda ingin menyimpan output: ");
                    try{
                        Menu.pathOut = bufferedReader.readLine();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    IO.StringToFile(hasil, Menu.pathOut);
                }

            }else if(action == 2){
                System.out.println();
                System.out.println("Pilih Metode penyelesaian determinan: ");
                System.out.println("1. Metode Ekspansi Kofaktor");
                System.out.println("2. Metode Reduksi Baris");
                System.out.println();

                System.out.print("Pilih Metode: ");
                int actionDet = 0;
                try{
                    actionDet = Integer.parseInt(bufferedReader.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }

                while(actionDet < 1 || actionDet > 2){
                    System.out.println("Tolong masukkan input yang benar!");
                    System.out.print("Pilih Metode: ");
                    try{
                        actionDet = Integer.parseInt(bufferedReader.readLine());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    System.out.println();
                }

                try{
                    Menu.m = askInputSqrt();
                }catch (IOException e){
                    e.printStackTrace();
                }

                System.out.println();
                double hasil = 0;
                if(actionDet == 1){
                    System.out.println("Penyelesaian dengan Metode Ekspansi Kofaktor");
                    hasil = Determinan.detCofactor(Menu.m);
                }else if(actionDet == 2){
                    System.out.println("Penyelesaian dengan Metode Reduksi Baris");
                    hasil = Determinan.detRowRed(Menu.m);
                }

                System.out.printf("Hasil determinan matrix adalah %f", hasil);
                System.out.println();

                System.out.print("Apakah anda ingin menyimpan output ke file? (0 : tidak | 1 : iya) : ");
                int jawab = 0;
                try{
                    jawab = Integer.parseInt(bufferedReader.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }

                if(jawab == 1){
                    System.out.print("Masukkan path dari file tempat anda ingin menyimpan output: ");
                    try{
                        Menu.pathOut = bufferedReader.readLine();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    Determinan.fileDeterminan(hasil, Menu.pathOut);
                }
            }else if(action == 3){
                System.out.println();
                System.out.println("Pilih Metode penyelesaian pencarian Matriks Balikan: ");
                System.out.println("1. Metode Gauss-Jordan");
                System.out.println("2. Metode Kofaktor-Adjoin");
                System.out.println();

                System.out.print("Pilih metode: ");
                int actionInv = 0;
                try{
                    actionInv = Integer.parseInt(bufferedReader.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }
                System.out.println();

                while(actionInv < 1 || actionInv > 2){
                    System.out.println("Tolong masukkan input yang benar!");
                    System.out.print("Pilih Metode: ");
                    try{
                        actionInv = Integer.parseInt(bufferedReader.readLine());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    System.out.println();
                }

                try{
                    Menu.m = askInputSqrt();
                }catch (IOException e){
                    e.printStackTrace();
                }

                Matrix hasil = new Matrix();
                System.out.println();

                if(actionInv == 1){
                    System.out.println("Penyelesaian dengan Metode Gauss-Jordan");
                    hasil = Inverse.inverseGJ(Menu.m);
                    System.out.println("Hasil matriks balikan: ");
                    hasil.displayMatrix();
                    
                }else if(actionInv == 2){
                    System.out.println("Penyelesaian dengan Metode Kofaktor-Adjoin");
                    hasil = Inverse.inverseCofactor(Menu.m);
                    System.out.println("Hasil matriks balikan: ");
                    hasil.displayMatrix();
                }

                System.out.println();

                System.out.print("Apakah anda ingin menyimpan output ke file? (0 : tidak | 1 : iya) : ");
                int jawab = 0;
                try{
                    jawab = Integer.parseInt(bufferedReader.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }

                if(jawab == 1){
                    System.out.print("Masukkan path dari file tempat anda ingin menyimpan output: ");
                    try{
                        Menu.pathOut = bufferedReader.readLine();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    IO.MatrixToFile(hasil, Menu.pathOut);
                }

            }else if(action == 4){
                System.out.println();
                System.out.println("Penyelesaian masalah Interpolasi Polinom");
                System.out.println();
                Matrix hasil = new Matrix();
                try{
                    hasil = askInputInterpolasi();
                }catch(IOException e){
                    e.printStackTrace();
                }

                Matrix titik = IO.TitikInterpolasiToMatrix(hasil);
                double x = IO.TitikInterpolasiToAbsis(hasil);

                double fx = Interpolasi.Interpolation(titik, x);
                System.out.printf("Taksiran untuk f(%f) ialah: %f\n",x, fx);


                System.out.println();

                System.out.print("Apakah anda ingin menyimpan output ke file? (0 : tidak | 1 : iya) : ");
                int jawab = 0;
                try{
                    jawab = Integer.parseInt(bufferedReader.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }

                if(jawab == 1){
                    System.out.print("Masukkan path dari file tempat anda ingin menyimpan output: ");
                    try{
                        Menu.pathOut = bufferedReader.readLine();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    Interpolasi.fileInterpolation(titik, x, Menu.pathOut);
                } 

            }else if(action == 5){
                System.out.println();
                System.out.println("Penyelesaian masalah Interpolasi Bicubic");
                System.out.println("Akan dicari nilai f(a,b)");
                System.out.println();

                System.out.println("Pilih cara masukan matrix 4x4 dan nilai a,b : ");
                System.out.println("1. Input melalui terminal");
                System.out.println("2. Menggunakan file txt");
                System.out.println();

                System.out.print("Pilih metode input matrix: ");
                int actionInputMatrixInt = 0;
                try{
                    actionInputMatrixInt = Integer.parseInt(bufferedReader.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }

                while(actionInputMatrixInt < 1 || actionInputMatrixInt > 2){
                    System.out.println("Tolong masukkan input yang benar!");
                    System.out.print("Pilih Metode: ");
                    try{
                        actionInputMatrixInt = Integer.parseInt(bufferedReader.readLine());
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    System.out.println();
                }
                
                double a = 0, b = 0;
                
                if (actionInputMatrixInt == 1){
                    Matrix bicubic = new Matrix(4,4);
                    for(int i = 0; i < 4; i++){
                        for(int j = 0; j < 4; j++){
                            System.out.printf("Masukkan elemen [%d][%d]: ",i,j);
                            double elmt = 0;
                            try{
                                elmt = Double.parseDouble(bufferedReader.readLine());
                            }catch(IOException e){
                                e.printStackTrace();
                            }
                            bicubic.setELMT(i, j, elmt);
                        }
                    }
                    System.out.print("Masukkan nilai a: ");
                    try{
                        a = Double.parseDouble(bufferedReader.readLine());
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    System.out.print("Masukkan nilai b: ");
                    try{
                        b = Double.parseDouble(bufferedReader.readLine());
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    Menu.m = Matrix.copyMatrix(bicubic);

                }else{
                    System.out.print("Masukkan path dari file yang berisi matrix: ");
                    try{
                        Menu.pathIn = bufferedReader.readLine();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    
                    Matrix temp = IO.FileToMatrix(Menu.pathIn);
                    Matrix bicubic = new Matrix(4,4);

                    for(int i = 0; i < 4; i++){
                        for(int j = 0; j < 4; j++){
                            bicubic.setELMT(i, j, temp.getELMT(i, j));
                        }
                    }
                    
                    Menu.m = Matrix.copyMatrix(bicubic);
                    a = temp.getELMT(temp.getNumRow()-1, 0);
                    b = temp.getELMT(temp.getNumRow()-1,1);
                }

                Menu.m.displayMatrix();
                System.out.println(a);
                System.out.println(b);

                String hasil = Bicubic.bicubicInterpolation(Menu.m, a, b);
                System.out.println(hasil);

                System.out.println();

                System.out.print("Apakah anda ingin menyimpan output ke file? (0 : tidak | 1 : iya) : ");
                int jawab = 0;
                try{
                    jawab = Integer.parseInt(bufferedReader.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }

                if(jawab == 1){
                    System.out.print("Masukkan path dari file tempat anda ingin menyimpan output: ");
                    try{
                        Menu.pathOut = bufferedReader.readLine();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    IO.StringToFile(hasil, Menu.pathOut);
                }
            }else if(action == 6){
                System.out.println();
                System.out.println("Penyelesaian masalah Regresi Linier Berganda");
                System.out.println();

                try{
                    Menu.m = askInput();
                }catch (IOException e){
                    e.printStackTrace();
                }

                String solution = RLB.regression(m);
                System.out.print("Apakah anda ingin menyimpan output ke file? (0 : tidak | 1 : iya) : ");
                int jawab = 0;
                try{
                    jawab = Integer.parseInt(bufferedReader.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }

                if(jawab == 1){
                    System.out.print("Masukkan path dari file tempat anda ingin menyimpan output: ");
                    try{
                        Menu.pathOut = bufferedReader.readLine();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    IO.StringToFile(solution, Menu.pathOut);
                }
            }

            System.out.println();
            System.out.println("Apakah anda ingin melanjutkan menggunakan calculator Matrix?");
            System.out.print("( 0 : tidak || 1 : iya) : ");
            int jawaban = 0;
            try{
                jawaban = Integer.parseInt(bufferedReader.readLine());
            }catch(IOException e){
                e.printStackTrace();
            }

            if(jawaban == 1){
                printMenu();
                System.out.print("Masukkan pilihan: ");

            try{
                action = Integer.parseInt(bufferedReader.readLine());
            }catch (IOException e){
                e.printStackTrace();
            }

            while (action < 1 || action > 7){
                System.out.println("Masukkan perintah yang benar!");
                System.out.print("Masukkan perintah berdasarkan nomor: ");
                try{
                    action = Integer.parseInt(bufferedReader.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }
                System.out.println();
            }

            }else{
                System.out.println();
                System.out.println();
                System.out.println("Terima kasih telah menggunakan program matrix calculator kelompok hoetan :)");
                System.out.println();
                System.out.println("################################## Closing Program ################################");
                action = 7;
            }
        }
        
    }
    public static void main(String[] args) {
        mainMenu();
    }
}
