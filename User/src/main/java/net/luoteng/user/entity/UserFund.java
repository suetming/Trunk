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

package net.luoteng.user.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.luoteng.model.AbstractObject;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user_fund")
public class UserFund  extends AbstractObject {

    @Id
    private String userId;

    /**
     * 可用余额
     */
    @Min(0)
    @Column(nullable = false,
            precision = 15,
            scale = 2)
    private BigDecimal availableAmount;

    /**
     * 冻结金额
     */
    @Min(0)
    @Column(nullable = false,
            precision = 15,
            scale = 2)
    private BigDecimal frozenAmount;

    /**
     * 待收总额
     */
    @Column(nullable = false,
            precision = 15,
            scale = 2)
    private BigDecimal dueInAmount;

    /**
     * 已收入总额
     */
    @Column(nullable = false,
            precision = 15,
            scale = 2)
    private BigDecimal inAmount;
    
    /**
     * 已支出总额
     */
    @Column(nullable = false,
            precision = 15,
            scale = 2)
    private BigDecimal outAmount;

    /**
     * 充值总额
     */
    @Min(0)
    @Column(nullable = false,
            precision = 15,
            scale = 2)
    private BigDecimal depositAmount;

    /**
     * 已提现总额
     */
    @Min(0)
    @Column(nullable = false,
            precision = 15,
            scale = 2)
    private BigDecimal withdrawAmount;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeLastUpdated;
    
    @PrePersist
    private void setup() {
        Date date = new Date();
        this.timeCreated = date;
        this.timeLastUpdated = date;
    }
    
}
