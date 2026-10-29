
package Library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileHandler {
    private static final String FILE_NAME = "library.dat";

    private FileHandler() {
        // Prevent creating FileHandler objects.
    }

    public static void saveLibrary(Library library)
            throws IOException {

        try (ObjectOutputStream output =
                new ObjectOutputStream(
                    new FileOutputStream(FILE_NAME))) {

            output.writeObject(library);
        }
    }

    public static Library loadLibrary() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println(
                "No saved data found. Starting a new library."
            );

            return new Library();
        }

        try (ObjectInputStream input =
                new ObjectInputStream(
                    new FileInputStream(file))) {

            Object data = input.readObject();

            if (data instanceof Library) {
                System.out.println(
                    "Library data loaded successfully."
                );

                return (Library) data;
            }

            System.out.println(
                "Saved file does not contain library data."
            );

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(
                "Could not load saved data: " + e.getMessage()
            );

            System.out.println(
                "Starting with an empty library. "
                + "Keep a backup of the original file."
            );
        }

        return new Library();
    }
}
