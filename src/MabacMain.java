import java.util.Scanner;

public class MabacMain {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        MabacSPK spk = new MabacSPK();
        int pilihan = 0;
        do {
            System.out.println("\n=========================================");
            System.out.println("      MABAC: PEMILIHAN DESA TERBAIK");
            System.out.println("=========================================");
            System.out.println(" Matriks keputusan :");
            spk.tampilMatriks();
            System.out.println("=========================================");
            pilihan = menu();
            switch (pilihan) {
                case 1:
                spk.hitung();
                break;

                case 2:
                System.out.print("\nBaris ke- : ");
                int bar = sc.nextInt();
                System.out.print("Kolom ke- : ");
                int kol = sc.nextInt();
                System.out.print("Nilai baru : ");
                int nil = sc.nextInt();
                spk.updateMatriks(bar, kol, nil);
                System.out.println("==PERUBAHAN TERSIMPAN==\n");
                break;

                case 3:
                spk.tampilKriteria();
                System.out.println("=============");
                System.out.println("| 1. Edit   |");
                System.out.println("| 2. Tambah |");
                System.out.println("=============");
                System.out.print("Pilih : ");
                int pil = sc.nextInt();
                if (pil == 1) {
                    System.out.println("----------EDIT KRITERIA----------");
                    System.out.print("Pilih kriteria yg akan diedit (masukan angka) : ");
                    int krit = sc.nextInt();
                    System.out.print("Nama Kriteria : ");
                    String nama = sc.next();
                    System.out.print("Bobot Kriteria : ");
                    float bobot = sc.nextFloat();
                    System.out.print("Jenis Kriteria (Benefit/Cost) : ");
                    String jen = sc.next();
                    spk.editKriteria(krit, nama, bobot, jen);
                    System.out.println("==PERUBAHAN TERSIMPAN==\n");
                }else if (pil == 2) {
                    System.out.print("Nama Kriteria : ");
                    String addC = sc.next();
                    System.out.print("Bobot Kriteria : ");
                    float addW = sc.nextFloat();
                    System.out.print("Jenis Kriteria (benefit/cost) : ");
                    String addCj = sc.next();
                    spk.tambahKriteria(addC, addW, addCj);
                }
                break;
            
                case 4:
                spk.tampilAlternatif();
                System.out.println("=============");
                System.out.println("| 1. Tambah |");
                System.out.println("| 2. Hapus  |");
                System.out.println("=============");
                System.out.print("Pilih : ");
                int pilih = sc.nextInt();
                if(pilih == 1){
                    System.out.print("Nama Alternatif : ");
                    String addA = sc.next();
                    spk.tambahAlternatif(addA);
                }else if(pilih == 2){
                    System.out.print("Pilih Alternatif (masukan angka) : ");
                    int alt = sc.nextInt();
                    spk.hapusAlternatif(alt);
                    System.out.println("==PERUBAHAN TERSIMPAN==\n");
                }
                break;

                case 0:
                System.out.println("==TERIMAKASIH==");
                break;

                default:
                    System.out.println("MASUKAN SALAH!");
                    break;
            }
        } while (pilihan !=0);
    }

    public static int menu() {
        System.out.println("=========================================");
        System.out.println(" 1. Hitung");
        System.out.println(" 2. Edit Matriks Keputusan");
        System.out.println(" 3. Kriteria");
        System.out.println(" 4. Alternatif");
        System.out.println(" 0. Keluar");
        System.out.println("=========================================");
        System.out.print("Pilih (0-4) = ");
        int pilih = sc.nextInt();

        return pilih;
    }
}
