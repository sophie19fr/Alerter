package com.devises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

public class XmlParser<K> {

	private XmlPullParser xpp;
	private List<String> telephone = new ArrayList<String>();
	private List<String> email = new ArrayList<String>();

	public XmlParser(XmlPullParser xpp) {
		super();
		this.xpp = xpp;
	}

	//la methode prend en argument un String (l'attribut : str)
	// et retourne une liste de résultats, et voila
	public void getNodFromAttribute(String str1, String str2) {
		
		try {
			
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getAttributeCount() > 0) {

						String tel = xpp.getAttributeValue(null, str1);
						this.telephone.add(tel);
						String mail = xpp.getAttributeValue(null, str2);
						this.email.add(mail);
					}
				}

				xpp.next();
			}


		} catch (Exception e) {

		}

	}

	public List<String> getTelephone() {
		return telephone;
	}

	public void setTelephone(List<String> telephone) {
		this.telephone = telephone;
	}

	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}
	
	
}
