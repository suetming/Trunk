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

import java.util.Date;
import lombok.Data;
import net.luoteng.model.AbstractObject;

/**
 *
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc. All Rights Reserved.
 */
@Data
public class UserInfo extends AbstractObject {

    private String uid;

    private String screen_name;

    private String name;

    private String province;

    private String city;

    private String location;

    private String description;

    private String url;

    private String profile_image_url;

    private String domain;

    /**
     * 性别，m：男、f：女、n：未知
     */
    private String gender;

    private int followers_count;

    private int friends_count;

    private int statuses_count;

    private int favourites_count;

    private Date created_at;

    private boolean following;

    private boolean allow_all_act_msg;

    private boolean geo_enabled;

    private boolean verified;

    private boolean allow_all_comment;

    private String avatar_large;

    private String verified_reason;

    private boolean follow_me;

    private int online_status;

    private int bi_followers_count;

}
