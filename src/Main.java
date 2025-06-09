import java.util.ArrayList;
import java.util.List;

import commands.*;
import equipment.*;
import equipment.diagnostics.MagicalStabilityVisitor;
import ingredients.*;
import potions.base.*;
import potions.memento.*;
import potions.observer.*;
import potions.state.*;
import discipline.*;
import teaching.*;
import teaching.grades.*;
import teaching.textbook.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        // -------- SETUP --------

        // other
        Scanner scanner = new Scanner(System.in);

        // bookstore
        TextbookFactory flourishAndBlotts = new TextbookFactory();

        // grading portal
        RealGradingDatabase db = RealGradingDatabase.getInstance();
        GradingDatabase studentPortal = new StudentPortal(db);

        // other faculty
        Faculty gryffHOH = new HeadOfHouse("Gryffindor");
        Faculty slythHOH = new HeadOfHouse("Slytherin");
        Faculty huffHOH = new HeadOfHouse("Hufflepuff");
        Faculty ravHOH = new HeadOfHouse("Ravenclaw");
        Faculty deputyHeadmaster = new DeputyHeadmaster();
        Faculty headmaster = new Headmaster();
        gryffHOH.setSuperior(deputyHeadmaster);
        slythHOH.setSuperior(deputyHeadmaster);
        huffHOH.setSuperior(deputyHeadmaster);
        ravHOH.setSuperior(deputyHeadmaster);
        deputyHeadmaster.setSuperior(headmaster);

        // lab equipment
        Burner burner = new Burner();
        Cauldron cauldron = new Cauldron();
        StirringRod rod = new StirringRod();
        Vial vial = new Vial();
        List<Equipment> equipment = List.of(burner, cauldron, rod, vial);
        MagicalStabilityVisitor magicalStabilityVisitor = new MagicalStabilityVisitor();

        // professor & class
        Professor professor = new Professor("Snape", burner, cauldron, rod, vial);

        BasicStudent nevil = new BasicStudent("Neville", "Longbottom", "Gryffindor", 3, 64, 70, 75);
        BasicStudent seamus = new BasicStudent("Hannah", "Abbott", "Hufflepuff", 3, 45, 97, 98);
        AdvancedStudent hermione = new AdvancedStudent("Hermione", "Granger", "Gryffindor", 3, 95, 90);
        AdvancedStudent won = new AdvancedStudent("Draco", "Malfoy", "Slytherin", 3, 100, 87);

        FullStudentCollection roster = professor.createRoster(List.of(nevil, seamus, hermione, won));
        System.out.println(" ----------------\n| Current Roster |\n ----------------\n\n" + roster.toString());

        // --- JOIN THE CLASS ---
        System.out.print("[Sign up for Professor Snape's After-Class Lessons!]\nEnter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter your house: ");
        String house = scanner.nextLine();
        System.out.print("Enter your year: ");
        int year = scanner.nextInt();

        BasicStudent you = new BasicStudent(firstName, lastName, house, year);
        professor.addStudent(you, roster);

        // useage of: Singleton, Flyweight
        Wand wand = Wand.getInstance();
        Textbook book = flourishAndBlotts.getBook("potion", "Basic Potion-Making", "L. Borage");
        book.assign(firstName);

        // --- STARTING CLASS ---
        System.out.println("\n[All set! Let's begin!]\n");

        // usage of: Facade, Composite, Template, Null
        professor.conductLesson(LessonType.HEALING);

        // --- MAKE A POTION ---
        System.out.println("\n[Your turn now! Let's make this potion.]\n");

        // usage of: Command
        wand.setCommand(new BurnerOffCommand(burner, cauldron));
        wand.cast();
        System.out.print("\n");

        // usage of: Visitor
        System.out.println("[Running magical stability diagnostics...]");
        for (Equipment e : equipment) {
            e.accept(magicalStabilityVisitor);
        }
        System.out.print("\n[Equipment is good to use!]\n\n");

        // usage of: Decorator, Builder
        BasicIngredient ingredient = new Diced(new Mandrake());
        wand.setCommand(new AddIngredientCommand(cauldron, ingredient.toString()));
        wand.cast();
        System.out.print("\n");

        Potion potion = new PotionBuilder()
                .setName("Healing Potion Attempt")
                .setColor("light green")
                .setTemperature(Burner.HeatLevel.LOW)
                .addIngredient(ingredient)
                .setBrewingTime(30)
                .build();

        // usage of: Observer, Memento
        System.out.print("\n[Set up some failsafes!\n");
        BrewingLogObserver brewingLog = new BrewingLogObserver(potion);
        SafetyMonitorObserver safetyMonitor = new SafetyMonitorObserver(potion);
        PotionCaretaker potionLog = new PotionCaretaker();
        potionLog.saveState(potion.save());

        // usage of: Prototype, Bridge
        ingredient = ingredient.geminio();
        wand.setCommand(new AddIngredientCommand(cauldron, ingredient.toString()));
        wand.cast();
        potion.addIngredient(ingredient);
        System.out.print("\n");

        LiquidIngredient liquid = new DragonBlood(new Extract(), new Purify());
        liquid.prepare();
        System.out.print("\n");
        wand.setCommand(new AddIngredientCommand(cauldron, liquid.toString()));
        wand.cast();
        Thread.sleep(1000);
        potion.addIngredient(liquid);
        System.out.print("\n");
        potion.setColor("clear");
        System.out.print("\n");
        potionLog.saveState(potion.save());
        System.out.print("\n");

        ingredient = new Powdered(new Moonstone());
        wand.setCommand(new AddIngredientCommand(cauldron, ingredient.toString()));
        wand.cast();
        potion.addIngredient(ingredient);
        System.out.print("\n");

        liquid = new HelleboreSyrup(new Extract(), new Purify());
        liquid.prepare();
        System.out.print("\n");
        wand.setCommand(new AddIngredientCommand(cauldron, liquid.toString()));
        wand.cast();
        Thread.sleep(1000);
        potion.addIngredient(liquid);
        System.out.print("\n");

        potion.setColor("orange");
        System.out.print("\n");
        potionLog.saveState(potion.save());
        System.out.print("\n");

        // --- MAKING A MISTAKE ---
        wand.setCommand(new SetBurnerHeatCommand(burner, cauldron, Burner.HeatLevel.HIGH));
        wand.cast();
        System.out.print("\n");
        potion.setTemperature(Burner.HeatLevel.HIGH);
        System.out.print("\n");

        // usage of: Memento
        System.out.println("\n[That's too hot! Luckily you preserved the state beforehand.]\n");
        potion.restore(potionLog.goBack());
        System.out.print("\n");

        // usage of: Chain of Responsibility
        System.out.println("\n[Looks like Draco reported you for causing a ruckus...]");
        DisciplinaryReport report = new DisciplinaryReport(you, ReportLevel.MINOR);
        slythHOH.handleReport(report);
        System.out.print("\n");

        // --- CONTINUE POTION MAKING ---
        System.out.println("[Oh well. Let's continue.]\n");
        wand.setCommand(new SetBurnerHeatCommand(burner, cauldron, Burner.HeatLevel.MEDIUM));
        wand.cast();
        System.out.print("\n");
        potion.setTemperature(Burner.HeatLevel.MEDIUM);
        potion.setState(new BrewingState());
        System.out.print("\n");

        wand.setCommand(new StirCommand(rod, StirringRod.Direction.CLOCKWISE, 5));
        wand.cast();
        potion.stir(5);
        wand.setCommand(new StirCommand(rod, StirringRod.Direction.COUNTERCLOCKWISE, 5));
        wand.cast();
        potion.stir(5);
        wand.setCommand(new StirCommand(rod, StirringRod.Direction.CLOCKWISE, 5));
        wand.cast();
        potion.stir(5);
        wand.setCommand(new StirCommand(rod, StirringRod.Direction.COUNTERCLOCKWISE, 5));
        wand.cast();
        potion.stir(5);
        wand.setCommand(new StirCommand(rod, StirringRod.Direction.CLOCKWISE, 5));
        wand.cast();
        potion.stir(5);
        System.out.print("\n");

        System.out.println("[Running magical stability diagnostics...]");
        for (Equipment e : equipment) {
            e.accept(magicalStabilityVisitor);
        }

        // --- WRAP UP ---
        System.out.println("\n[All done! Wrap up and turn it in!]\n");
        wand.setCommand(new BurnerOffCommand(burner, cauldron));
        wand.cast();
        System.out.print("\n");
        wand.setCommand(new FillVialCommand(vial, potion.getName()));
        wand.cast();

        // --- FINAL GRADE ---
        System.out.println("\n[Snape will add your grade to the roster...]\n");
        you.setParticipationScore(100);
        you.setStabilityScore(43);
        you.setPotionComplexity(89);

        // usage of: Iterator, Proxy
        GradeCalculator grades = new GradeCalculator();
        grades.calculateGrades(roster);

        professor.assignGrade("28741", firstName, "Potions", 83.00);

    }
}
