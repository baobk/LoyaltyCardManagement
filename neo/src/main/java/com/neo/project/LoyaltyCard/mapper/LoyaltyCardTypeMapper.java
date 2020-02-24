package com.neo.project.LoyaltyCard.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoyaltyCardTypeMapper {
    Integer selectNextLoyaltyCardType(@Param("spentThreshold")double spentThreshold);
}
