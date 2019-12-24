package lxx.ligenote.mapper;

import lxx.ligenote.dto.QuestionDTO;
import lxx.ligenote.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName:QuestionMapper
 * Package:lxx.ligenote.mapper
 * Description:
 *
 * @Date:2019/12/16 13:52
 * @Author:857251389@qq.com
 */
@Mapper
public interface QuestionMapper {

    @Insert("INSERT INTO publish (title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("SELECT * FROM publish LIMIT #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset,@Param("size") Integer size);
    @Select("SELECT COUNT(1) FROM publish")
    Integer count();
    @Select("SELECT COUNT(1) FROM publish WHERE creator=#{id}")
    Integer countByUserId(@Param("id") Integer id);
    @Select("SELECT * FROM publish WHERE creator=#{id} LIMIT #{offset},#{size}")
    List<Question> listByUserId(@Param("id") Integer id, @Param("offset") Integer offset,@Param("size") Integer size);
}
