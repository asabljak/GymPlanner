package tvz.gymplanner.data.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import tvz.gymplanner.data.models.ExerciseModel;

/**
 * Created by Mateo Velenik on 12.4.2015..
 * Class used to build the SQLite database - here define the SQL that will be executed
 */
public class TrainingAppSQLiteHelper extends SQLiteOpenHelper {

    public TrainingAppSQLiteHelper(Context ctx) {
        super(ctx, "TrainingAppDb", null, 1);
    }

    private ArrayList<String> getBuildCommands() {
        ArrayList<String> cmds = new ArrayList<String>();

        cmds.add("CREATE TABLE SetsHistory (\n" +
                "    Id                INTEGER PRIMARY KEY NOT NULL,\n" +
                "    ExerciseHistoryId INTEGER NOT NULL,\n" +
                "    SetOrder          INTEGER NOT NULL,\n" +
                "    NumberOfRepeats   INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (ExerciseHistoryId) REFERENCES Exercises (Id) ON DELETE CASCADE\n" +
                ");");

        cmds.add("CREATE TABLE TrainingsExercises (\n" +
                "    Id         INTEGER PRIMARY KEY NOT NULL,\n" +
                "    TrainingId INTEGER NOT NULL,\n" +
                "    ExerciseId INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (ExerciseId) REFERENCES Exercises (Id) ON DELETE CASCADE,\n" +
                "    FOREIGN KEY (TrainingId) REFERENCES Trainings (Id) ON DELETE CASCADE\n" +
                ");");

        cmds.add("CREATE TABLE ExercisesHistory (\n" +
                "    Id                INTEGER PRIMARY KEY NOT NULL,\n" +
                "    TrainingHistoryId INTEGER NOT NULL,\n" +
                "    ExerciseId        INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (TrainingHistoryId) REFERENCES Trainings (Id) ON DELETE CASCADE,\n" +
                "    FOREIGN KEY (ExerciseId) REFERENCES Exercises (Id) ON DELETE CASCADE\n" +
                ");");

        cmds.add("CREATE TABLE Trainings (\n" +
                "    Id           INTEGER     PRIMARY KEY NOT NULL,\n" +
                "    Name         STRING (50) NOT NULL,\n" +
                "    Description  TEXT,\n" +
                "    CreationDate DATETIME\n" +
                ");");

        cmds.add("CREATE TABLE TrainingsHistory (\n" +
                "    Id           INTEGER  PRIMARY KEY NOT NULL,\n" +
                "    TrainingId   INTEGER  NOT NULL,\n" +
                "    TrainingDate DATETIME NOT NULL,\n" +
                "    FOREIGN KEY (TrainingId) REFERENCES Trainings (Id) ON DELETE CASCADE\n" +
                ");");

        cmds.add("CREATE TABLE ExerciseSets (\n" +
                "    Id                   INTEGER PRIMARY KEY NOT NULL,\n" +
                "    TrainingsExercisesId INTEGER NOT NULL,\n" +
                "    SetOrder             INTEGER NOT NULL,\n" +
                "    NumberOfRepeats      INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (TrainingsExercisesId) REFERENCES TrainingsExercises (Id) ON DELETE CASCADE\n" +
                ");");

        cmds.add("CREATE TABLE Exercises (\n" +
                "    Id          INTEGER     PRIMARY KEY NOT NULL,\n" +
                "    Name        STRING (50) NOT NULL,\n" +
                "    Description TEXT,\n" +
                "    Image       STRING\n" +
                ");");

        return cmds;
    }

    private ArrayList<String> getDropCommands() {
        ArrayList<String> cmds = new ArrayList<String>();

        cmds.add("DROP TABLE IF EXISTS SetsHistory;");
        cmds.add("DROP TABLE IF EXISTS TrainingsExercises;");
        cmds.add("DROP TABLE IF EXISTS ExercisesHistory;");
        cmds.add("DROP TABLE IF EXISTS Trainings;");
        cmds.add("DROP TABLE IF EXISTS TrainingsHistory;");
        cmds.add("DROP TABLE IF EXISTS ExerciseSets;");
        cmds.add("DROP TABLE IF EXISTS Exercises;");

        return cmds;
    }

    private ArrayList<String> getSeedCommands() {
        ArrayList<String> cmds = new ArrayList<String>();

        cmds.add("INSERT INTO Exercises (Id, Name, Description, Image) VALUES (1, 'Bench press', 'Svi znaju sto je bench press!', 'slika_bench_press');");
        cmds.add("INSERT INTO Exercises (Id, Name, Description, Image) VALUES (2, 'Squat', 'Cucnjevi koristeci olimpijsku sipku.', 'slika_squat');");
        cmds.add("INSERT INTO Exercises (Id, Name, Description, Image) VALUES (3, 'Pull downs', 'Povlacenje na lat masini.', 'slika_pull_downs');");
        cmds.add("INSERT INTO Exercises (Id, Name, Description, Image) VALUES (4, 'Deadlift', 'Mrtvo dizanje!', 'slika_deadlift');");
        cmds.add("INSERT INTO Exercises (Id, Name, Description, Image) VALUES (5, 'Dumbbell curls', 'Vjezba za biceps koristeci utege', 'slika_dumbbell_curls');");

        cmds.add("INSERT INTO Trainings (Id, Name, Description, CreationDate) VALUES (1, 'Najbolji trening', 'Ovo je samo neki opis','\" + new Date(2011,3,18).getTime() +\"');");

        cmds.add("INSERT INTO TrainingsExercises (Id, TrainingId, ExerciseId) VALUES(1, 1, 2);");
        cmds.add("INSERT INTO TrainingsExercises (Id, TrainingId, ExerciseId) VALUES(2, 1, 4);");
        cmds.add("INSERT INTO TrainingsExercises (Id, TrainingId, ExerciseId) VALUES(3, 1, 5);");

        cmds.add("INSERT INTO ExerciseSets (Id, TrainingsExercisesId, SetOrder, NumberOfRepeats) VALUES(1, 1, 1, 12);");
        cmds.add("INSERT INTO ExerciseSets (Id, TrainingsExercisesId, SetOrder, NumberOfRepeats) VALUES(2, 1, 2, 10);");
        cmds.add("INSERT INTO ExerciseSets (Id, TrainingsExercisesId, SetOrder, NumberOfRepeats) VALUES(3, 1, 3, 8);");

        cmds.add("INSERT INTO ExerciseSets (Id, TrainingsExercisesId, SetOrder, NumberOfRepeats) VALUES(4, 2, 1, 15);");
        cmds.add("INSERT INTO ExerciseSets (Id, TrainingsExercisesId, SetOrder, NumberOfRepeats) VALUES(5, 2, 2, 15);");

        cmds.add("INSERT INTO ExerciseSets (Id, TrainingsExercisesId, SetOrder, NumberOfRepeats) VALUES(6, 3, 1, 10);");
        cmds.add("INSERT INTO ExerciseSets (Id, TrainingsExercisesId, SetOrder, NumberOfRepeats) VALUES(7, 3, 2, 10);");
        cmds.add("INSERT INTO ExerciseSets (Id, TrainingsExercisesId, SetOrder, NumberOfRepeats) VALUES(8, 3, 3, 8);");
        cmds.add("INSERT INTO ExerciseSets (Id, TrainingsExercisesId, SetOrder, NumberOfRepeats) VALUES(9, 3, 4, 6);");



        return cmds;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String command : getBuildCommands()) {
            db.execSQL(command);
        }

        for (String command : getSeedCommands()) {
            db.execSQL(command);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (String cmd : this.getDropCommands()) {
            db.execSQL(cmd);
        }
        onCreate(db);
    }
}
