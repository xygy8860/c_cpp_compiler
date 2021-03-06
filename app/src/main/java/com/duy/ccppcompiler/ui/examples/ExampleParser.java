/*
 * Copyright 2018 Mr Duy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duy.ccppcompiler.ui.examples;

import com.duy.common.DLog;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Duy on 03-May-18.
 */

public class ExampleParser {
    private static final String TAG = "ExampleParser";

    public ArrayList<ExampleItem> parse(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        ArrayList<ExampleItem> result = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(in);
        doc.getDocumentElement().normalize();
        NodeList examples = doc.getElementsByTagName("examples").item(0).getChildNodes();
        for (int i = 0; i < examples.getLength(); i++) {
            Node example = examples.item(i);
            NodeList childNodes = example.getChildNodes();
            ExampleItem item = new ExampleItem();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node attr = childNodes.item(j);
                String nodeName = attr.getNodeName();
                if (nodeName.equalsIgnoreCase(ExampleItem.TITLE)) {
                    item.setTitle(attr.getTextContent());
                } else if (nodeName.equalsIgnoreCase(ExampleItem.DESC)) {
                    item.setDesc(attr.getTextContent());
                } else if (nodeName.equalsIgnoreCase(ExampleItem.RELATIVE_PATH)) {
                    item.setRelativePath(attr.getTextContent());
                }
            }
            if (DLog.DEBUG) DLog.d(TAG, "item = " + item);
            result.add(item);
        }
        return result;
    }

}
