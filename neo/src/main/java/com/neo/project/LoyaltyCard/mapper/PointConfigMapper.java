package com.neo.project.LoyaltyCard.mapper;

import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PointConfigMapper {
    int update(final int point);
    int checkConfig();
    int insert(int point);
    int selectConfig();
}
