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

package net.luoteng.action.service;

import java.awt.print.Pageable;
import java.util.Set;
import net.luoteng.action.enums.ActionType;
import net.luoteng.constant.GlobalConstant;
import net.luoteng.entity.embedded.RealmEntity;

/**
 * like unlike  
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface ActionService {

    /**
     * 
     * 
     * @param entity
     * @param userId
     * @param type
     * @return 
     */
    public long redo(RealmEntity entity, String userId, ActionType type);
    
    /**
     * 取消点赞
     * 
     * @param entity
     * @param userId
     * @param type
     * @return 
     */
    public long undo(RealmEntity entity, String userId, ActionType type);
    
    /**
     * 行为是否已经发生
     * 
     * @param entity
     * @param userId
     * @param type
     * @return 
     */
    public boolean exist(RealmEntity entity, String userId, ActionType type);
    
    /**
     * 列出相同行动执行人
     * 
     * @param entity
     * @param type
     * @param pageable
     * @return 
     */
    public Set<String> list(RealmEntity entity, ActionType type, Pageable pageable);
    
    /**
     * 行动总数
     * 
     * @param entity
     * @param type
     * @return 
     */
    public long count(RealmEntity entity, ActionType type);
}
