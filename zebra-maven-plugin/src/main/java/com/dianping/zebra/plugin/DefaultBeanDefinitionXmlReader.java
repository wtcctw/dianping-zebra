package com.dianping.zebra.plugin;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.Visitor;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.UserDataAttribute;

import com.dianping.lion.EnvZooKeeperConfig;
import com.dianping.lion.client.ConfigCache;
import com.dianping.lion.client.LionException;

public class DefaultBeanDefinitionXmlReader {

	private Document doc;

	public DefaultBeanDefinitionXmlReader(String path) {
		try {
			doc = new SAXReader().read(path);

			BeanVisitor visitor = new BeanVisitor(path);
			visitor.visit(doc);

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public class BeanVisitor implements Visitor {

		private Map<String, Element> dpdlDs = new HashMap<String, Element>();

		private Map<String, Element> c3p0Ds = new HashMap<String, Element>();

		private Pattern pattern = Pattern.compile("^jdbc:([a-zA-Z0-9]+)://([^/]+)/([^?]+).*$");

		private Document document;

		private String path;

		public BeanVisitor(String path) {
			this.path = path;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void visit(Document document) {
			System.out.println("parsing " + this.path);

			this.document = document;

			Element root = document.getRootElement();
			if (root.getName().equalsIgnoreCase("beans")) {
				List<Element> elements = root.elements("bean");

				if (elements != null) {
					for (Element ele : elements) {
						visit(ele);
					}

					System.out.println("DPDL:" + dpdlDs.size() + " C3P0:" + c3p0Ds.size());
					if (dpdlDs.size() != 0 || c3p0Ds.size() != 0) {
						replaceDpdl();
						replaceC3P0();

						try {
							// XPath xp = XPathFactory.newInstance().newXPath();
							// NodeList nl = (NodeList) xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);
							//
							// for (int i=0; i < nl.getLength(); ++i) {
							// Node node = nl.item(i);
							// node.getParentNode().removeChild(node);
							// }
							//
							deleteEmptyElement(root);
							XMLWriter write = new XMLWriter(new FileWriter(path + ".bak"));

							write.write(this.document);
							write.flush();
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

		private void deleteEmptyElement(Element ele) {
			List<Element> elements = ele.elements();
			for (Element element : elements) {
				if (element.isTextOnly() && element.getText().length() == 0) {
					ele.remove(element);
				} else {
					deleteEmptyElement(element);
				}
			}
		}

		private void replaceC3P0() {
		}

		@SuppressWarnings("unchecked")
		private void replaceDpdl() {
			for (Entry<String, Element> entry : dpdlDs.entrySet()) {
				Element ele = entry.getValue();

				List<Element> elements = ele.elements("property");

				for (Element element : elements) {
					Attribute attributeName = element.attribute("name");
					Attribute attributeValue = element.attribute("value");

					if (attributeName != null && attributeName.getStringValue().equals("writeDS") && attributeValue != null) {
						Element single = c3p0Ds.remove(attributeValue.getStringValue());
						this.document.getRootElement().remove(single);

						List<Element> properties = single.elements("property");

						for (Element property : properties) {
							if (property.attribute("name").getStringValue().equals("jdbcUrl")) {
								String jdbcUrl = property.attribute("value").getStringValue();

								String jdbcRef = parseJdbcUrl(jdbcUrl);

								if (jdbcRef != null) {
									System.out.println("jdbcRef:" + jdbcRef);
									addElement(entry.getKey(), jdbcRef, single);
									this.document.getRootElement().remove(ele);
								}
							}
						}
						System.out.println("writeDs : " + attributeValue.getStringValue());
					}

					if (attributeName != null && attributeName.getStringValue().equals("readDS")) {
						Element element2 = element.element("map").element("entry");
						System.out.println("readDs : " + element2.attribute("key").getStringValue());
						Element single = c3p0Ds.remove(element2.attribute("key").getStringValue());
						this.document.getRootElement().remove(single);
					}
				}
			}
		}

		private void addElement(String beanName, String jdbcRef, Element single) {
			Element rootElement = this.document.getRootElement();
			Element element = single.createCopy();

			element.attribute("id").setValue(beanName);
			element.attribute("class").setValue("com.dianping.zebra.group.jdbc.GroupDataSource");
			element.add(new UserDataAttribute(new QName("init-method"), "init"));
			element.remove(element.attribute("destroy-method"));
			List<Element> elements = element.elements("property");
			for (Element ele : elements) {
				if (ele.attribute("name").getStringValue().equals("jdbcUrl")) {
					ele.attribute("name").setValue("jdbcRef");
					ele.attribute("value").setValue(jdbcRef);
				} else if (ele.attribute("name").getStringValue().equals("user")
				      || ele.attribute("name").getStringValue().equals("password")) {
					element.remove(ele);
				}
			}

			rootElement.add(element);
		}

		private String parseJdbcUrl(String jdbcUrl) {
			String realJdbcUrl = null;

			if (jdbcUrl.startsWith("${") && jdbcUrl.endsWith("}")) {
				try {
					realJdbcUrl = ConfigCache.getInstance(EnvZooKeeperConfig.getZKAddress()).getProperty(
					      jdbcUrl.substring(2, jdbcUrl.length() - 1));
				} catch (LionException e) {
					e.printStackTrace();
				}
			} else {
				realJdbcUrl = jdbcUrl;
			}

			if (realJdbcUrl != null && realJdbcUrl.length() > 0) {
				Matcher m = pattern.matcher(realJdbcUrl);
				if (m.find()) {
					return m.group(3).toLowerCase();
				}
			}

			return realJdbcUrl;
		}

		@Override
		public void visit(DocumentType documentType) {
		}

		@Override
		public void visit(Element node) {
			Attribute attributeId = node.attribute("id");
			Attribute attributeClass = node.attribute("class");

			if (attributeId != null && attributeClass != null) {
				if (attributeClass.getStringValue().equals("com.mchange.v2.c3p0.ComboPooledDataSource")
				      || attributeClass.getStringValue().equals("com.dianping.dpdl.sql.DPDataSource")) {
					System.out.println("detect datasource " + attributeId.getStringValue());

					if (attributeClass.getStringValue().equals("com.mchange.v2.c3p0.ComboPooledDataSource")) {
						c3p0Ds.put(attributeId.getStringValue(), node);
					} else {
						dpdlDs.put(attributeId.getStringValue(), node);
					}
				}
			}
		}

		@Override
		public void visit(Attribute node) {
		}

		@Override
		public void visit(CDATA node) {
		}

		@Override
		public void visit(Comment node) {
		}

		@Override
		public void visit(Entity node) {
		}

		@Override
		public void visit(Namespace namespace) {
		}

		@Override
		public void visit(ProcessingInstruction node) {
		}

		@Override
		public void visit(Text node) {
		}
	}
}
