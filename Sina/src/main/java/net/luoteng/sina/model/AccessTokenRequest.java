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

import lombok.AllArgsConstructor;
import lombok.Data;
import net.luoteng.model.AbstractObject;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Data
@AllArgsConstructor
public class AccessTokenRequest extends AbstractObject {

    /**
     * 申请应用时分配的AppKey。
     */
    private String client_id;
    
    /**
     * 申请应用时分配的AppSecret。
     */
    private String client_secret;
    
    /**
     * 请求的类型，填写authorization_code
     */
    private String grant_type;
 
    /**
     * 调用authorize获得的code值。
     */
    private String code;
    
    /**
     * 回调地址，需需与注册应用里的回调地址一致。
     */
    private String redirect_uri;
    
}
