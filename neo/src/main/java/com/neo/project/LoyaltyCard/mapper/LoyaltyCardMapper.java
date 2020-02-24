package com.neo.project.LoyaltyCard.mapper;

import com.neo.project.LoyaltyCard.pojo.LoyaltyCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LoyaltyCardMapper {
    LoyaltyCard selectById(@Param("id") int id);
    List<LoyaltyCard> selectAll();
}
