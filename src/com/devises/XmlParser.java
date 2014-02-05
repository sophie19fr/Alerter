package com.devises;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

public class XmlParser {

	private XmlPullParser xpp;
	private List<String> numeros = new ArrayList<String>();

	public XmlParser(XmlPullParser xpp) {
		super();
		this.xpp = xpp;
	}

	//la methode prend en argument un String (l'attribut : str)
	// et retourne une liste de résultats, et voila
	public List<String> getNodFromAttribute(String str) {
		try {

			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getAttributeCount() > 0) {

						String res = xpp.getAttributeValue(null, str);
						numeros.add(res);

					}
				}

				xpp.next();
			}

		} catch (Exception e) {

		}

		return numeros;
	}
}
