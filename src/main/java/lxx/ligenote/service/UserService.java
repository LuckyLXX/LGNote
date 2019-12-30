package lxx.ligenote.service;

import lxx.ligenote.mapper.UserMapper;
import lxx.ligenote.model.User;
import lxx.ligenote.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * ClassName:UserService
 * Package:lxx.ligenote.service
 * Description:
 *
 * @Date:2019/12/25 10:06
 * @Author:857251389@qq.com
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(example);
        if(users.size()==0){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            User dbuser = users.get(0);
            User upDateUser = new User();
            upDateUser.setGmtModified(System.currentTimeMillis());
            upDateUser.setAvatarUrl(user.getAvatarUrl());
            upDateUser.setName(user.getName());
            upDateUser.setToken(user.getToken());
            example.createCriteria().andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(upDateUser,example);
        }
    }
}
