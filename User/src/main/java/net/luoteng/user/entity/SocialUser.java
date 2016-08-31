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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import net.luoteng.entity.AbstractTimeScopeEntity;
import net.luoteng.user.enums.SocialType;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public class SocialUser extends AbstractTimeScopeEntity {

    /**
     * 用户ID
     */
    @Column(nullable = false)
    private String userId;
    
    /**
     * 社交ID（应用）
     */
    @Column(nullable = false)
    private String clientSocialId;

    /**
     * 社交ID（平台）
     */
    private String unionSocialId;
    
    /**
     * 社交类型
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialType type;

    /**
     * 社交账号
     */
    private String account;
    
    /**
     * 第三方的实名（可能与userName不一致）
     */
    private String realName;
    
}
