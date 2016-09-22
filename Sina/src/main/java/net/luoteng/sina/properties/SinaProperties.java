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

package net.luoteng.sina.properties;

import net.luoteng.model.AbstractObject;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * sina properties
 * 
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@ConfigurationProperties(prefix = "net.luoteng.sina")
public class SinaProperties  extends AbstractObject {

    /**
     * 公众号APPID 微信开发平台应用id
     *
     * @return
     */
    private String appId;

    /**
     * @return
     */
    private String appSecret;

    /**
     * 
     */
    private String uriRedirect;
    
    /**
     * 
     */
    private String uriAccessToken;
    
    /**
     * 
     */
    private String uriUserInfo;
    
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getUriRedirect() {
        return uriRedirect;
    }

    public void setUriRedirect(String uriRedirect) {
        this.uriRedirect = uriRedirect;
    }

    public String getUriAccessToken() {
        return uriAccessToken;
    }

    public void setUriAccessToken(String uriAccessToken) {
        this.uriAccessToken = uriAccessToken;
    }

    public String getUriUserInfo() {
        return uriUserInfo;
    }

    public void setUriUserInfo(String uriUserInfo) {
        this.uriUserInfo = uriUserInfo;
    }
    
}
