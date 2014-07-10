//package com.dianping.zebra.admin.tools;
//
//import static com.dianping.zebra.admin.sqlMap.Constants.ELEMENT_DELETE;
//import static com.dianping.zebra.admin.sqlMap.Constants.ELEMENT_INSERT;
//import static com.dianping.zebra.admin.sqlMap.Constants.ELEMENT_SELECT;
//import static com.dianping.zebra.admin.sqlMap.Constants.ELEMENT_TYPEALIAS;
//import static com.dianping.zebra.admin.sqlMap.Constants.ELEMENT_UPDATE;
//
//import org.xml.sax.Attributes;
//import org.xml.sax.SAXException;
//
//import com.dianping.zebra.admin.sqlMap.entity.CacheModel;
//import com.dianping.zebra.admin.sqlMap.entity.FlushInterval;
//import com.dianping.zebra.admin.sqlMap.entity.Property;
//import com.dianping.zebra.admin.sqlMap.entity.Result;
//import com.dianping.zebra.admin.sqlMap.entity.SqlMap;
//import com.dianping.zebra.admin.sqlMap.transform.DefaultSaxParser;
//
//public class CustmizedDefaultSaxParser extends DefaultSaxParser {
//
//	@Override
//	public void endElement(String uri, String localName, String qName) throws SAXException {
//		if (uri == null || uri.length() == 0) {
//			Object currentObj = m_objs.pop();
//			String currentTag = m_tags.pop();
//
//			if (currentObj instanceof SqlMap) {
//				SqlMap sqlMap = (SqlMap) currentObj;
//
//				if (ELEMENT_TYPEALIAS.equals(currentTag)) {
//					sqlMap.addTypeAlias(getText());
//				} else if (ELEMENT_SELECT.equals(currentTag)) {
//					sqlMap.addSelect(getText());
//				} else if (ELEMENT_INSERT.equals(currentTag)) {
//					sqlMap.addInsert(getText());
//				} else if (ELEMENT_UPDATE.equals(currentTag)) {
//					sqlMap.addUpdate(getText());
//				} else if (ELEMENT_DELETE.equals(currentTag)) {
//					sqlMap.addDelete(getText());
//				}
//			}
//		}
//
//		m_text.setLength(0);
//	}
//
//	@Override
//	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//		if (uri == null || uri.length() == 0) {
//			if (m_objs.isEmpty()) { // root
//				parseRoot(qName, attributes);
//			} else {
//				Object parent = m_objs.peek();
//				String tag = m_tags.peek();
//
//				if (parent instanceof SqlMap) {
//					parseForSqlMap((SqlMap) parent, tag, qName, attributes);
//				} else if (parent instanceof CacheModel) {
//					parseForCacheModel((CacheModel) parent, tag, qName, attributes);
//				} else if (parent instanceof FlushInterval) {
//					parseForFlushInterval((FlushInterval) parent, tag, qName, attributes);
//				} else if (parent instanceof Property) {
//					parseForProperty((Property) parent, tag, qName, attributes);
//				} else if (parent instanceof Result) {
//					parseForResult((Result) parent, tag, qName, attributes);
//				} else {
//					throw new RuntimeException(String.format("Unknown entity(%s) under %s!", qName, parent.getClass()
//					      .getName()));
//				}
//			}
//
//			m_text.setLength(0);
//		} else {
//			throw new SAXException(String.format("Namespace(%s) is not supported by %s.", uri, this.getClass().getName()));
//		}
//	}
//
//}
