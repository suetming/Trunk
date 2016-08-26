/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.utils;

import org.apache.commons.lang3.StringUtils;
import org.stringtemplate.v4.ST;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public class TextUtils {

    /**
     * <p>文本渲染
     * 
     * https://github.com/antlr/stringtemplate4/blob/master/doc/introduction.md
     * 
     * @param template
     * @param objects
     * @return 
     */
    public static String render(String template, Object...objects) {
        ST st = new ST(template);
        for (Object object : objects) {
            String key = StringUtils.uncapitalize(object.getClass().getSimpleName());
            st.add(key, object);
        }
        return st.render();
    }
    
}
