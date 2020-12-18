package page;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class createList {

	
public static final String URL = "http://localhost:9092/index.html";
	
@FindBy(className = "btn-success")
private WebElement startList;

    @FindBy(id = "name")
    private WebElement createListInput;
    @FindBy(id = "dueDate")
    private WebElement dueInput;

	@FindBy(className = "btn-success")
	private WebElement newORsubmit;
	@FindBy(className = "btn-danger")
	private WebElement deleteList;
	@FindBy(className = "btn-warning")
	private WebElement addTask;
	
	public void createList(String newList) {
		
		startList.click();
		createListInput.sendKeys(newList);
		newORsubmit.click();
		
	}
	
	
	public void deleteList() {
		
		deleteList.click();
		
		
	}
	
public void createTask(String name, String date) {
		
		addTask.click();
		
		newORsubmit.click();
		createListInput.sendKeys(name);
		dueInput.sendKeys(date);
		newORsubmit.click();
		
	}

public void editTask(String name) {
	
	addTask.click();
	
	
	createListInput.sendKeys(name);
	
	newORsubmit.click();
	
}
	
	
}