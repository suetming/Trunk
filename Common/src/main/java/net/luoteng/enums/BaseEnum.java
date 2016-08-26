/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.enums;

import java.util.List;

/**
 * base enum
 * 
 * @author suetming <suetming.ma at gmail.com>
 * @param <T> 
 */
public interface BaseEnum<T> {
    
    /**
     * msg along with enum
     *
     * @return
     */
    String getMsg();
    
    /**
     * 
     * @return 
     */
    List<T> toList();
}