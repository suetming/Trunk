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

package net.luoteng.payment.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@ConfigurationProperties(prefix = "net.luoteng.payment.wechat.native")
public class WechatNativeProperties {

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
     * 链接超时限制
     * <p>
     * 毫秒数
     *
     * @return
     */
    private int connectionTimeout;

    /**
     * 读取数据超时限制
     * <p>
     * 毫秒数
     *
     * @return
     */
    private int readTimeout;

    /**
     * 预支付交易单调用地址
     *
     * @return
     */
    private String uriPrepay;

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

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String getUriPrepay() {
        return uriPrepay;
    }

    public void setUriPrepay(String uriPrepay) {
        this.uriPrepay = uriPrepay;
    }
    
}
