/*
В папке Games создайте несколько директорий: src, res, savegames, temp.
В каталоге src создайте две директории: main, test.
В подкаталоге main создайте два файла: Main.java, Utils.java.
В каталог res создайте три директории: drawables, vectors, icons.
В директории temp создайте файл temp.txt.
*/
import java.io.File;
import java.util.Set;

public class Main {

    static Set<File>files;

    public static void main(String[] args) {
        files = Instal.instal("C:/java/Games");

    }



}