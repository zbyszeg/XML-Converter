package com.zbyszeg.sopoltrad.analyseconverter;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parser {
	private String fileName, fileBody, all;
	private int condition, fl, ifl;

	public Parser() {
		fileBody = "\t\tSegments:\tWords:\tCharacters:\tPercent (words):\tTags:\n";
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@SuppressWarnings("static-access")
	public void parse() {
		try {
			File inputFile = new File(fileName);

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

						fileBody += "Analyse total:\n";

						if (node2.getNodeType() == node2.ELEMENT_NODE) {
							Element element2 = (Element) node2;
							NodeList locked = element2.getElementsByTagName("locked");

							for (int k = 0; k < locked.getLength(); k++) {
								Node node3 = locked.item(k);

								if (node3.getNodeType() == node3.ELEMENT_NODE) {
									Element element3 = (Element) node3;

									fileBody += "\tLocked:\t" + element3.getAttribute("segments") + "\t"
											+ element3.getAttribute("words") + "\t"
											+ element3.getAttribute("characters") + "\t" + "="
											+ element3.getAttribute("words") + "/$A$2\t" + element3.getAttribute("tags")
											+ "\n";
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

									fileBody += "\tContext Match:\t" + element3.getAttribute("segments") + "\t"
											+ element3.getAttribute("words") + "\t"
											+ element3.getAttribute("characters") + "\t" + "="
											+ element3.getAttribute("words") + "/$A$2\t" + element3.getAttribute("tags")
											+ "\n";
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

									fileBody += "\tPerfect Match:\t" + element3.getAttribute("segments") + "\t"
											+ element3.getAttribute("words") + "\t"
											+ element3.getAttribute("characters") + "\t" + "="
											+ element3.getAttribute("words") + "/$A$2\t" + element3.getAttribute("tags")
											+ "\n";
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

									fileBody += "\tRepetitions:\t" + element3.getAttribute("segments") + "\t"
											+ element3.getAttribute("words") + "\t"
											+ element3.getAttribute("characters") + "\t" + "="
											+ element3.getAttribute("words") + "/$A$2\t" + element3.getAttribute("tags")
											+ "\n";
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

									fileBody += "\tCross File Repetitions:\t" + element3.getAttribute("segments") + "\t"
											+ element3.getAttribute("words") + "\t"
											+ element3.getAttribute("characters") + "\t" + "="
											+ element3.getAttribute("words") + "/$A$2\t" + element3.getAttribute("tags")
											+ "\n";
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

									fileBody += "\t100%:\t" + element3.getAttribute("segments") + "\t"
											+ element3.getAttribute("words") + "\t"
											+ element3.getAttribute("characters") + "\t" + "="
											+ element3.getAttribute("words") + "/$A$2\t" + element3.getAttribute("tags")
											+ "\n";
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

									fileBody += "\t" + element3.getAttribute("min") + "% - "
											+ element3.getAttribute("max") + "%:\t" + element3.getAttribute("segments")
											+ "\t" + element3.getAttribute("words") + "\t"
											+ element3.getAttribute("characters") + "\t" + "="
											+ element3.getAttribute("words") + "/$A$2\t" + element3.getAttribute("tags")
											+ "\n";
								}
							}
						}

						if (node2.getNodeType() == node2.ELEMENT_NODE) {
							Element element2 = (Element) node2;
							NodeList internalFuzzy = element2.getElementsByTagName("internalFuzzy");
							ifl = internalFuzzy.getLength();

							if (ifl != 0) {
								fileBody += "\tInternal:\n";
							}

							for (int k = ifl - 1; k >= 0; k--) {
								Node node3 = internalFuzzy.item(k);

								if (node3.getNodeType() == node3.ELEMENT_NODE) {
									Element element3 = (Element) node3;

									fileBody += "\t" + element3.getAttribute("min") + "% - "
											+ element3.getAttribute("max") + "%:\t" + element3.getAttribute("segments")
											+ "\t" + element3.getAttribute("words") + "\t"
											+ element3.getAttribute("characters") + "\t" + "="
											+ element3.getAttribute("words") + "/$A$2\t" + element3.getAttribute("tags")
											+ "\n";
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

									fileBody += "\tNew:\t" + element3.getAttribute("segments") + "\t"
											+ element3.getAttribute("words") + "\t"
											+ element3.getAttribute("characters") + "\t" + "="
											+ element3.getAttribute("words") + "/$A$2\t" + element3.getAttribute("tags")
											+ "\n";
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

									fileBody += "\tTotal:\t" + element3.getAttribute("segments") + "\t"
											+ element3.getAttribute("words") + "\t"
											+ element3.getAttribute("characters") + "\t100%\t"
											+ element3.getAttribute("tags") + "\n";
									all = element3.getAttribute("words");
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

							fileBody += "\nFile(" + (j + 1) + ") - " + element.getAttribute("name") + ":" + "\n";
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

											fileBody += "\tLocked:\t" + element3.getAttribute("segments") + "\t"
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

											fileBody += "\tContext Match:\t" + element3.getAttribute("segments") + "\t"
													+ element3.getAttribute("words") + "\t"
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

											fileBody += "\tPerfect Match:\t" + element3.getAttribute("segments") + "\t"
													+ element3.getAttribute("words") + "\t"
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

											fileBody += "\tRepetitions:\t" + element3.getAttribute("segments") + "\t"
													+ element3.getAttribute("words") + "\t"
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

											fileBody += "\tCross File Repetitions:\t"
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

											fileBody += "\t100%:\t" + element3.getAttribute("segments") + "\t"
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

											fileBody += "\t" + element3.getAttribute("min") + "% - "
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
										fileBody += "\tInternal:\n";
									}

									for (int l = ifl - 1; l >= 0; l--) {
										Node node3 = internalFuzzy.item(l);

										if (node3.getNodeType() == node3.ELEMENT_NODE) {
											Element element3 = (Element) node3;

											fileBody += "\t" + element3.getAttribute("min") + "% - "
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

											fileBody += "\tNew:\t" + element3.getAttribute("segments") + "\t"
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

											fileBody += "\tTotal:\t" + element3.getAttribute("segments") + "\t"
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
					"Nieprawidłowa nazwa pliku lub brak pliku w folderze. Spróbuj wpisać inną nazwę.",
					"Błąd odczytu pliku", JOptionPane.ERROR_MESSAGE);
		}
	}

	public String getFileBody() {
		return fileBody;
	}

	public int getCondition() {
		return condition;
	}

	public String getAll() {
		return all;
	}
}
