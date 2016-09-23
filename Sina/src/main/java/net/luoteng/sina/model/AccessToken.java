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

package net.luoteng.sina.model;

import lombok.Data;
import net.luoteng.model.AbstractObject;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Data
public class AccessToken extends AbstractObject {

    /**
     * 用户授权的唯一票据，用于调用微博的开放接口，
     * 同时也是第三方应用验证微博用户登录的唯一票据，
     * 第三方应用应该用该票据和自己应用内的用户建立
     * 唯一影射关系，来识别登录状态，不能使用本返回
     * 值里的UID字段来做登录识别
     */
    private String access_token;
    
    /**
     * access_token的生命周期，单位是秒数。
     */
    private long expires_in;
    
    /**
     * access_token的生命周期（该参数即将废弃，开发者请使用expires_in）。
     */
    private long remind_in;
    
    /**
     * 授权用户的UID，本字段只是为了方便开发者，
     * 减少一次user/show接口调用而返回的，第三
     * 方应用不能用此字段作为用户登录状态的识别，
     * 只有access_token才是用户授权的唯一票据。
     */
    private String uid;
    
}
