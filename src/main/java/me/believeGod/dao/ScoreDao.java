package me.believeGod.dao;

import me.believeGod.entity.Score;
import me.believeGod.entity.ScoreKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ScoreDao extends JpaRepository<Score, ScoreKey>, JpaSpecificationExecutor<Score> {
}
