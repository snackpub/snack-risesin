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

import com.risesin.core.secure.constant.SecureConstant;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获取客户端详情authenticationSuccessHandler
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class BaseClientDetailsServiceImpl implements BaseIClientDetailsService {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public BaseIClientDetails loadClientByClientId(String clientId) {
        try {
            return jdbcTemplate.queryForObject(SecureConstant.DEFAULT_SELECT_STATEMENT, new String[]{clientId}, new BeanPropertyRowMapper<>(ApplyClientDetails.class));
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<ApplyClientDetails> loadClients() {

        try {
            return jdbcTemplate.query(SecureConstant.BASE_STATEMENT, new String[]{}, new BeanPropertyRowMapper<>(ApplyClientDetails.class));
        } catch (Exception ex) {
            return null;
        }
    }
}
