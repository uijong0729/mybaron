package org.interior.repository.riotapi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionDatabaseRepository extends JpaRepository<ChampionDatabase, Integer>{
	ChampionDatabase findByName(String name);
	ChampionDatabase findByIndividualKey(int key);
	ChampionDatabase findByVersion(String version);
}
