package com.risesin.system.wrapper.system;

import com.risesin.core.tool.utils.SpringUtil;
import com.risesin.system.service.dict.DictServiceImpl;
import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.utils.BeanUtil;

import com.risesin.systemapi.system.entity.RisesinClient;
import com.risesin.systemapi.system.vo.RisesinClientVO;

/**
* 包装类,返回视图层所需的字段
*
* @author risesin
* @date 2019-12-22
*/
public class RisesinClientWrapper extends BaseEntityWrapper<RisesinClient, RisesinClientVO> {

		private static DictServiceImpl dictService;

		static {
			dictService = SpringUtil.getBean(DictServiceImpl.class);
		}

		public static RisesinClientWrapper build() {
			return new RisesinClientWrapper();
		}


		@Override
		public RisesinClientVO entityVO(RisesinClient value) {
			RisesinClientVO risesinClientVO = BeanUtil.copy(value,RisesinClientVO.class);
			return risesinClientVO;
		}
}
