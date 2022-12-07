package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day7 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day7.class);
        List<String> l2 = FileUtil.readfileExempel(Day7.class);
        TimeUtil.startTime();
        oneStar(l2);
        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l2);
        twoStar(l);
        TimeUtil.endTime();
    }

    private static void oneStar(List<String> l) {
        Folder f = parseCommands(l);

        long tot = findTotalSize(f, 100000);
        System.out.println("star one: " + tot);
    }

    private static long findTotalSize(Folder f, int i) {
        long sum = 0;
        if (f.totalSize() < i) {
            sum += f.totalSize();
        }
        for (Folder folders : f.children.values()) {
            sum += findTotalSize(folders, i);
        }
        return sum;
    }

    private static Folder parseCommands(List<String> l) {
        Folder f = new Folder();
        Folder currentFolder = f;
        f.name = "/";
        for (String s : l) {
            String[] strings = s.split(" ");
            if (strings[0].charAt(0) == '$') {
                currentFolder = changeDirectory(currentFolder, strings);
            } else if ("dir".equals(strings[0])) {
                Folder value = new Folder();
                value.name = strings[1];
                value.parent = currentFolder;
                currentFolder.children.put(strings[1], value);
            } else {
                File file = new File();
                String string = strings[1];
                file.name = string;
                file.size = Long.parseLong(strings[0]);
                String[] split = string.split("\\.");
                if (split.length > 1) {
                    file.type = split[1];
                } else {
                    file.type = "";
                }
                file.parent = currentFolder;
                currentFolder.files.add(file);
            }
        }
        return f;
    }

    private static Folder changeDirectory(Folder currentFolder, String[] strings) {
        if ("cd".equals(strings[1])) {
            if ("..".equals(strings[2])) {
                currentFolder = currentFolder.parent;
            } else {
                if ("/".equals(strings[2])) {
                    return currentFolder;
                }
                currentFolder = currentFolder.children.get(strings[2]);
            }
        }
        return currentFolder;
    }


    private static void twoStar(List<String> l) {
        Folder f = parseCommands(l);
        long sizeNeed = 30000000 - Math.abs(f.totalSize() - 70000000);
        ArrayList<Long> arr = new ArrayList<>();
        findFolderSize(f, sizeNeed, arr);
        long min = Long.MAX_VALUE;
        for (Long aLong : arr) {
            min = Math.min(aLong, min);
        }
        System.out.println("star two: " + min);
    }

    private static long findFolderSize(Folder f, long sizeNeed, ArrayList<Long> arr) {
        long sum = 0;
        if (f.totalSize() > sizeNeed) {
            sum = f.totalSize();
            arr.add(sum);
        }
        for (Folder folders : f.children.values()) {
            long folderSize = findFolderSize(folders, sizeNeed, arr);
            if (folderSize != 0) {

                arr.add(folderSize);
            }
        }
        return sum;
    }

    static class Folder {
        Folder parent;
        String name;
        ArrayList<File> files;
        HashMap<String, Folder> children;

        public Folder() {
            files = new ArrayList<>();
            children = new HashMap<>();
        }

        public long totalSize() {
            long size = 0;
            for (File file : files) {
                size += file.size;
            }
            for (Folder folder : children.values()) {
                size += folder.totalSize();
            }
            return size;
        }

        @Override
        public String toString() {
            return "Folder{" +
                    ", name='" + name + '\'' +
                    ", files=" + files +
                    ", children=" + children.values() +
                    '}';
        }
    }

    static class File {
        Folder parent;
        String name;
        String type;
        long size;

        @Override
        public String toString() {
            return "File{" +
                    "parent=" + parent.name +
                    ", name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", size=" + size +
                    '}';
        }
    }
}
