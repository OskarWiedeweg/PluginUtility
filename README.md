
# PluginUtility

A lightweight framework, that helps you to ship your plugins faster. No "Ups, I forgot to register my listeners" again.


## Features

- [x] Automatic guice setup, with build in `component scan`.
- [ ] Automatic listener registration.
## Installation

Install PluginUtility with maven:

```xml
<dependency>
    <groupId>io.devoskar</groupId>
    <artifactId>plugin-utils</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
    
## Usage    

```java
public class TestPlugin extends PluginUtility {

}
```


## Authors

- [@OskarWiedeweg](https://www.github.com/OskarWiedeweg)

