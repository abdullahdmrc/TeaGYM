package org.example.teagym;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class FileOperationsHandler {
    public static void updateFile(String filePath, String row1, String row2, int n) {
        try {
            File dosya = new File(filePath);
            FileReader fileReader = new FileReader(dosya);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String satir;
            StringBuilder icerik = new StringBuilder();
            while ((satir = bufferedReader.readLine()) != null) {
                String[] parts = satir.split(",");
                if (parts.length > (n +1)  && parts[n].trim().equalsIgnoreCase(row1)) {
                    icerik.append(row2 + "\n");
                } else {
                    icerik.append(satir).append("\n");
                }
            }
            bufferedReader.close();

            FileWriter fileWriter = new FileWriter(dosya);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(icerik.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Dosya işlemleri hatası: " + e.getMessage());
        }
    }
    public static void appendToFile(String filePath, String textToAppend) {
        // FileWriter nesnesini try-with-resources yapısı içinde oluşturuyoruz.
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            // Yazılacak metni dosyaya ekliyoruz.
            fileWriter.write( textToAppend);
            fileWriter.write(System.lineSeparator()); // Yeni satıra geçiş için
        } catch (IOException e) {
            // Hata durumunda hata mesajını yazdırıyoruz.
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
