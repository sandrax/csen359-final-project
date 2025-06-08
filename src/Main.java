import java.util.ArrayList;
import java.util.List;

import commands.*;
import equipment.*;
import equipment.diagnostics.MagicalStabilityVisitor;
import ingredients.*;
import discipline.*;
import teaching.*;
import teaching.grades.GradeCalculator;

public class Main {
    public static void main(String[] args) throws Exception {

        // TODO: PUT ACTUAL SIMULATION HERE WHEN ALL PARTS ARE COMPLETE
        // PUT DESIGN PATTERN SPECIFIC TESTS IN test/
        // so its not as cluttered

        // create the wand
        Wand wand = Wand.getInstance();

        // create the lab equipment
        Burner burner = new Burner();
        Cauldron cauldron = new Cauldron();
        StirringRod rod = new StirringRod();
        Vial vial = new Vial();

        // set up wand commands
        AddIngredientCommand addNettles = new AddIngredientCommand(cauldron, "aconite");
        AddIngredientCommand addPurifiedWater = new AddIngredientCommand(cauldron, "purified water");
        AddIngredientCommand addCrushedNewtEye = new AddIngredientCommand(cauldron, "diced kidney bean");
        BurnerOffCommand burnerOff = new BurnerOffCommand(burner, cauldron);
        SetBurnerHeatCommand burnerLow = new SetBurnerHeatCommand(burner, cauldron, Burner.HeatLevel.LOW);
        SetBurnerHeatCommand burnerMed = new SetBurnerHeatCommand(burner, cauldron, Burner.HeatLevel.MEDIUM);
        SetBurnerHeatCommand burnerHigh = new SetBurnerHeatCommand(burner, cauldron, Burner.HeatLevel.HIGH);
        StirCommand stirClockwise = new StirCommand(rod, StirringRod.Direction.CLOCKWISE, 5);
        FillVialCommand fillVial = new FillVialCommand(vial, "potion");

        // set up the equipment visitor
        List<Equipment> equipment = List.of(burner, cauldron, rod, vial);
        MagicalStabilityVisitor magicalStabilityVisitor = new MagicalStabilityVisitor();

        // set up students
        BasicStudent nevil = new BasicStudent("Neville", "Longbottom", "Gryffindor", 1, 64, 80, 95);
        BasicStudent seamus = new BasicStudent("Seamus", "Finnigan", "Gryffindor", 2, 45, 67, 90);
        BasicStudentCollection basicStudents = new BasicStudentCollection();
        basicStudents.addStudent(nevil);
        basicStudents.addStudent(seamus);

        AdvancedStudent hermione = new AdvancedStudent("Hermione", "Granger", "Gryffindor", 4, 95, 90);
        AdvancedStudent won = new AdvancedStudent("Won", "Reasely", "Slytherin", 4);
        AdvancedStudentCollection advancedStudents = new AdvancedStudentCollection();
        advancedStudents.addStudent(hermione);
        advancedStudents.addStudent(won);

        // set up the professor and TA


        // start the lesson

        // make the potions with builder

        // store potions

        System.out.println("Running magical stability diagnostics...\n");

        for (Equipment e : equipment) {
            e.accept(magicalStabilityVisitor);
        }

        // test prototype
        System.out.println("\n--- Gathering Ingredients ---\n");

        Aconite a1 = new Aconite("root");
        Aconite a2 = a1.geminio();
        System.out.println("new aconite?: " + (a1 != a2));

        Mandrake m1 = new Mandrake();
        Mandrake m2 = m1.geminio();
        System.out.println("new mandrake?: " + (m1 != m2));

        OccamyEgg o1 = new OccamyEgg();
        OccamyEgg o2 = o1.geminio();
        System.out.println("new egg?: " + (o1 != o2));

        // test decorator (combined with prototype)
        BasicIngredient i = new Powdered(new Moonstone());
        System.out.println(i);
        BasicIngredient i2 = i.geminio();
        System.out.println("duplicate powdered? " + (i != i2));

        i = new Diced(new KidneyBean("red"));
        System.out.println(i);
        i2 = i.geminio();
        System.out.println("duplicate diced? " + (i != i2));

        // test bridge
        LiquidIngredient base = new DragonBlood(new Extract(), new Purify());
        base.prepare();

        base = new HelleboreSyrup(new Extract(), new Purify());
        base.prepare();

        // test chain of responsibility
        System.out.println("\n--- Filing a Disciplinary Report ---\n");

        Faculty ghoh = new HeadOfHouse("Gryffindor");
        Faculty shoh = new HeadOfHouse("Slytherin");
        Faculty dep = new DeputyHeadmaster();
        Faculty hm = new Headmaster();

        DisciplinaryReport report = new DisciplinaryReport(won, ReportLevel.MINOR);
        ghoh.handleReport(report);
        shoh.handleReport(report);

        System.out.println("---------");

        report = new DisciplinaryReport(won, ReportLevel.MAJOR);
        ghoh.handleReport(report);

        System.out.println("---------");

        ghoh.setSuperior(dep);
        ghoh.handleReport(report);

        System.out.println("---------");

        report = new DisciplinaryReport(won, ReportLevel.SEVERE);
        ghoh.handleReport(report);

        System.out.println("---------");

        dep.setSuperior(hm);
        dep.handleReport(report);

        System.out.println("---------");

        report = new DisciplinaryReport(won, ReportLevel.ILLEGAL);
        ghoh.handleReport(report);

        System.out.println("---------");

        report = new DisciplinaryReport(won, ReportLevel.UNCATEGORIZED);
        ghoh.handleReport(report);

        // calculate grades
        GradeCalculator grades = new GradeCalculator();
        grades.calculateGrades(basicStudents);
        grades.calculateGrades(advancedStudents);
    }
}
