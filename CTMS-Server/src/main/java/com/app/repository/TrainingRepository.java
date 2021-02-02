package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.dto.TrainingDetails;
import com.app.pojos.Training;

public interface TrainingRepository extends JpaRepository<Training, Integer>{
	
	@Query("select new com.app.dto.TrainingDetails( training,technology,trainer,user) from Training training,Technology"
		+ " technology,Trainer trainer,User user where training.trainer_id=trainer.trainer_id "
		+ "and training.technology_id=technology.technology_id"
			+ " and training.empid=user.empid "
			+ "and training.project_mgr_status='requested' and training.traning_cor_status='requested'")
	List<TrainingDetails> getTrainingDetails();

	@Query("select new com.app.dto.TrainingDetails( training,technology,trainer,user) from Training training,Technology"
		+ " technology,Trainer trainer,User user where training.trainer_id=trainer.trainer_id "
		+ "and training.technology_id=technology.technology_id"
			+ " and training.empid=user.empid "
			
			+ " and training.id=:id")
	TrainingDetails getTrainingDetailsById(int id);
	@Query("select new com.app.dto.TrainingDetails( training,technology,trainer,user) from Training training,Technology"
			+ " technology,Trainer trainer,User user where (training.trainer_id=trainer.trainer_id "
			+ "and training.technology_id=technology.technology_id"
				+ " and training.empid=user.empid) "
				+ "and (training.project_mgr_status='requested' and training.traning_cor_status='requested' or "
				+ "training.project_mgr_status='updated' and training.traning_cor_status='updated') "
				+ "and (training.empid=:empid)")
	List<TrainingDetails> getPendingTrainingDetails(int empid);
	
	
	
	@Query("select new com.app.dto.TrainingDetails( training,technology,trainer,user) from Training training,Technology"
			+ " technology,Trainer trainer,User user where (training.trainer_id=trainer.trainer_id "
			+ "and training.technology_id=technology.technology_id"
				+ " and training.empid=user.empid) "
				+ "and (training.project_mgr_status='approved' or training.traning_cor_status='approved' or "
				+ "training.project_mgr_status='rejected' or training.traning_cor_status='rejected') "
				+ "and (training.empid=:empid) order by training.id")
	List<TrainingDetails> getTrainingHistory(int empid);
	
	
	@Query("select new com.app.dto.TrainingDetails( training,technology,trainer,user) from Training training,Technology"
			+ " technology,Trainer trainer,User user where (training.trainer_id=trainer.trainer_id "
			+ "and training.technology_id=technology.technology_id"
				+ " and training.empid=user.empid) "
				+ "and (training.project_mgr_status='approved' or training.traning_cor_status='approved' or "
				+ "training.project_mgr_status='rejected' or training.traning_cor_status='rejected') order by training.id"
				)
	List<TrainingDetails> getTrainingHistory();
}
