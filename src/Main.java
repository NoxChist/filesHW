/*
В папке Games создайте несколько директорий: src, res, savegames, temp.
В каталоге src создайте две директории: main, test.
В подкаталоге main создайте два файла: Main.java, Utils.java.
В каталог res создайте три директории: drawables, vectors, icons.
В директории temp создайте файл temp.txt.
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    static Map<String, File> files;

    public static void main(String[] args) {
        files = Instal.instal("C:/java/Games");

        GameProgress game1 = new GameProgress(100, 2, 1, 0.0),
                game2 = new GameProgress(30, 10, 8, 50.89),
                game3 = new GameProgress(80, 6, 15, 1037.56);

        if (files.containsKey("savegames")) {
            File savegemes = files.get("savegames");
            saveGame(savegemes.getPath() + "/game1.dat", game1);
            saveGame(savegemes.getPath() + "/game2.dat", game2);
            saveGame(savegemes.getPath() + "/game3.dat", game3);

            List<File> listFiles = List.of(savegemes.listFiles());
            List<String> filesPath = new ArrayList<>();
            for (File f : listFiles) {
                if (f.isFile()) {
                    filesPath.add(f.getPath());
                }
            }
            zipFiles(savegemes.getPath() + "/test.zip", filesPath);

        }
    }

    public static void saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception e) {
            System.out.println("Something goes wrong");
        }
    }

    public static void zipFiles(String pathToZip, List<String> filesPath) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathToZip))) {
            for (String p : filesPath) {
                File f = new File(p);
                if (f.exists() && f.isFile()) {
                    try (FileInputStream fis = new FileInputStream(p)) {
                        ZipEntry entry = new ZipEntry(f.getName());
                        zout.putNextEntry(entry);
                        byte buffer[] = new byte[(fis.available())];
                        fis.read(buffer);
                        zout.write(buffer);
                        zout.closeEntry();
                    }
                    f.delete();
                }
            }
        } catch (Exception e) {
        }
    }
}