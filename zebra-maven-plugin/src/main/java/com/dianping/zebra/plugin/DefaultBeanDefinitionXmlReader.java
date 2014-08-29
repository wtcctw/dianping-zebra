package com.dianping.zebra.plugin;

import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.ProcessingInstruction;
import org.dom4j.Text;
import org.dom4j.Visitor;
import org.dom4j.io.SAXReader;

public class DefaultBeanDefinitionXmlReader {

	private Document doc;

	public DefaultBeanDefinitionXmlReader(String path) {
		try {
			doc = new SAXReader().read(path);

			if (doc.getRootElement().getName().equalsIgnoreCase("beans")) {

				System.out.println(path);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public class BeanVisitor implements Visitor {

		@Override
		public void visit(Document document) {
			if (document.getRootElement().getName().equalsIgnoreCase("beans")) {
			}
		}

		@Override
		public void visit(DocumentType documentType) {
			// TODO Auto-generated method stub

		}

		@Override
		public void visit(Element node) {
			// TODO Auto-generated method stub

		}

		@Override
		public void visit(Attribute node) {
			// TODO Auto-generated method stub

		}

		@Override
		public void visit(CDATA node) {
			// TODO Auto-generated method stub

		}

		@Override
		public void visit(Comment node) {
			// TODO Auto-generated method stub

		}

		@Override
		public void visit(Entity node) {
			// TODO Auto-generated method stub

		}

		@Override
		public void visit(Namespace namespace) {
			// TODO Auto-generated method stub

		}

		@Override
		public void visit(ProcessingInstruction node) {
			// TODO Auto-generated method stub

		}

		@Override
		public void visit(Text node) {
			// TODO Auto-generated method stub

		}

	}

}
