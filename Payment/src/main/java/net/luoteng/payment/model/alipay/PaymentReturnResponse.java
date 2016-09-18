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

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Data
public class PaymentReturnResponse extends AbstractObject {

    private String is_success;
    
    private String sign_type;
    
    private String sign;
    
    private String out_trade_no;
    
    private String subject;
    
    private String payment_type;
    
    private String exterface;
    
    private String trade_no;
    
    private String trade_status;
    
    private String notify_id;
    
    private String notify_time;
    
    private String notify_type;
    
    private String body;
    
    private String total_fee;
    
    private String extra_common_param;
    
}
