package com.risesin.system.service.dict;

import com.risesin.systemapi.dict.vo.INode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-09
 * @DESCRIPTION 树 暂放这里(明天移除)
 * @since 1.0.0
 */
public class TreeUtils<T extends INode> {


    public List merge(List<T> list) {
        //再创建一个List用来存放最终的树状结构数据
        List<Map<String, Object>> MenuList = new ArrayList<Map<String, Object>>();

        //把从数据库查询出来的数据遍历处理
        for (T vo : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            //通过判断，0代表没有父级，也就是一级
            if ("0".equals(vo.getParentCode())) {
                map.put("code", vo.getCode());
                map.put("parentCode", vo.getParentCode());
                map.put("name", vo.getName());
                map.put("status", vo.getStatus());
                map.put("createTime", vo.getCreateTime());
                //继续往一级下面遍历，调用下面的处理方法，放入树状结构
                map.put("children", getChildren(list, vo.getCode()));
                //先把一级存入结果
                MenuList.add(map);
            }
        }
        return MenuList;
    }

    //结果集转树状结构
    private List getChildren(List<T> data, String code) {//参数为数据库的（原数据，一级id）
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (data == null || data.size() == 0 || code == null) {
            return list;
        }

        for (T vo : data) {
            Map<String, Object> map = new HashMap<String, Object>();
            //如果本级id与下面数据的父id相同，就说明是子父级关系
            if (code.equals(vo.getParentCode())) {
                map.put("code", vo.getCode());
                map.put("parentCode", vo.getParentCode());
                map.put("name", vo.getName());
                map.put("status", vo.getStatus());
                map.put("createTime", vo.getCreateTime());
                //递归，查询子级下的子级
                map.put("children", getChildren(data, vo.getCode()));
                list.add(map);
            }
        }
        return list;
    }

}
