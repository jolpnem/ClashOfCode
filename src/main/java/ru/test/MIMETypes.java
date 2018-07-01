package ru.test;

import java.io.*;
import java.util.*;

class MIMETypes {
    private static final String path = "src/resources/MIMETypes.txt";
    private static Scanner in;

    static {
        try {
            in = new Scanner(new FileInputStream(new File(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        long startTime = System.currentTimeMillis();

        int associationTableSize = in.nextInt();
        int numberOfFileNamesToBeAnalyzed = in.nextInt();

        Map<String, String> associationTable = new HashMap<>();

        for (int i = 0; i < associationTableSize; i++)
            associationTable.put(in.next().toLowerCase(), in.next());

        in.nextLine();

        for (int i = 0; i < numberOfFileNamesToBeAnalyzed; i++) {
            String fileName = in.nextLine().toLowerCase();

            boolean isFound = false;
            for (Map.Entry entry : associationTable.entrySet()) {
                if (fileName.contains("." + entry.getKey())) {
                    System.out.println(entry.getValue());
                    isFound = true;
                    break;
                }
            }
            if (!isFound) System.out.println("UNKNOWN");
        }

        System.out.println("finished in " + (System.currentTimeMillis() - startTime));
    }
}