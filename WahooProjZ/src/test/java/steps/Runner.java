package steps;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "features", 
		glue = "steps", 
		plugin = { "pretty", "json:target/report/json/output.json",
				"html:target/report/html/index.html" },
		tags = ("@wahooFullTest"))

public class Runner {
	
}