package org.dows.file.adapter;

import org.dows.file.api.model.FileType;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 应用配置
 */
public final class AppConfig {
    private static final Map<String, FileType> FILE_TYPE_CACHE = new HashMap<>();

    static {
        //解析文件存储配置文件
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder bulider = dbf.newDocumentBuilder();
            InputStream inputStream = null;
            File file = new File("config/simplefs_config.xml");
            if (file.exists()) {
                inputStream = new FileInputStream(file);
            }
            if (inputStream == null) {
                inputStream = AppConfig.class.getClassLoader().getResourceAsStream("simplefs_config.xml");
            }
            Document doc = bulider.parse(inputStream);
            XPathFactory factory = XPathFactory.newInstance();//实例化XPathFactory对象
            XPath xpath = factory.newXPath();
            XPathExpression compile = xpath.compile("//filetype");
            NodeList nodes = (NodeList) compile.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                FileType fileType = new FileType();
                NamedNodeMap attributes = node.getAttributes();
                for (int j = 0; j < attributes.getLength(); j++) {
                    Node attr = attributes.item(j);
                    if ("name".equals(attr.getNodeName())) {
                        fileType.setName(attr.getTextContent());
                    } else if ("value".equals(attr.getNodeName())) {
                        fileType.setValue(attr.getTextContent());
                    } else if ("path".equals(attr.getNodeName())) {
                        fileType.setPath(attr.getTextContent());
                    } else if ("prefix".equals(attr.getNodeName())) {
                        fileType.setPrefix(attr.getTextContent());
                    }
                }

                FILE_TYPE_CACHE.put(fileType.getValue(), fileType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FileType getFileType(String key) {
        return FILE_TYPE_CACHE.get(key);
    }
}
