/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.config;

import com.dev.vin.demo.commons.Tool;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 *
 * @author TUANPLA
 */
@Component
@PropertySource(value = {"classpath:application.properties"})
public class MyConfig {

    static Logger logger = Logger.getLogger(MyConfig.class);
    @Autowired
    private Environment environment;

    @PostConstruct
    public void init() {
        try {
            //--- INIT CONSTANT
            DE_BUG = getBoolean("omis.general.debug", DE_BUG);
            Tool.debug("============> DE_BUG: " + DE_BUG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean running = false;
    public static boolean DE_BUG;
    public static final String USER_SESSION_NAME = "userlogin";
    // ----Constants
    public static String SMTP_MAIL;
    public static String SMTP_PASS;
    public static String MAIL_HOST;
    public static String FROM_NAME;
    public static String MAIL_DEBUG;
    public static final int SEND_MAIL_FALSE = 1;
    // General

    public static int MAX_HOT;
    public static int MAX_TOP;
    public static String ARTICLES_IMG_THUMB_ALIAS;
    public static String ARTICLES_IMG_CACHE_ALIAS;
    // Noi cache Anh cua Content va Content
    public static int[] WIDTH_IMAGE_THUMBNAIL;
    public static int WIDTH_IMAGE_IN_CONTENT;
    public static int MAX_ROW_IN_CAT;

    //***********
    //-------Define Exception
    public static int ADMIN_MAX_ROW = 20;
    //--Menu Type
    public static final int MENU_CAT = 0;
    public static final int MENU_LINK = 1;
    // IS DELL
    public static final boolean ISDEL = true;
    //------------STATUS CONTENT------------------------------
    public static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    int getInt(String properties, int defaultVal) {
        try {
            return Integer.parseInt(environment.getProperty(properties, defaultVal + ""));
        } catch (NumberFormatException e) {
            logger.error(e);
            return defaultVal;
        }
    }

    long getLong(String properties, long defaultVal, String categoryName) {
        try {
            return Long.parseLong(environment.getProperty(properties, defaultVal + ""));
        } catch (NumberFormatException e) {
            logger.error(e);
            return defaultVal;
        }
    }

    Double getDouble(String properties, Double defaultVal) {
        try {
            return Double.parseDouble(environment.getProperty(properties, defaultVal + ""));
        } catch (NumberFormatException e) {
            logger.error(e);
            return defaultVal;
        }
    }

    String getString(String properties, String defaultVal) {
        try {
            return environment.getProperty(properties, defaultVal);
        } catch (Exception e) {
            logger.error(e);
            return defaultVal;
        }
    }

    boolean getBoolean(String properties, boolean defaultVal) {
        try {
            return environment.getProperty(properties, "0").equals("1") || environment.getProperty(properties, "false").equals("true");
        } catch (Exception e) {
            logger.error(e);
            return defaultVal;
        }
    }

    public static enum STATUS {
        ALL(127, "Tất cả"),
        UNACTIVE(0, "Chưa kích hoạt"),
        ACTIVE(1, "Kích hoạt"),
        LOCK(2, "Khóa"),;
        //--
        public int val;
        public String desc;

        private STATUS(int val, String desc) {
            this.val = val;
            this.desc = desc;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public String getName() {
            return desc;
        }

        public void setName(String name) {
            this.desc = name;
        }
    }

//    public static ArrayList<ModelEnum> getStatus() {
//        ArrayList<ModelEnum> _mystatus = new ArrayList<>();
//        for (MyConfig.STATUS one : MyConfig.STATUS.values()) {
//            ModelEnum _status = new ModelEnum(one.val, one.desc);
//            _mystatus.add(_status);
//        }
//        return _mystatus;
//    }
}
