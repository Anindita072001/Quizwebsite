package com.webquiz.repository;

import org.springframework.data.repository.CrudRepository;

import com.webquiz.entity.Score;

public interface ScoreRepository extends CrudRepository<Score, Integer>{

}
