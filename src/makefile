CONTEXT   = ../webapps/ROOT
CLSDIR    = $(CONTEXT)/WEB-INF/classes
CLASSES   = crowdtrust/TaskServlet.class crowdtrust/CrowdRegisterServlet.class crowdtrust/CrowdLoginServlet.class
CLASSPATH = ../webapps/ROOT/WEB-INF/lib/servlet-api-3.0.jar

all: $(CLASSES)

clean:
	rm -f $(CLASSES) */*.class

install: $(CLASSES)
	install -m600 $(CLASSES) $(CLSDIR)

crowdtrust/TaskServlet.class: crowdtrust/TaskServlet.java
	javac -classpath $(CLASSPATH) crowdtrust/TaskServlet.java

crowdtrust/CrowdRegisterServlet.class: crowdtrust/CrowdRegisterServlet.java
	javac -classpath $(CLASSPATH) crowdtrust/CrowdRegisterServlet.java

crowdtrust/CrowdLoginServlet.class: crowdtrust/CrowdLoginServlet.java
	javac -classpath $(CLASSPATH) crowdtrust/CrowdLoginServlet.java
