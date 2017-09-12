import org.w3c.dom.*;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.*;
import java.io.*;

public class Main {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}

		int fl, ifl;
		String filebody = "";
		String all = "Words:\t";
		String name;

		fl = 0;
		ifl = 0;

		filebody += "\t\tSegments:\tWords:\tCharacters:\tPercent (words):\tTags:\n";

		int condition = 0;

		while (condition != 1) {
			name = (String) JOptionPane.showInputDialog(null, "WprowadŸ nazwê pliku XML z analiz¹:",
					"Sopoltrad Analyse Converter", JOptionPane.QUESTION_MESSAGE, null, null, "analyse.xml");

			if (name == null)
				System.exit(1);

			try {
				File inputFile = new File(name);

				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(inputFile);

				doc.getDocumentElement().normalize();

				NodeList batchTotal = doc.getElementsByTagName("batchTotal");

				for (int i = 0; i < batchTotal.getLength(); i++) {
					Node node = batchTotal.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						NodeList analyse = element.getElementsByTagName("analyse");

						for (int j = 0; j < analyse.getLength(); j++) {
							Node node2 = analyse.item(j);

							filebody += "Analyse total:\n";

							if (node2.getNodeType() == node2.ELEMENT_NODE) {
								Element element2 = (Element) node2;
								NodeList locked = element2.getElementsByTagName("locked");

								for (int k = 0; k < locked.getLength(); k++) {
									Node node3 = locked.item(k);

									if (node3.getNodeType() == node3.ELEMENT_NODE) {
										Element element3 = (Element) node3;

										filebody += "\tLocked:\t" + element3.getAttribute("segments") + "\t"
												+ element3.getAttribute("words") + "\t"
												+ element3.getAttribute("characters") + "\t" + "="
												+ element3.getAttribute("words") + "/$B$1\t"
												+ element3.getAttribute("tags") + "\n";

									}
								}
							}

							if (node2.getNodeType() == node2.ELEMENT_NODE) {
								Element element2 = (Element) node2;
								NodeList contextMatch = element2.getElementsByTagName("inContextExact");

								for (int k = 0; k < contextMatch.getLength(); k++) {
									Node node3 = contextMatch.item(k);

									if (node3.getNodeType() == node3.ELEMENT_NODE) {
										Element element3 = (Element) node3;

										filebody += "\tContext Match:\t" + element3.getAttribute("segments") + "\t"
												+ element3.getAttribute("words") + "\t"
												+ element3.getAttribute("characters") + "\t" + "="
												+ element3.getAttribute("words") + "/$B$1\t"
												+ element3.getAttribute("tags") + "\n";
									}
								}
							}

							if (node2.getNodeType() == node2.ELEMENT_NODE) {
								Element element2 = (Element) node2;
								NodeList perfectMatch = element2.getElementsByTagName("perfect");

								for (int k = 0; k < perfectMatch.getLength(); k++) {
									Node node3 = perfectMatch.item(k);

									if (node3.getNodeType() == node3.ELEMENT_NODE) {
										Element element3 = (Element) node3;

										filebody += "\tPerfect Match:\t" + element3.getAttribute("segments") + "\t"
												+ element3.getAttribute("words") + "\t"
												+ element3.getAttribute("characters") + "\t" + "="
												+ element3.getAttribute("words") + "/$B$1\t"
												+ element3.getAttribute("tags") + "\n";

									}
								}
							}

							if (node2.getNodeType() == node2.ELEMENT_NODE) {
								Element element2 = (Element) node2;
								NodeList repetitions = element2.getElementsByTagName("repeated");

								for (int k = 0; k < repetitions.getLength(); k++) {
									Node node3 = repetitions.item(k);

									if (node3.getNodeType() == node3.ELEMENT_NODE) {
										Element element3 = (Element) node3;

										filebody += "\tRepetitions:\t" + element3.getAttribute("segments") + "\t"
												+ element3.getAttribute("words") + "\t"
												+ element3.getAttribute("characters") + "\t" + "="
												+ element3.getAttribute("words") + "/$B$1\t"
												+ element3.getAttribute("tags") + "\n";

									}
								}
							}

							if (node2.getNodeType() == node2.ELEMENT_NODE) {
								Element element2 = (Element) node2;
								NodeList crossRepetitions = element2.getElementsByTagName("crossFileRepeated");

								for (int k = 0; k < crossRepetitions.getLength(); k++) {
									Node node3 = crossRepetitions.item(k);

									if (node3.getNodeType() == node3.ELEMENT_NODE) {
										Element element3 = (Element) node3;

										filebody += "\tCross File Repetitions:\t" + element3.getAttribute("segments")
												+ "\t" + element3.getAttribute("words") + "\t"
												+ element3.getAttribute("characters") + "\t" + "="
												+ element3.getAttribute("words") + "/$B$1\t"
												+ element3.getAttribute("tags") + "\n";

									}
								}
							}

							if (node2.getNodeType() == node2.ELEMENT_NODE) {
								Element element2 = (Element) node2;
								NodeList exact = element2.getElementsByTagName("exact");

								for (int k = 0; k < exact.getLength(); k++) {
									Node node3 = exact.item(k);

									if (node3.getNodeType() == node3.ELEMENT_NODE) {
										Element element3 = (Element) node3;

										filebody += "\t100%:\t" + element3.getAttribute("segments") + "\t"
												+ element3.getAttribute("words") + "\t"
												+ element3.getAttribute("characters") + "\t" + "="
												+ element3.getAttribute("words") + "/$B$1\t"
												+ element3.getAttribute("tags") + "\n";

									}
								}
							}

							if (node2.getNodeType() == node2.ELEMENT_NODE) {
								Element element2 = (Element) node2;
								NodeList fuzzy = element2.getElementsByTagName("fuzzy");
								fl = fuzzy.getLength();

								for (int k = fl - 1; k >= 0; k--) {
									Node node3 = fuzzy.item(k);

									if (node3.getNodeType() == node3.ELEMENT_NODE) {
										Element element3 = (Element) node3;

										filebody += "\t" + element3.getAttribute("min") + "% - "
												+ element3.getAttribute("max") + "%:\t"
												+ element3.getAttribute("segments") + "\t"
												+ element3.getAttribute("words") + "\t"
												+ element3.getAttribute("characters") + "\t" + "="
												+ element3.getAttribute("words") + "/$B$1\t"
												+ element3.getAttribute("tags") + "\n";

									}
								}
							}

							if (node2.getNodeType() == node2.ELEMENT_NODE) {
								Element element2 = (Element) node2;
								NodeList internalFuzzy = element2.getElementsByTagName("internalFuzzy");
								ifl = internalFuzzy.getLength();

								if (ifl != 0) {
									filebody += "\tInternal:\n";
								}

								for (int k = ifl - 1; k >= 0; k--) {
									Node node3 = internalFuzzy.item(k);

									if (node3.getNodeType() == node3.ELEMENT_NODE) {
										Element element3 = (Element) node3;

										filebody += "\t" + element3.getAttribute("min") + "% - "
												+ element3.getAttribute("max") + "%:\t"
												+ element3.getAttribute("segments") + "\t"
												+ element3.getAttribute("words") + "\t"
												+ element3.getAttribute("characters") + "\t" + "="
												+ element3.getAttribute("words") + "/$B$1\t"
												+ element3.getAttribute("tags") + "\n";

									}
								}
							}

							if (node2.getNodeType() == node2.ELEMENT_NODE) {
								Element element2 = (Element) node2;
								NodeList newItems = element2.getElementsByTagName("new");

								for (int k = 0; k < newItems.getLength(); k++) {
									Node node3 = newItems.item(k);

									if (node3.getNodeType() == node3.ELEMENT_NODE) {
										Element element3 = (Element) node3;

										filebody += "\tNew:\t" + element3.getAttribute("segments") + "\t"
												+ element3.getAttribute("words") + "\t"
												+ element3.getAttribute("characters") + "\t" + "="
												+ element3.getAttribute("words") + "/$B$1\t"
												+ element3.getAttribute("tags") + "\n";

									}
								}
							}

							if (node2.getNodeType() == node2.ELEMENT_NODE) {
								Element element2 = (Element) node2;
								NodeList total = element2.getElementsByTagName("total");

								for (int k = 0; k < total.getLength(); k++) {
									Node node3 = total.item(k);

									if (node3.getNodeType() == node3.ELEMENT_NODE) {
										Element element3 = (Element) node3;

										filebody += "\tTotal:\t" + element3.getAttribute("segments") + "\t"
												+ element3.getAttribute("words") + "\t"
												+ element3.getAttribute("characters") + "\t100%\t"
												+ element3.getAttribute("tags") + "\n";
										all += element3.getAttribute("words");

									}
								}
							}
						}
					}
				}

				NodeList allFiles = doc.getElementsByTagName("task");

				for (int i = 0; i < allFiles.getLength(); i++) {
					Node nodeRoot = allFiles.item(i);

					if (nodeRoot.getNodeType() == Node.ELEMENT_NODE) {
						Element elementRoot = (Element) nodeRoot;
						NodeList file = elementRoot.getElementsByTagName("file");

						for (int j = 0; j < file.getLength(); j++) {
							Node nodeRoot2 = file.item(j);

							if (nodeRoot2.getNodeType() == Node.ELEMENT_NODE) {
								Element element = (Element) nodeRoot2;

								filebody += "\nFile(" + (j + 1) + ") - " + element.getAttribute("name") + ":" + "\n";
								NodeList analyse = element.getElementsByTagName("analyse");

								for (int k = 0; k < analyse.getLength(); k++) {
									Node node2 = analyse.item(k);

									if (node2.getNodeType() == node2.ELEMENT_NODE) {
										Element element2 = (Element) node2;
										NodeList locked = element2.getElementsByTagName("locked");

										for (int l = 0; l < locked.getLength(); l++) {
											Node node3 = locked.item(l);

											if (node3.getNodeType() == node3.ELEMENT_NODE) {
												Element element3 = (Element) node3;

												filebody += "\tLocked:\t" + element3.getAttribute("segments") + "\t"
														+ element3.getAttribute("words") + "\t"
														+ element3.getAttribute("characters") + "\t\t"
														+ element3.getAttribute("tags") + "\n";
											}
										}
									}

									if (node2.getNodeType() == node2.ELEMENT_NODE) {
										Element element2 = (Element) node2;
										NodeList contextMatch = element2.getElementsByTagName("inContextExact");

										for (int l = 0; l < contextMatch.getLength(); l++) {
											Node node3 = contextMatch.item(l);

											if (node3.getNodeType() == node3.ELEMENT_NODE) {
												Element element3 = (Element) node3;

												filebody += "\tContext Match:\t" + element3.getAttribute("segments")
														+ "\t" + element3.getAttribute("words") + "\t"
														+ element3.getAttribute("characters") + "\t\t"
														+ element3.getAttribute("tags") + "\n";
											}
										}
									}

									if (node2.getNodeType() == node2.ELEMENT_NODE) {
										Element element2 = (Element) node2;
										NodeList perfectMatch = element2.getElementsByTagName("perfect");

										for (int l = 0; l < perfectMatch.getLength(); l++) {
											Node node3 = perfectMatch.item(l);

											if (node3.getNodeType() == node3.ELEMENT_NODE) {
												Element element3 = (Element) node3;

												filebody += "\tPerfect Match:\t" + element3.getAttribute("segments")
														+ "\t" + element3.getAttribute("words") + "\t"
														+ element3.getAttribute("characters") + "\t\t"
														+ element3.getAttribute("tags") + "\n";
											}
										}
									}

									if (node2.getNodeType() == node2.ELEMENT_NODE) {
										Element element2 = (Element) node2;
										NodeList repetitions = element2.getElementsByTagName("repeated");

										for (int l = 0; l < repetitions.getLength(); l++) {
											Node node3 = repetitions.item(l);

											if (node3.getNodeType() == node3.ELEMENT_NODE) {
												Element element3 = (Element) node3;

												filebody += "\tRepetitions:\t" + element3.getAttribute("segments")
														+ "\t" + element3.getAttribute("words") + "\t"
														+ element3.getAttribute("characters") + "\t\t"
														+ element3.getAttribute("tags") + "\n";
											}
										}
									}

									if (node2.getNodeType() == node2.ELEMENT_NODE) {
										Element element2 = (Element) node2;
										NodeList crossRepetitions = element2.getElementsByTagName("crossFileRepeated");

										for (int l = 0; l < crossRepetitions.getLength(); l++) {
											Node node3 = crossRepetitions.item(l);

											if (node3.getNodeType() == node3.ELEMENT_NODE) {
												Element element3 = (Element) node3;

												filebody += "\tCross File Repetitions:\t"
														+ element3.getAttribute("segments") + "\t"
														+ element3.getAttribute("words") + "\t"
														+ element3.getAttribute("characters") + "\t\t"
														+ element3.getAttribute("tags") + "\n";
											}
										}
									}

									if (node2.getNodeType() == node2.ELEMENT_NODE) {
										Element element2 = (Element) node2;
										NodeList exact = element2.getElementsByTagName("exact");

										for (int l = 0; l < exact.getLength(); l++) {
											Node node3 = exact.item(l);

											if (node3.getNodeType() == node3.ELEMENT_NODE) {
												Element element3 = (Element) node3;

												filebody += "\t100%:\t" + element3.getAttribute("segments") + "\t"
														+ element3.getAttribute("words") + "\t"
														+ element3.getAttribute("characters") + "\t\t"
														+ element3.getAttribute("tags") + "\n";
											}
										}
									}

									if (node2.getNodeType() == node2.ELEMENT_NODE) {
										Element element2 = (Element) node2;
										NodeList fuzzy = element2.getElementsByTagName("fuzzy");

										for (int l = fl - 1; l >= 0; l--) {
											Node node3 = fuzzy.item(l);

											if (node3.getNodeType() == node3.ELEMENT_NODE) {
												Element element3 = (Element) node3;

												filebody += "\t" + element3.getAttribute("min") + "% - "
														+ element3.getAttribute("max") + "%:\t"
														+ element3.getAttribute("segments") + "\t"
														+ element3.getAttribute("words") + "\t"
														+ element3.getAttribute("characters") + "\t\t"
														+ element3.getAttribute("tags") + "\n";
											}
										}
									}

									if (node2.getNodeType() == node2.ELEMENT_NODE) {
										Element element2 = (Element) node2;
										NodeList internalFuzzy = element2.getElementsByTagName("internalFuzzy");

										if (ifl != 0) {
											filebody += "\tInternal:\n";
										}

										for (int l = ifl - 1; l >= 0; l--) {
											Node node3 = internalFuzzy.item(l);

											if (node3.getNodeType() == node3.ELEMENT_NODE) {
												Element element3 = (Element) node3;

												filebody += "\t" + element3.getAttribute("min") + "% - "
														+ element3.getAttribute("max") + "%:\t"
														+ element3.getAttribute("segments") + "\t"
														+ element3.getAttribute("words") + "\t"
														+ element3.getAttribute("characters") + "\t\t"
														+ element3.getAttribute("tags") + "\n";
											}
										}
									}

									if (node2.getNodeType() == node2.ELEMENT_NODE) {
										Element element2 = (Element) node2;
										NodeList newItems = element2.getElementsByTagName("new");

										for (int l = 0; l < newItems.getLength(); l++) {
											Node node3 = newItems.item(l);

											if (node3.getNodeType() == node3.ELEMENT_NODE) {
												Element element3 = (Element) node3;

												filebody += "\tNew:\t" + element3.getAttribute("segments") + "\t"
														+ element3.getAttribute("words") + "\t"
														+ element3.getAttribute("characters") + "\t\t"
														+ element3.getAttribute("tags") + "\n";
											}
										}
									}

									if (node2.getNodeType() == node2.ELEMENT_NODE) {
										Element element2 = (Element) node2;
										NodeList total = element2.getElementsByTagName("total");

										for (int l = 0; l < total.getLength(); l++) {
											Node node3 = total.item(l);

											if (node3.getNodeType() == node3.ELEMENT_NODE) {
												Element element3 = (Element) node3;

												filebody += "\tTotal:\t" + element3.getAttribute("segments") + "\t"
														+ element3.getAttribute("words") + "\t"
														+ element3.getAttribute("characters") + "\t100%\t"
														+ element3.getAttribute("tags") + "\n";
											}
										}
									}
								}
							}
						}
					}
				}
				condition = 1;

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Nieprawid³owa nazwa pliku lub brak pliku w folderze. Spróbuj wpisaæ inn¹ nazwê.", "B³¹d odczytu pliku",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		StringBuilder _filebody = new StringBuilder(filebody);
		_filebody.insert(0, all + "\n\n");

		try {
			PrintWriter save = new PrintWriter("analyse.xl");
			save.print(_filebody);
			save.close();
			JOptionPane.showMessageDialog(null, "Zapisano plik analyse.xl, który mo¿na otowrzyæ w programie MS Excell.",
					"Zakoñczono pomyœlnie", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			// brak dostêpu do pliku - plik otwarty
			e.printStackTrace();
		}
	}
}
