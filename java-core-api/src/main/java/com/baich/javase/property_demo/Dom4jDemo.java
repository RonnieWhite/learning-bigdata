package com.baich.javase.property_demo;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-23
 * Time : 08:42
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Dom4jDemo {
    public static void main(String[] args) throws Exception {
        SAXReader reader = new SAXReader();
        URL resource = Dom4jDemo.class.getClassLoader().getResource("test.xml");
        if (resource != null) {
            Document document = reader.read(resource);
            Element rootElement = document.getRootElement();
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element stu = iterator.next();
                List<Attribute> attributes = stu.attributes();
                for (Attribute attribute : attributes) {
                    System.out.println(attribute.getValue());
                }
            }
        }
    }

    @Test
    public void test() throws Exception {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("hive-site.xml");
        SAXReader reader = new SAXReader();
        if (stream != null) {
            Document doc = reader.read(stream);
            Element configEle = doc.getRootElement();
            Iterator<Element> configs = configEle.elementIterator();
            while (configs.hasNext()) {
                Element props = configs.next();
                Iterator<Element> elementIterator = props.elementIterator();
                while (elementIterator.hasNext()) {
                    Element prop = elementIterator.next();
                    if ("value".equals(prop.getName())) {
                        System.out.println(prop.getName() + "::" + prop.getStringValue());
                    }
                }
            }
        }
    }

    @Test
    public void testSep() {
        String sep = "_#!";
        String string = "abc_#!rdfg";
        String[] seps = string.split(sep);
        System.out.println(seps[0]);
        System.out.println(seps[1]);
    }
}
