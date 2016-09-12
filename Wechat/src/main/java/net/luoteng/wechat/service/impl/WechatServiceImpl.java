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
import lombok.extern.slf4j.Slf4j;
import net.luoteng.constant.GlobalConstant;
import net.luoteng.model.common.RestResponse;
import net.luoteng.utils.FormUtils;
import net.luoteng.wechat.properties.WechatNativeProperties;
import net.luoteng.wechat.properties.WechatProperties;
import net.luoteng.wechat.properties.WechatPublicProperties;
import net.luoteng.wechat.service.WechatService;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Slf4j
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
    
//    public RestResponse getAccessToken() {
//        FormUtils.toFormUrlEncode(client);
//        String url = String.format("%1$s?%2$s", args);
//    }
    
    private String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        okhttp3.Response response = client.newCall(request).execute();
        String result = response.body().string();
        log.info("wechat get url {}, result {}", url, result);
        return result;
    }
    
}
