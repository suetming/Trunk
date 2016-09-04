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

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import net.luoteng.entity.AbstractTimeScopeEntity;

/**
 * authenticatable
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Data
public abstract class Authenticatable extends AbstractTimeScopeEntity {

    /**
     * account passphrase
     * 
     * @return
     */
    @Column(nullable = false, length = 40)
    protected String passphrase;

    /**
     * salt value in hex
     * 
     * @return
     */
    @Column(nullable = false, length = 120)
    protected String salt;

    /**
     * The timestamp that last successfully login
     * 
     * @return
     */
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastLoginDate;

    /**
     * indicate whether the entity need to change its password on signin
     * 
     * @return
     */
    @Column(nullable = false)
    protected boolean needChangePassword;
    
    /**
     * active account or not.
     * 
     * @return
     */
    @Column(nullable = false)
    protected boolean enabled;
    
}
