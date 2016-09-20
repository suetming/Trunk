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

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import net.luoteng.coupon.enums.CouponEventType;
import net.luoteng.coupon.enums.CouponType;
import net.luoteng.entity.AbstractTimeScopeEntity;
import net.luoteng.entity.embedded.RealmEntity;

/**
 * coupon package
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public class CouponPackage extends AbstractTimeScopeEntity {

    /**
     * 后台显示用名称
     */
    @Column(nullable = false)
    private String name;

    /**
     * 该批奖券在前台显示的名字
     */
    @Column(nullable = false)
    private String displayName;
    
    /**
     * 奖券类型
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CouponType type;

    /**
     * 奖券包对应的模板
     */
    private String templateId;

    /**
     * 触发奖券分配的用户事件类型.
     * 
     * 为空则不能根据事件触发，一般用于自动发券
     */
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private CouponEventType eventType;

    /**
     * 发行总数，不是总值，而是奖券的总张数
     */
    @Column(nullable = false)
    private int totalCount;

    /**
     * 票面价值 代金券，单位（元）  折扣券，打9折，则parValue值为90
     * <strong>当为免费券时，表示所能免除的金额上限</strong>
     */
    @Column(nullable = false)
    private int parValue;
    
    /**
     * 最小使用门槛
     */
    @Column(nullable = false)
    private int minOrderAmount;
    
    /**
     * 奖券包的展示图标
     */
    private String icon;
    
    /**
     * 奖券包的使用规则
     */
    @Column(nullable = true, length = 2000)
    private String rule;

    /**
     * 发行时间
     */
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeIssued;

    /**
     * 生效时间.
     *
     * 生效时间后才可以进行兑换等操作
     *
     * null表示即时生效
     */
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStart;

    /**
     * 过期时间，该批次的所有奖券都有同样的过期时间.
     *
     * null表示永不过期
     */
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeExpire;
    
    /**
     * 详情介绍
     */
    @Column(nullable = true, length = 2000)
    private String description;
    
    /**
     * 关联某次行为实体而产生的奖券包，用于红包分享
     */
    private RealmEntity entity;
    
    /**
     * 附加信息
     */
    @Lob
    private String priv;
    
}
