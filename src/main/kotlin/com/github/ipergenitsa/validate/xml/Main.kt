package com.github.ipergenitsa.validate.xml

import org.xml.sax.SAXException
import java.io.File
import java.io.IOException
import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

class Main

fun main(args: Array<String>) {
    val schemaFile = Main::class.java.classLoader.getResource("book-library-scheme.xsd")
    val xmlFile = StreamSource(File(Main::class.java.classLoader.getResource("book-library.xml").toURI()))

    val schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
    try {
        val schema = schemaFactory.newSchema(schemaFile)
        val validator = schema.newValidator()
        validator.validate(xmlFile)
        println(xmlFile.systemId + " is valid")
    } catch (e: SAXException) {
        println(xmlFile.systemId + " is NOT valid reason:" + e)
    } catch (e: IOException) {
    }
}