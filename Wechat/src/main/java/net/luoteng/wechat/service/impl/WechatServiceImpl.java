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

package net.luoteng.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import net.luoteng.constant.GlobalConstant;
import net.luoteng.enums.DataType;
import net.luoteng.enums.ResponseCode;
import net.luoteng.model.AbstractObject;
import net.luoteng.model.common.RestResponse;
import net.luoteng.utils.FormUtils;
import net.luoteng.utils.XMLUtils;
import net.luoteng.wechat.enums.GrantType;
import net.luoteng.wechat.model.AccessToken;
import net.luoteng.wechat.model.AccessTokenRequest;
import net.luoteng.wechat.model.UserInfo;
import net.luoteng.wechat.model.UserInfoRequest;
import net.luoteng.wechat.properties.WechatNativeProperties;
import net.luoteng.wechat.properties.WechatProperties;
import net.luoteng.wechat.properties.WechatPublicProperties;
import net.luoteng.wechat.service.WechatService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Slf4j
@Component
public class WechatServiceImpl implements WechatService, GlobalConstant {

    /**
     * 微信企业向用户转账，需加载证书
     */
    OkHttpClient client;
    
    @Autowired
    WechatProperties wechatConfig;

    @Autowired
    WechatPublicProperties wechatPublicConfig;

    @Autowired
    WechatNativeProperties wechatNativeConfig;
    
    @PostConstruct
    public void init() {
        log.info("wechat service init");

        client = new OkHttpClient();
        try (FileInputStream instream = new FileInputStream(new File(wechatConfig.getPathLocalCert()))) {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");

            keyStore.load(instream, wechatConfig.getCertPassword().toCharArray());

            Set<KeyManager> keymanagers = new LinkedHashSet();
            KeyManagerFactory kmfactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmfactory.init(keyStore, wechatConfig.getCertPassword().toCharArray());
            KeyManager[] kms = kmfactory.getKeyManagers();
            if (kms != null) {
                keymanagers.addAll(Arrays.asList(kms));
            }

            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(!keymanagers.isEmpty() ? (KeyManager[]) keymanagers.toArray(new KeyManager[keymanagers.size()]) : null,
                    null,
                    new SecureRandom());

            client = new OkHttpClient.Builder().sslSocketFactory(sslcontext.getSocketFactory()).build();

            log.info("wechat service init successed");
        } catch (KeyStoreException | FileNotFoundException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException ex) {
            log.error("wechat service something is wrong when load wechat cert {}", ex);
        } catch (IOException | java.security.cert.CertificateException ex) {
            log.error("wechat service is wrong when load wechat cert {}", ex);
        }
    }
    
    @Override
    public RestResponse getAccessToken(String code) {
        RestResponse response = new RestResponse();
        try {
            String form = FormUtils.toFormUrlEncode(
                    new AccessTokenRequest(
                            GrantType.client_credential.name(),
                            wechatConfig.getAppId(), 
                            wechatConfig.getAppSecret(), code));
            String url = String.format("%1$s?%2$s", wechatConfig.getUriAccessToken(), form);
            return response.success(get(url, DataType.JSON, AccessToken.class));
        } catch (IOException ex) {
            log.error("wechat access token exception {}", ex);
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
                            token.getOpenid()));
            String url = String.format("%1$s?%2$s", wechatConfig.getUriUserInfo(), form);
            return response.success(get(url, DataType.JSON, UserInfo.class));
        } catch (IOException ex) {
            log.error("wechat get user info exception {}", ex);
            return response.error(ResponseCode.ERROR_THIRD_PLATFORM);
        }
    }
    
    private <T extends AbstractObject> T get(String url, DataType dataType, Class<T> clazz) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        okhttp3.Response response = client.newCall(request).execute();
        String plain = response.body().string();
        log.info("wechat get url {}, result {}", url, plain);
        
        if (dataType == DataType.JSON) {
            return JSON.parseObject(plain, clazz);
        } else if (dataType == DataType.XML) {
            
            try {
                return XMLUtils.toObject(plain, clazz);
            } catch (JAXBException ex) {
                log.error("wechat xml to object error {}", ex);
            }
        }
        
        return null;
    }
    
}
