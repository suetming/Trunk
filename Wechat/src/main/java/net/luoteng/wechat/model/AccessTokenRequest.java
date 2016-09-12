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

package net.luoteng.wechat.model;

import net.luoteng.model.AbstractObject;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public class AccessTokenRequest extends AbstractObject {

    /**
     * 获取access_token填写client_credential
     */
    private String grant_type;
    
    /**
     * 第三方用户唯一凭证
     */
    private String appid;
    
    /**
     * 第三方用户唯一凭证密钥，即appsecret
     */
    private String secret;
    
}
