import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MabacSPK {
    private ArrayList<Kriteria> kriteria = new ArrayList<>();
    private ArrayList<Alternatif> alternatif = new ArrayList<>();
    private int[][] matriksKeputusan;
    private Scanner sc = new Scanner(System.in);

    MabacSPK(){
        //kriteria awal yang ada di jobsheet
        kriteria.add(new Kriteria("Pendidikan Masyarakat", 0.25f, true));
        kriteria.add(new Kriteria("Kesehatan Masyarakat", 0.30f, true));
        kriteria.add(new Kriteria("Ekonomi Masyarakat", 0.25f, true));
        kriteria.add(new Kriteria("Pemberdayaan kesejahteraan keluarga", 0.20f, true));
        //alternatif awal yang ada di jobsheet
        alternatif.add(new Alternatif("Nangkaan"));
        alternatif.add(new Alternatif("Sukowiryo"));
        alternatif.add(new Alternatif("Kembang"));
        alternatif.add(new Alternatif("Tamansari"));
        alternatif.add(new Alternatif("Kademangan"));
        alternatif.add(new Alternatif("Pejaten"));
        alternatif.add(new Alternatif("Badean"));
        alternatif.add(new Alternatif("Blindungan"));
        //matriks keputusan yang ada di jobsheet
        matriksKeputusan = new int[][]{
            {90, 81, 89, 77},
            {70, 80, 80, 85},
            {85, 69, 78, 80},
            {95, 80, 83, 80},
            {82, 75, 85, 82},
            {76, 85, 80, 87},
            {72, 80, 75, 78},
            {68, 72, 79, 86}
        };
    }

    public void tampilKriteria() {
        System.out.println("_______________________KRITERIA________________________");
        System.out.printf("  | %-35s  %-5s  %-7s\n", "Nama Kriteria","Bobot", "Jenis");
        System.out.println("--|----------------------------------------------------");
        for (int i = 0; i < kriteria.size(); i++) {
            System.out.printf("%2s| %-35s  %-5s  %-7s\n", "C"+(i+1), kriteria.get(i).getNamaKriteria(), kriteria.get(i).getBobot(), (kriteria.get(i).getJenis() ? "Benefit" : "Cost"));
        }        
        System.out.println("-------------------------------------------------------");
    }

    public void editKriteria(int index, String nama, float bobot, String jenis){
        kriteria.get(index-1).setNamaKriteria(nama);
        kriteria.get(index-1).setBobot(bobot);
        kriteria.get(index-1).setJenis(jenis.equalsIgnoreCase("benefit") ? true : false);
    }

    public void tambahKriteria(String nama, float bobot, String jenis){
        kriteria.add(new Kriteria(nama, bobot, (jenis.equalsIgnoreCase("benefit") ? true : false)));
        System.out.println("Masukkan nilai untuk tiap alternatif");
        int[] baruC= new int[alternatif.size()];
        for (int i = 0; i < alternatif.size(); i++) {
            System.out.print(alternatif.get(i).getNamaAlternatif() + "(1-100) : ");
            baruC[i] = sc.nextInt();
        }

        int[][] mat = new int[alternatif.size()][kriteria.size()];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < matriksKeputusan[0].length; j++) {
                mat[i][j]=matriksKeputusan[i][j];
            }
        }

        for (int i = 0; i < mat.length; i++) {
            mat[i][mat[0].length-1]=baruC[i];
        }
        matriksKeputusan = mat;
    }

    public void tampilAlternatif() {
        System.out.println("______________________ALTERNATIF_______________________");
        System.out.printf("  | %s\n", "Nama Desa");
        System.out.println("--|----------------------------------------------------");
        for (int i = 0; i <alternatif.size(); i++) {
            System.out.printf("%2s| %s\n", "A"+(i+1), alternatif.get(i).getNamaAlternatif());
        }        
        System.out.println("-------------------------------------------------------");
    }

    public void tambahAlternatif(String nama){
        alternatif.add(new Alternatif(nama));
        System.out.println("Masukkan nilai untuk tiap kriteria");
        int[] baruA = new int[kriteria.size()];
        for (int i = 0; i < baruA.length; i++) {
            System.out.print(kriteria.get(i).getNamaKriteria()+" (1-100) : ");
            baruA[i] = sc.nextInt();
        }

        int[][] mat = new int[alternatif.size()][kriteria.size()];
        for (int i = 0; i < matriksKeputusan.length; i++) {
            for (int j = 0; j < matriksKeputusan[0].length; j++) {
                mat[i][j]=matriksKeputusan[i][j];
            }
        }

        for (int j = 0; j < mat[0].length; j++) {
            mat[mat.length-1][j]=baruA[j];
        }
        matriksKeputusan = mat;
    }

    public void hapusAlternatif(int index){
        alternatif.remove(index-1);
        int[][] matBaru = new int[alternatif.size()][kriteria.size()];
        int idx =0;
        for (int i = 0; i < matriksKeputusan.length; i++) {
            if (i != index-1) {
                for (int j = 0; j < matBaru[0].length; j++) {
                    matBaru[idx][j] = matriksKeputusan[i][j];
                }   
                idx++;
            }
        }
        matriksKeputusan = matBaru;
    }

    public void updateMatriks(int baris, int kolom, int nilai){
        matriksKeputusan[baris-1][kolom-1] = nilai;
    }

    public void tampilMatriks() {
        System.out.println("_________________________________________");
        System.out.print("           |");
        for (int i = 0; i < kriteria.size(); i++) {
            System.out.print("  C" + (i+1) + " ");
        }
        System.out.println("\n=========================================");
        for (int i = 0; i < matriksKeputusan.length; i++) {
            System.out.printf("%-10s |",alternatif.get(i).getNamaAlternatif());
            for (int j = 0; j < matriksKeputusan[0].length; j++) {
                System.out.printf("  %2d " ,matriksKeputusan[i][j]);
            }
            System.out.println();
        }
    }

    public void hitung(){
        double[][] hitung = normalisasi();
        hitung = pembobotan(hitung);
        double[] batas = perkiraanBatas(hitung);
        hitung = jarak(hitung, batas);
        Double[] hasil = hasilAkhir(hitung);
        System.out.println("-------------SKOR AKHIR-------------");
        for (int i = 0; i < hasil.length; i++) {
            System.out.printf("%-11s = %s\n", alternatif.get(i).getNamaAlternatif(), hasil[i]);
        }
        System.out.println("------------------------------------");
        Double max = Collections.max(Arrays.asList(hasil));// Mencari alternatif terbaik
        int rank1 = Arrays.asList(hasil).indexOf(max);
        System.out.println("Dapat disimpulkan, Desa " + alternatif.get(rank1).getNamaAlternatif() + " merupakan alternatif terbaik dengan skor = " + max);
    }

    private double[][] normalisasi(){
        double[][] normal = new double[alternatif.size()][kriteria.size()];
        //mencari nilai maks dan min tiap kolom
        int[] max = new int[normal[0].length];
        int[] min = new int[normal[0].length];
        for (int i = 0; i < normal[0].length; i++) {
            int[] column = new int[normal.length];
            for (int j = 0; j < column.length; j++) {
                column[j] = (int) matriksKeputusan[j][i];
            }
            max[i] = Arrays.stream(column).max().getAsInt();
            min[i] = Arrays.stream(column).min().getAsInt();
        }
        //normalisasi
        for (int i = 0; i < normal.length; i++) {
            for (int j = 0; j < normal[0].length; j++) {
                if(kriteria.get(j).getJenis()){
                    normal[i][j] = (matriksKeputusan[i][j] == min[j]) ? 0 : ((double)(matriksKeputusan[i][j] - min[j]) / (max[j]-min[j]));
                }else{
                    normal[i][j] = (matriksKeputusan[i][j] == min[j]) ? 0 : ((double)(matriksKeputusan[i][j] - min[j]) / (min[j] - max[j]));
                }
            }
        }
        return normal;
    }

    private double[][] pembobotan(double[][] arrayHitung){
        for (int i = 0; i < arrayHitung.length; i++) {
            for (int j = 0; j < arrayHitung[0].length; j++) {
                arrayHitung[i][j] = (kriteria.get(j).getBobot() * arrayHitung[i][j]) + kriteria.get(j).getBobot();
            }
        }
        return arrayHitung;
    }

    private double[] perkiraanBatas(double[][] arrayHitung){
        double[] batas = new double[kriteria.size()];
        double pangkat =(double) 1/alternatif.size();
        for (int i = 0; i < arrayHitung[0].length; i++) {
            batas[i] = 1;
            for (int j = 0; j < arrayHitung.length; j++) {
                batas[i] *= arrayHitung[j][i];
            }
            batas[i] = Math.pow(batas[i], pangkat);
        }
        return batas;
    }

    private double[][] jarak(double[][] arrayHitung, double[] batas){
        for (int i = 0; i < arrayHitung.length; i++) {
            for (int j = 0; j < arrayHitung[0].length; j++) {
                arrayHitung[i][j] -= batas[j];
            }
        }
        return arrayHitung;
    }

    private Double[] hasilAkhir(double[][] arrayHitung){
        Double[] hasilAkhir = new Double[alternatif.size()];
        for (int i = 0; i < arrayHitung.length; i++) {
            hasilAkhir[i] = (double) 0;
            for (int j = 0; j < arrayHitung[0].length; j++) {
                hasilAkhir[i] += arrayHitung[i][j];
            }
        }
        return hasilAkhir;
    }
}