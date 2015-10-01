package tvz.gymplanner.data.repositories;

import android.content.Context;

import java.util.ArrayList;

import tvz.gymplanner.data.dao.ExerciseDao;
import tvz.gymplanner.data.entities.Exercise;
import tvz.gymplanner.data.models.ExerciseModel;

/**
 * Created by Mateo Velenik on 6.5.2015..
 */
public class ExerciseRepository extends RepositoryBase {

    private ExerciseDao exerciseDao;

    public ExerciseRepository(Context ctx) {
        super(ctx);
        this.exerciseDao = new ExerciseDao(db);
    }

    public ArrayList<ExerciseModel> getAll() {
        ArrayList<ExerciseModel> exercises = new ArrayList<ExerciseModel>();

        for (Exercise exercise : exerciseDao.getAll()) {
            ExerciseModel model = new ExerciseModel();
            model.setId(exercise.getId());
            model.setName(exercise.getName());
            model.setDescription(exercise.getDescription());
            model.setImage(exercise.getImage());
            exercises.add(model);
        }

        return exercises;
    }

    public ExerciseModel getByName(String name){
        Exercise exercise = exerciseDao.getByName(name);
        ExerciseModel model=new ExerciseModel();
        model.setId(exercise.getId());
        model.setName(exercise.getName());
        model.setDescription(exercise.getDescription());
        model.setImage(exercise.getImage());
        return model;
    }

    public ExerciseModel createNew(ExerciseModel model) {

        Exercise exists = exerciseDao.getByName(model.getName());
        if (exists != null) {
            return null;
        }

        Exercise exercise = new Exercise();

        exercise.setName(model.getName());
        exercise.setDescription(model.getDescription());
        exercise.setImage(model.getImage());

        Exercise saved = exerciseDao.save(exercise);

        if (saved != null) {
            model.setId(saved.getId());
            return model;
        }

        return null;
    }

    public ExerciseModel updateExisting(ExerciseModel model) {

        Exercise oldExercise = exerciseDao.getById(model.getId());
        Exercise existsWithSameName = exerciseDao.getByName(model.getName());

        if (oldExercise == null || (existsWithSameName != null && oldExercise.getId() != existsWithSameName.getId())) {
            return null;
        }

        oldExercise.setName(model.getName());
        oldExercise.setDescription(model.getDescription());
        oldExercise.setImage(model.getImage());
        Exercise savedExercise = exerciseDao.save(oldExercise);

        return savedExercise != null ? model : null;
    }

    public boolean delete(ExerciseModel model) {
        return exerciseDao.delete(model.getId());
    }
}
