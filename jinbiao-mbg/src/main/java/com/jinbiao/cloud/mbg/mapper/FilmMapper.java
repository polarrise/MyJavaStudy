package com.jinbiao.cloud.mbg.mapper;

import com.jinbiao.cloud.mbg.model.Film;
import com.jinbiao.cloud.mbg.model.FilmExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FilmMapper {
    long countByExample(FilmExample example);

    int deleteByExample(FilmExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Film record);

    int insertSelective(Film record);

    List<Film> selectByExample(FilmExample example);

    Film selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Film record, @Param("example") FilmExample example);

    int updateByExample(@Param("record") Film record, @Param("example") FilmExample example);

    int updateByPrimaryKeySelective(Film record);

    int updateByPrimaryKey(Film record);
}