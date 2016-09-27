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

package net.luoteng.action.service.impl;

import java.awt.print.Pageable;
import java.util.Set;
import net.luoteng.action.enums.ActionType;
import net.luoteng.action.service.ActionService;
import net.luoteng.constant.GlobalConstant;
import net.luoteng.entity.embedded.RealmEntity;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

/**
 * action service
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Component
public class ActionServiceImpl implements ActionService, GlobalConstant  {

    private final static String PATTERN_KEY = "1$s:%2$s:%3$s_%4$s:%5$s";
    
    @Value("${net.luoteng.client}")
    String client;
    
    @Autowired 
    StringRedisTemplate redis;
    
    @Override
    public long redo(RealmEntity entity, String userId, ActionType type) {
        opsForZSet().add(key(entity, type), userId, DateTime.now().toDate().getTime());
        return count(entity, type);
    }

    @Override
    public long undo(RealmEntity entity, String userId, ActionType type) {
        opsForZSet().remove(key(entity, type), userId);
        return count(entity, type);
    }

    @Override
    public boolean exist(RealmEntity entity, String userId, ActionType type) {
        String key = key(entity, type);
        
        if (!redis.hasKey(key)) {
            return false;
        }
        
        return opsForZSet().score(key, userId) > 0;
    }

    @Override
    public Set<String> list(RealmEntity entity, ActionType type, Pageable pageable) {
        return opsForZSet().reverseRangeByScore(
                key(entity, type), 
                DateTime.now().toDate().getTime(), 0.0, 0, count(entity, type));
    }

    @Override
    public long count(RealmEntity entity, ActionType type) {
        return opsForZSet().zCard(key(entity, type));
    }

    private ZSetOperations opsForZSet() {
        return redis.opsForZSet();
    }
 
    private String key(RealmEntity entity, ActionType type) {
        return String.format(PATTERN_KEY, 
                GLOBAL_NAMESPACE, client, 
                entity.getRealm().name(), entity.getEntityId(), 
                type.name());
    }
    
}
