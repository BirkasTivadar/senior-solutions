package singleton.trainer;

public class TrainerService {

    private static final TrainerService INSTANCE = new TrainerService();

    private TrainerService() {
    }

    public static TrainerService getInstance() {
        return INSTANCE;
    }

    public void createTrainer(String name) {
        if (isBlank(name)) {
            throw new IllegalArgumentException("Empty name");
        }
        TrainerRepository.getInstance().save(new Trainer(name));
    }

    private boolean isBlank(String name) {
        return name == null || name.isBlank();
    }

    public Trainer findByName(String name) {
        if (isBlank(name)) {
            throw new IllegalArgumentException("Empty name");
        }
        return TrainerRepository.getInstance().findByName(name);
    }
}
