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

package net.luoteng.payment.model.alipay;

import net.luoteng.model.AbstractObject;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public class AlipayOrderQueryRequest extends AbstractObject {

    /**
     * 支付宝分配给开发者的应用ID
     */
    private String app_id;
    
    /**
     * 	接口名称
     */
    private String method;
    
    /**
     * 请求使用的编码格式，如utf-8,gbk,gb2312等
     */
    private String charset;
    
    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA
     */
    private String sign_type;
    
    /**
     * 	商户请求参数的签名串
     */
    private String sign;
    
    /**
     * 调用的接口版本
     */
    private String out_trade_no;
    
    /**
     * 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
     */
    private String timestamp;
    
    /**
     * 请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
     */
    private String version;
    
    private String biz_content;

    public AlipayOrderQueryRequest(String app_id, String out_trade_no) {
        this.app_id = app_id;
        this.out_trade_no = out_trade_no;
    }
    
    
    
}
