package com.dianping.zebra.group.config.system.transform;

import static com.dianping.zebra.group.config.system.Constants.ELEMENT_APP_BLACK_LIST;
import static com.dianping.zebra.group.config.system.Constants.ELEMENT_COOKIE_DOMAIN;
import static com.dianping.zebra.group.config.system.Constants.ELEMENT_COOKIE_EXPIRED_TIME;
import static com.dianping.zebra.group.config.system.Constants.ELEMENT_COOKIE_NAME;
import static com.dianping.zebra.group.config.system.Constants.ELEMENT_ENCRYPT_SEED;
import static com.dianping.zebra.group.config.system.Constants.ELEMENT_GLOBAL_BLACK_LIST;
import static com.dianping.zebra.group.config.system.Constants.ELEMENT_HEALTH_CHECK_INTERVAL;
import static com.dianping.zebra.group.config.system.Constants.ELEMENT_MAX_ERROR_COUNTER;
import static com.dianping.zebra.group.config.system.Constants.ELEMENT_RETRY_TIMES;
import static com.dianping.zebra.group.config.system.Constants.ENTITY_SYSTEM_CONFIG;

import com.dianping.zebra.group.config.system.IEntity;
import com.dianping.zebra.group.config.system.IVisitor;
import com.dianping.zebra.group.config.system.entity.SystemConfig;

public class DefaultXmlBuilder implements IVisitor {

   private IVisitor m_visitor = this;

   private int m_level;

   private StringBuilder m_sb;

   private boolean m_compact;

   public DefaultXmlBuilder() {
      this(false);
   }

   public DefaultXmlBuilder(boolean compact) {
      this(compact, new StringBuilder(4096));
   }

   public DefaultXmlBuilder(boolean compact, StringBuilder sb) {
      m_compact = compact;
      m_sb = sb;
      m_sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n");
   }

   public String buildXml(IEntity<?> entity) {
      entity.accept(m_visitor);
      return m_sb.toString();
   }

   protected void endTag(String name) {
      m_level--;

      indent();
      m_sb.append("</").append(name).append(">\r\n");
   }

   protected String escape(Object value) {
      return escape(value, false);
   }
   
   protected String escape(Object value, boolean text) {
      if (value == null) {
         return null;
      }

      String str = value.toString();
      int len = str.length();
      StringBuilder sb = new StringBuilder(len + 16);

      for (int i = 0; i < len; i++) {
         final char ch = str.charAt(i);

         switch (ch) {
         case '<':
            sb.append("&lt;");
            break;
         case '>':
            sb.append("&gt;");
            break;
         case '&':
            sb.append("&amp;");
            break;
         case '"':
            if (!text) {
               sb.append("&quot;");
               break;
            }
         default:
            sb.append(ch);
            break;
         }
      }

      return sb.toString();
   }
   
   protected void indent() {
      if (!m_compact) {
         for (int i = m_level - 1; i >= 0; i--) {
            m_sb.append("   ");
         }
      }
   }

   protected void startTag(String name) {
      startTag(name, false, null);
   }
   
   protected void startTag(String name, boolean closed, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
      startTag(name, null, closed, dynamicAttributes, nameValues);
   }

   protected void startTag(String name, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
      startTag(name, null, false, dynamicAttributes, nameValues);
   }

   protected void startTag(String name, Object text, boolean closed, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
      indent();

      m_sb.append('<').append(name);

      int len = nameValues.length;

      for (int i = 0; i + 1 < len; i += 2) {
         Object attrName = nameValues[i];
         Object attrValue = nameValues[i + 1];

         if (attrValue != null) {
            m_sb.append(' ').append(attrName).append("=\"").append(escape(attrValue)).append('"');
         }
      }

      if (dynamicAttributes != null) {
         for (java.util.Map.Entry<String, String> e : dynamicAttributes.entrySet()) {
            m_sb.append(' ').append(e.getKey()).append("=\"").append(escape(e.getValue())).append('"');
         }
      }

      if (text != null && closed) {
         m_sb.append('>');
         m_sb.append(escape(text, true));
         m_sb.append("</").append(name).append(">\r\n");
      } else {
         if (closed) {
            m_sb.append('/');
         } else {
            m_level++;
         }
   
         m_sb.append(">\r\n");
      }
   }

   protected void tagWithText(String name, String text, Object... nameValues) {
      if (text == null) {
         return;
      }
      
      indent();

      m_sb.append('<').append(name);

      int len = nameValues.length;

      for (int i = 0; i + 1 < len; i += 2) {
         Object attrName = nameValues[i];
         Object attrValue = nameValues[i + 1];

         if (attrValue != null) {
            m_sb.append(' ').append(attrName).append("=\"").append(escape(attrValue)).append('"');
         }
      }

      m_sb.append(">");
      m_sb.append(escape(text, true));
      m_sb.append("</").append(name).append(">\r\n");
   }

   protected void element(String name, String text, boolean escape) {
      if (text == null) {
         return;
      }
      
      indent();
      
      m_sb.append('<').append(name).append(">");
      
      if (escape) {
         m_sb.append(escape(text, true));
      } else {
         m_sb.append("<![CDATA[").append(text).append("]]>");
      }
      
      m_sb.append("</").append(name).append(">\r\n");
   }

   @Override
   public void visitSystemConfig(SystemConfig systemConfig) {
      startTag(ENTITY_SYSTEM_CONFIG, null);

      tagWithText(ELEMENT_HEALTH_CHECK_INTERVAL, String.valueOf(systemConfig.getHealthCheckInterval()));

      tagWithText(ELEMENT_MAX_ERROR_COUNTER, String.valueOf(systemConfig.getMaxErrorCounter()));

      element(ELEMENT_COOKIE_DOMAIN, systemConfig.getCookieDomain(), true);

      element(ELEMENT_COOKIE_NAME, systemConfig.getCookieName(), true);

      tagWithText(ELEMENT_RETRY_TIMES, String.valueOf(systemConfig.getRetryTimes()));

      element(ELEMENT_ENCRYPT_SEED, systemConfig.getEncryptSeed(), true);

      tagWithText(ELEMENT_COOKIE_EXPIRED_TIME, String.valueOf(systemConfig.getCookieExpiredTime()));

      element(ELEMENT_GLOBAL_BLACK_LIST, systemConfig.getGlobalBlackList(), true);

      element(ELEMENT_APP_BLACK_LIST, systemConfig.getAppBlackList(), true);

      endTag(ENTITY_SYSTEM_CONFIG);
   }
}
