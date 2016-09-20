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

package net.luoteng.coupon.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import net.luoteng.coupon.enums.CouponStatus;
import net.luoteng.entity.AbstractTimeScopeEntity;
import net.luoteng.entity.embedded.RealmEntity;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public class Coupon extends AbstractTimeScopeEntity {

    /**
     * 持有人
     */
    private RealmEntity owner;
    
    /**
     * 因为使用某个对象而使用本红包
     */
    private RealmEntity entity;
    
    /**
     * 所属的package
     */
    private String packageId;

    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    private CouponStatus status;

    /**
     * 奖券分配给持有人的时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date timePlaced;

    /**
     * 奖券使用的时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeUsed;
    
    /**
     * 最终实际兑换成现金的金额 单位（元）
     */
    @Column(nullable = false,
            precision = 15,
            scale = 2)
    private BigDecimal actualAmount;
    
    /**
     * 最小使用门槛
     */
    @Column(nullable = false)
    private int minOrderAmount;
    
    /**
     * 附加信息
     */
    @Lob
    private String priv;
    
}
