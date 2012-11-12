CONTEXT   = ../webapps/ROOT
CLSDIR    = $(CONTEXT)/WEB-INF/classes/crowdtrust
CLASSES   = crowdtrust/RegisterServlet.class crowdtrust/LoginServlet.class #crowdtrust/TaskServlet.class
CLASSPATH = ../webapps/ROOT/WEB-INF/lib/servlet-api-3.0.jar

all: $(CLASSES)

clean:
	rm -f $(CLASSES) */*.class

install: $(CLASSES)
	install -m600 $(CLASSES) $(CLSDIR)

#crowdtrust/TaskServlet.class: crowdtrust/TaskServlet.java
#	javac -classpath $(CLASSPATH) crowdtrust/TaskServlet.java

crowdtrust/RegisterServlet.class: crowdtrust/RegisterServlet.java
	javac -classpath $(CLASSPATH) crowdtrust/RegisterServlet.java

crowdtrust/LoginServlet.class: crowdtrust/LoginServlet.java
	javac -classpath $(CLASSPATH) crowdtrust/LoginServlet.java
