/*
 * Copyright 2016 suetming <suetming.ma at gmail.com>.
 *
 * This work is licensed under the 
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License.
 * To view a copy of this license, visit 
 *
 *      http://creativecommons.org/licenses/by-nc-nd/3.0/
 *
 * or send a letter to Creative Commons, 444 Castro Street, Suite 900, 
 * Mountain View, California, 94041, USA.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.luoteng.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
//import javax.ws.rs.core.Form;
import net.luoteng.constant.GlobalConstant;
import org.apache.commons.beanutils.BeanMap;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public class FormUtils implements GlobalConstant {

    /**
     * 将BaseRequest类的对象转换成页面生成所需要的名值对.
     *
     * 根据要求名字的拼写需要注意大小写
     *
     * @param request ChkValue需要已经存在
     * @return
     */
    public static Map<String, String> toMap(Object request) {
        Map<String, String> result = new HashMap<>();
        for (Object entryObj : new BeanMap(request).entrySet()) {
            Map.Entry entry = (Map.Entry) entryObj;
            String key = entry.getKey().toString();
            String value = entry.getValue() == null ? "" : entry.getValue().toString();
            if (!key.equalsIgnoreCase("class")) {
                String newKey = key.substring(0, 1).concat(key.substring(1));
                result.put(newKey, value);
            }
        }
        return result;
    }
    
    public static String toForm(Object request) {
        StringBuilder buffer = new StringBuilder();
        for (Object entryObj : new BeanMap(request).entrySet()) {
            Map.Entry entry = (Map.Entry) entryObj;
            String key = entry.getKey().toString();
            
            if (entry.getValue() == null) continue;
            
            String value = entry.getValue().toString();
            if (key.equalsIgnoreCase("class")) continue;
            
            String newKey = key.substring(0, 1).concat(key.substring(1));
            buffer.append("&").append(newKey).append("=").append(value);
        }
        return buffer.substring(1);
    }
    
    public static String toFormUrlEncode(Object request) throws UnsupportedEncodingException {
        StringBuilder buffer = new StringBuilder();
        for (Object entryObj : new BeanMap(request).entrySet()) {
            Map.Entry entry = (Map.Entry) entryObj;
            String key = entry.getKey().toString();
            
            if (entry.getValue() == null) continue;
            
            String value = entry.getValue().toString();
            if (key.equalsIgnoreCase("class")) continue;
            
            String newKey = key.substring(0, 1).concat(key.substring(1));
            buffer.append("&").append(newKey).append("=").append(URLEncoder.encode(value, GLOBAL_ENCODING));
        }
        return buffer.substring(1);
    }
    
}
