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

package net.luoteng.config.service.impl;

import com.alibaba.fastjson.JSON;
import net.luoteng.config.dao.ConfigDAO;
import net.luoteng.config.entity.Config;
import net.luoteng.config.model.AbstractConfig;
import net.luoteng.config.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Component
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    ConfigDAO configDAO;
    
    @Override
    public <T extends AbstractConfig> T load(Class<T> clazz) {
        Config entity = configDAO.get(clazz.getSimpleName(), clazz.getPackage().getName());
        if (entity == null) return null;

        return JSON.parseObject(entity.getPriv(), clazz);
    }

    @Override
    public <T extends AbstractConfig> T save(T entity) {
        
        Config config = configDAO.get(entity.getClass().getSimpleName(), entity.getClass().getPackage().getName());
        if (config == null) {
            config = new Config(
                entity.getClass().getSimpleName(), 
                entity.getClass().getPackage().getName(), 
                JSON.toJSONString(entity));
            configDAO.save(config);
        } else {
            config.setPriv(JSON.toJSONString(entity));
            configDAO.save(config);
        }
        
        return entity;
    }
    
}
