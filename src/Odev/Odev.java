package Odev;

import java.io.*;
import java.util.Scanner;

public class Odev {

    public static void main(String[] args) {
        String inputFilePath = "/Users/emreonel/desktop/src/Odev/input.txt";
        File inputFile = new File(inputFilePath);
        String outputFilePath = inputFile.getParent() + "/output.txt";

        try {
            Scanner dosyaOkuyucu = new Scanner(inputFile);
            FileWriter yazici = new FileWriter(outputFilePath);
            BufferedWriter bufferedWriter = new BufferedWriter(yazici);

            while (dosyaOkuyucu.hasNextLine()) {
                String islem = dosyaOkuyucu.nextLine().trim(); 
                if (islem.equalsIgnoreCase("Exit")) {
                    break; 
                }

                if (dosyaOkuyucu.hasNextInt()) {
                    int ustSinir = dosyaOkuyucu.nextInt(); 
                    dosyaOkuyucu.nextLine(); 
                    
                    switch (islem.toLowerCase()) {
                        case "armstrong":
                            bufferedWriter.write("Armstrong " + ustSinir + ": ");
                            for (int sayi = 0; sayi <= ustSinir; sayi++) {
                                if (armstrongSayisiMi(sayi)) {
                                    bufferedWriter.write(sayi + " ");
                                }
                            }
                            bufferedWriter.newLine();
                            break;
                        case "emirp":
                            bufferedWriter.write("Emirp " + ustSinir + ": ");
                            for (int sayi = 2; sayi <= ustSinir; sayi++) {
                                if (emirpSayisiMi(sayi)) {
                                    bufferedWriter.write(sayi + " ");
                                }
                            }
                            bufferedWriter.newLine();
                            break;
                        default:
                            bufferedWriter.write("Geçersiz işlem: " + islem);
                            bufferedWriter.newLine();
                    }
                } else {
                    bufferedWriter.write("Hatalı giriş: İşlemden sonra bir sayı bekleniyor.");
                    bufferedWriter.newLine();
                }
            }

            dosyaOkuyucu.close();
            bufferedWriter.close();
            System.out.println("Çıktılar " + outputFilePath + " dosyasına yazıldı.");
        } catch (IOException e) {
            System.out.println("Dosya okuma/yazma hatası: " + e.getMessage());
        }
    }

    public static boolean armstrongSayisiMi(int sayi) {
        int orijinalSayi = sayi;
        int basamakSayisi = String.valueOf(sayi).length();
        int toplam = 0;

        while (sayi != 0) {
            int basamak = sayi % 10;
            toplam += Math.pow(basamak, basamakSayisi);
            sayi /= 10;
        }

        return toplam == orijinalSayi;
    }

    public static boolean emirpSayisiMi(int sayi) {
        if (!asalMi(sayi)) {
            return false;
        }
        int tersSayi = sayininTersiniAl(sayi);
        if (tersSayi == sayi) {
            return false;
        }
        return asalMi(tersSayi);
    }

    public static boolean asalMi(int sayi) {
        if (sayi < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(sayi); i++) {
            if (sayi % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int sayininTersiniAl(int sayi) {
        int tersSayi = 0;
        while (sayi != 0) {
            int basamak = sayi % 10;
            tersSayi = tersSayi * 10 + basamak;
            sayi /= 10;
        }
        return tersSayi;
    }
}
