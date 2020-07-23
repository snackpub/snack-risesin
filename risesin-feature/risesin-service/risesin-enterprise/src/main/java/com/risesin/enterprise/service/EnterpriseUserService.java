package com.risesin.enterprise.service;

import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.enterprise.dao.EnterpriseUserDao;
import com.risesin.user.entity.EnterpriseUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * enterpriseUser服务层
 *
 * @author Administrator
 */
@Service
@Transactional
public class EnterpriseUserService {

    @Autowired
    private EnterpriseUserDao enterpriseUserDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${risesin-properties.sms.prefix}")
    private String prefix;

    @Value("${risesin-properties.sms.expireTime}")
    private String expireTime;


    /**
     * 查询全部列表
     *
     * @return
     */

    public List<EnterpriseUser> findAll() {
        return enterpriseUserDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<EnterpriseUser> findSearch(Map whereMap, int page, int size) {
        Specification<EnterpriseUser> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return enterpriseUserDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<EnterpriseUser> findSearch(Map whereMap) {
        Specification<EnterpriseUser> specification = createSpecification(whereMap);
        return enterpriseUserDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public EnterpriseUser findById(Integer pkId) {

        return SqlHelper.optional(enterpriseUserDao.findById(pkId));
    }

    /**
     * 增加
     *
     * @param enterpriseUser
     */
    public void add(EnterpriseUser enterpriseUser) {
        enterpriseUserDao.save(enterpriseUser);
    }

    /**
     * 修改
     *
     * @param enterpriseUser
     */
    public void update(EnterpriseUser enterpriseUser) {
        enterpriseUserDao.update(enterpriseUser, enterpriseUser.getId());
    }


    /**
     * 发送短信验证码
     *
     * @param mobile 11位手机号
     */
    public void sendSms(String mobile) {
        String code = RandomStringUtils.randomNumeric(6);
        //System.out.println("s = " + s);

        //生成6位短信验证码
//		Random random = new Random();
//		int max=999999;//最大数
//		int min=100000;//最小数
//		int code = random.nextInt(max);//随机生成
//		if(code < min) {
//			code = code + min;
//		}
        //System.out.println(mobile+"收到验证码是："+code);
        //redisTemplate.opsForValue().set(prefix + mobile, String.valueOf(code), Long.parseLong(expireTime), TimeUnit.MINUTES);
        Map<String, String> map = new HashMap();
        map.put("mobile", mobile);
        map.put("checkCode", String.valueOf(code));
        rabbitTemplate.convertAndSend("sms", map);
    }

    /**
     * 用户登录或注册
     *
     * @param mobile
     * @param checkCode 用户输入的登录验证码
     */
    public EnterpriseUser loginOrResgiter(String mobile, String checkCode) {
        //String phone = enterpriseUser.getPhone();
        //从缓存中获取验证码与用户输入验证码进行比对
        if (checkCode.equals(redisTemplate.opsForValue().get(prefix + mobile))) {
            EnterpriseUser user = enterpriseUserDao.findByPhoneAndStatus(mobile, (byte)0);
            if (null == user) {
                EnterpriseUser enterpriseUser = new EnterpriseUser();
                enterpriseUser.setPhone(mobile);
                //不存在用户，注册用户
                user = enterpriseUserDao.save(enterpriseUser);
            }
            return user;
        }
        return null;
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<EnterpriseUser> createSpecification(Map searchMap) {

        return new Specification<EnterpriseUser>() {

            @Override
            public Predicate toPredicate(Root<EnterpriseUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 真实姓名
                if (searchMap.get("fullName") != null && !"".equals(searchMap.get("fullName"))) {
                    predicateList.add(cb.like(root.get("fullName").as(String.class), "%" + (String) searchMap.get("fullName") + "%"));
                }
                // 手机号
                if (searchMap.get("phone") != null && !"".equals(searchMap.get("phone"))) {
                    predicateList.add(cb.like(root.get("phone").as(String.class), "%" + (String) searchMap.get("phone") + "%"));
                }
                // 客户来源
                if (searchMap.get("source") != null && !"".equals(searchMap.get("source"))) {
                    predicateList.add(cb.like(root.get("source").as(String.class), "%" + (String) searchMap.get("source") + "%"));
                }
                // 工作单位
                if (searchMap.get("companyName") != null && !"".equals(searchMap.get("companyName"))) {
                    predicateList.add(cb.like(root.get("companyName").as(String.class), "%" + (String) searchMap.get("companyName") + "%"));
                }
                // 联系电话
                if (searchMap.get("contactNumber") != null && !"".equals(searchMap.get("contactNumber"))) {
                    predicateList.add(cb.like(root.get("contactNumber").as(String.class), "%" + (String) searchMap.get("contactNumber") + "%"));
                }
                // 所在地区
                if (searchMap.get("area") != null && !"".equals(searchMap.get("area"))) {
                    predicateList.add(cb.like(root.get("area").as(String.class), "%" + (String) searchMap.get("area") + "%"));
                }
                // 街道地址
                if (searchMap.get("address") != null && !"".equals(searchMap.get("address"))) {
                    predicateList.add(cb.like(root.get("address").as(String.class), "%" + (String) searchMap.get("address") + "%"));
                }
                // 传真
                if (searchMap.get("fax") != null && !"".equals(searchMap.get("fax"))) {
                    predicateList.add(cb.like(root.get("fax").as(String.class), "%" + (String) searchMap.get("fax") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
