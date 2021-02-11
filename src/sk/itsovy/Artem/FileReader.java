package sk.itsovy.Artem;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader extends Component {
    private String[] readFromFile() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("."));
        int returnVal = jFileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                return makeReadFromFile(jFileChooser);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new String[0];
    }

    private String[] makeReadFromFile(JFileChooser jFileChooser) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(jFileChooser.getSelectedFile());
        byte[] content = new byte[fileInputStream.available()];
        fileInputStream.read(content);
        return new String(content, "UTF-8").split("\n");
    }

    public String[] getInfoFromFile() {
        return readFromFile();
    }
}

class WordFinder {
    private FileReader fileReader = new FileReader();

    public void findNextWord(String word) {
        boolean flag = false;
        int i = 1;
        for (String lines : fileReader.getInfoFromFile()) {
            int j = 1;
            String[] line = lines.split(" ");
            for (String words : line) {
                if (words.equalsIgnoreCase(word)) {
                    System.out.println("'" + words + "'" + " - the word you're looking for is placed at line " + i + " on position " + j);
                    i++;
                    flag = true;
                }
                j++;
            }
        }
        if (!flag) {
            System.out.println("There's no such word in selected file!");
        }
    }
}
