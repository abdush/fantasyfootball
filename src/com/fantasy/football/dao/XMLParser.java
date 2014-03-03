/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.dao;

import com.fantasy.football.model.League;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Abdu
 */
public class XMLParser {

    public XMLParser() {
    }

    /**
     * Loads the content of the xml file. Loads the League data from the
     * database XML file and bind it to the Objects.
     */
    public static League loadFromXmlFile() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(League.class);
            Unmarshaller jaxbTestsUnmarshaller = jaxbContext.createUnmarshaller();
            File XMLfile = new File("data/League.xml");
            //unmarshall the object and store it to the League object
            League league = (League) jaxbTestsUnmarshaller.unmarshal(XMLfile);
            return league;
        } catch (JAXBException e) {
            System.err.println(e);
            return null;
        }
    }

    /**
     * Update the content of the xml file. This function is called in order to
     * save the league data back to the file.
     */
    public void updateXmlFile(League league) {

        try {
            // create JAXB context and initializing Marshaller
            JAXBContext jaxbTestsContext = JAXBContext.newInstance(League.class);
            Marshaller jaxbMarshaller = jaxbTestsContext.createMarshaller();
            // for getting nice formatted output
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //specify the location and name of xml file to be created
            File XMLfile = new File("data/League.xml");
            // Writing to XML file
            jaxbMarshaller.marshal(league, XMLfile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
