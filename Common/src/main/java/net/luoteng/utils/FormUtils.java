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

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
//import javax.ws.rs.core.Form;
import net.luoteng.constant.GlobalConstant;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc. All Rights Reserved.
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

            if (entry.getValue() == null) {
                continue;
            }

            String value = entry.getValue().toString();
            if (key.equalsIgnoreCase("class")) {
                continue;
            }

            String newKey = key.substring(0, 1).concat(key.substring(1));
            buffer.append("&").append(newKey).append("=").append(value);
        }
        return buffer.substring(1);
    }
    
    public static String toForm(Object request, boolean sorted) throws UnsupportedEncodingException {
        StringBuilder buffer = new StringBuilder();

        List<Entry<Object, Object>> entrys = new ArrayList<>(new BeanMap(request).entrySet());

        if (sorted) {
            Collections.sort(entrys, new Comparator<Entry<Object, Object>>() {
                @Override
                public int compare(Entry<Object, Object> o1, Entry<Object, Object> o2) {
                    String key1 = o1.getKey().toString();
                    String key2 = o2.getKey().toString();
                    for(int i = 0; i < Math.min(key1.length(), key2.length()); i ++) {
                        byte[] b1 = key1.getBytes();
                        byte[] b2 = key2.getBytes();
                        
                        if (Math.abs(b1[i] - b2[i]) > 0) {
                            System.err.println(key1 + "-" + (char)(b1[i]) + ":" + key2 + "-" + (char)b2[i]);
                            return b1[i] - b2[i];
                        }
                    }
                    return key1.charAt(0) - key2.charAt(0);
                }
            });
        }

        for (Object entryObj : entrys) {
            Map.Entry entry = (Map.Entry) entryObj;
            String key = entry.getKey().toString();

            if (entry.getValue() == null) {
                continue;
            }

            String value = entry.getValue().toString();
            if (key.equalsIgnoreCase("class")) {
                continue;
            }

            String newKey = key.substring(0, 1).concat(key.substring(1));
            buffer.append("&").append(newKey).append("=").append(value);
        }
        return buffer.substring(1);
    }

    public static String toFormUrlEncode(Object request) throws UnsupportedEncodingException {
        return toFormUrlEncode(request, false);
    }

    public static String toFormUrlEncode(Object request, boolean sorted) throws UnsupportedEncodingException {
        StringBuilder buffer = new StringBuilder();

        List<Entry<Object, Object>> entrys = new ArrayList<>(new BeanMap(request).entrySet());

        if (sorted) {
            Collections.sort(entrys, new Comparator<Entry<Object, Object>>() {
                @Override
                public int compare(Entry<Object, Object> o1, Entry<Object, Object> o2) {
                    String key1 = o1.getKey().toString();
                    String key2 = o2.getKey().toString();
                    for(int i = 0; i < Math.min(key1.length(), key2.length()); i ++) {
                        byte[] b1 = key1.getBytes();
                        byte[] b2 = key2.getBytes();
                        
                        if (Math.abs(b1[i] - b2[i]) > 0) {
                            System.err.println(key1 + "-" + (char)(b1[i]) + ":" + key2 + "-" + (char)b2[i]);
                            return b1[i] - b2[i];
                        }
                    }
                    return key1.charAt(0) - key2.charAt(0);
                }
            });
        }

        for (Object entryObj : entrys) {
            Map.Entry entry = (Map.Entry) entryObj;
            String key = entry.getKey().toString();

            if (entry.getValue() == null) {
                continue;
            }

            String value = entry.getValue().toString();
            if (key.equalsIgnoreCase("class")) {
                continue;
            }

            String newKey = key.substring(0, 1).concat(key.substring(1));
            buffer.append("&").append(newKey).append("=").append(URLEncoder.encode(value, GLOBAL_ENCODING));
        }
        return buffer.substring(1);
    }

//    public static void main(String[] args) {
//        AlipayOrder order = new AlipayOrder(
//                "parnter",
//                "sellid",
//                "outTradeNo",
//                "subject",
//                "body",
//                "0.01",
//                "notifyurl",
//                "returnurl",
//                "create_direct_pay_by_user",
//                "1",
//                "utf-8",
//                String.format("%dm", 30), null);
//        System.err.println(order.toString());
//        try {
//            System.err.println(FormUtils.toFormUrlEncode(order, true));
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(FormUtils.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @AllArgsConstructor
//    public static class AlipayOrder implements Serializable {
//
//        /**
//         * 签约合作者身份ID
//         */
//        @Getter
//        @Setter
//        private String partner;
//
//        /**
//         * 签约卖家支付宝账号
//         */
//        @Getter
//        @Setter
//        private String seller_id;
//
//        /**
//         * 商户网站唯一订单号
//         */
//        @Getter
//        @Setter
//        private String out_trade_no;
//
//        /**
//         * 商品名称
//         */
//        @Getter
//        @Setter
//        private String subject;
//
//        /**
//         * 商品详情
//         */
//        @Getter
//        @Setter
//        private String body;
//
//        /**
//         * 商品金额
//         */
//        @Getter
//        @Setter
//        private String total_fee;
//
//        /**
//         * 服务器异步通知页面路径
//         */
//        @Getter
//        @Setter
//        private String notify_url;
//
//        /**
//         * 服务器同步跳转页面路径
//         */
//        @Getter
//        @Setter
//        private String return_url;
//
//        /**
//         * 服务接口名称
//         */
//        @Getter
//        @Setter
//        private String service;
//
//        /**
//         * 支付类型
//         */
//        @Getter
//        @Setter
//        private String payment_type;
//
//        /**
//         * 参数编码
//         */
//        @Getter
//        @Setter
//        private String _input_charset;
//
//        /**
//         * 设置未付款交易的超时时间默认30分钟， 一旦超时，该笔交易就会自动被关闭取值范围： 1m～15d。m-分钟，h-小时，
//         * 该参数数值不接受小数点，如1.5h，可转换为90m。
//         */
//        @Getter
//        @Setter
//        private String it_b_pay;
//
//        /**
//         * 扫码支付的方式，支持前置模式和跳转模式。
//         */
//        @Getter
//        @Setter
//        private String qr_pay_mode;
//
//        @Override
//        public String toString() {
//            return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, false);
//        }
//    }
}
