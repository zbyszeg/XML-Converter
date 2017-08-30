import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class Main {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		int lok, cm, pm, f100, rp, cfrp, nm, all, fl, ifl;

		lok = 0;
		cm = 0;
		pm = 0;
		f100 = 0;
		rp = 0;
		cfrp = 0;
		nm = 0;
		all = 0;
		fl = 0;
		ifl = 0;

		int f[] = new int[0];
		int i_f[] = new int[0];

		try {
			File inputFile = new File("analyse_cogen.xml");

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
						System.out.println(node2.getNodeName() + ":");

						if (node2.getNodeType() == node2.ELEMENT_NODE) {
							Element element2 = (Element) node2;
							NodeList locked = element2.getElementsByTagName("locked");

							for (int k = 0; k < locked.getLength(); k++) {
								Node node3 = locked.item(k);

								if (node3.getNodeType() == node3.ELEMENT_NODE) {
									Element element3 = (Element) node3;
									System.out.println("\tLocked:\t\t\t" + element3.getAttribute("words"));
									lok = Integer.parseInt(element3.getAttribute("words"));
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
									System.out.println("\tContext Match:\t\t" + element3.getAttribute("words"));
									cm = Integer.parseInt(element3.getAttribute("words"));
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
									System.out.println("\tPerfect Match:\t\t" + element3.getAttribute("words"));
									pm = Integer.parseInt(element3.getAttribute("words"));
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
									System.out.println("\tRepetitions:\t\t" + element3.getAttribute("words"));
									rp = Integer.parseInt(element3.getAttribute("words"));
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
									System.out.println("\tCross File Repetitions:\t" + element3.getAttribute("words"));
									cfrp = Integer.parseInt(element3.getAttribute("words"));
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
									System.out.println("\t100%:\t\t\t" + element3.getAttribute("words"));
									f100 = Integer.parseInt(element3.getAttribute("words"));
								}
							}
						}

						if (node2.getNodeType() == node2.ELEMENT_NODE) {
							Element element2 = (Element) node2;
							NodeList fuzzy = element2.getElementsByTagName("fuzzy");
							fl = fuzzy.getLength();
							f = new int[fl];

							for (int k = 0; k < fl; k++) {
								Node node3 = fuzzy.item(k);

								if (node3.getNodeType() == node3.ELEMENT_NODE) {
									Element element3 = (Element) node3;
									System.out.println("\t" + element3.getAttribute("min") + "% - "
											+ element3.getAttribute("max") + "%:\t\t" + element3.getAttribute("words"));
									f[k] = Integer.parseInt(element3.getAttribute("words"));
								}
							}
						}

						if (node2.getNodeType() == node2.ELEMENT_NODE) {
							Element element2 = (Element) node2;
							NodeList internalFuzzy = element2.getElementsByTagName("internalFuzzy");
							ifl = internalFuzzy.getLength();
							i_f = new int[ifl];

							System.out.println("\n\tInternal:");

							for (int k = 0; k < ifl; k++) {
								Node node3 = internalFuzzy.item(k);

								if (node3.getNodeType() == node3.ELEMENT_NODE) {
									Element element3 = (Element) node3;
									System.out.println("\t" + element3.getAttribute("min") + "% - "
											+ element3.getAttribute("max") + "%:\t\t" + element3.getAttribute("words"));
									i_f[k] = Integer.parseInt(element3.getAttribute("words"));
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
									System.out.println("\n\tNew:\t\t\t" + element3.getAttribute("words"));
									nm = Integer.parseInt(element3.getAttribute("words"));
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
									System.out.println("\n\tTotal:\t\t\t" + element3.getAttribute("words"));
									all = Integer.parseInt(element3.getAttribute("words"));
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}
}
