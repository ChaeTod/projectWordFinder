package sk.itsovy.Artem;

@FunctionalInterface
interface Prototype {
    WordFinder display(String word);
}

@FunctionalInterface
interface FileBrowser {
    String[] display();
}

public class Main {
    public String[] showFileComponent() {
        FileReader fileReader = new FileReader();
        return fileReader.getInfoFromFile();
    }

    public static void main(String[] args) {
        Main main = new Main();
        FileBrowser fileBrowser = main::showFileComponent;

        System.out.println("Selected   file:");
        for (String line : fileBrowser.display()) {
            System.out.println(line);

            System.out.println("Searching for inputted word");
            Prototype prototype = WordFinder::new;
            prototype.display("wasxx");

        }
    }
}
