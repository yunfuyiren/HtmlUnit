package com.yunfuyiren.htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitTest1 {
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException  
	{
		//the parameter pionted as the specific browser like chrome.
		final WebClient webClient=new WebClient(BrowserVersion.CHROME);  
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		//get the content of the whole page  
		final HtmlPage page=webClient.getPage("http://www.yanyulin.info");
//		System.out.println(page.asText());
		webClient.closeAllWindows();
		
		//get the specific html elem of the HtmlPage by 'get;
//		HtmlDivision div=(HtmlDivision)page.getElementById("topnav");
//		System.out.println(div.asText());
		
		//get the specific html elem of the page by 'XPath' which is 
		//used to search with complex conditions
		final HtmlDivision div=(HtmlDivision)page.getByXPath("//div").get(2);
		System.out.println(div.asXml());
		
		//get the link from the page
		java.util.List<HtmlAnchor>
			achList=page.getAnchors();
		for(HtmlAnchor ach:achList)
		{
			System.out.println(ach.getHrefAttribute());
		}
	}
}
