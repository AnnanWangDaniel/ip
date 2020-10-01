import tasks.*;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ImportExportFileHandler {
	FileWriter fileWriter;
	boolean isExistBefore;
	File file;

	public ImportExportFileHandler(ArrayList<Task> tasks) {
		try {
			this.file = new File("files/duke.txt");

			if (this.file.exists()) {
				this.isExistBefore = true;
				this.ImportFile(tasks);
			} else {
				this.isExistBefore = false;
				this.file.getParentFile().mkdirs();
				this.file.createNewFile();
			}
		} catch (IOException e) {
			System.out.println("Failure: the file cannot be initialized.");
		}

	}

	public void ImportFile(ArrayList<Task> tasks) {
		try {
			Scanner scanner = new Scanner(this.file);
			String buffer;
			while (scanner.hasNext()) {
				buffer = scanner.nextLine();
				Task.parseTask(buffer, tasks);
			}
		} catch (IOException e) {
			System.out.println("Error: file reading failure.");
		}

	}

	public void UpdateFile(ArrayList<Task> tasks) {
		try {
			this.fileWriter = new FileWriter(this.file, false);
		} catch (IOException e) {
			System.out.println("Error: file writing failure.");
		}
		for (Task task: tasks) {
			try {
				this.fileWriter.write(task.toString() + '\n');

			} catch (IOException e) {
				System.out.println("Error: file writing failure.");
			}
		}
		try {
			this.fileWriter.close();
		} catch (IOException e) {
			System.out.println("Error: file updating failure.");
		}

	}
}
