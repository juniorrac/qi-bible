package notorious.qibible.models;

public class Question {
    public enum Difficulty {
        FACILE(5, "#4CAF50"),      // Vert - 5 points
        MOYEN(10, "#FF9800"),      // Orange - 10 points  
        DIFFICILE(15, "#F44336"),  // Rouge - 15 points
        EXPERT(20, "#9C27B0");     // Violet - 20 points

        private final int points;
        private final String color;

        Difficulty(int points, String color) {
            this.points = points;
            this.color = color;
        }

        public int getPoints() { return points; }
        public String getColor() { return color; }
        public String getDisplayName() {
            switch (this) {
                case FACILE: return "⭐ Facile";
                case MOYEN: return "⭐⭐ Moyen";
                case DIFFICILE: return "⭐⭐⭐ Difficile";
                case EXPERT: return "⭐⭐⭐⭐ Expert";
                default: return "";
            }
        }
    }

    private String question;
    private String correctAnswer;
    private String[] options;
    private Difficulty difficulty;

    public Question(String question, String correctAnswer, String[] options, Difficulty difficulty) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.options = options;
        this.difficulty = difficulty;
    }

    // Getters
    public String getQuestion() { return question; }
    public String getCorrectAnswer() { return correctAnswer; }
    public String[] getOptions() { return options; }
    public Difficulty getDifficulty() { return difficulty; }
    
    // Setters
    public void setQuestion(String question) { this.question = question; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }
    public void setOptions(String[] options) { this.options = options; }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }
}