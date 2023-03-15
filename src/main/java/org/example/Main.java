package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<File> dir1 = new ArrayList<>();
    static List<File> dir2 = new ArrayList<>();
    public static void main(String[] args) {
        init();
        boolean areContinuing = true;
        Scanner sc = new Scanner(System.in);
        help();
        while (areContinuing){
            String s = sc.nextLine();
            if(s.startsWith("/createFile")){
                System.out.println("Введите директорию, в которую хотите создать файл");
                String dir = sc.nextLine();
                System.out.println("\nВведите название файла и размер через консоль");
                String fileInfo = sc.nextLine();
                String[] fileNameAndSize = fileInfo.split(" ");
                addNewFile(dir, fileNameAndSize[0], Integer.parseInt(fileNameAndSize[1]));
            } else if (s.startsWith("/move")) {
                String[]parts = s.split(" ");
                String oldDirName = parts[1];
                String newDirName = parts[2];
                String fileName = parts[3];
                if(oldDirName.equals("dir1")){
                    move(dir1, newDirName, fileName);
                 }else move(dir2, newDirName, fileName);
            } else if (s.equals("/showFiles")) {
                showFiles("dir1", dir1);
                showFiles("dir2", dir2);
            } else if (s.startsWith("/rename")) {
                String[] s0 = s.split(" ");
                String dirName = s0[1];
                String fileName = s0[2];
                String newName = s0[3];
                if (dirName.equals("dir1")) {
                    rename(dir1, fileName, newName);
                }else if (dirName.equals("dir2")) {
                    rename(dir2, fileName,newName);
                }else System.out.println("Неверная директория. Выберите dir1 или dir2");
            } else if (s.startsWith("/delete")) {
                String[] s1 = s.split(" ");
                String dirName = s1[1];
                String fileName = s1[2];
                if(dirName.equals("dir1")){
                    delete(dir1, fileName);
                } else if (dirName.equals("dir2")) {
                    delete(dir2, fileName);
                } else System.out.println("Неверная папка");
            } else if (s.startsWith("/end")) {
                System.out.println("Завершение работы");
                areContinuing = false;
            } else if (s.startsWith("/help")) {
                help();
            } else System.out.println("Неверная программа");
        }
    }
    public static void init(){
        File file1 = new File("text1.txt", 20); //CTR+D
        File file2 = new File("text2.txt", 20); //alt+Enter
        File file3 = new File("text3.txt", 20);
        File file4 = new File("text4.txt", 20);
        File file5 = new File("text5.txt", 20);
        File file6 = new File("text6.txt", 20);
        File file7 = new File("text7.txt", 20);
        File file8 = new File("text8.txt", 20);
        dir1.add(file1);
        dir1.add(file2);
        dir1.add(file3);
        dir1.add(file4);
        dir2.add(file5);
        dir2.add(file6);
        dir2.add(file7);
        dir2.add(file8);
    }
    public static void help(){
        System.out.println("Добро пожаловать в программу EXPLORER");
        System.out.println("""
                Доступные команды:
                /createFile - Создать файл
                /move [откуда берем][куда кладем][имя файла]- Переместить файл
                /showFiles - Показать файлы
                /delete [откуда удаляем][имя файла] - Удалить файл
                /help - Вернуться к этому месту
                /end - Выход из программы""");
    }
    public static void delete(List<File> dir, String fileName){
        for (File file : dir){
            if(file.getName().equals(fileName)){
                dir.remove(file);
                System.out.println("Файл удален!");
                return;
            }
        }
        System.out.println("Файл не найден!");
    }

    //rename [директория][имя файла][новое имя] - переименовать файл
    public static void rename(List<File> directory, String fileName, String newName){
        for(File file: directory){
            if (file.getName().equals(fileName)){
                //здесь должен быть код переименования файла
                System.out.println("Файл успешно переименован");
                return;
            }
            else System.out.println("Файл не найден!");
        }
    }
    public static void move (List<File> dirFrom, String newDirName, String fileName){
        for (File file: dirFrom){
            if (file.getName().equals(fileName)){
                addNewFile(newDirName, fileName, file.getSize());
                dirFrom.remove(file);
                System.out.println("Файл успешно перемещен");
                return;
            }
        }
        System.out.println("Файл не найден!");
    }

    public static void addNewFile(String directory, String fileName, int size){
        if(directory.equals("dir1")) {
            String fileNameUnique = isFileUnique(dir1, fileName);
            File newFile = new File(fileNameUnique, size);
            dir1.add(newFile);
            System.out.println("Файл успешно создан");
        } else if (directory.equals("dir2")) {
            String fileNameUnique = isFileUnique(dir2, fileName);
            File newFile = new File(fileNameUnique, size);
            dir2.add(newFile);
            System.out.println("Файл успешно создан");
        } else{
            System.out.println("Неверное имя директории. Введите dir1 или dir2");
        }
    }

    public static String isFileUnique(List<File> directory, String fileName){
        int i = 1;
            for(File file: directory){      //проверка всей директории
                if (file.getName().equals(fileName)){ //если имя неуникально
                    if(i == 1){     //если нашли с первого раза
                        fileName = fileName + i++;      //приклеиваем ему цифру
                    }
                    isFileUnique(directory, fileName); //если не с первого, повторяем цикл
                }
                //fileName = fileName.substring(0, fileName.length()-2); //если имя уникально
                //fileName = fileName + i++;
                //isFileUnique(directory, fileName);
            }
            return fileName;
    }

    public static void showFiles(String dirName, List<File> dir){
        System.out.println(dirName + ":/");
        for (File file: dir){
            System.out.println("__" + file);
        }
        System.out.println("/end");
    }

}