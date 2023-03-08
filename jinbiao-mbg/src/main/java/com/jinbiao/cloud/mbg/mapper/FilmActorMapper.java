package com.jinbiao.cloud.mbg.mapper;

import com.jinbiao.cloud.mbg.model.FilmActor;
import com.jinbiao.cloud.mbg.model.FilmActorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FilmActorMapper {
    long countByExample(FilmActorExample example);

    int deleteByExample(FilmActorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FilmActor record);

    int insertSelective(FilmActor record);

    List<FilmActor> selectByExample(FilmActorExample example);

    FilmActor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FilmActor record, @Param("example") FilmActorExample example);

    int updateByExample(@Param("record") FilmActor record, @Param("example") FilmActorExample example);

    int updateByPrimaryKeySelective(FilmActor record);

    int updateByPrimaryKey(FilmActor record);
}