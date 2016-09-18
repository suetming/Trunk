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

import lombok.Data;
import net.luoteng.model.AbstractObject;
import org.apache.commons.lang3.StringUtils;

/**
 *
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc. All Rights Reserved.
 */
@Data
public class AlipayOrderQueryResponse extends AbstractObject {

    /**
     * 网关返回码
     */
    private String code;

    private String msg;

    private String sign;

    private String sub_code;

    private String sub_msg;

    private Order alipay_trade_query_response;
    
    @Data
    public static class Order extends AbstractObject {

        private String trade_no;

        private String out_trade_no;

        private String buyer_logon_id;

        private String trade_status;

        private String total_amount;

        private String receipt_amount;

        private String buyer_pay_amount;

        private String send_pay_date;

        private String buyer_user_id;

        private String discount_goods_detail;
    }
    
    public boolean isSuccess() {
        return StringUtils.isNoneBlank(code) && code.contentEquals("10000");
    }
}
