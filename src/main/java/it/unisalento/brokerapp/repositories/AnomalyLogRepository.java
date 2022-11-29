package it.unisalento.brokerapp.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.AnomalyLog;

@Repository
public interface AnomalyLogRepository extends JpaRepository<AnomalyLog, Integer>{
	
	public List<AnomalyLog> findByVector(int vectorId);

	@Query (value = "SELECT * FROM brokerAppDB.anomaly_log WHERE date(date) = :date ", nativeQuery = true )
	public List<AnomalyLog> findByDate(@Param (value = "date") String date);

	@Query (value = "INSERT INTO BrokerAppDB.anomaly_log (id, action, date, type, vector) VALUES (NULL, :action, :date, :type , :vectorId )", nativeQuery = true)
	@Modifying
	public int logAnomaly(@Param (value = "action") String action, @Param (value = "date") String date, @Param (value = "type") String type, @Param (value = "vectorId") int vectorId);

	public List<AnomalyLog> findByAction(String action);

	public List<AnomalyLog> findByType(String type);

	@Query (value = "INSERT INTO BrokerAppDB.anomaly_log (id, action, date, type, vector) VALUES (NULL, :action , :date , :type , :vectorId )", nativeQuery = true)
	@Modifying
	public int logFix(@Param (value = "action") String action, @Param (value = "date") String date, @Param (value = "type") String type, @Param (value = "vectorId") int vectorId);

	@Query (value = "INSERT INTO BrokerAppDB.anomaly_log (id, action, date, type, vector) VALUES (NULL, :action , :date , :type , :vectorId )", nativeQuery = true)
	@Modifying
	public int logReport(@Param (value = "action") String action, @Param (value = "date") String date, @Param (value = "type") String type, @Param (value = "vectorId") int vectorId);

	@Query (value = "INSERT INTO BrokerAppDB.anomaly_log (id, action, date, type, vector) VALUES (NULL, :action , :date , :type , :vectorId )", nativeQuery = true)
	@Modifying
	public int logDelete(@Param (value = "action") String action, @Param (value = "date") String date, @Param (value = "type") String type, @Param (value = "vectorId") int vec);

}