package net.luoteng.model;


import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * base object
 * 
 * @author suetming <suetming.ma at gmail.com>
 */
public abstract class AbstractObject implements Serializable {

    static final long serialVersionUID = 20160723L;
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, false);
    }

}
