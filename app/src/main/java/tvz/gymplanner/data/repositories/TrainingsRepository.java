package tvz.gymplanner.data.repositories;

import android.content.Context;

import java.util.ArrayList;

import tvz.gymplanner.data.dao.ExerciseDao;
import tvz.gymplanner.data.dao.ExerciseSetsDao;
import tvz.gymplanner.data.dao.TrainingDao;
import tvz.gymplanner.data.dao.TrainingsExercisesDao;
import tvz.gymplanner.data.entities.Exercise;
import tvz.gymplanner.data.entities.ExerciseSets;
import tvz.gymplanner.data.entities.Training;
import tvz.gymplanner.data.entities.TrainingsExercises;
import tvz.gymplanner.data.models.ExerciseModel;
import tvz.gymplanner.data.models.ExerciseSetModel;
import tvz.gymplanner.data.models.TrainingExerciseModel;
import tvz.gymplanner.data.models.TrainingModel;

/**
 * Created by Mateo Velenik on 22.4.2015..
 */
public class TrainingsRepository extends RepositoryBase {
    private TrainingDao trainingDao;
    private TrainingsExercisesDao trainingsExercisesDao;
    private ExerciseSetsDao exerciseSetsDao;
    private ExerciseDao exerciseDao;

    public TrainingsRepository(Context ctx) {
        super(ctx);
        this.trainingDao = new TrainingDao(db);
        this.trainingsExercisesDao = new TrainingsExercisesDao(db);
        this.exerciseSetsDao = new ExerciseSetsDao(db);
        this.exerciseDao = new ExerciseDao(db);
    }

    public ArrayList<TrainingModel> getAll() {
        ArrayList<TrainingModel> allTrainings = new ArrayList<TrainingModel>();
        ArrayList<Training> trainings = trainingDao.getAll();

        for (Training training : trainings) {
            allTrainings.add(getById(training.getId()));
        }

        return allTrainings;
    }

    public TrainingModel getById(int trainingId) {
        Training training = trainingDao.getById(trainingId);

        if (training == null) {
            return null;
        }

        TrainingModel model = new TrainingModel();
        model.setId(training.getId());
        model.setName(training.getName());
        model.setDescription(training.getDescription());
        model.setDateCreated(training.getDateCreated());

        ArrayList<TrainingsExercises> trainingsExercises = trainingsExercisesDao.getAllForTraining(trainingId);

        for (TrainingsExercises trainingExercise : trainingsExercises) {
            ExerciseModel exerciseModel = new ExerciseModel();
            exerciseModel.setId(trainingExercise.getId());

            Exercise exercise = exerciseDao.getById(trainingExercise.getExerciseId());
            exerciseModel.setName(exercise.getName());
            exerciseModel.setDescription(exercise.getDescription());
            exerciseModel.setImage(exercise.getImage());

            ArrayList<ExerciseSets> exerciseSets = exerciseSetsDao.getAllForExercise(trainingExercise.getId());

            for (ExerciseSets exerciseSet : exerciseSets) {
                ExerciseSetModel setModel = new ExerciseSetModel();
                setModel.setId(exerciseSet.getId());
                setModel.setNumberOfRepeats(exerciseSet.getNumberOfRepeats());
                setModel.setOrder(exerciseSet.getOrder());
                exerciseModel.addSet(setModel);
            }
            model.addExercise(exerciseModel);
        }

        return model;
    }

    public TrainingModel getByName(String trainingName){
        Training foundTraining = trainingDao.getByName(trainingName);
        if(foundTraining!=null){
            return getById(foundTraining.getId());
        }
        return null;
    }

    public TrainingModel createNew(TrainingModel model) {
        Training exists = trainingDao.getByName(model.getName());

        if (exists != null) {
            return null;
        }

        Training training = new Training();
        training.setName(model.getName());
        training.setDescription(model.getDescription());
        training.setDateCreated(model.getDateCreated());

        Training savedTraining = trainingDao.save(training);

        if (savedTraining != null) {
            model.setId(savedTraining.getId());
            return model;
        }

        return null;
    }

    public TrainingModel updateExisting(TrainingModel model) {

        Training oldTraining = trainingDao.getById(model.getId());
        Training existsWithSameName = trainingDao.getByName(model.getName());

        if (oldTraining == null || existsWithSameName != null) {
            return null;
        }

        oldTraining.setDescription(model.getDescription());
        Training saved = trainingDao.save(oldTraining);
        return saved != null ? model : null;
    }

    public boolean delete(TrainingModel model) {

        ArrayList<TrainingsExercises> allExercises = trainingsExercisesDao.getAllForTraining(model.getId());

        for (TrainingsExercises exercise : allExercises) {
            ArrayList<ExerciseSets> exerciseSets = exerciseSetsDao.getAllForExercise(exercise.getId());
            for (ExerciseSets exerciseSet : exerciseSets) {
                exerciseSetsDao.delete(exerciseSet.getId());
            }
            trainingsExercisesDao.delete(exercise.getId());
        }

        return trainingDao.delete(model.getId());
    }

    public TrainingExerciseModel addExerciseTo(TrainingModel training, ExerciseModel exercise) {

        TrainingsExercises trainingExercise = new TrainingsExercises();
        trainingExercise.setTrainingId(training.getId());
        trainingExercise.setExerciseId(exercise.getId());

        TrainingsExercises saved = trainingsExercisesDao.save(trainingExercise);

        if (saved != null) {
            TrainingExerciseModel model = new TrainingExerciseModel();
            model.setId(saved.getId());
            model.setTrainingId(saved.getTrainingId());
            model.setExerciseId(saved.getExerciseId());
            return model;
        }

        return null;
    }

    public boolean deleteExercise(TrainingExerciseModel model) {
        ArrayList<ExerciseSets> exerciseSets = exerciseSetsDao.getAllForExercise(model.getId());

        for (ExerciseSets set : exerciseSets) {
            exerciseSetsDao.delete(set.getId());
        }
        return trainingsExercisesDao.delete(model.getId());
    }

    public ExerciseSetModel addSetTo(TrainingExerciseModel model, int numberOfRepeats) {

        ArrayList<ExerciseSets> existingSets = exerciseSetsDao.getAllForExercise(model.getId());

        Exercise origEx = exerciseDao.getById(model.getExerciseId());
        Training origTr = trainingDao.getById(model.getTrainingId());


        ExerciseSets set = new ExerciseSets();
        set.setNumberOfRepeats(numberOfRepeats);
        set.setTrainingsExercisesId(model.getId());
        set.setOrder(existingSets.size() + 1);

        ExerciseSets saved = exerciseSetsDao.save(set);

        if (saved != null) {
            ExerciseSetModel exerciseSetModel = new ExerciseSetModel();
            exerciseSetModel.setId(saved.getId());
            exerciseSetModel.setNumberOfRepeats(saved.getNumberOfRepeats());
            exerciseSetModel.setOrder(saved.getOrder());
            exerciseSetModel.setTrainingsExercisesId(saved.getTrainingsExercisesId());
            return exerciseSetModel;
        }

        return null;
    }

    public boolean deleteLastSetFrom(TrainingExerciseModel model) {
        ArrayList<ExerciseSets> exerciseSets = exerciseSetsDao.getAllForExercise(model.getId());

        if (exerciseSets.size() < 1) {
            return false;
        }

        ExerciseSets lastSet = exerciseSets.get(0);

        for (ExerciseSets exerciseSet : exerciseSets) {
            if (lastSet.getOrder() < exerciseSet.getOrder()) {
                lastSet = exerciseSet;
            }
        }

        return exerciseSetsDao.delete(lastSet.getId());
    }
}
