package by.fpmibsu.EasyLearning.Bean;

public abstract class Bean {
    public Bean() {
        id = 0L;
    }

    public Bean(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        this.id = id;
    }

    protected Long id;
}
