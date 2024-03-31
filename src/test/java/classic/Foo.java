package classic;

import java.util.Objects;

public class Foo {
    private final String name;
    private final Integer age;

    public Foo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foo foo = (Foo) o;
        return Objects.equals(name, foo.name) && Objects.equals(age, foo.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
