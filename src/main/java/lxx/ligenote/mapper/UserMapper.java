package lxx.ligenote.mapper;

import lxx.ligenote.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName:UserMapper
 * Package:lxx.ligenote.mapper
 * Description:
 *
 * @Date:2019/12/11 20:30
 * @Author:857251389@qq.com
 */
@Mapper
public interface UserMapper {
    @Insert("insert into USER (name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);
    @Select("SELECT * FROM user WHERE token=#{value}")
    User searchToken(String value);
    @Select("SELECT * FROM user WHERE id=#{creator}")
    User findById(Integer creator);
}
