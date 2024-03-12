package stepDefinitions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



@RunWith(Cucumber.class)
public class stepDefinition {
	
	@Given("user is on landing page")
	public void user_is_on_landing_page() {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("Hello World!  EveryThing Works Fine");
	}
	@When("user login into application with username and password")
	public void user_login_into_application_with_username_and_password() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Hello World!  EveryThing Works Fine");
	}
	@Then("home page is populated")
	public void home_page_is_populated() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Hello World!  EveryThing Works Fine");
	}
	@Then("cards are displayed")
	public void cards_are_displayed() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Hello World!  EveryThing Works Fine");
	}

}
