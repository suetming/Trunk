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

package net.luoteng.sina.service;

import net.luoteng.constant.GlobalConstant;
import net.luoteng.model.common.RestResponse;
import net.luoteng.sina.model.AccessToken;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface SinaService extends GlobalConstant {

    /**
     * 获取 access token
     * 
     * @param code
     * @return 
     */
    RestResponse getAccessToken(String code);
    
    /**
     * 获取用户信息
     * 
     * @param token
     * @return 
     */
    RestResponse getUserInfo(AccessToken token);
    
}
