package com.devises;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/*
 * AnalyseurXML de type XPP
 *
 * getXmlISFromUrl(String url) : recupere des octets sur un serveur sous forme d'un flux de caracteres
 *
 * getValuesFromElement(InputStream is, String asElement) : chaine contenant les #text d'un arbre DOM
 *
 * getValueFromElement(InputStream is, String asElement) : chaine contenant la valeur d'un #text d'un element
 */
public class XmlParser {

    /**
     * 
     * @param asURL : l'URL
     * @return : un flux
     */
    public InputStream getXmlISFromUrl(String asURL)
    {
        // --- This abstract class is the superclass of all classes representing an input stream of bytes.
        InputStream is = null;

        try {
            // --- Le client HTTP
            DefaultHttpClient httpClient = new DefaultHttpClient();

            // --- La requete
            HttpGet httpRequete = new HttpGet(asURL);

            // --- La reponse
            // --- qui est le resultat d'une requete faite au serveur grace au client
            HttpResponse httpReponse = httpClient.execute(httpRequete);

            // --- Recupere le "body" de la reponse
            HttpEntity httpEntite = httpReponse.getEntity();

            // --- Recupere le contenu dans un InputStream
            is = httpEntite.getContent();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // --- Renvoie un InputStream contenant le XML
        return is;
    } /// getXmlFromUrl



    /**
     * getValuesFromElement : les valeurs correspondant au critere
     * @param is : un flux
     * @param asElement : l'element recherche
     * @return : la(les) valeur(s) de l'element
     */
    public String getValuesFromElement(InputStream is, String asElement)
    {
        StringBuilder lsbValeurs = new StringBuilder("");

        try {
        // --- Creation d'une fabrique de parser
        XmlPullParserFactory fabriqueXPP = XmlPullParserFactory.newInstance();
        fabriqueXPP.setNamespaceAware(true);

        // --- Creation d'un parser
        XmlPullParser xpp = fabriqueXPP.newPullParser();

        // --- Affectation du flux au parser
        xpp.setInput(is, null);
        //xpp.setInput(new InputStreamReader(is));

        // --- Tant que le document n'a pas ete analyse jusqu'a fin
        while(xpp.getEventType() != XmlPullParser.END_DOCUMENT) {

            // --- Si c'est une balise ouvrante
            if(xpp.getEventType() == XmlPullParser.START_TAG) {
                   // --- Recuperation d'un #text de balise
                if(xpp.getName().equals(asElement)) {
                    xpp.next(); // --- Pour aller sur le noeud #text
                    lsbValeurs.append(xpp.getText() + "\n");
                } /// IF balise "coordinates" trouvee

            } /// IF start_tag

            // --- On passe a la balise suivante ou element suivant
            xpp.next();

        } /// WHILE parse

        }
        catch (ClientProtocolException e) {
        }
        catch (IOException e) {
        }
        catch (XmlPullParserException e) {
        }

        return lsbValeurs.toString();
    } /// getValuesFromElement



    /**
     * getValueFromElement : la premier valeur correspondant au critere
     * @param is : un flux
     * @param asElement : l'element recherche
     * @return : LA valeur de l'element
     */
    public String getValueFromElement(InputStream is, String asElement)
    {
        String lsValeur = "";
        boolean lbTrouve = false;

        try {
        // --- Creation d'une fabrique de parser
        XmlPullParserFactory fabriqueXPP = XmlPullParserFactory.newInstance();
        fabriqueXPP.setNamespaceAware(true);

        // --- Creation d'un parser
        XmlPullParser xpp = fabriqueXPP.newPullParser();

        // --- Affectation du flux au parser
        xpp.setInput(is, null);
        //xpp.setInput(new InputStreamReader(is));

        // --- Tant que le document n'a pas ete analyse jusqu'a fin
        while(xpp.getEventType() != XmlPullParser.END_DOCUMENT && !lbTrouve) {

            // --- Si c'est une balise ouvrante
            if(xpp.getEventType() == XmlPullParser.START_TAG) {
                   // --- Recuperation d'un #text de balise
                if(xpp.getName().equals(asElement)) {
                    xpp.next(); // --- Pour aller sur le noeud #text
                    lsValeur = xpp.getText();
                    lbTrouve = true;
                } /// IF balise trouvee

            } /// IF start_tag

            // --- On passe a la balise suivante ou element suivant
            xpp.next();

        } /// WHILE parse

        }
        catch (ClientProtocolException e) {
        }
        catch (IOException e) {
        }
        catch (XmlPullParserException e) {
        }

        return lsValeur;
    } /// getValueFromElement

} /// class AnalyseurXMLXPP