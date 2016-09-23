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
package net.luoteng.sina.service.impl;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import net.luoteng.enums.DataType;
import net.luoteng.enums.ResponseCode;
import net.luoteng.model.AbstractObject;
import net.luoteng.model.common.RestResponse;
import net.luoteng.sina.enums.GrantType;
import net.luoteng.sina.model.AccessToken;
import net.luoteng.sina.model.AccessTokenRequest;
import net.luoteng.sina.model.UserInfo;
import net.luoteng.sina.model.UserInfoRequest;
import net.luoteng.sina.properties.SinaProperties;
import net.luoteng.sina.service.SinaService;
import net.luoteng.utils.FormUtils;
import net.luoteng.utils.XMLUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc. All Rights Reserved.
 */
@Slf4j
@Component
public class SinaServiceImpl implements SinaService {

    /**
     * 微信企业向用户转账，需加载证书
     */
    OkHttpClient client;

    @Autowired
    SinaProperties config;

    @PostConstruct
    public void init() {
        log.info("sina service init");

        client = new OkHttpClient();
    }

    @Override
    public RestResponse getAccessToken(String code) {
        RestResponse response = new RestResponse();
        try {
            String form = FormUtils.toFormUrlEncode(
                    new AccessTokenRequest(
                            GrantType.authorization_code.name(),
                            config.getAppId(),
                            config.getAppSecret(), code, config.getUriRedirect()));
        String uri = String.format("%1$s?%2$s", config.getUriAccessToken(), form);
            return response.success(post(uri, "", DataType.JSON, AccessToken.class));
        } catch (IOException ex) {
            log.error("sina access token exception {}", ex);
            return response.error(ResponseCode.ERROR_THIRD_PLATFORM);
        }
    }

    @Override
    public RestResponse getUserInfo(AccessToken token) {
        RestResponse response = new RestResponse();
        try {
            String form = FormUtils.toFormUrlEncode(
                    new UserInfoRequest(
                            token.getAccess_token(),
                            token.getUid()));
            String uri = String.format("%1$s?%2$s", config.getUriUserInfo(), form);
            return response.success(post(uri, "", DataType.JSON, UserInfo.class));
        } catch (IOException ex) {
            log.error("sina get user info exception {}", ex);
            return response.error(ResponseCode.ERROR_THIRD_PLATFORM);
        }
    }

    private <T extends AbstractObject> T get(String url, DataType dataType, Class<T> clazz) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        okhttp3.Response response = client.newCall(request).execute();
        String plain = response.body().string();
        log.info("sina get url {}, result {}", url, plain);

        if (dataType == DataType.JSON) {
            return JSON.parseObject(plain, clazz);
        } else if (dataType == DataType.XML) {

            try {
                return XMLUtils.toObject(plain, clazz);
            } catch (JAXBException ex) {
                log.error("sina xml to object error {}", ex);
            }
        }

        return null;
    }

    private <T extends AbstractObject> T post(String url, String data, DataType dataType, Class<T> clazz) {
        try {
            RequestBody body = RequestBody.create(MediaType.parse(GLOBAL_APPLICAIONT_X_WWW_FORM_URLENCODED), data);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            okhttp3.Response response = client.newCall(request).execute();
            log.info("sina post url {}, {} result {}", url, data, response.body().string());

            switch (dataType) {
                case JSON: {
                    return JSON.parseObject(response.body().string(), clazz);
                }
                case XML: {
                    return XMLUtils.toObject(response.body().string(), clazz);
                }
            }
        } catch (JAXBException | IOException ex) {
            log.error("sina error {}", ex);
        }

        return null;
    }
    

}
