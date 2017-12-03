# Mapping XmlConfiguration properties to Pojo using Spring's DataBinders

````
name = "spark_jobs.xml"
source = {LinkedHashMap@1644}  size = 7
 0 = {LinkedHashMap$Entry@1968} "sparkAppsToMonitor.sparkAppLaunchConfiguration[0].resource[0]" -> "app1.jar"
 1 = {LinkedHashMap$Entry@1969} "sparkAppsToMonitor.sparkAppLaunchConfiguration[0].resource[1]" -> "app11.jar"
 2 = {LinkedHashMap$Entry@1970} "sparkAppsToMonitor.sparkAppLaunchConfiguration[0].name" -> "app1"
 3 = {LinkedHashMap$Entry@1971} "sparkAppsToMonitor.sparkAppLaunchConfiguration[1].resource[0]" -> "app2.jar"
 4 = {LinkedHashMap$Entry@1972} "sparkAppsToMonitor.sparkAppLaunchConfiguration[1].resource[1]" -> "app22.jar"
 5 = {LinkedHashMap$Entry@1973} "sparkAppsToMonitor.sparkAppLaunchConfiguration[1].name" -> "app2"
 6 = {LinkedHashMap$Entry@1974} "sparkAppsToMonitor.version" -> "10.2"
 
 name = "applicationConfig: [classpath:/application.yaml]"
 source = {LinkedHashMap@2007}  size = 7
  0 = {LinkedHashMap$Entry@2010} "sparkAppsToMonitor.version" -> "10.2"
  1 = {LinkedHashMap$Entry@2011} "sparkAppsToMonitor.apps[0].name" -> "app1"
  2 = {LinkedHashMap$Entry@2012} "sparkAppsToMonitor.apps[0].resources[0].resource" -> "app1.jar"
  3 = {LinkedHashMap$Entry@2013} "sparkAppsToMonitor.apps[0].resources[1].resource" -> "app11.jar"
  4 = {LinkedHashMap$Entry@2014} "sparkAppsToMonitor.apps[1].name" -> "app2"
  5 = {LinkedHashMap$Entry@2015} "sparkAppsToMonitor.apps[1].resources[0].resource" -> "app2.jar"
  6 = {LinkedHashMap$Entry@2016} "sparkAppsToMonitor.apps[1].resources[1].resource" -> "app22.jar"
  ````
  ### To Generate Metadata from the configuration using spring-boot-configuration process
  Use [Kapt](https://kotlinlang.org/docs/reference/kapt.html)
  ````
                <executions>
                    <!--https://kotlinlang.org/docs/reference/kapt.html-->
                    <execution>
                        <id>kapt</id>
                        <goals>
                            <goal>kapt</goal>
                        </goals>
                        <configuration>
                            <sourceDirs>
                                <sourceDir>src/main/kotlin</sourceDir>
                                <sourceDir>src/main/kotlin</sourceDir>
                            </sourceDirs>
                            <annotationProcessorPaths>
                                <!-- Specify your annotation processors here. -->
                                <annotationProcessorPath>
                                    <groupId>org.springframework.boot</groupId>
                                    <artifactId>spring-boot-configuration-processor</artifactId>
                                    <version>${spring-boot.version}</version>
                                </annotationProcessorPath>
                            </annotationProcessorPaths>
                        </configuration>
                    </execution>
                    <execution>
                        <id>compile</id>
                        <phase>compile</phase>
                        <goals>
                            <goal>compile</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>test-compile</id>
                        <phase>test-compile</phase>
                        <goals>
                            <goal>test-compile</goal>
                        </goals>
                    </execution>
                </executions>

  ````
  
  
 ## Issues
 1. Lombok and Kotlin
 [Answer](https://stackoverflow.com/questions/25607272/compiling-a-java-class-in-memory-with-lombok-annotations-and-java-jdk-8)
 `Lombok` annotations are not supported
 
  ````
  [WARNING] Unable to create directory /home/srini/Development/Projects/kotlin_lessons/target/kaptStubs/compile
  [WARNING] Duplicate source root: /home/srini/Development/Projects/kotlin_lessons/src/main/kotlin
  [WARNING] /home/srini/Development/Projects/kotlin_lessons/target/kaptStubs/compile/error/NonExistentClass.java:3: warning: Can't initialize javac processor due to (most likely) a class loader problem: java.lang.NoClassDefFoundError: com/sun/tools/javac/processing/JavacProcessingEnvironment
  [WARNING] 
  
  [WARNING] public final class NonExistentClass {
  [WARNING]              ^
  [WARNING]   	at lombok.javac.apt.LombokProcessor.init(LombokProcessor.java:83)
  [WARNING]   	at lombok.core.AnnotationProcessor$JavacDescriptor.want(AnnotationProcessor.java:87)
  [WARNING]   	at lombok.core.AnnotationProcessor.init(AnnotationProcessor.java:140)
  [WARNING]   	at lombok.launch.AnnotationProcessorHider$AnnotationProcessor.init(AnnotationProcessor.java:69)
  [WARNING]   	at com.sun.tools.javac.processing.JavacProcessingEnvironment$ProcessorState.<init>(JavacProcessingEnvironment.java:500)
  [WARNING]   	at com.sun.tools.javac.processing.JavacProcessingEnvironment$DiscoveredProcessors$ProcessorStateIterator.next(JavacProcessingEnvironment.java:597)
  [WARNING]   	at com.sun.tools.javac.processing.JavacProcessingEnvironment.discoverAndRunProcs(JavacProcessingEnvironment.java:690)
  [WARNING]   	at com.sun.tools.javac.processing.JavacProcessingEnvironment.access$1800(JavacProcessingEnvironment.java:91)
  [WARNING]   	at com.sun.tools.javac.processing.JavacProcessingEnvironment$Round.run(JavacProcessingEnvironment.java:1035)
  [WARNING]   	at com.sun.tools.javac.processing.JavacProcessingEnvironment.doProcessing(JavacProcessingEnvironment.java:1176)
  ````
  ### Banner
  [Used this site](http://patorjk.com/software/taag/#p=testall&h=0&v=1&f=Crawford2&t=Spark-App-Monitor)