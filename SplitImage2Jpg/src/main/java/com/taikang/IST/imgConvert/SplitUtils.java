package com.taikang.IST.imgConvert;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.StreamSupport;

public class SplitUtils {
    static public String getBaseName(String filepath) {
        String[] subDirs = SplitUtils.splitPath(filepath);
        String[] subFileNames = subDirs[subDirs.length - 1].split("\\.");
        return subFileNames[0];
    }

    static public String[] splitPath(String pathString) {
        Path path = Paths.get(pathString);
        return StreamSupport.stream(path.spliterator(), false).map(Path::toString).toArray(String[]::new);
    }

    static public String stringRightJust(String s, int width, char fill_char) {
        if (s.length() >= width)
            return s;

        String ret = new String(new char[width - s.length()]).replace("\0", String.valueOf(fill_char));
        return ret + s;
    }

    static public String getSplittedName(String filepath, int i, int width) {
        return String.format("%s-%s.jpg", getBaseName(filepath), stringRightJust(String.valueOf(i + 1), width, '0'));
    }

    static public String getFullSplittedName(String outDir, String filepath, int i, int width) {
        return Paths.get(outDir, getSplittedName(filepath, i, width)).toString();
    }
}
