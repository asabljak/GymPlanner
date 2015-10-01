package tvz.gymplanner.data.seed;

        import android.content.Context;

        import tvz.gymplanner.data.models.ExerciseModel;
        import tvz.gymplanner.data.models.TrainingModel;
        import tvz.gymplanner.data.repositories.ExerciseRepository;
        import tvz.gymplanner.data.repositories.TrainingsRepository;

/**
 * Created by Mateo Velenik on 8.6.2015..
 */
public class SeedData {

    public static void Seed(Context context){

        ExerciseRepository exRepo = new ExerciseRepository(context);

        exRepo.createNew(new ExerciseModel("BICEPS CURL", "noge sirine ramena, ravna leda, drzati sipku ispred sebe ispruzeno prema dolje, hvat sirine ramena, laktovi uvuceni", "SLIKA"));
        exRepo.createNew(new ExerciseModel("CONCENTRATION CURL", "u sjedećem polozaju staviti jednorucni uteg ispred sebe, straznju stranu ruke nasloniti na bedro - podizati uteg do ramena, zadrzati i vratiti dolje", "SLIKA"));
        exRepo.createNew(new ExerciseModel("HAMMER CURL", "u stajanju, noge sirine ramena, utezi u rukama uz tijelo, ruke ispruzene - podizati ravno gore do ramena, zadrzati te vratiti dolje", "SLIKA"));
        exRepo.createNew(new ExerciseModel("INCLINE CURL", "na kosoj klupi (45 kut) sjesti sa rukama ispruzenima prema dolje, utezi u rukama, prsti prema naprijed - dizati gore, drzeći laktove nazad, zadrzati i vratiti u pocetni polozaj", "SLIKA"));
        exRepo.createNew(new ExerciseModel("INCLINE HAMMER CURL", "na kosoj klupi (45 kut) sjesti sa rukama ispruzenima prema dolje, utezi u rukama, prsti prema tijelu - dizati gore, drzeći laktove nazad, zadrzati i vratiti u pocetni polozaj", "SLIKA"));
        exRepo.createNew(new ExerciseModel("UNDERHAND PULL UP", "stajati ispod sipke i primiti je u sirini ramena sa prstima prema sebi - podizanje gore dok brada ne prede sipku i lagano se vratiti nazad", "SLIKA"));
        exRepo.createNew(new ExerciseModel("TRICEP DIP", "zahvatiti sipke u sirini ramena , ravno ruke - spustati se lagano dolje do ravnine 90 stupnjeva tijela sa podlakticama i nazad gore", "SLIKA"));
        exRepo.createNew(new ExerciseModel("BENCH DIP", "ispruzeno tijelo ispred klupe, drzati se rukama sa straznje strane za klupu, sirina ramena - spustati se lagano do poda dok se ne osjeti potpuno istezanje te povratak gore", "SLIKA"));
        exRepo.createNew(new ExerciseModel("ROPE PUSH DOWN", "primiti sipku/uze rukama u visini ramena, dlanovi okrenuti jedni prema drugima, laktovi do tijela - povlaciti dolje dok se ruke ne ispruze te lagano vraćati natrag", "SLIKA"));
        exRepo.createNew(new ExerciseModel("BENCH PRESS", "primiti sipku malo sire od sirine ramena, laktovi uz tijelo - spustati dolje dok ne takne prsa, paziti da laktovi ne predu 45 stupnjeva i vratiti nazad", "SLIKA"));
        exRepo.createNew(new ExerciseModel("INCLINE BENCH PRESS", "na kosoj klupi, primiti sipku malo sire od sirine ramena - spustati  dok se ne taknu prsa i lagano vratiti nazad", "SLIKA"));
        exRepo.createNew(new ExerciseModel("INCLINE FLY", "na kosoj klupi primiti u svaku ruku uteg  u drzati ispruzeno u vis ispred sebe, prsti okrenuti medusobno\t - lagano siriti ruke dok se ne dodu u ravninu sa prsima te vratiti nazad", "SLIKA"));
        exRepo.createNew(new ExerciseModel("TRBUSNJACI", "leći na pod, noge skvrcene u koljenu, stopala na podu - lagano podizati gornji dio tijela, dok se ne osjeti zatezanje, te povratak dolje", "SLIKA"));
        exRepo.createNew(new ExerciseModel("TRBUSNJACI - NOGE", "leći na pod, ruke pored tijela - podizati ispruzene noge u vis, te lagano vraćati nazad", "SLIKA"));
        exRepo.createNew(new ExerciseModel("SKLEKOVI", "osloniti se na podu na ruke i vrhove stopala, tijelo posve ravno, dlanove spojiti u sredini tijela - spustati se do poda i podizati nazad gore", "SLIKA"));
    }
}
