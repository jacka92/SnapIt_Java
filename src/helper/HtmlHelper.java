/*
 * 
 */
package helper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.apache.commons.io.FileUtils;

import com.googlecode.jatl.Html;

// TODO: Auto-generated Javadoc
/**
 * The Class HtmlHelper.
 */
public class HtmlHelper {

	/** The h. */
	private static HtmlHelper h = new HtmlHelper();

	/**
	 * Creates the and write html.
	 *
	 * @param title the title
	 * @param h1 the h1
	 * @param h2 the h2
	 * @param writeFileName the write file name
	 */
	public void createAndWriteHtml(String title, String h1, String h2,
			String writeFileName) {
		StringWriter sw = new StringWriter();
		new Html(sw) {
			{
				html().head();
				title().text(title).end();
				h1().text(h1).end();
				h2().text(h2).end();
				done();
				String result = sw.getBuffer().toString();
				// For viewing the result string in the console:
				// System.out.println(result);
				try {
					// Use FileUtils from Apache Commons io to write
					// string to a file
					FileUtils
							.writeStringToFile(new File(writeFileName), result);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
	}

	/**
	 * Write html.
	 *
	 * @param title the title
	 * @param h1 the h1
	 * @param h2 the h2
	 * @param writeFileName the write file name
	 */
	public static void writeHTML(String title, String h1, String h2,
			String writeFileName) {
		h.createAndWriteHtml(title, h1, h2, writeFileName);
	}

	/**
	 * Read and display html.
	 *
	 * @param readFileName the read file name
	 * @param windowTitle the window title
	 * @param noFileMessageDialoge the no file message dialoge
	 */
	public void ReadAndDisplayHtml(String readFileName, String windowTitle,
			String noFileMessageDialoge) {
		if (new File(readFileName).isFile() == true) {
			try {
				// Use FileUtils from Apache Commons io to read file to
				// a string
				String htmlFile = FileUtils.readFileToString(new File(
						readFileName));
				// For viewing the read string in the console:
				// System.out.println(htmlFile);

				// JEditorPane & JScrollPane are used to display the
				// saved file in a new panel.
				JEditorPane disp = new JEditorPane("text/html", htmlFile);
				disp.setEditable(false);
				JScrollPane scrollPane = new JScrollPane(disp);
				JFrame jf = new JFrame(windowTitle);
				jf.getContentPane().add(scrollPane, BorderLayout.CENTER);
				jf.setSize(new Dimension(600, 300));
				jf.setLocationRelativeTo(null);
				jf.setVisible(true);

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, noFileMessageDialoge);
		}
	}

	/**
	 * Read html.
	 *
	 * @param readFileName the read file name
	 * @param windowTitle the window title
	 * @param noFileMessageDialoge the no file message dialoge
	 */
	public static void readHtml(String readFileName, String windowTitle,
			String noFileMessageDialoge) {
		h.ReadAndDisplayHtml(readFileName, windowTitle, noFileMessageDialoge);
	}

}
