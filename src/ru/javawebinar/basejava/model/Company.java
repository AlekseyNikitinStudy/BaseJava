package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link homePage;

    private List<Period> periods;

    public Company(String name, String url, List<Period> periods) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(periods, "periods must not be null");
        homePage = new Link(name, url);
        this.periods = periods;
    }

    public Company(Link homePage, List<Period> periods) {
        Objects.requireNonNull(homePage, "homepage must not be null");
        Objects.requireNonNull(periods, "periods must not be null");
        this.homePage = homePage;
        this.periods = periods;
    }

    public Company() {
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return Objects.equals(homePage, company.homePage) &&
                Objects.equals(getPeriods(), company.getPeriods());
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, getPeriods());
    }

    @Override
    public String toString() {
        return "Company{" +
                "homePage=" + homePage +
                ", periods=" + periods +
                '}';
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate start;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate end;
        private String name;
        private String description;

        public Period(LocalDate start, LocalDate end, String name, String description) {
            Objects.requireNonNull(start, "start date must not be null");
            Objects.requireNonNull(end, "end date must not be null");
            Objects.requireNonNull(name, "name must not be null");
            this.start = start;
            this.end = end;
            this.name = name;
            this.description = description == null ? "" : description;
        }

        public Period() {
        }

        public LocalDate getStart() {
            return start;
        }

        public void setStart(LocalDate start) {
            this.start = start;
        }

        public LocalDate getEnd() {
            return end;
        }

        public void setEnd(LocalDate end) {
            this.end = end;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "\nExperiencePeriod{" +
                    "start=" + start +
                    ", end=" + end +
                    ", position='" + name + '\'' +
                    ", experience='" + description + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Period)) return false;
            Period period = (Period) o;
            return Objects.equals(start, period.start)
                    && Objects.equals(end, period.end)
                    && Objects.equals(name, period.name)
                    && Objects.equals(description, period.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end, name, description);
        }
    }
}

