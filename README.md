![selenium camp logo](http://seleniumcamp.com/wp-content/themes/selenium/images/logo-color.svg "Selenium Camp 2017")
# Selenium Camp 2017 - How does Java 8 exert hidden power on Test Automation? 

This project provides [talk's](http://seleniumcamp.com/talk/how-java-8-can-simplify-test-automation) demos' implementation.

Video recoding will be available in a month. 

WARNING: it's not a QAA framework! It's just a set of useful code snippets, which were created in a **Java 7 vs Java 8** comparison format.

Here's a list of samples you may follow by watching talk's video recording:

####Pattern Matching
 - `core.driver.Java7WebDriverFactory`: switch vs if / else
 - `core.driver.Java8WebDriverFactory`: [javaslang](http://www.javaslang.io) matchers
 
####Lambdas / Functional Interfaces
 - `core.wrappers.Java7BasePage`: explicit waits using Java 7
 - `core.wrappers.Java8BasePage1stAttempt`: explicit waits using Java 8 (direct approach)
 - `core.wrappers.Java8BasePage2stAttempt`: explicit waits using Java 8 (increasing readability via `core.wrappers.WaitCondition` - parametrized enum with [lombok](https://projectlombok.org))
 
####Streams
 - `resources/app/catalog.html`: target AUT (you should put it into some http server's space before tests execution)
 - `model.Product`: common entity for storing product's info retrieved from AUT ([lombok](https://projectlombok.org) and [javamoney](https://javamoney.github.io))
 - `pages.Java7ProductsPage`: Java 7 sample for getting a product with max discount according to retrieved AUT data
 - `pages.Java8ProductsPage`: Java 8 sample for getting a product with max / min discount (or any other field) according to AUT data ([streamex](https://github.com/amaembo/streamex) and [lombok](https://projectlombok.org))
 - `core.wrappers.BasePage`: abstract page for Java 8 sample ([javaslang](http://www.javaslang.io), [jsoup](https://jsoup.org), [javamoney](https://javamoney.github.io) and [streamex](https://github.com/amaembo/streamex))
 - `testcases.SC17Java8WithoutDPTests`: test case without `DataProvider` usage for Java 8 sample ([javamoney](https://javamoney.github.io) and [lombok](https://projectlombok.org))
 - `testcases.SC17Java8WithDPTests`: test case with `DataProvider` and `Tuples` usage for Java 8 sample ([javamoney](https://javamoney.github.io), [lombok](https://projectlombok.org) and [javaslang](http://www.javaslang.io))
 
####Additional samples (out of talk's scope)
 - `core.misc.PageFactory`: PageObjects factory for custom elements' types ([lombok](https://projectlombok.org), [streamex](https://github.com/amaembo/streamex) and [joor](https://github.com/jOOQ/jOOR))
 - `utils.FileUtils`: custom waits ([awaitility](https://github.com/awaitility/awaitility))
