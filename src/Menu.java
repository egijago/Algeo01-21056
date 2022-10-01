import java.util.*;
public class Menu {

    public static void printMenu(){
        System.out.println();
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic");
        System.out.println("6. Regresi Linear Berganda");
        System.out.println("7. Keluar");
        System.out.println();
    }

    public static void mainMenu(){
        System.out.println("Selamat datang di Program Matrix Calculator kelompok hoetan.");
        System.out.println("Silahkan pilih aksi pada menu di bawah.");
        printMenu();
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan perintah berdsasarkan nomor: ");
        int action = sc.nextInt();
        while(action != 7){
            if(action == 1){
                System.out.println("Pilihan Metode: ");
                System.out.println("1. Metode Eliminasi Gauss");
                System.out.println("2. Metode Eliminasi Gauss-Jordan");
                System.out.println("3. Metode Matriks Balikan");
                System.out.println("4. Kaidah Cramer");
                System.out.println();

                System.out.print("Pilih Metode yang ingin digunakan: ");
                int actionSPL = sc.nextInt();

                System.out.println("Pilih cara masukkan matrix: ");
                System.out.println("1. Input melalui terminal");
                System.out.println("2. Menggunakan file txt");
                System.out.println();

                System.out.print("Pilih metode input matrix: ");
                int actionInputMatrix = sc.nextInt();

                if(actionInputMatrix == 1){
                    Matrix matrix = IO.inputTerminalToMatrix();
                }else{
                    System.out.print("Masukkan path dari file matrix: ");
                    String path = sc.nextLine();
                    Matrix matrix = IO.FileToMatrix(path);
                }

                System.out.println("Pilih metode output: ");
                System.out.println("1. Output ke terminal");
                System.out.println("2. Output ke file");

                

                


            }
        }
        
    }
    public static void main(String[] args) {
        mainMenu();
    }
}
