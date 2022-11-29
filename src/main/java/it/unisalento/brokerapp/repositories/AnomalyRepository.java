package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.Anomaly;

@Repository
public interface AnomalyRepository extends JpaRepository<Anomaly, Integer>{

	public Anomaly findByVectorId(int vectorId);
	
	public Anomaly findById(int id);
	
	@Query(value = "SELECT * FROM BrokerAppDb.anomaly WHERE gps_err = true", nativeQuery = true)
	public List <Anomaly> findByGpsErr();
	
	@Query(value = "SELECT * FROM BrokerAppDb.anomaly WHERE fridge_err = true", nativeQuery = true)
	public List <Anomaly> findByFridgeErr();
	
	@Query(value = "SELECT * FROM BrokerAppDb.anomaly WHERE temp_err = true", nativeQuery = true)
	public List <Anomaly> findByTempErr();
	
	@Query(value = "SELECT * FROM BrokerAppDb.anomaly WHERE tail_err = true", nativeQuery = true)
	public List <Anomaly> findByTailErr();
	
	@Query(value = "SELECT * FROM BrokerAppDb.anomaly WHERE gen1_err = true", nativeQuery = true)
	public List <Anomaly> findByGen1Err();
	
	@Query(value = "SELECT * FROM BrokerAppDb.anomaly WHERE gen2_err = true", nativeQuery = true)
	public List <Anomaly> findByGen2Err();
	
	@Query(value = "SELECT * FROM BrokerAppDb.anomaly WHERE gen3_err = true", nativeQuery = true)
	public List <Anomaly> findByGen3Err();

	@Query(value = "update BrokerAppDb.anomaly set gps_err = :val where vector_id = :vectorId", nativeQuery = true)
	@Modifying
	public int manGps (@Param(value = "val") Boolean val, @Param(value = "vectorId") int vectorId);
	
	@Query(value = "update BrokerAppDb.anomaly set fridge_err = :val where vector_id = :vectorId", nativeQuery = true)
	@Modifying
	public int manFridge (@Param(value = "val") Boolean val, @Param(value = "vectorId") int vectorId);
	
	@Query(value = "update BrokerAppDb.anomaly set temp_err = :val where vector_id = :vectorId", nativeQuery = true)
	@Modifying
	public int manTemp (@Param(value = "val") Boolean val, @Param(value = "vectorId") int vectorId);
	
	@Query(value = "update BrokerAppDb.anomaly set tail_err = :val where vector_id = :vectorId", nativeQuery = true)
	@Modifying
	public int manTail (@Param(value = "val") Boolean val, @Param(value = "vectorId") int vectorId);
	
	@Query(value = "update BrokerAppDb.anomaly set gen1_err = :val where vector_id = :vectorId", nativeQuery = true)
	@Modifying
	public int manGen1 (@Param(value = "val") Boolean val, @Param(value = "vectorId") int vectorId);
	
	@Query(value = "update BrokerAppDb.anomaly set gen2_err = :val where vector_id = :vectorId", nativeQuery = true)
	@Modifying
	public int manGen2 (@Param(value = "val") Boolean val, @Param(value = "vectorId") int vectorId);
	
	@Query(value = "update BrokerAppDb.anomaly set gen3_err = :val where vector_id = :vectorId", nativeQuery = true)
	@Modifying
	public int manGen3 (@Param(value = "val") Boolean val, @Param(value = "vectorId") int vectorId);

	@Query(value = "UPDATE BrokerAppDB.anomaly "
			+ "SET gps_err = 0, "
			+ "fridge_err = 0, "
			+ "temp_err = 0, "
			+ "tail_err = 0, "
			+ "gen1_err = 0, "
			+ "gen2_err = 0, "
			+ "gen3_err = 0 "
			+ "WHERE vector_id = ?", nativeQuery = true)
	@Modifying
	public int fixVector(int vectorId);

}
