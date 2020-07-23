/**
 * Copyright (c) 2018-2028, Chill Zhuang (smallchill@163.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.risesin.auth.provider;

import org.springframework.security.oauth2.provider.ClientDetailsService;

import java.util.List;

/**
 * 多终端注册接口
 *
 * @author honey
 */
public interface BaseIClientDetailsService extends ClientDetailsService {
    /**
     * 根据客户端id获取客户端详情
     *
     * @param clientId 客户端id
     * @return IClientDetails
     */
    @Override
    BaseIClientDetails loadClientByClientId(String clientId);

    /**
     * 获取所有客户端
     *
     * @return list
     */
    List<ApplyClientDetails> loadClients();
}
