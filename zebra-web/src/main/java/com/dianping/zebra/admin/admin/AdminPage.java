package com.dianping.zebra.admin.admin;

import org.unidal.web.mvc.Page;
import org.unidal.web.mvc.annotation.ModuleMeta;

public enum AdminPage implements Page {

   UPDATE("update", "update", "Update", "Updates", true),

   SERVICE("service", "service", "Service", "Service", true),

   NOTIFY("notify", "notify", "Notify", "Notify", true),

   BLACKLIST("blacklist", "blacklist", "Blacklist", "Blacklist", true),

   MERGE("merge", "merge", "Merge", "Merge", true),

   CONFIG("config", "config", "Config", "Config", true);

   private String m_name;

   private String m_path;

   private String m_title;

   private String m_description;

   private boolean m_standalone;

   private AdminPage(String name, String path, String title, String description, boolean standalone) {
      m_name = name;
      m_path = path;
      m_title = title;
      m_description = description;
      m_standalone = standalone;
   }

   public static AdminPage getByName(String name, AdminPage defaultPage) {
      for (AdminPage action : AdminPage.values()) {
         if (action.getName().equals(name)) {
            return action;
         }
      }

      return defaultPage;
   }

   public String getDescription() {
      return m_description;
   }

   public String getModuleName() {
      ModuleMeta meta = AdminModule.class.getAnnotation(ModuleMeta.class);

      if (meta != null) {
         return meta.name();
      } else {
         return null;
      }
   }

   @Override
   public String getName() {
      return m_name;
   }

   @Override
   public String getPath() {
      return m_path;
   }

   public String getTitle() {
      return m_title;
   }

   public boolean isStandalone() {
      return m_standalone;
   }

   public AdminPage[] getValues() {
      return AdminPage.values();
   }
}
