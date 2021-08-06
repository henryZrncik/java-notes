package ioFilesystem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
class Employee implements Serializable {

    @JsonProperty("person_name")
    String name;
    int salary;

    public Employee() {     // always have default constructor/
    }

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
}
