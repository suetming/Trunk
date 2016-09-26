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

package net.luoteng.config.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.luoteng.entity.AbstractTimeScopeEntity;

/**
 * dynamic configuration
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_config", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"classSimpleName", "packageName"})
})
public class Config extends AbstractTimeScopeEntity {

    /**
     * 类名
     */
    private String classSimpleName;
    
    /**
     * 包名
     */
    private String packageName;
    
    /**
     * 私有数据
     */
    @Lob
    private String priv;
    
}
