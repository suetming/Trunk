/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.captcha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.luoteng.model.AbstractObject;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Captcha extends AbstractObject {

    /**
     * token
     */
    private String uuid;

    /**
     * captcha的字面答案
     */
    private String answer;

    /**
     * png图片
     */
    private String content;

}
