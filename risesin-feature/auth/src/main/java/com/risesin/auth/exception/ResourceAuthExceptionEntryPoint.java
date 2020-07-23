/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.risesin.auth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.constant.RisesinConstant;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author lengleng
 * @date 2019/2/1
 * 客户端异常处理
 * 1. 可以根据 AuthenticationException 不同细化异常处理
 */
@Slf4j
@Component
@AllArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {
	private final ObjectMapper objectMapper;

	@Override
	@SneakyThrows
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) {
		response.setCharacterEncoding("utf-8");
		response.setContentType(RisesinConstant.CONTENT_TYPE);
		R<String> result = new R<>();
		result.setCode(ResultCode.UN_AUTHORIZED.getCode());
		if (authException != null) {
			result.setMsg("error");
			result.setData(authException.getMessage());
		}
		response.setStatus(ResultCode.UN_AUTHORIZED.getCode());
		PrintWriter printWriter = response.getWriter();
		printWriter.append(objectMapper.writeValueAsString(result));
	}
}
