import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class updater {
	public static void main(String args[]) {

		File tmpDir = new File("temp");
		boolean exists = tmpDir.exists();

		if (exists) {
			// delete current things
			try {
				delete(new File("icon"));
				delete(new File("news"));
				delete(new File("picture"));
				delete(new File("sound"));
				delete(new File("game.exe"));
				delete(new File("README.txt"));
				delete(new File("error.txt"));
				delete(new File("version.txt"));
				delete(new File("guide.html"));
				delete(new File("update logs.txt"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// move files from temp folder to current directory
			try {
				Files.move(Paths.get("temp\\game\\icon"), Paths.get("icon"));
				Files.move(Paths.get("temp\\game\\news"), Paths.get("news"));
				Files.move(Paths.get("temp\\game\\picture"), Paths.get("picture"));
				Files.move(Paths.get("temp\\game\\sound"), Paths.get("sound"));
				Files.move(Paths.get("temp\\game\\README.txt"), Paths.get("README.txt"));
				Files.move(Paths.get("temp\\game\\version.txt"), Paths.get("version.txt"));
				Files.move(Paths.get("temp\\game\\game.exe"), Paths.get("game.exe"));
				Files.move(Paths.get("temp\\game\\guide.html"), Paths.get("guide.html"));
				Files.move(Paths.get("temp\\game\\update logs.txt"), Paths.get("update logs.txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 2nd delete "file" and "temp" folder
			String SRC_FOLDER1 = "file";
			String SRC_FOLDER2 = "temp";
			File directory1 = new File(SRC_FOLDER1);
			File directory2 = new File(SRC_FOLDER2);
			try {
				delete(directory1);
				delete(directory2);
			} catch (IOException e1) {
			}

			// mention user update finished
			JFrame jf = new JFrame();
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "Update finished!");

			// open new exe
			File game = new File("game.exe");
			try {
				Desktop.getDesktop().open(game);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		else
			JOptionPane.showMessageDialog(new JFrame(), "You must update the game via 'game.exe'");

		System.exit(0);
	}

	public static void unzip(File source, String out) throws IOException {
		try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source))) {
			ZipEntry entry = zis.getNextEntry();
			while (entry != null) {
				File file = new File(out, entry.getName());
				if (entry.isDirectory())
					file.mkdirs();

				else {
					File parent = file.getParentFile();
					if (!parent.exists())
						parent.mkdirs();
					try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
						byte[] buffer = new byte[Math.toIntExact(entry.getSize())];
						int location;
						while ((location = zis.read(buffer)) != -1) {
							bos.write(buffer, 0, location);
						}
					}
				}
				entry = zis.getNextEntry();
			}
		}
	}

	private static void delete(File file) throws IOException {

		// if it is a folder
		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {
				file.delete();
				System.out.println("Directory is deleted : " + file.getAbsolutePath());
			}

			else {
				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					delete(fileDelete);
				}
				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					System.out.println("Directory is deleted : " + file.getAbsolutePath());
				}
			}
		}

		else {
			// if file, delete it
			file.delete();
			System.out.println("File is deleted : " + file.getAbsolutePath());
		}
	}
}