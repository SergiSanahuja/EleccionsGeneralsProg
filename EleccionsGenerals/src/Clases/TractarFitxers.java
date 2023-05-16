/**
 * tractarFitxers.java
 * @version 1.0
 * author Sergi Sanahuja and Elyas El Jerari
 */
package Clases;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class TractarFitxers {

    public static void descomprimirDATsZIP() {
        String fileName = "./zips/02201606_MESA.zip";

        try (FileInputStream fis = new FileInputStream(fileName);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ZipInputStream zis = new ZipInputStream(bis)) {
             ZipFile zf = new ZipFile(fileName);

            ZipEntry ze;

            while ((ze = zis.getNextEntry()) != null) {

                if (!ze.getName().endsWith(".DAT")) continue;

                ZipEntry e = zf.getEntry(ze.getName());

                InputStream is = zf.getInputStream(e);

                if (!Files.exists(Paths.get("./fitxers/"))) {
                    Files.copy(is, Paths.get("./fitxers/" + ze.getName()));
                    System.out.println("El fitxer: " + ze.getName() + " s'ha afegit a la carpeta \"./fitxers/\"");
                }else{
                    System.out.println("el fitxer:" + ze.getName()+ " en la carpeta \"./fitxers/\" ja existeix.");
                }


            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
