import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//

public class Instal {
    static StringBuilder logs = new StringBuilder();
    static Set<File>files = new HashSet<>();

    public static Set<File> instal(String path){

        File src, res, savegames,temp,main,test,drawbles,vectors, icons, Main,Utils,tempF,
                gameDir = new File(path);

        if(gameDir.exists() && gameDir.isDirectory()){
            src = dirFactory("src",gameDir);
            res = dirFactory("res",gameDir);
            savegames = dirFactory("savegames",gameDir);
            temp = dirFactory("temp",gameDir);
            if(src.exists()){
                main = dirFactory("main",src);
                test = dirFactory("test",src);
                if(main.exists()){
                    Main = fileFactory("Main.java", main);
                    Utils = fileFactory("Utils.java", main);
                }
            }
            if(res.exists()){
                drawbles= dirFactory("drawbles",res);
                vectors = dirFactory("vectors",res);
                icons= dirFactory("icons",res);
            }
            if(temp.exists()){
                tempF = fileFactory("temp.txt", temp);
                if(tempF!=null){
                    try(FileWriter out = new FileWriter(tempF)){
                        out.write(logs.toString());
                    }catch (IOException e){};
                }
            }
        }
        else {
            System.out.println(LocalDateTime.now() + " Каталог " + gameDir.getName() +" ("+gameDir.getPath()+ ") не обнаружен.\n");
        }
        return files;
    }

    public static File fileFactory (String name, File dirPath)
    {
        if(dirPath.exists() && dirPath.isDirectory()){
            File file = new File(dirPath, name);
            if(!file.exists()){
                try {
                    if (file.createNewFile()){
                        logs.append(LocalDateTime.now() + " Файл " + name + " был успешно создан.\n");
                        files.add(file);
                        return file;
                    }
                } catch (IOException e){
                    logs.append(LocalDateTime.now() + e.getMessage()+"\n");
                }
            }else {
                logs.append(LocalDateTime.now() +" Ошибка при создании " + name+ " уже существует.\n");
                files.add(file);
                return file;
            }
        } else {
            logs.append(LocalDateTime.now() +"Ошибка при создании " + name+ " указан неверный путь.\n");
        }
        return null;
    }
    public static File dirFactory (String name, File dirPath){

        if(dirPath.exists() && dirPath.isDirectory()){
            File dir = new File(dirPath, name);
            if (!dir.exists()){
                try {
                    if (dir.mkdir()){
                        logs.append(LocalDateTime.now() + " Каталог " + name + " был успешно создан.\n");
                        files.add(dir);
                        return dir;
                    }
                } catch (Exception e){
                    logs.append(LocalDateTime.now() + e.getMessage()+"\n");
                    System.out.println(LocalDateTime.now() + e.getMessage()+"\n");
                }
            } else {
                logs.append(LocalDateTime.now() +" Ошибка при создании " + name+ " уже существует.\n");
                files.add(dir);
                return dir;
            }
        }else{
            logs.append(LocalDateTime.now() +" Ошибка при создании " + name+ " указан неверный путь.\n");
        }
        return null;
    }
}
