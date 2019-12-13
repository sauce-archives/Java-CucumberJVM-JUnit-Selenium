## Java-CucumberJVM-Junit
[![Travis Status](https://travis-ci.org/saucelabs-sample-test-frameworks/Java-CucumberJVM-JUnit-Selenium.svg?branch=master)](https://travis-ci.org/saucelabs-sample-test-frameworks/Java-CucumberJVM-JUnit-Selenium)

This code is provided on an "AS-IS” basis without warranty of any kind, either express or implied, including without limitation any implied warranties of condition, uninterrupted use, merchantability, fitness for a particular purpose, or non-infringement. Your tests and testing environments may require you to modify this framework. Issues regarding this framework should be submitted through GitHub. For questions regarding Sauce Labs integration, please see the Sauce Labs documentation at https://wiki.saucelabs.com/. This framework is not maintained by Sauce Labs Support.

### Environment Setup

1. Global Dependencies
    * Install [Maven](https://maven.apache.org/install.html)
    * Or Install Maven with [Homebrew](http://brew.sh/) (Easier)
    ```
    $ brew install maven
    ```
2. Sauce Labs Credentials
    * In the terminal, export your Sauce Labs credentials as environmental variables:
    ```
    $ export SAUCE_USERNAME=<your Sauce Labs username>
    $ export SAUCE_ACCESS_KEY=<your Sauce Labs access key>
    ```
    For testing against Sauce Labs' EU datacenter, please add:
    ```
    $ export SAUCE_ENDPOINT=ondemand.eu-central-1.saucelabs.com
    $ export SAUCE_REST_ENDPOINT=https://eu-central-1.saucelabs.com/rest
    ```
   
3. Project Dependencies
    * Check that packages are available
    ```
    $ cd Java-CucumberJVM-JUnit
    $ mvn test-compile
    ```
    * You may also want to run the command below to check for outdated dependencies. Please be sure to verify and review updates before editing your pom.xml file as they may not be compatible with your code.
    ```
    $ mvn versions:display-dependency-updates
    ```
    
### Running the tests
This set of tests makes use of the [parallel feature](https://cucumber.io/blog/announcing-cucumber-jvm-4-0-0/) in Cucumber 4.0.0. Using Maven with
the `sure-fire` plugin allows tests to be run in parallel by feature file. Each scenario in each feature file will be executed on a Sauce session. 
```
$ mvn clean install test
```

[Sauce Labs Dashboard](https://saucelabs.com/beta/dashboard/)

### Advice/Troubleshooting
1. It may be useful to use a Java IDE such as IntelliJ or Eclipse to help troubleshoot potential issues. 
2. There may be additional latency when using a remote webdriver to run tests on Sauce Labs. Timeouts and/or waits may need to be increased.
    * [Selenium tips regarding explicit waits](https://wiki.saucelabs.com/display/DOCS/Best+Practice%3A+Use+Explicit+Waits)

### Resources
##### [Sauce Labs Documentation](https://wiki.saucelabs.com/)

##### [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)

##### [Junit Documentation](http://junit.org/javadoc/latest/index.html)

##### [Java Documentation](https://docs.oracle.com/javase/7/docs/api/)

##### [Stack Overflow](http://stackoverflow.com/)
* A great resource to search for issues not explicitly covered by documentation.

