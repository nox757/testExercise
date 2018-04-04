package fs;

import java.io.File;
import java.util.*;

public class ListFileImpl implements ListFile {

    private List<String> listFile = new ArrayList<String>();
    private String rootPath;

    public ListFileImpl(String rootPath) {
        this.rootPath = rootPath;

    }

    public void buildListFile() {
        Queue<File> queueFile = new PriorityQueue<File>();
        Collections.addAll(queueFile, new File(rootPath).listFiles());
        while(!queueFile.isEmpty()){
            File currentFile = queueFile.remove();
            if(currentFile.isDirectory()) {
                Collections.addAll(queueFile, currentFile.listFiles());
            } else {
                listFile.add(currentFile.getAbsolutePath());
            }
        }
    }

    public List<String> getListFile() {
        return listFile;
    }

}
