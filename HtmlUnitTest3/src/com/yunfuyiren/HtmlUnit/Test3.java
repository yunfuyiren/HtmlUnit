package com.yunfuyiren.HtmlUnit;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Test3 {
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException
	{
		getHomeTitle();
	}
	public static void getHomeTitle() throws FailingHttpStatusCodeException, MalformedURLException, IOException
	{
		final WebClient webClient=new WebClient();
		final HtmlPage htmlPage=webClient.getPage("http://www.iteye.com/");
		System.out.println(htmlPage.getTitleText());
		System.out.println(htmlPage.getTextContent());
		
	}
	
}
