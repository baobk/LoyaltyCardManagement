package com.neo.project.LoyaltyCard.respository;

import com.neo.project.LoyaltyCard.pojo.LoyaltyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard,Integer> {
}
