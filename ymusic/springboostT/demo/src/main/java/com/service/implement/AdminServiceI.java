package com.service.implement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.adminMapper;
import com.service.AdminService;

@Service
public class AdminServiceI implements AdminService {

    @Autowired
    private adminMapper adm;


    @Override//管理员service实现
    public boolean verify(String username, String password) {
        // TODO Auto-generated method stub
        return (adm.verify(username, password)>0);
    }
    
}
