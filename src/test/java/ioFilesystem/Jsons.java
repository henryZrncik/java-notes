package ioFilesystem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Jsons {
    public Jsons() throws Exception { }

    ObjectMapper objectMapper = new ObjectMapper();
    @Test void getUnknownJsonObjectOrList() throws IOException {
        BufferedReader bf1 = new BufferedReader(new FileReader("src/test/java/io/dataJsonObject.json"));
        JsonNode nodeHoldingSingleObject = objectMapper.readTree(bf1);
    }

    BufferedReader bf1 = new BufferedReader(new FileReader("src/test/java/io/dataJsonObject.json"));
    JsonNode nodeHoldingSingleObject = objectMapper.readTree(bf1);
    @Test void parseUnknownJson_GetChildNode(){  nodeHoldingSingleObject.get("badElem"); }// Node
    @Test void parseUnknownJson_IsArray(){  nodeHoldingSingleObject.isArray(); }// Bool
    @Test void parseUnknownJson_IsEmpty(){  nodeHoldingSingleObject.isEmpty(); }// Bool
    @Test void parseUnknownJson_IsObject(){  nodeHoldingSingleObject.isObject(); }// Bool
    @Test void parseUnknownJson_GetValue(){  nodeHoldingSingleObject.asText();}// String

    @Test void getKnownJson() throws IOException {
        BufferedReader bufferWith1JsonObj = new BufferedReader( new FileReader("src/test/java/io/dataJsonObject.json"));
        BufferedReader bufferWithMultipleJsonObj = new BufferedReader( new FileReader("src/test/java/io/dataJsonList.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("");
        Employee e1 = objectMapper.readValue( bufferWith1JsonObj, Employee.class);
        Employee[] personArray =  objectMapper.readValue(bufferWithMultipleJsonObj, Employee[].class);
    }

    @Test void convertObjectToJson() throws JsonProcessingException {
        Employee a = new Employee("ube", 7);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(a);
    }
}

