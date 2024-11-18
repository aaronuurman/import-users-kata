package kata;

import java.time.LocalDate;
import java.util.Objects;

class User {

    private String id;
    private String country;
    private String zip;
    private String name;
    private LocalDate birthday;
    private String birthday2;
    private String email;

    private User(Builder builder) {
        this.id = Objects.requireNonNull(builder.id);
        this.country = Objects.requireNonNull(builder.country);
        this.zip = Objects.requireNonNull(builder.zip);
        this.name = Objects.requireNonNull(builder.name);
        this.birthday = Objects.requireNonNull(builder.birthday);
        this.birthday2 = Objects.requireNonNull(builder.birthday2);
        this.email = Objects.requireNonNull(builder.email);
    }

    public String getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getZip() {
        return zip;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getBirthday2() {
        return birthday2;
    }

    public String getEmail() {
        return email;
    }


    public static class Builder {
        private String id;
        private String country;
        private String zip;
        private String name;
        private LocalDate birthday;
        private String birthday2;
        private String email;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder zip(String zip) {
            this.zip = zip;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder birthday2(String birthday) {
            this.birthday2 = birthday;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
