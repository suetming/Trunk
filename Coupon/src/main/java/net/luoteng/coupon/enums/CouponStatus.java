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

package net.luoteng.coupon.enums;

import lombok.Getter;
import net.luoteng.enums.BaseEnum;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public enum CouponStatus implements BaseEnum {

    /**
     * 奖券已经分配给持有人，等待使用
     */
    PLACED("已分配"),
    /**
     * 表示奖券已经按照规则使用.
     */
    USED("已使用"),
    /**
     * 运营人员或用户因为某种原因将该张奖券作废
     */
    CANCELLED("已作废"),
    /**
     * 奖券未能在到期前使用，属于最终状态
     */
    EXPIRED("过期");

    @Getter
    private final String msg;

    private CouponStatus(String msg) {
        this.msg = msg;
    }

}
