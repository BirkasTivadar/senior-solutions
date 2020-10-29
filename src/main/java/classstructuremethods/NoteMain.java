package classstructuremethods;

public class NoteMain {

    public static void main(String[] args) {

        Note note = new Note();

        note.setName("Kátya");
        note.setTopic("java");
        note.setText("backend");

        System.out.println(note.getNoteText());
    }
}
