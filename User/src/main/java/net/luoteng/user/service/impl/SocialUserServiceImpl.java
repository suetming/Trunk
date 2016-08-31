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

package net.luoteng.user.service.impl;

import net.luoteng.user.dao.SocialUserDAO;
import net.luoteng.user.entity.SocialUser;
import net.luoteng.user.enums.SocialType;
import net.luoteng.user.service.SocialUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Component
public class SocialUserServiceImpl implements SocialUserService {

    @Autowired
    SocialUserDAO socialUserDAO;
    
    @Override
    public SocialUser save(SocialUser model) {
        return socialUserDAO.save(model);
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SocialUser get(String id) {
        return StringUtils.isBlank(id) ? null : socialUserDAO.findOne(id);
    }

    @Override
    public SocialUser getByUser(String userId, SocialType type) {
        if (StringUtils.isBlank(userId)) return null;
        return socialUserDAO.getByUser(userId, type);
    }

}
