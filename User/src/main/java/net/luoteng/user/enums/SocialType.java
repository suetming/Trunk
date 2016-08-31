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
package net.luoteng.user.enums;

import net.luoteng.enums.BaseEnum;

/**
 * social type
 * 
 * @author suetming <suetming.ma at gmail.com>
 */
public enum SocialType implements BaseEnum {

    /**
     * 用户绑定的微博
     */
    SINA_WEIBO("新浪微博"),
    
    /**
     * 用户绑定的QQ
     */
    QQ("QQ"),
    
    /**
     * 用户绑定的微信号
     */
    WEICHAT("微信"),
    
    /**
     * 用户在“指点我”公众号下的唯一标识
     */
    WEICHAT_PUBLIC("微信公众号"),
    
    /**
     * 用户绑定的支付宝账号(暂仅用于提现时使用)
     */
    ALIPAY("支付宝");

    private final String msg;

    private SocialType(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
