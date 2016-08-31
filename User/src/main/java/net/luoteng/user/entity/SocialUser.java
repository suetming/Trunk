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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.luoteng.entity.AbstractTimeScopeEntity;
import net.luoteng.user.enums.SocialType;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_social_user", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"userId", "type"})
})
public class SocialUser extends AbstractTimeScopeEntity {

    /**
     * 用户ID
     */
    @NotNull
    @Column(nullable = false)
    private String userId;
    
    /**
     * 社交ID（应用）
     */
    @NotNull
    @Column(nullable = false)
    private String clientId;

    /**
     * 社交ID（平台）
     */
    private String unionId;
    
    /**
     * 社交类型
     */
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialType type;

    /**
     * 社交账号昵称
     */
    @NotNull
    @Column(nullable = false)
    private String nickname;
    
    /**
     * 第三方的实名
     */
    private String name;
    
    /**
     * 头像
     */
    private String avatar;
    
}
