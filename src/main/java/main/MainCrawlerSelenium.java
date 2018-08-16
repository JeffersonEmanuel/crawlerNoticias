package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MainCrawlerSelenium {

	public static void main(String[] args) {

		List<String> links = new ArrayList<String>();
		links.add("https://www.google.com/url?q=https://comentarios.estadao.com.br/embed/stream?asset_url%3Dhttps://politica.estadao.com.br/noticias/eleicoes,por-bolsonaro-psl-lanca-13-candidaturas-proprias-nas-eleicoes-2018,70002436844&sa=D&source=hangouts&ust=1534470413229000&usg=AFQjCNFbuvikuQHMr-RN6vsQif39LLZC1w");

		System.setProperty("webdriver.chrome.driver", "src\\DrivesSelenium\\chromedriver-windows.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		
		for (String link : links) {
			
			driver.get(link);
			
			List<WebElement> findElements = driver
					.findElements(By.cssSelector(".Comment__commentContainer___3vEGD.talk-stream-comment-container"));

			for (WebElement webElement : findElements) {
				
				String autor = webElement
						.findElement(By.cssSelector(".AuthorName__name___3O4jF"))
						.getText().toString();
				System.out.println(autor);
				
				String tempo = webElement
						.findElement(By.cssSelector(".CommentTimestamp__timestamp___2Ejbf.talk-comment-timestamp"))
						.getText().toString();
				System.out.println(tempo);
				
				List<WebElement> comentarios = webElement
						.findElements(By.cssSelector(".Linkify"));
				
				for (WebElement comentario : comentarios) {
					System.out.println(comentario.getText().toString());
				
				}
				
				String respeitar =  webElement
						.findElement(By.cssSelector(".talk-plugin-respect-count"))
						.getText().toString();
				System.out.println(respeitar);

			}	

		}
	}

}