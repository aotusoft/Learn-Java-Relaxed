package cn.book.service.impl;

import cn.book.mapper.AdminMapper;
import cn.book.pojo.Admin;
import cn.book.pojo.AdminExample;
import cn.book.service.AdminService;

import cn.book.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String name, String pwd) {

        AdminExample example = new AdminExample();
        /**
         * 添加条件
         * select * from admin where a_name='admin'
         */
        //添加用户名
        example.createCriteria().andANameEqualTo(name);

        List<Admin> list = adminMapper.selectByExample(example);
        if (list.size() > 0){
            Admin admin = list.get(0);
            //进行密码比对，密码是密文
            String miPwd = MD5Util.getMD5(pwd);
            if (miPwd.equals(admin.getaPass())){
                return admin;
            }
        }
        return null;
    }
}
