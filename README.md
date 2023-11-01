JAVA TEST AUTOMATION PROJECT

The test automation project is created using  a Page Object Model design. It contains the following test automation test cases covering several functionalities of the given website:http://training.skillo-bg.com:4200/posts/all

CONCEPTS INCLUDED
Parallel test runs
Page Object pattern
Common web page interaction methods
Common api interaction methods
Mavenised performance tests
Externalised test configuration
Commonly used test utility classes

TOOLS
IntelliJ IDEA Community
Maven
TestNG
Selenium Webdriver
Java


COMPONENTS

PAGES
Inside the "main" folder, each web page is represented by a Java class.

Each class consists ofs: Fileds, which represnets the elements' locators.Onstructor and Methods : to interact with those elements.
All the pages need a driver to interact with the browser
All the pages inherit from a single AbstractPage - BasePage which holds the WebDriver and WebDriverWait objects encapsulated.

TESTS
BaseTest.java: a parent class for all the test classes. Contains all the @Before and @After configuration methods
Other classes for the test scenarios - 

Uncuccessful Registration
Unsuccessful login 
Logout from Profile Page 
Uploading Post
Follow User/ Unfollow User

SCREENSHOT:
Most of the time we think to Capture Screenshot in WebDriver when some kind of error or exception surfaces while practicing testing, to resolve the same the framework has a method.
getScreenshot() is used to indicates driver to capture a screenshot and store it in //screenshot/packageName directory.
