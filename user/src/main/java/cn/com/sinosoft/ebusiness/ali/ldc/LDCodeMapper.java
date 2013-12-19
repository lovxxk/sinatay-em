package cn.com.sinosoft.ebusiness.ali.ldc;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository(value="ldcodeMapper")
public interface LDCodeMapper {
    int countByExample(LDCodeExample example);

    int deleteByExample(LDCodeExample example);

    int deleteByPrimaryKey(LDCodeKey key);

    int insert(LDCode record);

    int insertSelective(LDCode record);

    List<LDCode> selectByExample(LDCodeExample example);

    LDCode selectByPrimaryKey(LDCodeKey key);

//    int updateByExampleSelective(@Param("record") LDCode record, @Param("example") LDCodeExample example);
//
//    int updateByExample(@Param("record") LDCode record, @Param("example") LDCodeExample example);

    int updateByPrimaryKeySelective(LDCode record);

    int updateByPrimaryKey(LDCode record);
}