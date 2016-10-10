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

package net.luoteng.user.service;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface UserFundService {

    /**
     * 用户成功支付
     * 1、balanceAmount为通过指点钱包余额支付的部分
     * 2、partyAmount为三方实际支付（微信、支付宝）金额
     * 3、couponAmount为红包抵扣金额
     * 
     * （用户）可用-部分：availableAmount-balanceAmount
     * （用户）已支出+部分：outAmount+(balanceAmount+partyAmount)
     * （点师）待收+：dueInAmount+balancePayAmount+partyPayAmount+couponAmount
     * 
     * @param sender 支付方
     * @param receiver 收款方
     * @param balanceAmount 用户通过“钱包”余额所支付金额，可能为0
     * @return
     */
    boolean pay(String sender, String receiver, BigDecimal balanceAmount);
    
}
