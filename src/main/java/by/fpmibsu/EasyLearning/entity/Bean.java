package by.fpmibsu.EasyLearning.entity;

public abstract class Bean{
    public Bean() {
        id = Long.valueOf(0);
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
