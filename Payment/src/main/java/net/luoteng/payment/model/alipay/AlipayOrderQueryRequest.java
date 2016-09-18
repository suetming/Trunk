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

import com.alibaba.fastjson.JSON;
import lombok.Data;
import net.luoteng.constant.GlobalConstant;
import static net.luoteng.constant.TimeConstant.TIME_STANDARD;
import net.luoteng.enums.SignType;
import net.luoteng.model.AbstractObject;
import net.luoteng.payment.model.enums.alipay.Service;
import org.joda.time.DateTime;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Data
public class AlipayOrderQueryRequest extends AbstractObject {

    /**
     * 支付宝分配给开发者的应用ID
     * 
     * @return
     */
    private String app_id;
    
    /**
     * 	接口名称
     * 
     * @return
     */
    private String method;
    
    /**
     * 请求使用的编码格式，如utf-8,gbk,gb2312等
     * 
     * @return
     */
    private String charset;
    
    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA
     * 
     * @return
     */
    private String sign_type;
    
    /**
     * 	商户请求参数的签名串
     * 
     * @return
     */
    private String sign;
    
    /**
     * 调用的接口版本
     * 
     * @return
     */
    private String out_trade_no;
    
    /**
     * 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
     * 
     * @return
     */
    private String timestamp;
    
    /**
     * 请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
     * 
     * @return
     */
    private String version;
    
    /**
     * @return
     */
    private String biz_content;

    public AlipayOrderQueryRequest(String appId, String orderId) {
        this.app_id = appId;
        this.method = Service.TRADE_QUERY.getMsg();
        this.out_trade_no = orderId;
        this.version = "1.0";
        this.timestamp =new DateTime().toString(TIME_STANDARD); 
        this.charset = GlobalConstant.GLOBAL_ENCODING;
        this.biz_content = JSON.toJSONString(new AlipayOrder(out_trade_no));
        this.sign_type = SignType.RSA.name();
    }
    
}
