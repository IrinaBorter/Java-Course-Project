package client.control;

import common.model.CompletedTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;

public class CompletedTaskWriter {
    public static void writeStatistic(ArrayList<CompletedTask> completedTasks) {
        Iterator it = completedTasks.iterator();
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("statistics.txt", "rw");
            while (it.hasNext()) {
                randomAccessFile.writeUTF(it.next().toString() + "\r\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
