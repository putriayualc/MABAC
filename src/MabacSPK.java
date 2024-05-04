import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MabacSPK {
    ArrayList<Kriteria> kriteria = new ArrayList<>();
    ArrayList<Alternatif> alternatif = new ArrayList<>();
    int[][] matriksKeputusan;

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

    public void hitung(){
        double[][] hitung = normalisasi();
        hitung = pembobotan(hitung);
        double[] batas = perkiraanBatas(hitung);
        hitung = jarak(hitung, batas);
        Double[] hasil = hasilAkhir(hitung);

        Double max = Collections.max(Arrays.asList(hasil));
        int rank1 = Arrays.asList(hasil).indexOf(max);

        System.out.println("\nDapat disimpulkan, " + alternatif.get(rank1).getNamaAlternatif() + " merupakan alternatif terbaik dengan skor = " + hasil[rank1]);
    }

    private double[][] normalisasi(){
        double[][] normal = new double[kriteria.size()][alternatif.size()];

        //mencari nilai maks dan min tiap kolom
        int[] max = new int[normal[0].length];
        int[] min = new int[normal[0].length];
        for (int i = 0; i < normal[0].length; i++) {
            int[] column = new int[normal.length];
            for (int j = 0; j < column.length; j++) {
                column[j] = (int) normal[j][i];
            }
            max[i] = Arrays.stream(column).max().getAsInt();
            min[i] = Arrays.stream(column).min().getAsInt();
        }

        //normalisasi
        for (int i = 0; i < normal.length; i++) {
            for (int j = 0; j < normal[0].length; j++) {
                if(kriteria.get(i).getJenis()){
                    // normal[i][j] = (matriksKeputusan[i][j] == min[j]) ? 0 : ((matriksKeputusan[i][j] - min[j]) / (max[j]-min[j]));
                    
                }else{
                    // normal[i][j] = (matriksKeputusan[i][j] == min[j]) ? 0 : ((matriksKeputusan[i][j] - min[j]) / (min[j] - max[j]));
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
        for (int i = 0; i < arrayHitung[0].length; i++) {
            batas[i] = 1;
            for (int j = 0; j < arrayHitung.length; j++) {
                batas[i] *= arrayHitung[j][i];
            }
            batas[i] = Math.pow(batas[i], (1/kriteria.size()));
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
            for (int j = 0; j < arrayHitung[0].length; j++) {
                hasilAkhir[i] += arrayHitung[i][j];
            }
        }
        return hasilAkhir;
    }
}
