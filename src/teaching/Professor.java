package teaching;

public class Professor {
    private String name;
    private TeachingAssistant ta;

    public Professor(String name) {
        this.name = name;
    }

    // options to create a professor with or without a TA
    public Professor(String name, TeachingAssistant ta) {
        this.name = name;
        this.ta = ta;
    }

    public void assignTA(TeachingAssistant ta) {
        this.ta = ta;
    }

    public void conductLesson(LessonType lessonType) {
        if (ta == null) {
            System.out.println("Error starting lesson -- TA has not been assigned.");
            return;
        }
        System.out.println("Professor " + name + " is starting the lesson.\n");
        ta.prepareLesson(lessonType);
        System.out.println("Lesson ready. Professor " + name + " begins teaching.");
    }
}
